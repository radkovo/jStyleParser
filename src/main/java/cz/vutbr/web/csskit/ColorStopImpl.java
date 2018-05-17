package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermColor;
import cz.vutbr.web.css.TermFunction;
import cz.vutbr.web.css.TermLengthOrPercent;

public class ColorStopImpl implements TermFunction.Gradient.ColorStop {
    private TermColor color;
    private TermLengthOrPercent length;
    
    protected ColorStopImpl(TermColor color, TermLengthOrPercent length) {
        this.color = color;
        this.length = length;
    }

    @Override
    public TermColor getColor()
    {
        return color;
    }

    @Override
    public TermLengthOrPercent getLength()
    {
        return length;
    }
}