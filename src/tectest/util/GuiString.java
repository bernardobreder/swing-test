package tectest.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import tectest.gui.GComponent;
import tectest.gui.GContainer;

/**
 * Classe utilitária para string
 * 
 * @author Tecgraf
 */
public abstract class GuiString {

	/**
	 * Constroi uma string baseado no componente e seu nome
	 * 
	 * @param c
	 * @param name
	 * @param level
	 * @return string
	 */
	public static String string(GComponent c, String name, int level) {
		StringBuilder sb = new StringBuilder();
		sb.append(level(level));
		sb.append(name);
		sb.append(openAttribute());
		List<Entry<String, Object>> attributes = c.attributes();
		for (int n = attributes.size() - 1; n >= 0; n--) {
			Entry<String, Object> attribute = attributes.get(n);
			sb.append(objectAttribute(attribute.getKey(), attribute.getValue()));
			if (attributes.size() > 0 && n != 0) {
				sb.append(separatorAttribute());
			}
		}
		sb.append(closeAttribute());
		if (c instanceof GContainer) {
			sb.append(openBody());
			GContainer container = (GContainer) c;
			List<GComponent> children = container.children();
			for (GComponent child : children) {
				sb.append(child.cast(GComponent.class).toString(level + 1));
			}
			sb.append(closeBody());
		}
		sb.append(closeElement());
		return sb.toString();
	}

	/**
	 * Constroi uma string de alinhamento
	 * 
	 * @param level
	 * @return string de alinhamento
	 */
	public static String level(int level) {
		StringBuilder sb = new StringBuilder(level);
		for (int n = 0; n < level; n++) {
			sb.append('\t');
		}
		return sb.toString();
	}

	/**
	 * Retorna uma string que caracteriza um par chave e valor
	 * 
	 * @param key
	 * @param value
	 * @return string
	 */
	public static String objectAttribute(String key, Object value) {
		if (value instanceof Date) {
			Date dateValue = (Date) value;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateValue);
			if (calendar.get(Calendar.SECOND) != 0 || calendar.get(Calendar.MINUTE) != 0 || calendar.get(Calendar.HOUR_OF_DAY) != 0) {
				return stringAttribute(key, new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(dateValue));
			} else {
				return stringAttribute(key, new SimpleDateFormat("yyyy/MM/dd").format(dateValue));
			}
		} else if (value instanceof Double || value instanceof Float) {
			double doubleValue = ((Number) value).doubleValue();
			NumberFormat format = NumberFormat.getIntegerInstance();
			format.setMaximumFractionDigits(3);
			return stringAttribute(key, format.format(doubleValue));
		} else if (value instanceof GEvent) {
			return stringAttribute(key, "(...)");
		} else {
			return stringAttribute(key, value.toString());
		}
	}

	/**
	 * Constroi uma string de par chave e valor
	 * 
	 * @param key
	 * @param value
	 * @return string
	 */
	public static String stringAttribute(String key, String value) {
		StringBuilder sb = new StringBuilder(key.length() + value.length() + 3);
		sb.append(key);
		sb.append('=');
		sb.append('\"');
		sb.append(value);
		sb.append('\"');
		return sb.toString();
	}

	/**
	 * Separator de atributo
	 * 
	 * @return string
	 */
	public static String separatorAttribute() {
		return " ";
	}

	/**
	 * Abertura de atributo
	 * 
	 * @return string
	 */
	public static String openAttribute() {
		return " (";
	}

	/**
	 * Fechamento de atributo
	 * 
	 * @return string
	 */
	public static String closeAttribute() {
		return ")";
	}

	/**
	 * Abertura de corpo
	 * 
	 * @return string
	 */
	public static String openBody() {
		return " {\n";
	}

	/**
	 * Fechamento de corpo
	 * 
	 * @return string
	 */
	public static String closeBody() {
		return "}";
	}

	/**
	 * Fechamento de elemento
	 * 
	 * @return string
	 */
	public static String closeElement() {
		return "\n";
	}

}
