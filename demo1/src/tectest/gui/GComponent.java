package tectest.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JComponent;

import tectest.util.GColor;
import tectest.util.GFont;
import tectest.util.GLocale;

/**
 * Implementação do componente swing {@link JComponent}
 *
 * @author Bernardo Breder
 */
public abstract class GComponent {

	/** Driver */
	private transient final Component driver;
	/** Componente pai */
	private GContainer parent;
	/** Nome do componente */
	private String name;
	/** Cor de texto */
	private GColor foreground;
	/** Cor de fundo */
	private GColor background;
	/** Fonte do componente */
	private GFont font;
	/** Indica a lingua */
	private GLocale locale;
	/** Indica se o componente está visivel */
	private boolean visible;
	/** Indica se o componente está habilitado para uso */
	private boolean enabled;

	/**
	 * @param driver
	 */
	protected GComponent(Component driver) {
		this.driver = driver;
		this.enabled = true;
	}

	/**
	 * Altera o tamanho do componente
	 *
	 * @param width
	 * @param height
	 * @return this
	 */
	public GComponent size(int width, int height) {
		if (ui()) {
			driver().setSize(new Dimension(width, height));
		}
		return this;
	}

	/**
	 * Atribui um nome para o componente
	 *
	 * @param name
	 * @return this
	 */
	public GComponent name(String name) {
		this.name = name;
		if (ui()) {
			driver().setName(name);
		}
		return this;
	}

	/**
	 * Retorna o nome do componente
	 *
	 * @return nome
	 */
	public String name() {
		return name;
	}

	/**
	 * Atribui o componente pai
	 *
	 * @param parent
	 * @return this
	 */
	public GComponent parent(GContainer parent) {
		this.parent = parent;
		return this;
	}

	/**
	 * Retorna o componente que o possui
	 *
	 * @return componente pai
	 */
	public GContainer parent() {
		return parent;
	}

	/**
	 * Retorna
	 *
	 * @return foreground
	 */
	public GColor foreground() {
		if (foreground != null) {
			return foreground;
		}
		return parent.foreground();
	}

	/**
	 * @param foreground
	 * @return this
	 */
	public GComponent foreground(GColor foreground) {
		this.foreground = foreground;
		if (ui()) {
			driver().setForeground(foreground == null ? null : foreground.driver());
		}
		return this;
	}

	/**
	 * Retorna a cor de fundo do componente
	 *
	 * @return background
	 */
	public GColor background() {
		if (background != null) {
			return background;
		}
		return parent.background();
	}

	/**
	 * @param background
	 * @return this
	 */
	public GComponent background(GColor background) {
		this.background = background;
		if (ui()) {
			if (driver() instanceof JComponent) {
				JComponent jComponent = (JComponent) driver();
				jComponent.setOpaque(background != null);
			}
			driver().setBackground(background == null ? null : background.driver());
		}
		return this;
	}

	/**
	 * Retorna
	 *
	 * @return font
	 */
	public GFont font() {
		if (font != null) {
			return font;
		}
		return parent.font();
	}

	/**
	 * Altera a fonte do componente
	 *
	 * @param font
	 * @return this
	 */
	public GComponent font(GFont font) {
		this.font = font;
		if (ui()) {
			driver().setFont(font == null ? null : font.driver());
		}
		return this;
	}

	/**
	 * Retorna
	 *
	 * @return locale
	 */
	public GLocale locale() {
		return locale;
	}

	/**
	 * @param locale
	 */
	public void locale(GLocale locale) {
		this.locale = locale;
		if (ui()) {
			driver().setLocale(locale == null ? null : locale.driver());
		}
	}

	/**
	 * Torna a janela visivel
	 *
	 * @param visible
	 * @return this
	 */
	public <E extends GComponent> E visible(boolean visible) {
		this.visible = visible;
		if (ui()) {
			driver().setVisible(visible);
		}
		return cast();
	}

	/**
	 * Indica se a janela está visivel
	 *
	 * @return janela está visivel
	 */
	public boolean visible() {
		if (!visible || parent == null) {
			return false;
		}
		if (parent instanceof GFrame) {
			return true;
		}
		return parent.visible();
	}

	/**
	 * Busca pela janela que contém o componente
	 *
	 * @return janela que o possui
	 */
	public GFrame frame() {
		GComponent c = this;
		while (c != null && c instanceof GFrame == false) {
			c = c.parent();
		}
		return (GFrame) c;
	}

	/**
	 * Retorna o driver
	 *
	 * @return driver
	 */
	public Component driver() {
		return driver;
	}

	/**
	 * @return lista de atributos
	 */
	public List<Entry<String, Object>> attributes() {
		List<Entry<String, Object>> list = new ArrayList<Entry<String, Object>>();
		if (locale != null) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("locale", locale));
		}
		if (visible) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("visible", visible));
		}
		if (!enabled) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("enabled", enabled));
		}
		if (font != null) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("font", font));
		}
		if (background != null) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("background", background));
		}
		if (foreground != null) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("foreground", foreground));
		}
		if (name != null) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("name", name));
		}
		return list;
	}

	/**
	 * Indica se tem interface gráfica ou é teste
	 *
	 * @return true se tiver interface
	 */
	public boolean ui() {
		return driver != null;
	}

	/**
	 * @param c
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	public <T extends GComponent> T cast(Class<T> c) {
		return (T) this;
	}

	/**
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	public <T extends GComponent> T cast() {
		return (T) this;
	}

	/**
	 * Retorna a string que representa o objeto
	 *
	 * @param level
	 * @return string
	 */
	public abstract String toString(int level);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return toString(0);
	}

}
