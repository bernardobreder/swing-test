package tectest.gui;

import javax.swing.JScrollPane;

/**
 * Fixture generica de {@link JScrollPane}
 * 
 * @author Bernardo Breder
 */
public class GScrollPaneFixture extends GContainerFixture {

	/**
	 * @param target
	 */
	public GScrollPaneFixture(GScrollPane target) {
		super(target);
	}

	/**
	 * @return driver
	 */
	@Override
	public GScrollPane target() {
		return (GScrollPane) super.target();
	}

}
