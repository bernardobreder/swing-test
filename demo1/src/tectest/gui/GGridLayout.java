package tectest.gui;

import static tectest.util.GuiString.string;

import java.awt.GridLayout;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JPanel;

import tectest.util.GApp;

/**
 * Implementação genérica do {@link GGridLayout}
 * 
 * @author Bernardo Breder
 */
public class GGridLayout extends GLayout {

	/** Quantidade de linhas */
	private int rows;
	/** Quantidade de colunas */
	private int columns;
	/** Matrix de filho */
	private GComponent[][] children;
	/** Quantidade de filhos */
	private int childrenCount;

	/**
	 * @param rows
	 * @param columns
	 * @param hgap
	 * @param vgap
	 */
	public GGridLayout(int rows, int columns, int hgap, int vgap) {
		super(GApp.get().test() ? null : new JPanel(new GridLayout(rows, columns, hgap, vgap)));
		this.rows = rows;
		this.columns = columns;
		this.children = new GComponent[rows][columns];
		this.childrenCount = 0;
	}

	/**
	 * Adiciona um filho
	 * 
	 * @param component
	 * @return this
	 */
	public GGridLayout add(GComponent component) {
		int row = childrenCount / columns;
		int column = childrenCount % columns;
		childrenCount++;
		children[row][column] = component;
		if (ui()) {
			driver().add(component.driver());
		}
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GComponent> children() {
		List<GComponent> list = super.children();
		for (int n = 0; n < childrenCount; n++) {
			int row = n / columns;
			int column = n % columns;
			list.add(children[row][column]);
		}
		return list;
	}

	/**
	 * Retorna a quantidade de linhas
	 * 
	 * @return quantidade de linhas
	 */
	public int rows() {
		return rows;
	}

	/**
	 * Retorna a quantidade de colunas
	 * 
	 * @return quantidade de colunas
	 */
	public int columns() {
		return columns;
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
	public String toString(int level) {
		return string(this, "gridlayout", level);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GridLayout layout() {
		return (GridLayout) super.layout();
	}

}
