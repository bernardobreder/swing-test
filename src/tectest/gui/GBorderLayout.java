package tectest.gui;

import static tectest.util.GuiString.string;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import tectest.util.GApp;

/**
 * Implementação genérica do {@link GBorderLayout}
 * 
 * @author Bernardo Breder
 */
public class GBorderLayout extends GLayout {

	/** Componente no centro */
	private GComponent center;
	/** Componente no teto */
	private GComponent north;
	/** Componente no chão */
	private GComponent south;
	/** Componente a esquerda */
	private GComponent left;
	/** Componente a direita */
	private GComponent right;

	/**
	 * Construtor
	 */
	public GBorderLayout() {
		this(5, 5);
	}

	/**
	 * @param hgap
	 * @param vgap
	 */
	public GBorderLayout(int hgap, int vgap) {
		super(GApp.get().test() ? null : new JPanel(new BorderLayout(hgap, vgap)));
	}

	/**
	 * Atribui um componente no centro
	 * 
	 * @param component
	 * @return this
	 */
	public GBorderLayout center(GComponent component) {
		center = component;
		component.parent(this);
		if (ui()) {
			driver().add(component.driver(), BorderLayout.CENTER);
		}
		return this;
	}

	/**
	 * Retorna o componente do centro
	 * 
	 * @return componente do centro
	 */
	public GComponent center() {
		return center;
	}

	/**
	 * Atribui um componente no teto
	 * 
	 * @param component
	 * @return this
	 */
	public GBorderLayout north(GComponent component) {
		north = component;
		component.parent(this);
		if (ui()) {
			driver().add(component.driver(), BorderLayout.NORTH);
		}
		return this;
	}

	/**
	 * Retorna o componente do teto
	 * 
	 * @return componente do centro
	 */
	public GComponent north() {
		return north;
	}

	/**
	 * Atribui um componente de chão
	 * 
	 * @param component
	 * @return this
	 */
	public GBorderLayout south(GComponent component) {
		south = component;
		component.parent(this);
		if (ui()) {
			driver().add(component.driver(), BorderLayout.SOUTH);
		}
		return this;
	}

	/**
	 * Retorna o componente do chão
	 * 
	 * @return componente do centro
	 */
	public GComponent south() {
		return south;
	}

	/**
	 * Atribui um componente a esquerda
	 * 
	 * @param component
	 * @return this
	 */
	public GBorderLayout left(GComponent component) {
		left = component;
		component.parent(this);
		if (ui()) {
			driver().add(component.driver(), BorderLayout.WEST);
		}
		return this;
	}

	/**
	 * Retorna o componente a esquerda
	 * 
	 * @return componente do centro
	 */
	public GComponent left() {
		return left;
	}

	/**
	 * Atribui um componente a direita
	 * 
	 * @param component
	 * @return this
	 */
	public GBorderLayout right(GComponent component) {
		right = component;
		component.parent(this);
		if (ui()) {
			driver().add(component.driver(), BorderLayout.EAST);
		}
		return this;
	}

	/**
	 * Retorna o componente a direita
	 * 
	 * @return componente do centro
	 */
	public GComponent right() {
		return right;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GComponent> children() {
		List<GComponent> list = super.children();
		if (center != null) {
			list.add(center);
		}
		if (north != null) {
			list.add(north);
		}
		if (south != null) {
			list.add(south);
		}
		if (left != null) {
			list.add(left);
		}
		if (right != null) {
			list.add(right);
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(int level) {
		return string(this, "borderlayout", level);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BorderLayout layout() {
		return (BorderLayout) super.layout();
	}

}
