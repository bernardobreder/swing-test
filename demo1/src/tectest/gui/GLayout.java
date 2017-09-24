package tectest.gui;

import java.awt.LayoutManager;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JPanel;

/**
 * Implementação genérica do {@link GLayout}
 * 
 * @author Bernardo Breder
 */
public abstract class GLayout extends GContainer {

	/** Espaçamento na horizontal */
	private int hgap;
	/** Espaçamento na vertical */
	private int vgap;

	/**
	 * @param driver
	 */
	protected GLayout(JPanel driver) {
		super(driver);
	}

	/**
	 * Atribui um espaçamento na horizontal
	 * 
	 * @param value
	 * @return this
	 */
	public GLayout hgap(int value) {
		hgap = value;
		return this;
	}

	/**
	 * Retorna o espaçamento na horizontal
	 * 
	 * @return espaçamento na horizontal
	 */
	public int hgap() {
		return hgap;
	}

	/**
	 * Atribui um espaçamento na vertical
	 * 
	 * @param value
	 * @return espaçamento na vertical
	 */
	public GLayout vgap(int value) {
		vgap = value;
		return this;
	}

	/**
	 * Retorna o espaçamento na vertical
	 * 
	 * @return espaçamento na vertical
	 */
	public int vgap() {
		return vgap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Entry<String, Object>> attributes() {
		List<Entry<String, Object>> list = super.attributes();
		list.add(new AbstractMap.SimpleEntry<String, Object>("hgap", hgap));
		list.add(new AbstractMap.SimpleEntry<String, Object>("vgap", vgap));
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GComponent> children() {
		List<GComponent> list = super.children();
		return list;
	}

	/**
	 * Retorna o driver
	 * 
	 * @return driver
	 */
	@Override
	public JPanel driver() {
		return (JPanel) super.driver();
	}

	/**
	 * Retorna o layout
	 * 
	 * @return layout
	 */
	protected LayoutManager layout() {
		return driver().getLayout();
	}

}
