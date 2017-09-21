package tectest.gui;

import static tectest.util.GuiString.string;

import java.util.List;
import java.util.Map.Entry;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import tectest.util.GApp;

/**
 * Implementação genérica do {@link JScrollPane}
 * 
 * @author Bernardo Breder
 */
public class GScrollPane extends GContainer {

	/** Componente no centro */
	private GComponent component;
	/** Componente na linha */
	private GComponent rowHeader;
	/** Componente na coluna */
	private GComponent columnHeader;
	/** Componentes no corner */
	private GComponent[] corners;

	/**
	 * @param component
	 */
	public GScrollPane(GComponent component) {
		super(GApp.get().test() ? null : new JScrollPane(component.driver()));
		this.component = component;
		this.corners = new GComponent[Corner.values().length];
	}

	/**
	 * Retorna componente na linha
	 * 
	 * @return componente na linha
	 */
	public GComponent rowHeader() {
		return rowHeader;
	}

	/**
	 * Atribui um componente na linha
	 * 
	 * @param rowHeader
	 * @return this
	 */
	public GScrollPane rowHeader(GComponent rowHeader) {
		this.rowHeader = rowHeader;
		if (ui()) {
			driver().setRowHeaderView(rowHeader.driver());
		}
		return this;
	}

	/**
	 * Retorna componente na coluna
	 * 
	 * @return componente na coluna
	 */
	public GComponent columnHeader() {
		return columnHeader;
	}

	/**
	 * Atribui um componente na coluna
	 * 
	 * @param columnHeader
	 * @return this
	 */
	public GScrollPane columnHeader(GComponent columnHeader) {
		this.columnHeader = columnHeader;
		if (ui()) {
			driver().setColumnHeaderView(rowHeader.driver());
		}
		return this;
	}

	/**
	 * Retorna o componente do corner
	 * 
	 * @param corner
	 * @return componente do corner
	 */
	public GComponent corner(Corner corner) {
		return corners[corner.ordinal()];
	}

	/**
	 * Atribui o componente do corner
	 * 
	 * @param corner
	 * @param component
	 * @return this
	 */
	public GScrollPane corner(Corner corner, GComponent component) {
		this.corners[corner.ordinal()] = component;
		if (ui()) {
			driver().setCorner(corner.code(), component.driver());
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
		if (component != null) {
			list.add(component);
		}
		if (rowHeader != null) {
			list.add(rowHeader);
		}
		if (columnHeader != null) {
			list.add(columnHeader);
		}
		int cornerCount = Corner.values().length;
		for (int n = 0; n < cornerCount; n++) {
			if (corners[n] != null) {
				list.add(corners[n]);
			}
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(int level) {
		return string(this, "scroll", level);
	}

	/**
	 * Retorna o componente com cast
	 * 
	 * @return componente
	 */
	@Override
	public JScrollPane driver() {
		return (JScrollPane) super.driver();
	}

	/**
	 * Enumador para identificar qual corner
	 * 
	 * @author Tecgraf
	 */
	public static enum Corner {

		/** */
		LOWER_LEFT_CORNER(ScrollPaneConstants.LOWER_LEFT_CORNER),
		/** */
		LOWER_RIGHT_CORNER(ScrollPaneConstants.LOWER_RIGHT_CORNER),
		/** */
		UPPER_LEFT_CORNER(ScrollPaneConstants.UPPER_LEFT_CORNER),
		/** */
		UPPER_RIGHT_CORNER(ScrollPaneConstants.UPPER_RIGHT_CORNER),
		/** */
		LOWER_LEADING_CORNER(ScrollPaneConstants.LOWER_LEADING_CORNER),
		/** */
		LOWER_TRAILING_CORNER(ScrollPaneConstants.LOWER_TRAILING_CORNER),
		/** */
		UPPER_LEADING_CORNER(ScrollPaneConstants.UPPER_LEADING_CORNER),
		/** */
		UPPER_TRAILING_CORNER(ScrollPaneConstants.UPPER_TRAILING_CORNER);

		/** Código */
		private String code;

		/**
		 * @param code
		 */
		private Corner(String code) {
			this.code = code;
		}

		/**
		 * @return code
		 */
		public String code() {
			return code;
		}

	}

}
