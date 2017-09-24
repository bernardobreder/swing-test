package tectest.gui;

import static tectest.util.GuiString.string;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import tectest.util.GApp;
import tectest.util.GColor;
import tectest.util.GLocale;

/**
 * Estrutura que representa uma janela
 * 
 * @author Tecgraf
 */
public class GFrame extends GWindow {

	/** Título da janela */
	private String title = "Untitled";

	/**
	 * Construtor padrão
	 */
	public GFrame() {
		super(GApp.get().test() ? null : new JFrame());
		background(GColor.WHITE).locale(new GLocale("pt", "br"));
		if (ui()) {
			driver().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}

	/**
	 * Atribui um titulo para a janela
	 * 
	 * @param title
	 * @return this
	 */
	public GFrame title(String title) {
		this.title = title;
		if (ui()) {
			driver().setTitle(title);
		}
		return this;
	}

	/**
	 * Retorna o titulo da janela
	 * 
	 * @return titulo
	 */
	public String title() {
		return title;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Entry<String, Object>> attributes() {
		List<Entry<String, Object>> list = super.attributes();
		list.add(new AbstractMap.SimpleEntry<String, Object>("title", title));
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
		return string(this, "frame", level);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JFrame driver() {
		return (JFrame) super.driver();
	}

}
