package tectest.gui;

import static tectest.util.Gui.cloneComponent;

import javax.swing.JWindow;

import tectest.util.GFailure;

/**
 * Fixture generica de {@link JWindow}
 * 
 * @author Bernardo Breder
 */
public abstract class GWindowFixture extends GContainerFixture {

	/**
	 * @param target
	 */
	protected GWindowFixture(GWindow target) {
		super(target);
	}

	/**
	 * Fecha a janela
	 */
	public void close() {
		target().close();
	}

	/**
	 * @return this
	 */
	public GWindowFixture requireClosed() {
		if (target().visible()) {
			throw new GFailure(cloneComponent(target()).close(), target());
		}
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GWindow target() {
		return (GWindow) super.target();
	}

}
