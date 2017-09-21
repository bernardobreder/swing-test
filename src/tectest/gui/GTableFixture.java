package tectest.gui;

import org.junit.Assert;

/**
 * Fixture generica de {@link GTable}
 * 
 * @author Bernardo Breder
 */
public class GTableFixture extends GContainerFixture {

	/**
	 * @param target
	 */
	public GTableFixture(GTable target) {
		super(target);
	}

	/**
	 * @return driver
	 */
	@Override
	public GTable target() {
		return (GTable) super.target();
	}

	/**
	 * Verifica um valor
	 *
	 * @param rowIndex
	 * @param columnIndex
	 * @param expectedValue
	 * @return this
	 */
	public GTableFixture requireValue(int rowIndex, int columnIndex, String expectedValue) {
		Object valueAt = target().value(rowIndex, columnIndex);
		GComponent render = target().renderer().render(target(), rowIndex, columnIndex, valueAt, false);
		if (render instanceof GLabel) {
			GLabel label = (GLabel) render;
			Assert.assertEquals(expectedValue, label.text());
		} else {
			Assert.assertEquals(expectedValue, "");
		}
		return this;
	}

}
