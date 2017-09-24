package tectest;

import org.junit.After;
import org.junit.Before;

import tectest.util.GApp;

/**
 * Classe base de teste
 *
 * @author Bernardo Breder
 */
public class GTest {

	/** Ambiente grafico */
	protected GApp app;

	/**
	 * Inicializa todas as variáveis do framework grafico falso
	 */
	@Before
	public void beforeGTest() {
		app = new GApp(true);
	}

	/**
	 * Finaliza todas as variáveis de ambiente
	 */
	@After
	public void afterGTest() {
		app.close();
	}

}
