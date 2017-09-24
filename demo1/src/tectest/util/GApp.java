package tectest.util;

import java.awt.Image;
import java.util.Locale;
import java.util.Map;

import javax.swing.UIManager;

/**
 * Cria um contexto de aplicação garantindo isolamento de teste
 *
 * @author Bernardo Breder
 */
public class GApp {

	/** Instancia unica */
	private static GApp instance;
	/** Indica se está num ambiente de teste */
	private boolean test;
	/** Indica se a aplicação foi finalizada */
	private boolean closed;
	/** Localização */
	private GLocale locale;
	/** Mapa de imagens */
	private Map<String, Image> images;

	/**
	 * @param test
	 */
	public GApp(boolean test) {
		this.test = test;
		if (instance != null) {
			throw new IllegalStateException();
		}
		instance = this;
	}

	/**
	 * @return instancia unica
	 */
	public static GApp get() {
		if (instance == null) {
			throw new IllegalStateException("no application started");
		}
		return instance;
	}

	/**
	 * Indica se está num ambiente de teste
	 *
	 * @return ambiente de teste
	 */
	public boolean test() {
		return test;
	}

	/**
	 * Finaliza todas as variáveis de teste
	 */
	public void close() {
		instance = null;
		closed = true;
		if (test) {
			images = null;
			locale = null;
		}
	}

	/**
	 * Retorna a localização
	 *
	 * @return localização
	 */
	public GLocale locale() {
		return locale;
	}

	/**
	 * Registra a localização
	 *
	 * @param locale
	 * @return this
	 */
	public GApp locale(GLocale locale) {
		this.locale = locale;
		if (!test) {
			Locale.setDefault(locale.driver());
		}
		return this;
	}

	/**
	 * @return this
	 */
	public GApp windowLookAndFeel() {
		if (!test) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (Exception e) {
			}
		}
		return this;
	}

	/**
	 * @return this
	 */
	public GApp metalLookAndFeel() {
		if (!test) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			} catch (Exception e) {
			}
		}
		return this;
	}

	/**
	 * Registra uma imagem
	 *
	 * @param path
	 * @param url
	 * @return this
	 */
	public GApp image(String path, String url) {
		return this;
	}

}
