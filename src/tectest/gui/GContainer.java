package tectest.gui;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Componente base pai de outros
 * 
 * @author Tecgraf
 */
public abstract class GContainer extends GComponent {

	/**
	 * @param driver
	 */
	protected GContainer(Container driver) {
		super(driver);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Entry<String, Object>> attributes() {
		List<Entry<String, Object>> list = super.attributes();
		return list;
	}

	/**
	 * Retorna os filhos do componente
	 * 
	 * @return filhos do componente
	 */
	public List<GComponent> children() {
		List<GComponent> list = new ArrayList<GComponent>(10);
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Container driver() {
		return (Container) super.driver();
	}

}
