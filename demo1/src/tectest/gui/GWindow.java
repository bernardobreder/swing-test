package tectest.gui;

import java.awt.Window;
import java.util.List;
import java.util.Map.Entry;

/**
 * Componente que representa qualquer janela
 * 
 * @author Tecgraf
 */
public abstract class GWindow extends GContainer {

	/** Layout do componente */
	private GBorderLayout layout;
	/** Localização do componente */
	private GComponent location;
	/** Largura da janela */
	private int width;
	/** Altura da janela */
	private int height;

	/**
	 * @param driver
	 */
	protected GWindow(Window driver) {
		super(driver);
		layout(new GBorderLayout());
	}

	/**
	 * Fecha a janela
	 * 
	 * @return this
	 */
	public GWindow close() {
		visible(false);
		if (ui()) {
			driver().dispose();
		}
		return this;
	}

	/**
	 * Indica se está fechado
	 * 
	 * @return está fechado
	 */
	public boolean isClosed() {
		return visible() == false;
	}

	/**
	 * Atribui o layout do componente
	 * 
	 * @param layout
	 * @return this
	 */
	public GWindow layout(GBorderLayout layout) {
		this.layout = layout;
		layout.parent(this);
		if (ui()) {
			driver().add(layout.driver());
		}
		return this;
	}

	/**
	 * Retorna o layout do componente
	 * 
	 * @return layout do componente
	 */
	public GBorderLayout layout() {
		return layout;
	}

	/**
	 * Atribui uma localização relativa a um outro componente ou nulo para
	 * centralizar na tela
	 * 
	 * @param component
	 * @return this
	 */
	public GWindow location(GComponent component) {
		this.location = component;
		if (ui()) {
			driver().setLocationRelativeTo(component == null ? null : component.driver());
		}
		return this;
	}

	/**
	 * Retorna a localização relativa a um componente
	 * 
	 * @return localização relativa
	 */
	public GComponent location() {
		return location;
	}

	/**
	 * Retorna o comprimento
	 * 
	 * @return comprimento
	 */
	public int width() {
		return width;
	}

	/**
	 * Retorna a altura
	 * 
	 * @return altura
	 */
	public int height() {
		return height;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GWindow size(int width, int height) {
		this.width = width;
		this.height = height;
		if (ui()) {
			driver().setSize(width, height);
		}
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Entry<String, Object>> attributes() {
		List<Entry<String, Object>> list = super.attributes();
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GComponent> children() {
		List<GComponent> list = super.children();
		list.addAll(layout.children());
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Window driver() {
		return (Window) super.driver();
	}

}
