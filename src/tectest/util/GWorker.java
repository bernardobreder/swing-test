package tectest.util;

import tectest.util.Gui.Task;

/**
 * Classe que simula o comportamento de um SwingWorker
 */
public abstract class GWorker implements Runnable {

	/** Indica se a tarefa foi cancelada */
	private boolean cancel;
	/** Thread em segundo plano */
	private Thread thread;

	/**
	 * Executa a tarefa
	 */
	@Override
	public void run() {
		cancel = false;
		try {
			Gui.executeLater(new Task() {
				@Override
				public void execute() {
					initUI();
				}
			});
			if (cancel) {
				return;
			}
			perform();
			if (cancel) {
				return;
			}
			Gui.executeLater(new Task() {
				@Override
				public void execute() {
					updateUI();
				}
			});
		} catch (final Throwable t) {
			Gui.executeLater(new Task() {
				@Override
				public void execute() {
					handlerUI(t);
				}
			});
		} finally {
			Gui.executeLater(new Task() {
				@Override
				public void execute() {
					doneUI();
				}
			});
		}
	}

	/**
	 * Ação de inicialização
	 */
	protected void initUI() {
		if (GApp.get().test()) {

		}
	}

	/**
	 * Ação que irá acontecer em segundo plano
	 *
	 * @throws Throwable
	 */
	protected void perform() throws Throwable {

	}

	/**
	 * Ação que irá acontecer depois de ter terminado sem erro ação em segundo
	 * plano e que será executado na thread do inteface gráfica
	 */
	protected void updateUI() {

	}

	/**
	 * Ação que irá acontecer caso a ação em segundo plano ocorra um erro
	 *
	 * @param t
	 */
	protected void handlerUI(Throwable t) {

	}

	/**
	 * Ação que irá acontecer quando terminar a tarefa independente se houve
	 * erro ou não
	 */
	protected void doneUI() {

	}

	/**
	 * Indica que a tarefa deve ser finalizada
	 *
	 * @return this
	 */
	public GWorker cancel() {
		cancel = true;
		return this;
	}

	/**
	 * Indica se a tarefa foi cancelada
	 *
	 * @return cancelada
	 */
	public boolean cancelled() {
		return cancel;
	}

	/**
	 * Espera finalizar a execução
	 *
	 * @return this
	 */
	public GWorker join() {
		return this;
	}

	/**
	 * Inicia o worker
	 *
	 * @return this
	 */
	public GWorker start() {
		if (GApp.get().test()) {
			run();
		} else {
			thread = new Thread(this);
			thread.start();
		}
		return this;
	}

	/**
	 * Realiza o cast para uma classe
	 *
	 * @param c
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	public <E extends GWorker> E cast(Class<E> c) {
		return (E) this;
	}

}
