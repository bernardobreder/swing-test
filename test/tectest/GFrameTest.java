package tectest;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

import tectest.gui.GButton;
import tectest.gui.GButton.GButtonAction;
import tectest.gui.GFrame;
import tectest.gui.GFrameFixture;
import tectest.gui.GScrollPane;
import tectest.gui.GTable;
import tectest.gui.GTable.GTableModel;
import tectest.util.GApp;
import tectest.util.GWorker;
import tectest.util.Gui;
import tectest.util.Gui.Task;

public class GFrameTest extends GTest {

	/**
	 * Testador de titulo de janela
	 */
	@Test
	public void testFrameTitle() {
		GFrame frame = new GFrame().title("Titulo").visible(true);
		GFrameFixture frameFixture = new GFrameFixture(frame);
		frameFixture.requireTitle("Titulo");
		frameFixture.close();
		frameFixture.requireClosed();
	}

	/**
	 * Testador de click no botão
	 */
	@Test
	public void testButtonAction() {
		final AtomicInteger flag = new AtomicInteger(0);
		final GFrame frame = new GFrame().layout().center(new GButton("Ok").action(new GButtonAction() {
			@Override
			public void action(GButton button) {
				flag.incrementAndGet();
				button.text("Finished!");
			}
		})).frame().visible(true).frame();
		GFrameFixture frameFixture = new GFrameFixture(frame);
		frameFixture.button().requireText("Ok");
		frameFixture.button().click();
		frameFixture.button().requireText("Finished");
		Assert.assertEquals(1, flag.get());
	}

	/**
	 * Testador de leitura de tabela
	 */
	@Test
	public void testTable() {
		GFrame frame = new GFrame().layout().center(new GTable(new GTableModelSample())).frame().visible(true).frame();
		GFrameFixture frameFixture = new GFrameFixture(frame);
		frameFixture.table().requireValue(0, 0, "0x0");
		frameFixture.table().requireValue(0, 1, "0x1");
		frameFixture.table().requireValue(0, 2, "0x2");
		frameFixture.table().requireValue(0, 3, "0x3");
		frameFixture.table().requireValue(0, 4, "0x4");
		frameFixture.table().requireValue(1, 0, "1x0");
		frameFixture.table().requireValue(1, 1, "1x1");
		frameFixture.table().requireValue(1, 2, "1x2");
		frameFixture.table().requireValue(1, 3, "1x3");
		frameFixture.table().requireValue(1, 4, "1x4");
		frameFixture.table().requireValue(2, 0, "2x0");
		frameFixture.table().requireValue(2, 1, "2x1");
		frameFixture.table().requireValue(2, 2, "2x2");
		frameFixture.table().requireValue(2, 3, "2x3");
		frameFixture.table().requireValue(2, 4, "2x4");
		frameFixture.table().requireValue(3, 0, "3x0");
		frameFixture.table().requireValue(3, 1, "3x1");
		frameFixture.table().requireValue(3, 2, "3x2");
		frameFixture.table().requireValue(3, 3, "3x3");
		frameFixture.table().requireValue(3, 4, "3x4");
		frameFixture.table().requireValue(4, 0, "4x0");
		frameFixture.table().requireValue(4, 1, "4x1");
		frameFixture.table().requireValue(4, 2, "4x2");
		frameFixture.table().requireValue(4, 3, "4x3");
		frameFixture.table().requireValue(4, 4, "4x4");
	}

	/**
	 * Sample de modelo de tabela
	 *
	 * @author Bernardo Breder
	 */
	private static final class GTableModelSample implements GTableModel {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void value(GTable table, int rowIndex, int columnIndex, Object value) {
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object value(GTable table, int rowIndex, int columnIndex) {
			return "" + rowIndex + "x" + columnIndex;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int rowCount(GTable table) {
			return 5;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int columnCount(GTable table) {
			return 5;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String column(GTable table, int columnIndex) {
			return "" + (char) ('A' + columnIndex);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean cellEditable(GTable table, int rowIndex, int columnIndex) {
			return true;
		}
	}

	/**
	 * Aplicativo do teste
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		new GApp(true);
		Gui.executeInETD(new Task() {
			@Override
			public void execute() {
				new GFrame().layout().north(new GButton("Click").action(new GButtonAction() {
					@Override
					public void action(final GButton button) {
						new GWorker() {
							@Override
							protected void perform() throws Throwable {
								Gui.pause(1000);
							};

							@Override
							protected void updateUI() {
								button.frame().close();
							};
						}.start();
					}
				})).center(new GScrollPane(new GTable(new GTableModelSample()))).frame().size(800, 600).frame().location(null).visible(true).cast(GFrame.class);
			}
		});
	}
}
