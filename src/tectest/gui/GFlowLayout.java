package tectest.gui;

import static tectest.util.GuiString.string;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JPanel;

import tectest.util.GAlign;
import tectest.util.GApp;

/**
 * Implementação genérica do {@link GFlowLayout}
 * 
 * @author Bernardo Breder
 */
public class GFlowLayout extends GLayout {

	/** Filhos */
	private List<GComponent> children;
	/** Alinhamento */
	private GAlign align;

	/**
	 * Construtor
	 */
	public GFlowLayout() {
		this(GAlign.CENTER, 5, 5);
	}

	/**
	 * Construtor
	 * 
	 * @param align
	 * @param hgap
	 * @param vgap
	 */
	public GFlowLayout(GAlign align, int hgap, int vgap) {
		super(GApp.get().test() ? null : new JPanel(new FlowLayout(align.code(), hgap, vgap)));
		children = new ArrayList<GComponent>();
		align(align).hgap(hgap).vgap(vgap);
	}

	/**
	 * Adiciona um componente
	 * 
	 * @param component
	 * @return this
	 */
	public GFlowLayout add(GComponent component) {
		children.add(component);
		if (ui()) {
			driver().add(component.driver());
		}
		return this;
	}

	/**
	 * Atribui um alinhamento
	 * 
	 * @param align
	 * @return this
	 */
	public GFlowLayout align(GAlign align) {
		this.align = align;
		if (ui()) {
			layout().setAlignment(align.code());
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
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GComponent> children() {
		List<GComponent> list = super.children();
		list.addAll(children);
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(int level) {
		return string(this, "flowlayout", level);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FlowLayout layout() {
		return (FlowLayout) super.layout();
	}

}
