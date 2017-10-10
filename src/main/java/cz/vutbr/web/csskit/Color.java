package cz.vutbr.web.csskit;

/**
 * Simple holder class for color values
 */
public class Color {

    final int value;

    public Color(final int red, final int green, final int blue) {
        this(red, green, blue, 255);
    }

    public Color(final int red, final int green, final int blue, final int alpha) {
        this.value = ((alpha & 0xFF) << 24) |
            ((red & 0xFF) << 16) |
            ((green & 0xFF) << 8) |
            ((blue & 0xFF));
    }

    /**
     * Returns the RGB value representing the color.
     */
    public int getRGB() {
        return value;
    }

    /**
     * Returns the red value in the range 0-255.
     *
     * @return the red value.
     */
    public int getRed() {
        return (value >> 16) & 0xFF;
    }

    /**
     * Returns the green value in the range 0-255.
     *
     * @return the green value.
     */
    public int getGreen() {
        return (value >> 8) & 0xFF;
    }

    /**
     * Returns the blue value in the range 0-255.
     *
     * @return the blue component.
     */
    public int getBlue() {
        return (value) & 0xFF;
    }

    /**
     * Returns the alpha value in the range 0-255.
     *
     * @return the alpha value.
     */
    public int getAlpha() {
        return (value >> 24) & 0xff;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Color color = (Color) o;

        return value == color.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

}
