package cz.vutbr.web.csskit;

import cz.vutbr.web.css.ImportURI;
import cz.vutbr.web.css.StylesheetNotValidException;
import cz.vutbr.web.csskit.parser.SimpleNode;

import java.util.ArrayList;

/**
 * ImportUri
 * @author Jan Svercl, VUT Brno, 2008
 */
public class ImportURIImpl implements ImportURI {
  
    private String uri;
    private ArrayList<String> mediaList = new ArrayList<String>();

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

    public ArrayList<String> getMedia() {
        return mediaList;
    }
    
    protected ImportURIImpl(SimpleNode n) {
        SimpleNode cNodeURI = (SimpleNode)n.jjtGetChild(0);
        if(cNodeURI.getType().equals("uri")) {
            String uriStr = cNodeURI.getImage();
            uriStr = uriStr.replaceAll("^url\\(", "");
            uriStr = uriStr.replaceAll("\\)$", "");
            uriStr = uriStr.replaceAll("^'", "");
            uriStr = uriStr.replaceAll("^\"", "");
            uriStr = uriStr.replaceAll("'$", "");
            uriStr = uriStr.replaceAll("\"$", "");
            uri = uriStr;
        }
        else if(cNodeURI.getType().equals("string")) {
            String uriStr = cNodeURI.getImage();
            uriStr = uriStr.replaceAll("^'", "");
            uriStr = uriStr.replaceAll("^\"", "");
            uriStr = uriStr.replaceAll("'$", "");
            uriStr = uriStr.replaceAll("\"$", "");
            uri = uriStr;
        }

        /* media */
        for(int i = 1; i < n.jjtGetNumChildren(); i++) {
            SimpleNode cNodeMedium = (SimpleNode)n.jjtGetChild(i);
            if(cNodeMedium.getType().equals("medium")) {
                String mediumName = ((SimpleNode)cNodeMedium.jjtGetChild(0)).getImage();
                mediaList.add(mediumName);
            }
        }
    }
    
    @Override
    public String toString() {
        String out = "";
        out += "@import url('" + uri + "')";
        boolean first = true;
        for(String mName : mediaList) {
            if(!first) {
                out += ", ";
            }
            else {
                out += " ";
                first = false;
            }
            out += mName;
        }
        out += ";\n";
        return out;
    }
    
    public void check(String path) throws StylesheetNotValidException {
        //Nothing
    }
}
