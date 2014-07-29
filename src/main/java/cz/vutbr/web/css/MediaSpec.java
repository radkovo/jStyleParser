/**
 * MediaSpec.java
 *
 * Created on 25. 6. 2014, 13:28:43 by burgetr
 */
package cz.vutbr.web.css;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cz.vutbr.web.css.Term.Operator;


/**
 * This class represents the features of the output media used for displaying the document. It specifies the name
 * of the output media type (e.g. "screen", "print", etc.) and further features defined by the CSS3 specification.
 * The default values of the features are the following (corresponding to a standard desktop station):
 *
 * <ul>
 * <li><code>width: 1100px</code></li>
 * <li><code>height: 850px</code></li>
 * <li><code>device-width: 1920px</code></li>
 * <li><code>device-height: 1200px</code></li>
 * <li><code>color: 8</code></li>
 * <li><code>color-index: 0</code></li>
 * <li><code>monochrome: 0</code></li>
 * <li><code>resolution: 96dpi</code></li>
 * <li><code>scan: progressive</code></li>
 * <li><code>grid: 0</code></li>
 * </ul>
 * 
 * @author burgetr
 */
public class MediaSpec
{
    
    /**
     * Known media query features based on the specification
     * @see <a href="http://www.w3.org/TR/css3-mediaqueries/">http://www.w3.org/TR/css3-mediaqueries/</a>
     */
    public enum Feature
    {
        WIDTH(true), HEIGHT(true), DEVICE_WIDTH(true), DEVICE_HEIGHT(true),
        ORIENTATION(false), ASPECT_RATIO(true), DEVICE_ASPECT_RATIO(true),
        COLOR(true), COLOR_INDEX(true), MONOCHROME(true), RESOLUTION(true),
        SCAN(false), GRID(false);
        
        private boolean prefixed;
        
        private Feature(boolean prefixed)
        {
            this.prefixed = prefixed;
        }
        
        /**
         * Is the min/max prefix allowed for this feature?
         * @return {@code true} if the feature may be prefixed
         */
        public boolean isPrefixed()
        {
            return prefixed;
        }
    }
    
    /** the values of 'em' used for computing the pixel lengths */
    public static final float em = 16.0f;
    /** the values of 'em' used for computing the pixel lengths */
    public static final float ex = 10.0f;
    /** CSS3 uses a fixed value of 96DPI for computing the lengths */
    public static final float dpi = 96.0f;
    
    protected static Map<String, Feature> featureMap;
    
    static {
        featureMap = new HashMap<String, Feature>(13);
        featureMap.put("width", Feature.WIDTH);
        featureMap.put("height", Feature.HEIGHT);
        featureMap.put("device-width", Feature.DEVICE_WIDTH);
        featureMap.put("device-height", Feature.DEVICE_HEIGHT);
        featureMap.put("orientation", Feature.ORIENTATION);
        featureMap.put("aspect-ratio", Feature.ASPECT_RATIO);
        featureMap.put("device-aspect-ratio", Feature.DEVICE_ASPECT_RATIO);
        featureMap.put("color", Feature.COLOR);
        featureMap.put("color-index", Feature.COLOR_INDEX);
        featureMap.put("monochrome", Feature.MONOCHROME);
        featureMap.put("resolution", Feature.RESOLUTION);
        featureMap.put("scan", Feature.SCAN);
        featureMap.put("grid", Feature.GRID);
    }
    
    
    /** Media type name (e.g. "screen") */
    protected String type;
    
    /** Output area width in pixels */
    protected float width;
    /** Output area height in pixels */
    protected float height;
    /** Output device width in pixels */
    protected float deviceWidth;
    /** Oputput device height in pixels */
    protected float deviceHeight;
    /** Color depth (bits per color) or 0 for no colors */
    protected int color;
    /** Number of entries in the color lookup table or 0 when no indexed colors are used */
    protected int colorIndex;
    /** Bits per pixel of a monochrome frame buffer or 0 when the device is not monochrome */
    protected int monochrome;
    /** Resolution in DPI */
    protected float resolution;
    /** Indicates interlaced scanning for "tv" media types */
    protected boolean scanInterlace;
    /** 1 for a grid device, 0 for bitmap device */
    protected int grid;
    
    /** Matches an empty media query? */
    protected boolean matchEmpty;
    
    /**
     * Creates a new media specification with the given media type and default values of the features.
     * @param type The media type (e.g. "screen")
     */
    public MediaSpec(String type)
    {
        loadDefaults();
        this.type = type.trim().toLowerCase(Locale.ENGLISH);
        this.matchEmpty = true; //normally, an empty media query means any media
    }

    /**
     * Obtains the media type of this specification.
     * @return The media type name (e.g. "screen")
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * Obtains the width of the display area.
     * @return the width in pixels.
     */
    public float getWidth()
    {
        return width;
    }

    /**
     * Sets the width of the display area.
     * @param width The width in pixels.
     */
    public void setWidth(float width)
    {
        this.width = width;
    }

    /**
     * Obtains the height of the display area.
     * @return the height in pixels.
     */
    public float getHeight()
    {
        return height;
    }

    /**
     * Sets the height of the display area.
     * @param height The height in pixels.
     */
    public void setHeight(float height)
    {
        this.height = height;
    }

    /**
     * Sets the width and height height of the display area.
     * @param width The width in pixels.
     * @param height The height in pixels.
     */
    public void setDimensions(float width, float height)
    {
        this.width = width;
        this.height = height;
    }
    
    /**
     * Obtains the width of the rendering surface of the output device.
     * @return the width in pixels.
     */
    public float getDeviceWidth()
    {
        return deviceWidth;
    }

    /**
     * Sets the width of the rendering surface of the output device.
     * @param deviceWidth the width in pixels.
     */
    public void setDeviceWidth(float deviceWidth)
    {
        this.deviceWidth = deviceWidth;
    }

    /**
     * Obtains the height of the rendering surface of the output device.
     * @return the height in pixels.
     */
    public float getDeviceHeight()
    {
        return deviceHeight;
    }

    /**
     * Sets the height of the rendering surface of the output device.
     * @param deviceHeight the height in pixels.
     */
    public void setDeviceHeight(float deviceHeight)
    {
        this.deviceHeight = deviceHeight;
    }

    /**
     * Sets the width of the rendering surface of the output device.
     * @param deviceWidth the width in pixels.
     * @param deviceHeight the height in pixels.
     */
    public void setDeviceDimensions(float deviceWidth, float deviceHeight)
    {
        this.deviceWidth = deviceWidth;
        this.deviceHeight = deviceHeight;
    }

    /**
     * Obtains the number of bits per color component of the output device. 
     * @return The number of bits or 0 if the device is not a color device.
     */
    public int getColor()
    {
        return color;
    }

    /**
     * Sets the number of bits per color component of the output device.
     * @param color The number of bits or 0 if the device is not a color device.
     */
    public void setColor(int color)
    {
        this.color = color;
    }

    /**
     * Obtains the number of entries in the color lookup table of the output device.
     * @return The number of lookup table entries or 0 if the device does not use a color lookup table.
     */
    public int getColorIndex()
    {
        return colorIndex;
    }

    /**
     * Sets the number of entries in the color lookup table of the output device.
     * @param colorIndex The number of lookup table entries or 0 if the device does not use a color lookup table.
     */
    public void setColorIndex(int colorIndex)
    {
        this.colorIndex = colorIndex;
    }

    /**
     * Obtains the number of bits per pixel in a monochrome frame buffer.
     * @return The number of bits per pixel or 0 if the device is not a monochrome device.
     */
    public int getMonochrome()
    {
        return monochrome;
    }

    /**
     * Sets the number of bits per pixel in a monochrome frame buffer.
     * @param monochrome The number of bits per pixel or 0 if the device is not a monochrome device.
     */
    public void setMonochrome(int monochrome)
    {
        this.monochrome = monochrome;
    }

    /**
     * Sets the resolution of the output device, i.e. the density of the pixels.
     * @return The resolution in DPI.
     */
    public float getResolution()
    {
        return resolution;
    }

    /**
     * Obtains the resolution of the output device, i.e. the density of the pixels.
     * @param resolution The resolution in DPI.
     */
    public void setResolution(float resolution)
    {
        this.resolution = resolution;
    }

    /**
     * Checks if the device is using the interlaced or progressive scanning (for "tv" media only).
     * @return {@code true} if the device is using the interlaced scanning, {@code false} for progressive scanning.
     */
    public boolean isScanInterlace()
    {
        return scanInterlace;
    }

    /**
     * Sets the device scanning process (for "tv" media only).
     * @param scanInterlace {@code true} if the device is using the interlaced scanning, {@code false} for progressive scanning.
     */
    public void setScanInterlace(boolean scanInterlace)
    {
        this.scanInterlace = scanInterlace;
    }

    /**
     * Checks whether the output device is grid or bitmap.
     * @return If the output device is grid-based (e.g., a "tty" terminal, or a phone display with only one fixed font), the value will be 1. Otherwise, the value will be 0.
     */
    public int getGrid()
    {
        return grid;
    }

    /**
     * Sets whether the output device is grid or bitmap.
     * @param grid If the output device is grid-based (e.g., a "tty" terminal, or a phone display with only one fixed font), the value will be 1. Otherwise, the value will be 0.
     */
    public void setGrid(int grid)
    {
        this.grid = grid;
    }

    /**
     * Obtains the aspect ratio of the display area defined as width / height.
     * @return The aspect radio.
     */
    public float getAspectRatio()
    {
        return width / height;
    }
    
    /**
     * Obtains the aspect ratio of the device defined as deviceWidth / deviceHeight.
     * @return The aspect radio.
     */
    public float getDeviceAspectRation()
    {
        return deviceWidth / deviceHeight;
    }
    
    /**
     * Checks whether the display area orientation is portrait or landscape.
     * @return {@code true} when the value of the {@code height} media feature is greater than or equal to the value
     *  of the {@code width} media feature.
     */
    public boolean isPortrait()
    {
        return height >= width; //http://www.w3.org/TR/css3-mediaqueries/#orientation
    }
    
    /**
     * Sets whether this media specification should match empty media queries.
     * @param matchEmpty {@code true} when this media specification should match empty media queries (this is the default)
     */
    public void setMatchEmpty(boolean matchEmpty)
    {
        this.matchEmpty = matchEmpty;
    }
    
    //===============================================================================================

    /**
     * Checks if this media specification matches a given media query.
     * @param q The media query
     * @return {@code true} when this media specification matches the given media query.
     */
    public boolean matches(MediaQuery q)
    {
        //match the media type
        if (q.getType() != null)
        {
            if (q.getType().equals("all"))
            {
                if (q.isNegative())
                    return false; //"NOT all" doesn't match to anything
            }
            else if (q.getType().equals(this.getType()) == q.isNegative()) //other than all
                return false;
        }
        //match the eventual expressions
        for (MediaExpression e : q)
        {
            if (!this.matches(e))
                return false;
        }
        //everything matched
        return true;
    }
    
    /**
     * Checks if this media specification matches a given media query expression.
     * @param e The media query expression
     * @return {@code true} when this media specification matches the given expression.
     */
    public boolean matches(MediaExpression e)
    {
        String fs = e.getFeature();
        boolean isMin = false;
        boolean isMax = false; 
        if (fs.startsWith("min-")) { isMin = true; fs = fs.substring(4); }
        else if (fs.startsWith("max-")) { isMax = true; fs = fs.substring(4); }
        
        Feature feature = getFeatureByName(fs);
        if (feature != null &&  (!(isMin || isMax) || feature.isPrefixed())) //the name (including prefixes) is allowed
        {
            switch (feature)
            {
                case WIDTH:
                    return valueMatches(getExpressionLengthPx(e), width, isMin, isMax);
                case HEIGHT:
                    return valueMatches(getExpressionLengthPx(e), height, isMin, isMax);
                case DEVICE_WIDTH:
                    return valueMatches(getExpressionLengthPx(e), deviceWidth, isMin, isMax);
                case DEVICE_HEIGHT:
                    return valueMatches(getExpressionLengthPx(e), deviceHeight, isMin, isMax);
                case ORIENTATION:
                    String oid = getExpressionIdentifier(e);
                    if (oid == null)
                        return false;
                    else if (oid.equals("portrait"))
                        return isPortrait();
                    else if (oid.equals("landscape"))
                        return !isPortrait();
                    else
                        return false;
                case ASPECT_RATIO:
                    return valueMatches(getExpressionRatio(e), getAspectRatio(), isMin, isMax);
                case DEVICE_ASPECT_RATIO:
                    return valueMatches(getExpressionRatio(e), getDeviceAspectRation(), isMin, isMax);
                case COLOR:
                    return valueMatches(getExpressionInteger(e), color, isMin, isMax);
                case COLOR_INDEX:
                    return valueMatches(getExpressionInteger(e), colorIndex, isMin, isMax);
                case MONOCHROME:
                    return valueMatches(getExpressionInteger(e), monochrome, isMin, isMax);
                case RESOLUTION:
                    return valueMatches(getExpressionResolution(e), resolution, isMin, isMax);
                case SCAN:
                    String sid = getExpressionIdentifier(e);
                    if (sid == null)
                        return false;
                    else if (sid.equals("progressive"))
                        return !scanInterlace;
                    else if (sid.equals("interlace"))
                        return scanInterlace;
                    else
                        return false;
                case GRID:
                    Integer gval = getExpressionInteger(e);
                    if (gval == null)
                        return false;
                    else if (gval == 0 || gval == 1) //0 and 1 are the only allowed values
                        return gval == grid;
                    else
                        return false;
                default:
                    return false;
            }
        }
        else
            return false; //results in "not all" for the whole query
    }
    
    /**
     * Checks whether this media specification matches to at least one of the given media queries.
     * @param queries The list of media queries to be matched.
     * @return {@code true} when at least one query matches, {@code false} when no query matches. 
     */
    public boolean matchesOneOf(List<MediaQuery> queries)
    {
        for (MediaQuery q : queries)
        {
            if (matches(q))
                return true;
        }
        return false;
    }
    
    /**
     * Checks whether the media specification matches an empty or missing media query, i.e. whether the
     * media or import rules with no media queries should be accepted with this media specification.
     * @return {@code true} when this media specification matches an empty media query
     */
    public boolean matchesEmpty()
    {
        return true;
    }
    
    /**
     * Obtains the feature based on its name.
     * @param name The name of the feature 
     * @return The feature corresponding to the name or {@code null} if the name is unknown
     */
    protected Feature getFeatureByName(String name)
    {
        return featureMap.get(name);
    }
    
    /**
     * Checks whether a value coresponds to the given criteria.
     * @param current the actual media value
     * @param required the required value or {@code null} for invalid requirement
     * @param min {@code true} when the required value is the minimal one
     * @param max {@code true} when the required value is the maximal one
     * @return {@code true} when the value matches the criteria.
     */
    protected boolean valueMatches(Float required, float current, boolean min, boolean max)
    {
        if (required != null)
        {
            if (min)
                return (current >= required);
            else if (max)
                return (current <= required);
            else
                return current == required;
        }
        else
            return false; //invalid values don't match
    }
    
    /**
     * Checks whether a value coresponds to the given criteria.
     * @param required the required value
     * @param current the tested value or {@code null} for invalid value
     * @param min {@code true} when the required value is the minimal one
     * @param max {@code true} when the required value is the maximal one
     * @return {@code true} when the value matches the criteria.
     */
    protected boolean valueMatches(Integer required, int current, boolean min, boolean max)
    {
        if (required != null)
        {
            if (min)
                return (current >= required);
            else if (max)
                return (current <= required);
            else
                return current == required;
        }
        else
            return false; //invalid values don't match
    }
    
    /**
     * Obtains the length specified by the given media query expression.
     * @param e The media query expression specifying a length.
     * @return The length converted to pixels or {@code null} when the value cannot be converted to length. 
     */
    protected Float getExpressionLengthPx(MediaExpression e)
    {
        if (e.size() == 1) //the length requires exactly one value
        {
            Term<?> term = e.get(0);
            if (term instanceof TermLength)
                return pxLength((TermLength) term);
            else
                return null;
        }
        else
            return null;
    }
    
    /**
     * Obtains the resolution specified by the given media query expression.
     * @param e The media query expression specifying a resolution.
     * @return The length converted to pixels or {@code null} when the value cannot be converted to resolution. 
     */
    protected Float getExpressionResolution(MediaExpression e)
    {
        if (e.size() == 1) //the length requires exactly one value
        {
            Term<?> term = e.get(0);
            if (term instanceof TermResolution)
                return dpiResolution((TermResolution) term);
            else
                return null;
        }
        else
            return null;
    }
    
    /**
     * Obtains the ratio specified by the given media query expression.
     * @param e The media query expression specifying a ratio.
     * @return The length converted to pixels or {@code null} when the value cannot be converted to ratio. 
     */
    protected Float getExpressionRatio(MediaExpression e)
    {
        if (e.size() == 2) //the ratio is two integer values
        {
            Term<?> term1 = e.get(0);
            Term<?> term2 = e.get(1);
            if (term1 instanceof TermInteger && term2 instanceof TermInteger
                    && (((TermInteger) term2).getOperator() == Operator.SLASH))
                return ((TermInteger) term1).getValue() / ((TermInteger) term2).getValue();
            else
                return null;
        }
        else
            return null;
    }
    
    /**
     * Obtains the integer specified by the given media query expression.
     * @param e The media query expression specifying an integer.
     * @return The length converted to pixels or {@code null} when the value cannot be converted to integer. 
     */
    protected Integer getExpressionInteger(MediaExpression e)
    {
        if (e.size() == 1) //the length requires exactly one value
        {
            Term<?> term = e.get(0);
            if (term instanceof TermInteger)
                return ((TermInteger) term).getIntValue();
            else
                return null;
        }
        else
            return null;
    }
    
    /**
     * Obtains the identifier specified by the given media query expression.
     * @param e The media query expression specifying an identifier.
     * @return The identifier name or {@code null} when the value cannot be converted to an identifier. 
     */
    protected String getExpressionIdentifier(MediaExpression e)
    {
        if (e.size() == 1) //the length requires exactly one value
        {
            Term<?> term = e.get(0);
            if (term instanceof TermIdent)
                return ((TermIdent) term).getValue().trim().toLowerCase(Locale.ENGLISH);
            else
                return null;
        }
        else
            return null;
    }
    
    /** 
     * Converts a length from a CSS length to 'px'.
     * @param spec the CSS length specification
     * @return the length in 'px' or {@code null} when the unit is invalid 
     */
    protected Float pxLength(TermLength spec)
    {
        float nval = spec.getValue();
        TermLength.Unit unit = spec.getUnit();
        
        switch (unit)
        {
            case pt:
                return (nval * dpi) / 72.0f;
            case in:
                return nval * dpi;
            case cm:
                return (nval * dpi) / 2.54f;
            case mm:
                return (nval * dpi) / 25.4f;
            case pc:
                return (nval * 12 * dpi) / 72.0f;
            case px:
                return nval;
            case em:
                return em * nval;
            case ex:
                return ex * nval;
            default:
                return null;
        }
    }

    /** 
     * Converts a resolution from a CSS length to 'dpi'.
     * @param spec the CSS resolution specification
     * @return the resolution in 'dpi' or {@code null} when the unit is invalid 
     */
    protected Float dpiResolution(TermResolution spec)
    {
        float nval = spec.getValue();
        TermLength.Unit unit = spec.getUnit();
        
        switch (unit)
        {
            case dpi:
                return nval;
            case dpcm:
                return nval * 2.54f;
            case dppx:
                return nval * getResolution();
            default:
                return null;
        }
    }
    
    //===============================================================================================
    
    /**
     * Loads some reasonable defaults that correspond to a normal desktop configuration.
     */
    protected void loadDefaults()
    {
        width = 1100;
        height = 850;
        deviceWidth = 1920;
        deviceHeight = 1200;
        color = 8;
        colorIndex = 0;
        monochrome = 0;
        resolution = 96;
        scanInterlace = false;
        grid = 0;
    }
    
    //===============================================================================================
    
    @Override
    public String toString()
    {
        StringBuilder ret = new StringBuilder();
        ret.append(type).append('[');
        ret.append("width:").append(width).append("; ");
        ret.append("height:").append(height).append("; ");
        ret.append("device-width:").append(deviceWidth).append("; ");
        ret.append("device-height:").append(deviceHeight).append("; ");
        ret.append("color:").append(color).append("; ");
        ret.append("color-index:").append(colorIndex).append("; ");
        ret.append("monochrome:").append(monochrome).append("; ");
        ret.append("resolution:").append(resolution).append("; ");
        ret.append("scan:").append(scanInterlace ? "interlace" : "progressive").append("; ");
        ret.append("grid:").append(grid).append(";");
        ret.append(']');
        return ret.toString();
    }
    
}
