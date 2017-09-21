package tectest.util;

import java.awt.FlowLayout;

/**
 * Estrutura que enumera os alinhamentos
 * 
 * @author Tecgraf
 */
public enum GAlign {

	/**
	 * This value indicates that each row of components should be
	 * left-justified.
	 */
	LEFT(FlowLayout.LEFT),

	/**
	 * This value indicates that each row of components should be centered.
	 */
	CENTER(FlowLayout.CENTER),

	/**
	 * This value indicates that each row of components should be
	 * right-justified.
	 */
	RIGHT(FlowLayout.RIGHT),

	/**
	 * This value indicates that each row of components should be justified to
	 * the leading edge of the container's orientation, for example, to the left
	 * in left-to-right orientations.
	 */
	LEADING(FlowLayout.LEADING),

	/**
	 * This value indicates that each row of components should be justified to
	 * the trailing edge of the container's orientation, for example, to the
	 * right in left-to-right orientations.
	 */
	TRAILING(FlowLayout.TRAILING);

	/** Código do alinhamnto */
	private int code;

	/**
	 * @param code
	 */
	private GAlign(int code) {
		this.code = code;
	}

	/**
	 * Construtor baseado no código
	 * 
	 * @param code
	 * @return alinhamento
	 */
	public static GAlign valueOf(int code) {
		switch (code) {
		case FlowLayout.LEFT:
			return LEFT;
		case FlowLayout.CENTER:
			return CENTER;
		case FlowLayout.RIGHT:
			return RIGHT;
		case FlowLayout.LEADING:
			return LEADING;
		case FlowLayout.TRAILING:
			return TRAILING;
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Retorna o código
	 * 
	 * @return código
	 */
	public int code() {
		return code;
	}

}