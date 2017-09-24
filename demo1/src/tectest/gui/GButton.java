package tectest.gui;

import static tectest.util.GuiString.string;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JButton;

import tectest.util.GApp;
import tectest.util.GEvent;

/**
 * Implementação genérica do {@link JButton}
 * 
 * @author Bernardo Breder
 */
public class GButton extends GComponent {

	/** Texto */
	private String text;
	/** Ação de clicar */
	private GButtonAction action;

	/**
	 * Constroi sem texto
	 */
	public GButton() {
		this("");
	}

	/**
	 * Constroi o botão
	 * 
	 * @param text
	 */
	public GButton(String text) {
		super(GApp.get().test() ? null : new JButton());
		text(text);
	}

	/**
	 * Atribui o texto do botão
	 * 
	 * @param text
	 * @return this
	 */
	public GButton text(String text) {
		this.text = text;
		if (ui()) {
			driver().setText(text);
		}
		return this;
	}

	/**
	 * Retorna o texto do botão
	 * 
	 * @return texto do botão
	 */
	public String text() {
		return text;
	}

	/**
	 * Atribui a ação do botão
	 * 
	 * @param action
	 * @return this
	 */
	public GButton action(final GButtonAction action) {
		this.action = action;
		if (ui()) {
			driver().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					action.action(GButton.this);
				}
			});
		}
		return this;
	}

	/**
	 * Retorna a ação do botão
	 * 
	 * @return ação do botão
	 */
	public GButtonAction action() {
		return action;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Entry<String, Object>> attributes() {
		List<Entry<String, Object>> list = super.attributes();
		if (action != null) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("action", action));
		}
		if (text != null) {
			list.add(new AbstractMap.SimpleEntry<String, Object>("title", text));
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(int level) {
		return string(this, "button", level);
	}

	/**
	 * Retorna o driver
	 * 
	 * @return driver
	 */
	@Override
	public JButton driver() {
		return (JButton) super.driver();
	}

	/**
	 * Ação de clicar no botão
	 * 
	 * @author Tecgraf
	 */
	public static interface GButtonAction extends GEvent {

		/**
		 * @param button
		 */
		public void action(GButton button);

	}

}
