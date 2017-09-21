package tectest.gui;

import static tectest.util.Gui.cloneComponent;

import javax.swing.JButton;

import tectest.util.GFailure;

/**
 * Fixture generica de {@link JButton}
 * 
 * @author Bernardo Breder
 */
public class GButtonFixture extends GComponentFixture {

	/**
	 * @param target
	 */
	public GButtonFixture(GButton target) {
		super(target);
	}

	/**
	 * Verifica o texto do botão
	 * 
	 * @param expectedText
	 * @return this
	 */
	public GButtonFixture requireText(String expectedText) {
		if (!target().text().equals(expectedText)) {
			throw new GFailure(cloneComponent(target()).text(expectedText), target());
		}
		return this;
	}

	/**
	 * Clica no botão
	 * 
	 * @return this
	 */
	public GButtonFixture click() {
		if (target().action() != null) {
			target().action().action(target());
		}
		return this;
	}

	/**
	 * @return driver
	 */
	@Override
	public GButton target() {
		return (GButton) super.target();
	}

}
