package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermUri;
import cz.vutbr.web.csskit.parser.SimpleNode;

/**
 * TermUri
 * @author Jan Svercl, VUT Brno, 2008
 */
public class TermUriImpl extends TermImpl implements TermUri {

    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        if(uri == null) {
            throw new NullPointerException();
        }    
        else {
            this.uri = uri;
        }
    }
    
    public TermUriImpl(String uri) {
        setUri(uri);
    }
    
    @Override
    public String toString() {
        return operator("url('" + uri + "')");
    }
    
    protected static TermUriImpl getUriByNode(SimpleNode term) {
        if(term != null) {
            if((term.jjtGetNumChildren() == 1)) {
                if(((SimpleNode)term.jjtGetChild(0)).getType().equals("uri")) {
                    String uri = ((SimpleNode)term.jjtGetChild(0)).getImage();
                    if(uri != null) {
                        uri = uri.replaceAll("^url\\(", "");
                        uri = uri.replaceAll("\\)$", "");
                        uri = uri.replaceAll("^'", "");
                        uri = uri.replaceAll("^\"", "");
                        uri = uri.replaceAll("'$", "");
                        uri = uri.replaceAll("\"$", "");
                        TermUriImpl termUri = new TermUriImpl(uri);
                        return termUri;
                    }
                }
            }
        }
        return null;
    }
}
