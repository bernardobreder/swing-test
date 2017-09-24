package tectest.gui;

import static tectest.util.GuiString.string;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import tectest.util.GAlign;
import tectest.util.GApp;
import tectest.util.GuiImage;

/**
 * Implementação genérica do {@link JLabel}
 * 
 * @author Bernardo Breder
 */
public class GLabel extends GComponent {

	/** Texto */
	private String text;
	/** Imagem */
	private String image;
	/** Alinhamento */
	private GAlign align;

	/**
	 * Construtor
	 */
	public GLabel() {
		this("");
	}

	/**
	 * @param text
	 */
	public GLabel(String text) {
		super(GApp.get().test() ? null : new JLabel(text));
		text(text);
	}

	/**
	 * Altera o texto do label
	 * 
	 * @param text
	 * @return this
	 */
	public GLabel text(String text) {
		this.text = text;
		if (ui()) {
			driver().setText(text);
		}
		return this;
	}

	/**
	 * Altera o texto
	 * 
	 * @return texto
	 */
	public String text() {
		return text;
	}

	/**
	 * Altera a imagem do label
	 * 
	 * @param image
	 * @return this
	 */
	public GLabel image(String image) {
		this.image = image;
		if (ui()) {
			driver().setIcon(new ImageIcon(GuiImage.image(image)));
		}
		return this;
	}

	/**
	 * Retorna a imagem
	 * 
	 * @return caminho da imagem
	 */
	public String image() {
		return image;
	}

	/**
	 * Altera o alinhamento do label
	 * 
	 * @param align
	 * @return alinhamento do label
	 */
	public GLabel align(GAlign align) {
		this.align = align;
		if (ui()) {
			driver().setHorizontalAlignment(align.code());
		}
		return this;
	}

	/**
	 * Retorna o alinhamento
	 * 
	 * @return alinhamento
	 */
	public GAlign align() {
		return align;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Entry<String, Object>> attributes() {
		List<Entry<String, Object>> list = super.attributes();
		if (text != null) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("title", text));
		}
		if (image != null) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("image", image));
		}
		// TODO Align attribute
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(int level) {
		return string(this, "label", level);
	}

	/**
	 * Retorna o driver
	 * 
	 * @return driver
	 */
	@Override
	public JLabel driver() {
		return (JLabel) super.driver();
	}

}
