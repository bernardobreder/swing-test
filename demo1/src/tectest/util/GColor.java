package tectest.util;

import java.awt.Color;

/**
 * Estrutura que armazena uma cor
 *
 * @author Tecgraf
 */
public class GColor {

	/**
	 * The color white. In the default sRGB space.
	 */
	public final static GColor WHITE = new GColor(255, 255, 255);

	/**
	 * The color light gray. In the default sRGB space.
	 */
	public final static GColor LIGHT_GRAY = new GColor(192, 192, 192);

	/**
	 * The color gray. In the default sRGB space.
	 */
	public final static GColor GRAY = new GColor(128, 128, 128);

	/**
	 * The color dark gray. In the default sRGB space.
	 */
	public final static GColor DARK_GRAY = new GColor(64, 64, 64);

	/**
	 * The color black. In the default sRGB space.
	 */
	public final static GColor BLACK = new GColor(0, 0, 0);

	/**
	 * The color red. In the default sRGB space.
	 */
	public final static GColor RED = new GColor(255, 0, 0);

	/**
	 * The color pink. In the default sRGB space.
	 */
	public final static GColor PINK = new GColor(255, 175, 175);

	/**
	 * The color orange. In the default sRGB space.
	 */
	public final static GColor ORANGE = new GColor(255, 200, 0);

	/**
	 * The color yellow. In the default sRGB space.
	 */
	public final static GColor YELLOW = new GColor(255, 255, 0);

	/**
	 * The color green. In the default sRGB space.
	 */
	public final static GColor GREEN = new GColor(0, 255, 0);

	/**
	 * The color magenta. In the default sRGB space.
	 */
	public final static GColor MAGENTA = new GColor(255, 0, 255);

	/**
	 * The color cyan. In the default sRGB space.
	 */
	public final static GColor CYAN = new GColor(0, 255, 255);

	/**
	 * The color blue. In the default sRGB space.
	 */
	public final static GColor BLUE = new GColor(0, 0, 255);

	/**
	 * Retorna a cor de uma celula selecionada
	 */
	public final static GColor TABLE_CELL_SELECTED = new GColor(184, 207, 229);

	/** Fator */
	private static final float FACTOR = 0.7f;

	/**
	 * The color value.
	 *
	 * @serial
	 * @see #getRGB
	 */
	protected int value;

	/**
	 * Construtor de cor
	 *
	 * @param r
	 * @param g
	 * @param b
	 */
	public GColor(int r, int g, int b) {
		this(r, g, b, 255);
	}

	/**
	 * Construtor de cor
	 *
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	public GColor(int r, int g, int b, int a) {
		value = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
	}

	/**
	 * Construtor de cor
	 *
	 * @param rgb
	 */
	public GColor(int rgb) {
		value = 0xff000000 | rgb;
	}

	/**
	 * Construtor de cor
	 *
	 * @param r
	 * @param g
	 * @param b
	 */
	public GColor(float r, float g, float b) {
		this((int) (r * 255 + 0.5), (int) (g * 255 + 0.5), (int) (b * 255 + 0.5));
	}

	/**
	 * Construtor de cor
	 *
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	public GColor(float r, float g, float b, float a) {
		this((int) (r * 255 + 0.5), (int) (g * 255 + 0.5), (int) (b * 255 + 0.5), (int) (a * 255 + 0.5));
	}

	/**
	 * Returns the red component in the range 0-255 in the default sRGB space.
	 *
	 * @return the red component.
	 * @see #getRGB
	 */
	public int getRed() {
		return (value >> 16) & 0xFF;
	}

	/**
	 * Returns the green component in the range 0-255 in the default sRGB space.
	 *
	 * @return the green component.
	 * @see #getRGB
	 */
	public int getGreen() {
		return (value >> 8) & 0xFF;
	}

	/**
	 * Returns the blue component in the range 0-255 in the default sRGB space.
	 *
	 * @return the blue component.
	 * @see #getRGB
	 */
	public int getBlue() {
		return (value >> 0) & 0xFF;
	}

	/**
	 * Returns the alpha component in the range 0-255.
	 *
	 * @return the alpha component.
	 * @see #getRGB
	 */
	public int getAlpha() {
		return (value >> 24) & 0xff;
	}

	/**
	 * RGB da cor
	 *
	 * @return rgb
	 */
	public int getRGB() {
		return value;
	}

	/**
	 * Aumenta o brilho
	 *
	 * @return cor com brilho
	 */
	public GColor brighter() {
		return brighter(FACTOR);
	}

	/**
	 * Aumenta o brilho
	 *
	 * @param factor
	 *
	 * @return cor com brilho
	 */
	public GColor brighter(float factor) {
		int r = getRed();
		int g = getGreen();
		int b = getBlue();
		int alpha = getAlpha();
		int i = (int) (1.0 / (1.0 - factor));
		if (r == 0 && g == 0 && b == 0) {
			return new GColor(i, i, i, alpha);
		}
		if (r > 0 && r < i) {
			r = i;
		}
		if (g > 0 && g < i) {
			g = i;
		}
		if (b > 0 && b < i) {
			b = i;
		}
		return new GColor(Math.min((r / factor), 255), Math.min((g / factor), 255), Math.min((b / factor), 255), alpha);
	}

	/**
	 * Aumenta a escuridão da cor
	 *
	 * @return cor escura
	 */
	public GColor darker() {
		return darker(FACTOR);
	}

	/**
	 * Aumenta a escuridão da cor
	 *
	 * @param factor
	 * @return cor escura
	 */
	public GColor darker(float factor) {
		return new GColor(Math.max((int) (getRed() * factor), 0), Math.max((int) (getGreen() * factor), 0), Math.max((int) (getBlue() * factor), 0), getAlpha());
	}

	/**
	 * Retorna a cor do AWT
	 *
	 * @return cor do AWT
	 */
	public Color driver() {
		return new Color(value);
	}

	/**
	 * Computes the hash code for this <code>GColor</code>.
	 *
	 * @return a hash code value for this object.
	 * @since JDK1.0
	 */
	@Override
	public int hashCode() {
		return value;
	}

	/**
	 * Determines whether another object is equal to this <code>GColor</code>.
	 * <p>
	 * The result is <code>true</code> if and only if the argument is not
	 * <code>null</code> and is a <code>GColor</code> object that has the same
	 * red, green, blue, and alpha values as this object.
	 *
	 * @param obj
	 *            the object to test for equality with this <code>GColor</code>
	 * @return <code>true</code> if the objects are the same; <code>false</code>
	 *         otherwise.
	 * @since JDK1.0
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof GColor && ((GColor) obj).value == value;
	}

	/**
	 * Returns a string representation of this <code>GColor</code>. This method
	 * is intended to be used only for debugging purposes. The content and
	 * format of the returned string might vary between implementations. The
	 * returned string might be empty but cannot be <code>null</code>.
	 *
	 * @return a string representation of this <code>GColor</code>.
	 */
	@Override
	public String toString() {
		return "(" + getRed() + "," + getGreen() + "," + getBlue() + ")";
	}

}
