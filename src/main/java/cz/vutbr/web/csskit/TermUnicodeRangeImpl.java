/**
 * TermUnicodeRangeImpl.java
 *
 * Created on 2. 11. 2017, 11:20:24 by burgetr
 */
package cz.vutbr.web.csskit;

import cz.vutbr.web.css.TermUnicodeRange;

/**
 * 
 * @author burgetr
 */
public class TermUnicodeRangeImpl extends TermImpl<String> implements TermUnicodeRange
{

    @Override
    public TermUnicodeRange setValue(String uri) {
        this.value = uri;
        return this;
    }
    
}
