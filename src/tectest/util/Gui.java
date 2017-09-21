package tectest.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.SwingUtilities;

import tectest.gui.GComponent;
import tectest.gui.GContainer;

/**
 * Classe utilitária de interface gráfica
 *
 * @author Bernardo Breder
 */
public abstract class Gui {

	/**
	 * Executa a tarefa na ETD e retorna o valor da execução.
	 *
	 * @param queue
	 * @return retorna do processamento
	 */
	public static <E> E executeInETD(final Query<E> queue) {
		final AtomicReference<E> ref = new AtomicReference<E>();
		executeAndWait(new Runnable() {
			@Override
			public void run() {
				ref.set(queue.execute());
			}
		});
		return ref.get();
	}

	/**
	 * Executa a tarefa na ETD e espera terminar a execução
	 *
	 * @param task
	 */
	public static void executeInETD(final Task task) {
		executeAndWait(new Runnable() {
			@Override
			public void run() {
				task.execute();
			}
		});
	}

	/**
	 * Realiza uma espera enquanto a condição retorna false
	 *
	 * @param condition
	 */
	public static void pause(Condition condition) {
		while (!condition.accept()) {
			pause(10);
		}
	}

	/**
	 * Realiza uma espera por um determinado milisegundos
	 *
	 * @param millis
	 */
	public static void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Executa o {@link Runnable} e não espera terminar a execução
	 *
	 * @param task
	 */
	public static void executeLater(final Task task) {
		if (GApp.get().test()) {
			task.execute();
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					task.execute();
				}
			});
		}
	}

	/**
	 * Executa o {@link Runnable} e espera terminar a execução
	 *
	 * @param runnable
	 */
	protected static void executeAndWait(Runnable runnable) {
		if (GApp.get().test()) {
			runnable.run();
		} else {
			try {
				SwingUtilities.invokeAndWait(runnable);
			} catch (Exception e) {
				throw new RuntimeException("unexpected error", e);
			}
		}
	}

	/**
	 * Clona um objetivo
	 *
	 * @param element
	 * @return objeto clonado
	 */
	public static <E> E cloneObject(E element) {
		try {
			Class<? extends Object> c = element.getClass();
			Object instance = c.newInstance();
			@SuppressWarnings("unchecked")
			E result = (E) instance;
			while (c != null) {
				Field[] fields = c.getDeclaredFields();
				for (Field field : fields) {
					if (!Modifier.isTransient(field.getModifiers())) {
						field.setAccessible(true);
						field.set(result, field.get(element));
					}
				}
				c = c.getSuperclass();
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Clona um componente
	 *
	 * @param element
	 * @return elemento clonado
	 */
	public static <E extends GComponent> E cloneComponent(E element) {
		E result = cloneObject(element);
		if (result.parent() != null) {
			result.parent(cloneComponent(result.parent()));
		}
		return result;
	}

	/**
	 * Busca em profundidade um componente
	 *
	 * @param target
	 * @param componentClass
	 * @return componente buscado ou nulo caso não ache
	 */
	public static <T extends GComponent> T find(GContainer target, Class<T> componentClass) {
		List<GComponent> children = target.children();
		for (int n = 0; n < children.size(); n++) {
			GComponent child = children.get(n);
			if (componentClass.isInstance(child)) {
				return componentClass.cast(child);
			}
			if (child instanceof GContainer) {
				GContainer container = (GContainer) child;
				T result = find(container, componentClass);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	/**
	 * Retorna um erro de tipo não esperado
	 *
	 * @return erro de tipo não esperado
	 */
	public static RuntimeException unexpectedTypeOf() {
		return new RuntimeException("unexpected type");
	}

	/**
	 * Interface da query
	 *
	 * @author Bernardo Breder
	 * @param <E>
	 */
	public static interface Query<E> {

		/**
		 * Realiza a execução da query e retorna a resposta
		 *
		 * @return resposta da query
		 */
		public E execute();

	}

	/**
	 * Interface da tarefa
	 *
	 * @author Bernardo Breder
	 */
	public static interface Task {

		/**
		 * Realiza o processamento da tarefa
		 */
		public void execute();

	}

	/**
	 * Interface de condição
	 *
	 * @author Bernardo Breder
	 */
	public static interface Condition {

		/**
		 * Retorna true caso a condição foi alcançada
		 *
		 * @return true caso a condição foi alcançada
		 */
		public boolean accept();

	}

	/**
	 * Indica se a thread corrente é a thread principal
	 *
	 * @return thread corrente é a thread principal
	 */
	public static boolean isMainThread() {
		return Thread.currentThread().getName().equals("main");
	}

}
