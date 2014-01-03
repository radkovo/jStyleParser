package cz.vutbr.web.css;

import java.net.URL;

/**
 * Basic CSS declaration consisting of list of terms.
 * Implements comparable to allow set declaration with bigger priority,
 * either by its important! flag or by its selector's specificity
 * 
 * @author burgetr
 * @author kapy
 * @author Jan Svercl, VUT Brno, 2008
 */
public interface Declaration extends Rule<Term<?>>, PrettyOutput, Comparable<Declaration> {

    public boolean isImportant();
    
    public void setImportant(boolean important);
    
    public String getProperty();

    public void setProperty(String property);
    
    public Source getSource();
    
    public void setSource(Source src);
    
    //==================================================================================================
    
    /**
     * The declaration source information. 
     * @author burgetr
     */
    public static class Source {
        
        private URL url;
        private int line;
        private int position;
        
        public Source(URL url, int line, int position)
        {
            this.url = url;
            this.line = line;
            this.position = position;
        }
        
        public Source(Source other)
        {
            this.url = other.url;
            this.line = other.line;
            this.position = other.position;
        }
        
        public URL getUrl()
        {
            return url;
        }
        
        public void setUrl(URL url)
        {
            this.url = url;
        }
        
        public int getLine()
        {
            return line;
        }
        
        public void setLine(int line)
        {
            this.line = line;
        }
        
        public int getPosition()
        {
            return position;
        }

        public void setPosition(int position)
        {
            this.position = position;
        }

        @Override
        public String toString()
        {
            return ((url == null) ? "<internal>" : url.toString()) + ":" + line + ":" + position;
        }
    }
    
}
