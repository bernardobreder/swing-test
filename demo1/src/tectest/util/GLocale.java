package tectest.util;

import java.util.Locale;

/**
 * Localização de linguagas
 *
 * @author Tecgraf
 */
public class GLocale {

	/**
	 * Useful constant for language.
	 */
	public static final GLocale ENGLISH = new GLocale("en", "");

	/**
	 * Useful constant for language.
	 */
	public static final GLocale FRENCH = new GLocale("fr", "");

	/**
	 * Useful constant for language.
	 */
	public static final GLocale GERMAN = new GLocale("de", "");

	/**
	 * Useful constant for language.
	 */
	public static final GLocale ITALIAN = new GLocale("it", "");

	/**
	 * Useful constant for language.
	 */
	public static final GLocale JAPANESE = new GLocale("ja", "");

	/**
	 * Useful constant for language.
	 */
	public static final GLocale KOREAN = new GLocale("ko", "");

	/**
	 * Useful constant for language.
	 */
	public static final GLocale CHINESE = new GLocale("zh", "");

	/**
	 * Useful constant for language.
	 */
	public static final GLocale SIMPLIFIED_CHINESE = new GLocale("zh", "CN");

	/**
	 * Useful constant for language.
	 */
	public static final GLocale TRADITIONAL_CHINESE = new GLocale("zh", "TW");

	/**
	 * Useful constant for country.
	 */
	public static final GLocale FRANCE = new GLocale("fr", "FR");

	/**
	 * Useful constant for country.
	 */
	public static final GLocale GERMANY = new GLocale("de", "DE");

	/**
	 * Useful constant for country.
	 */
	public static final GLocale ITALY = new GLocale("it", "IT");

	/**
	 * Useful constant for country.
	 */
	public static final GLocale JAPAN = new GLocale("ja", "JP");

	/**
	 * Useful constant for country.
	 */
	public static final GLocale KOREA = new GLocale("ko", "KR");

	/**
	 * Useful constant for country.
	 */
	public static final GLocale CHINA = SIMPLIFIED_CHINESE;

	/**
	 * Useful constant for country.
	 */
	public static final GLocale PRC = SIMPLIFIED_CHINESE;

	/**
	 * Useful constant for country.
	 */
	public static final GLocale TAIWAN = TRADITIONAL_CHINESE;

	/**
	 * Useful constant for country.
	 */
	public static final GLocale UK = new GLocale("en", "GB");

	/**
	 * Useful constant for country.
	 */
	public static final GLocale US = new GLocale("en", "US");

	/**
	 * Useful constant for country.
	 */
	public static final GLocale CANADA = new GLocale("en", "CA");

	/**
	 * Useful constant for country.
	 */
	public static final GLocale CANADA_FRENCH = new GLocale("fr", "CA");

	/**
	 * Useful constant for the root locale. The root locale is the locale whose
	 * language, country, and variant are empty ("") strings. This is regarded
	 * as the base locale of all locales, and is used as the language/country
	 * neutral locale for the locale sensitive operations.
	 *
	 * @since 1.6
	 */
	public static final GLocale ROOT = new GLocale("", "");

	/** Linguage */
	private final String language;
	/** Pais */
	private final String country;

	/**
	 * Construct a locale from language and country. This constructor normalizes
	 * the language value to lowercase and the country value to uppercase.
	 * <p>
	 * <b>Note:</b>
	 * <ul>
	 * <li>ISO 639 is not a stable standard; some of the language codes it
	 * defines (specifically "iw", "ji", and "in") have changed. This
	 * constructor accepts both the old codes ("iw", "ji", and "in") and the new
	 * codes ("he", "yi", and "id"), but all other API on Locale will return
	 * only the OLD codes.
	 * <li>For backward compatibility reasons, this constructor does not make
	 * any syntactic checks on the input.
	 * </ul>
	 *
	 * @param language
	 *            An ISO 639 alpha-2 or alpha-3 language code, or a language
	 *            subtag up to 8 characters in length. See the
	 *            <code>Locale</code> class description about valid language
	 *            values.
	 * @param country
	 *            An ISO 3166 alpha-2 country code or a UN M.49 numeric-3 area
	 *            code. See the <code>Locale</code> class description about
	 *            valid country values.
	 * @exception NullPointerException
	 *                thrown if either argument is null.
	 */
	public GLocale(String language, String country) {
		this.language = language;
		this.country = country;
	}

	/**
	 * Construct a locale from a language code. This constructor normalizes the
	 * language value to lowercase.
	 * <p>
	 * <b>Note:</b>
	 * <ul>
	 * <li>ISO 639 is not a stable standard; some of the language codes it
	 * defines (specifically "iw", "ji", and "in") have changed. This
	 * constructor accepts both the old codes ("iw", "ji", and "in") and the new
	 * codes ("he", "yi", and "id"), but all other API on Locale will return
	 * only the OLD codes.
	 * <li>For backward compatibility reasons, this constructor does not make
	 * any syntactic checks on the input.
	 * </ul>
	 *
	 * @param language
	 *            An ISO 639 alpha-2 or alpha-3 language code, or a language
	 *            subtag up to 8 characters in length. See the
	 *            <code>Locale</code> class description about valid language
	 *            values.
	 * @exception NullPointerException
	 *                thrown if argument is null.
	 * @since 1.4
	 */
	public GLocale(String language) {
		this(language, "");
	}

	/**
	 * Retorna a localização em AWT
	 * 
	 * @return localização em AWT
	 */
	public Locale driver() {
		return new Locale(language, country);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + country.hashCode();
		result = prime * result + language.hashCode();
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		GLocale other = (GLocale) obj;
		return country.equals(other.country) || language.equals(other.language);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return language + "-" + country;
	}

}
