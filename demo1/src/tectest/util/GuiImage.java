package tectest.util;

import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classe de imagem
 * 
 * @author Tecgraf
 */
public abstract class GuiImage {

	/** Mapa de imagens */
	private static final Map<String, Image> map = new TreeMap<String, Image>();

	/**
	 * Cadastra uma imagem com um caminho
	 * 
	 * @param path
	 * @param image
	 */
	public static void image(String path, Image image) {
		map.put(path, image);
	}

	/**
	 * Retorna uma imagem baseado num caminho
	 * 
	 * @param path
	 * @return imagem
	 */
	public static Image image(String path) {
		return map.get(path);
	}

	/**
	 * Limpa todas as imagens cadastradas
	 */
	public static void clear() {
		map.clear();
	}

}
