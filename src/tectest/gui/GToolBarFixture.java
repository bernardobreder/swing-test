package tectest.gui;

import javax.swing.JToolBar;

/**
 * Fixture generica de {@link JToolBar}
 * 
 * @author Bernardo Breder
 */
public class GToolBarFixture extends GComponentFixture {

	/**
	 * @param target
	 */
	public GToolBarFixture(GToolBar target) {
		super(target);
	}

	/**
	 * @return driver
	 */
	@Override
	public GToolBar target() {
		return (GToolBar) super.target();
	}

}
