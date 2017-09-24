package tectest.gui;

import javax.swing.JComponent;

/**
 * Fixture generica de {@link JComponent}
 * 
 * @author Bernardo Breder
 */
public abstract class GComponentFixture {

	/** Driver da fixture */
	private GComponent target;

	/**
	 * @param target
	 */
	protected GComponentFixture(GComponent target) {
		this.target = target;
	}

	/**
	 * Retorna com cast
	 * 
	 * @param c
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	public <T extends GComponentFixture> T cast(Class<T> c) {
		return (T) this;
	}

	/**
	 * @return driver
	 */
	public GComponent target() {
		return target;
	}

}
