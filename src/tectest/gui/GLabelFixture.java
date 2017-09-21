package tectest.gui;

import static tectest.util.Gui.cloneComponent;

import javax.swing.JLabel;

import tectest.util.GFailure;

/**
 * Fixture generica de {@link JLabel}
 * 
 * @author Bernardo Breder
 */
public class GLabelFixture extends GComponentFixture {

	/**
	 * @param target
	 */
	public GLabelFixture(GLabel target) {
		super(target);
	}

	/**
	 * @param expectedText
	 * @return this
	 */
	public GLabelFixture requireText(String expectedText) {
		if (!target().text().equals(expectedText)) {
			throw new GFailure(cloneComponent(target()).text(expectedText), target());
		}
		return this;
	}


	/**
	 * @return driver
	 */
	@Override
	public GLabel target() {
		return (GLabel) super.target();
	}

}
