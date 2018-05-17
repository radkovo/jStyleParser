package cz.vutbr.web.csskit.fn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermFactory;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermIdent;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.css.TermLengthOrPercent;
import cz.vutbr.web.css.TermList;
import cz.vutbr.web.css.TermPercent;
import cz.vutbr.web.csskit.TermFunctionImpl;

public class RadialGradientImpl extends TermFunctionImpl implements TermFunction.RadialGradient {
    
    //default property values
    private static final TermPercent DEFAULT_POSITION = CSSFactory.getTermFactory().createPercent(50.0f);
    private static final TermIdent DEFAULT_SHAPE = CSSFactory.getTermFactory().createIdent("ellipse");
    private static final TermIdent CIRCLE_SHAPE = CSSFactory.getTermFactory().createIdent("circle");
    private static final TermIdent DEFAULT_SIZE = CSSFactory.getTermFactory().createIdent("farthest-corner");
    
    private TermIdent shape;
    private TermLengthOrPercent[] size;
    private TermIdent sizeIdent;
    private TermLengthOrPercent[] position;
    private List<TermFunction.Gradient.ColorStop> colorStops;
    
    public RadialGradientImpl() {
        setValid(false);
    }
    
    @Override
    public TermIdent getShape() {
        return shape;
    }
    
    @Override
    public TermLengthOrPercent[] getSize() {
        return size;
    }

    @Override
    public TermIdent getSizeIdent() {
        return sizeIdent;
    }

    @Override
    public TermLengthOrPercent[] getPosition() {
        return position;
    }

    @Override
    public List<ColorStop> getColorStops() {
        return colorStops;
    }
    
    @Override
    public TermList setValue(List<Term<?>> value) {
        super.setValue(value);
        List<List<Term<?>>> args = getSeparatedArgs(DEFAULT_ARG_SEP);
        if (args.size() > 1) {
            int firstStop = 0;
            //check for shape and size
            if (decodeShape(args.get(0))) {
                firstStop = 1;
            } else { //no shape info provided, use defaults
                sizeIdent = DEFAULT_SIZE;
                shape = DEFAULT_SHAPE;
                position = new TermLengthOrPercent[2];
                position[0] = position[1] = DEFAULT_POSITION;
            }
            //check for stops
            colorStops = loadColorStops(args, firstStop);
            if (colorStops != null)
                setValid(true);
        }
        return this;
    }

    private boolean decodeShape(List<Term<?>> arglist) {
        List<Term<?>> args = new ArrayList<>(arglist);
        //determine the 'at' position
        int atpos = -1;
        for (int i = 0; i < args.size(); i++) {
            Term<?> arg = args.get(i);
            if (arg instanceof TermIdent && ((TermIdent) arg).getValue().equalsIgnoreCase("at")) {
                atpos = i;
                break;
            }
        }
        if (atpos != -1) {
            List<Term<?>> posList = args.subList(atpos + 1, args.size());
            if (!decodePosition(posList))
                return false;
            args = args.subList(0, atpos);
        } else { //no position, use the default (center)
            position = new TermLengthOrPercent[2];
            position[0] = position[1] = DEFAULT_POSITION;
        }
        //determine the shape
        boolean isCircle = false;
        for (Iterator<Term<?>> it = args.iterator(); it.hasNext();)
        {
            Term<?> arg = it.next();
            String idval = (arg instanceof TermIdent) ? ((TermIdent) arg).getValue() : null;
            if (idval != null && (idval.equalsIgnoreCase("circle") || idval.equalsIgnoreCase("ellipse"))) {
                shape = (TermIdent) arg;
                isCircle = idval.equalsIgnoreCase("circle");
                it.remove();
                break;
            }
        }
        //determine the size
        if (shape == null) { //shape not given
            if (args.size() == 0) {
                sizeIdent = DEFAULT_SIZE;
                shape = DEFAULT_SHAPE;
            } else if (args.size() == 1) {
                Term<?> arg = args.get(0);
                if (isExtentKeyword(arg)) {
                    sizeIdent = (TermIdent) arg;
                    shape = DEFAULT_SHAPE; //see https://drafts.csswg.org/css-images-3/#radial-gradients
                } else if (arg instanceof TermLength) {
                    size = new TermLengthOrPercent[1];
                    size[0] = (TermLength) arg;
                    shape = CIRCLE_SHAPE;
                } else {
                    return false;
                }
            } else if (args.size() == 2) {
                size = new TermLengthOrPercent[2];
                int i = 0;
                for (Term<?> arg : args) {
                    if (arg instanceof TermLengthOrPercent) {
                        size[i++] = (TermLengthOrPercent) arg;
                    } else {
                        size = null;
                        return false;
                    }
                }
                shape = DEFAULT_SHAPE;
            } else {
                return false;
            }
        } else if (!isCircle) { //ellipse
            if (args.size() == 0) {
                sizeIdent = DEFAULT_SIZE;
            } else if (args.size() == 1) {
                Term<?> arg = args.get(0);
                if (isExtentKeyword(arg)) {
                    sizeIdent = (TermIdent) arg;
                } else {
                    return false;
                }
            } else if (args.size() == 2) {
                size = new TermLengthOrPercent[2];
                int i = 0;
                for (Term<?> arg : args) {
                    if (arg instanceof TermLengthOrPercent) {
                        size[i++] = (TermLengthOrPercent) arg;
                    } else {
                        size = null;
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else { //circle
            if (args.size() == 0) {
                sizeIdent = DEFAULT_SIZE;
            } else if (args.size() == 1) {
                Term<?> arg = args.get(0);
                if (isExtentKeyword(arg)) {
                    sizeIdent = (TermIdent) arg;
                } else if (arg instanceof TermLength) {
                    size = new TermLengthOrPercent[1];
                    size[0] = (TermLength) arg;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        //parsed ok
        return true;
    }
    
    private boolean decodePosition(List<Term<?>> arglist) {
        if (arglist.size() == 1 || arglist.size() == 2) {
            
            position = new TermLengthOrPercent[2];
            //distribute terms to position
            for (Term<?> term : arglist) {
                if (term instanceof TermIdent || term instanceof TermLengthOrPercent) {
                    storeBackgroundPosition(position, term);
                } else {
                    return false;
                }
            }
            //check validity
            int assigned = 0;
            int valid = 0;
            for (int i = 0; i < 2; i++) {
                if (position[i] == null) {
                    position[i] = DEFAULT_POSITION;
                    valid++;
                } else if (position[i] instanceof TermLengthOrPercent) {
                    assigned++;
                    valid++;
                }
            }
            return (assigned > 0 && valid == 2);
            
        } else {
            return false;
        }
    }
    
    private void storeBackgroundPosition(Term<?>[] storage, Term<?> term) {
        
        if (term instanceof TermIdent) {
            String idval = ((TermIdent) term).getValue();
            TermFactory tf = CSSFactory.getTermFactory();
            if (idval.equalsIgnoreCase("left"))
                setPositionValue(storage, 0, tf.createPercent(0.0f));
            else if (idval.equalsIgnoreCase("right"))
                setPositionValue(storage, 0, tf.createPercent(100.0f));
            else if (idval.equalsIgnoreCase("top"))
                setPositionValue(storage, 1, tf.createPercent(0.0f));
            else if (idval.equalsIgnoreCase("bottom"))
                setPositionValue(storage, 1, tf.createPercent(100.0f));
            else if (idval.equalsIgnoreCase("center"))
                setPositionValue(storage, -1, tf.createPercent(50.0f));
        }
        else
            setPositionValue(storage, -1, term);
    }
    
    private void setPositionValue(Term<?>[] s, int index, Term<?> term) {
        switch (index) {
            case -1: if (s[0] == null) //any position - use the free position
                         s[0] = term;
                     else
                         s[1] = term;
                     break;
            case 0: if (s[0] != null) //if the position is occupied, move the old value
                        s[1] = s[0];
                    s[0] = term;
                    break;
            case 1: if (s[1] != null)
                        s[0] = s[1];
                    s[1] = term;
                    break;
        }
    }
    
    private boolean isExtentKeyword(Term<?> term) {
        if (term instanceof TermIdent) {
            switch (((TermIdent) term).getValue()) {
                case "closest-corner":
                case "closest-side":
                case "farthest-corner":
                case "farthest-side":
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
    
}