package tectest.util;

import org.junit.ComparisonFailure;

import tectest.gui.GComponent;

/**
 * Classe de erro de teste
 * 
 * @author Tecgraf
 */
public class GFailure extends ComparisonFailure {

	/**
	 * @param expected
	 * @param actual
	 */
	public GFailure(GComponent expected, GComponent actual) {
		super("", expected.toString(), actual.toString());
	}

}
