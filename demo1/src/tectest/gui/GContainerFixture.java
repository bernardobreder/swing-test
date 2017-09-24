package tectest.gui;

import static tectest.util.Gui.find;

import java.awt.Container;

/**
 * Fixture generica de {@link Container}
 * 
 * @author Bernardo Breder
 */
public abstract class GContainerFixture extends GComponentFixture {

	/**
	 * @param target
	 */
	protected GContainerFixture(GContainer target) {
		super(target);
	}

	/**
	 * Busca pelo botão
	 * 
	 * @return fixture do botão
	 */
	public GButtonFixture button() {
		return new GButtonFixture(find(target(), GButton.class));
	}

	/**
	 * Busca pela tabela
	 * 
	 * @return tabela
	 */
	public GTableFixture table() {
		return new GTableFixture(find(target(), GTable.class));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GContainer target() {
		return (GContainer) super.target();
	}

}
