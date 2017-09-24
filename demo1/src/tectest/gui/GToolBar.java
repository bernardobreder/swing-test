package tectest.gui;

import static tectest.util.GuiString.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import tectest.util.GApp;

/**
 * Implementação genérica do {@link JToolBar}
 * 
 * @author Bernardo Breder
 */
public class GToolBar extends GContainer {

	/** Filhos da tool bar */
	private List<GComponent> children;

	/**
	 * Construtor
	 */
	public GToolBar() {
		super(GApp.get().test() ? null : new JToolBar());
		children = new ArrayList<GComponent>();
	}

	/**
	 * Adiciona um filho
	 * 
	 * @param component
	 * @return this
	 */
	public GToolBar add(GComponent component) {
		children.add(component);
		component.parent(this);
		driver().add(component.driver());
		return this;
	}

	/**
	 * Adiciona um separador
	 * 
	 * @return this
	 */
	public GToolBar addSeparator() {
		driver().addSeparator();
		if (ui()) {
			driver().add(new JSeparator(SwingConstants.HORIZONTAL));
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
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(int level) {
		return string(this, "toolbar", level);
	}

	/**
	 * Retorna o driver
	 * 
	 * @return driver
	 */
	@Override
	public JToolBar driver() {
		return (JToolBar) super.driver();
	}

}
