package tectest.gui;

import static tectest.util.Gui.cloneComponent;
import tectest.util.GFailure;

/**
 * Fixture generica de {@link GFrame}
 * 
 * @author Bernardo Breder
 */
public class GFrameFixture extends GWindowFixture {

	/**
	 * @param target
	 */
	public GFrameFixture(GFrame target) {
		super(target);
	}

	/**
	 * @param title
	 * @return this
	 */
	public GFrameFixture requireTitle(String title) {
		if (!target().title().equals(title)) {
			throw new GFailure(cloneComponent(target()).title(title), target());
		}
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GFrame target() {
		return (GFrame) super.target();
	}

}
