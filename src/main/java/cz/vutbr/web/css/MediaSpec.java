/**
 * MediaSpec.java
 *
 * Created on 25. 6. 2014, 13:28:43 by burgetr
 */
package cz.vutbr.web.css;

/**
 * This class represents the properties of the output media used for displaying the document.
 * 
 * @author burgetr
 */
public class MediaSpec
{
    /** Media type name (e.g. "screen") */
    protected String type;
    
    /** Output area width in pixels */
    protected int width;
    /** Output area height in pixels */
    protected int height;
    /** Output device width in pixels */
    protected int deviceWidth;
    /** Oputput device height in pixels */
    protected int deviceHeight;
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
    
    
    public MediaSpec(String type)
    {
        loadDefaults();
        this.type = new String(type);
    }
    
    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @return the deviceWidth
     */
    public int getDeviceWidth()
    {
        return deviceWidth;
    }

    /**
     * @return the deviceHeight
     */
    public int getDeviceHeight()
    {
        return deviceHeight;
    }

    /**
     * @return the color
     */
    public int getColor()
    {
        return color;
    }

    /**
     * @return the colorIndex
     */
    public int getColorIndex()
    {
        return colorIndex;
    }

    /**
     * @return the monochrome
     */
    public int getMonochrome()
    {
        return monochrome;
    }

    /**
     * @return the resolution
     */
    public float getResolution()
    {
        return resolution;
    }

    /**
     * @return the scanInterlace
     */
    public boolean isScanInterlace()
    {
        return scanInterlace;
    }

    /**
     * @return the grid
     */
    public int getGrid()
    {
        return grid;
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
    
}
