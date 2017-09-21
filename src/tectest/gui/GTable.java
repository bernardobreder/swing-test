package tectest.gui;

import static tectest.util.GuiString.string;

import java.awt.Component;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import tectest.util.GApp;
import tectest.util.GColor;

/**
 * Implementação genérica do {@link JTable}
 * 
 * @author Bernardo Breder
 */
public class GTable extends GContainer {

	/** Modelo de dados */
	private GTableModel model;
	/** Editor de célula */
	private GTableRenderer renderer;
	/** Editor de célula */
	private GTableEditor editor;
	/** Modelo de seleção */
	private GTableSelection selection;
	/** Quantidade de linhas atuais */
	private int rowCount;
	/** Quantidade de colunas atuais */
	private int columnCount;
	/** Estrutura de dados das células atuais */
	private Object[][] cells;

	/**
	 * @param model
	 */
	public GTable(GTableModel model) {
		super(GApp.get().test() ? null : new JTable());
		model(model);
		renderer(new GDefaultTableRenderer());
	}

	/**
	 * Retorna a quantidade de linhas
	 * 
	 * @return quantidade de linhas
	 */
	public int rows() {
		return rowCount;
	}

	/**
	 * Retorna a quantidade de colunas
	 * 
	 * @return quantidade de colunas
	 */
	public int columns() {
		return columnCount;
	}

	/**
	 * Retorna o valor de uma celula
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 * @return valor de uma celula
	 */
	public Object value(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rowCount) {
			throw new IllegalArgumentException("row index " + rowIndex + " not between 0 and " + rowCount);
		}
		if (columnIndex < 0 || columnIndex >= columnCount) {
			throw new IllegalArgumentException("column index " + columnIndex + " not between 0 and " + columnCount);
		}
		if (cells[rowIndex][columnIndex] == null) {
			cells[rowIndex][columnIndex] = model.value(this, rowIndex, columnIndex);
		}
		return cells[rowIndex][columnIndex];
	}

	/**
	 * Retorna o editor de células
	 * 
	 * @param editor
	 * @return this
	 */
	public GTable editor(GTableEditor editor) {
		if (editor == null) {
			throw new IllegalArgumentException("editor is null");
		}
		this.editor = editor;
		return this;
	}

	/**
	 * Retorna o editor
	 * 
	 * @return editor
	 */
	public GTableEditor editor() {
		return editor;
	}

	/**
	 * Atribui o selecionador
	 * 
	 * @param selection
	 * @return this
	 */
	public GTable selection(GTableSelection selection) {
		if (selection == null) {
			throw new IllegalArgumentException("selection is null");
		}
		this.selection = selection;
		return this;
	}

	/**
	 * Retorna o selecionador
	 * 
	 * @return selecionador
	 */
	public GTableSelection selection() {
		return selection;
	}

	/**
	 * Atribui um renderizador
	 * 
	 * @param renderer
	 * @return this
	 */
	public GTable renderer(GTableRenderer renderer) {
		if (renderer == null) {
			throw new IllegalArgumentException("renderer is null");
		}
		this.renderer = renderer;
		if (ui()) {
			driver().setDefaultRenderer(Object.class, new AdapterTableCellRenderer());
		}
		return this;
	}

	/**
	 * Retorna o renderizador
	 * 
	 * @return renderizador
	 */
	public GTableRenderer renderer() {
		return renderer;
	}

	/**
	 * Dispara mudança de conteúdo de tabela mas não de quantidade de linhas e
	 * colunas
	 * 
	 * @return this
	 */
	public GTable fireDataChanged() {
		cells = new Object[rowCount][columnCount];
		if (ui()) {
			((AbstractTableModel) driver().getModel()).fireTableDataChanged();
		}
		return this;
	}

	/**
	 * Dispara mudança estrutural na tabela aonde houve mudança de linhas e
	 * colunas
	 * 
	 * @return this
	 */
	public GTable fireStructureChanged() {
		rowCount = model.rowCount(this);
		columnCount = model.columnCount(this);
		cells = new Object[rowCount][columnCount];
		if (ui()) {
			((AbstractTableModel) driver().getModel()).fireTableStructureChanged();
		}
		return this;
	}

	/**
	 * Atribui um modelo de dados
	 * 
	 * @param model
	 * @return this
	 */
	public GTable model(GTableModel model) {
		if (model == null) {
			throw new IllegalArgumentException("model is null");
		}
		this.model = model;
		rowCount = model.rowCount(this);
		columnCount = model.columnCount(this);
		cells = new Object[rowCount][columnCount];
		if (ui()) {
			driver().setModel(new AdapterTableModel());
		}
		return fireStructureChanged();
	}

	/**
	 * Retorna o modelo de dados
	 * 
	 * @return modelo de dados
	 */
	public GTableModel model() {
		return model;
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
	 * {@inheritDoc}
	 */
	@Override
	public List<GComponent> children() {
		List<GComponent> list = super.children();
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(int level) {
		return string(this, "table", level);
	}

	/**
	 * Retorna o driver
	 * 
	 * @return driver
	 */
	@Override
	public JTable driver() {
		return (JTable) super.driver();
	}

	/**
	 * Modelo de tabela que informa os dados.
	 */
	public static interface GTableModel {

		/**
		 * Retorna a quantidade de linhas que a tabela tem
		 * 
		 * @param table
		 *            tabela envolvida
		 * @return quantidade de linhas
		 */
		public int rowCount(GTable table);

		/**
		 * Retorna a quantidade de colunas que a tabela tem
		 * 
		 * @param table
		 *            tabela envolvida
		 * @return quantidade de colunas
		 */
		public int columnCount(GTable table);

		/**
		 * Retorna o nome das colunas
		 * 
		 * @param table
		 *            tabela envolvida
		 * @param columnIndex
		 *            indice da coluna
		 * @return quantidade de linhas
		 */
		public String column(GTable table, int columnIndex);

		/**
		 * Indica se a célula é editavel
		 * 
		 * @param table
		 *            tabela envolvida
		 * @param rowIndex
		 *            indice da linha
		 * @param columnIndex
		 *            indice da coluna
		 * @return célula é editavel
		 */
		public boolean cellEditable(GTable table, int rowIndex, int columnIndex);

		/**
		 * Retorna a estrutura de dados de uma célula
		 * 
		 * @param table
		 *            tabela envolvida
		 * @param rowIndex
		 *            indice da linha
		 * @param columnIndex
		 *            indice da coluna
		 * @return estrutura de dados da célula
		 */
		public Object value(GTable table, int rowIndex, int columnIndex);

		/**
		 * Atribui a estrutura de dados de uma célula
		 * 
		 * @param table
		 *            tabela envolvida
		 * @param rowIndex
		 *            indice da linha
		 * @param columnIndex
		 *            indice da coluna
		 * @param value
		 *            valor a ser atribuido
		 */
		public void value(GTable table, int rowIndex, int columnIndex, Object value);

	}

	/**
	 * Renderizador de uma estrutura de dados de uma célula
	 */
	public static interface GTableRenderer {

		/**
		 * Retorna o componente que renderiza a estrutura de dados de uma céluça
		 * 
		 * @param table
		 *            tabela envolvida
		 * @param rowIndex
		 *            indice da linha
		 * @param columnIndex
		 *            indice da coluna
		 * @param value
		 *            estrutura de dados do modelo
		 * @param selected
		 *            célula selecionado
		 * @return componente que renderiza a estrutura de dados
		 */
		public GComponent render(GTable table, int rowIndex, int columnIndex, Object value, boolean selected);

	}

	/**
	 * Renderizador padrão de celula
	 * 
	 * @author Bernardo Breder
	 */
	public static class GDefaultTableRenderer extends GLabel implements GTableRenderer {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GComponent render(GTable table, int rowIndex, int columnIndex, Object value, boolean selected) {
			if (selected) {
				background(GColor.TABLE_CELL_SELECTED);
			} else {
				background(GColor.WHITE);
			}
			text(value == null ? "" : value.toString());
			return this;
		}
	}

	/**
	 * Interface que define o editor de uma célula
	 * 
	 * @author Tecgraf
	 */
	public static interface GTableEditor {

		/**
		 * Retorna o editor correspondente a célula e valor
		 * 
		 * @param table
		 *            tabela envolvida
		 * @param value
		 *            valor da célula vindo do modelo atualizado
		 * @param isSelected
		 *            indica se a célula está selecionada
		 * @param rowIndex
		 *            indice da linha
		 * @param columnIndex
		 *            indice da coluna
		 * @return componente
		 */
		public GComponent editor(GTable table, Object value, boolean isSelected, int rowIndex, int columnIndex);

		/**
		 * Após o usuário digitar o conteúdo, será solicitado ao editor o valor
		 * a ser atribuido ao modelo.
		 * 
		 * @param table
		 *            tabela envolvida
		 * @param editor
		 *            editor da célula
		 * @return estrutura de dados do editor
		 */
		public Object value(GTable table, GComponent editor);

	}

	/**
	 * Interface que determina a seleção de uma tabela
	 * 
	 * @author Tecgraf
	 */
	public static interface GTableSelection {

		/**
		 * Remove todas as seleções antigas e seleciona uma célula
		 * 
		 * @param table
		 *            tabela envolvida
		 * @param rowIndex
		 *            indice da linha
		 * @param columnIndex
		 *            indice da coluna
		 */
		public void select(GTable table, int rowIndex, int columnIndex);

		/**
		 * Adiciona uma seleção de uma célula
		 * 
		 * @param table
		 *            tabela envolvida
		 * @param rowIndex
		 *            indice da linha
		 * @param columnIndex
		 *            indice da coluna
		 */
		public void add(GTable table, int rowIndex, int columnIndex);

		/**
		 * Remove uma seleção existente
		 * 
		 * @param table
		 *            tabela envolvida
		 * @param rowIndex
		 *            indice da linha
		 * @param columnIndex
		 *            indice da coluna
		 */
		public void remove(GTable table, int rowIndex, int columnIndex);

	}

	/**
	 * Modelo de tabela interna que implementa no swing o modelo de tabela do
	 * componente
	 * 
	 * @author Tecgraf
	 */
	private class AdapterTableModel extends AbstractTableModel {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return model.value(GTable.this, rowIndex, columnIndex);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int getRowCount() {
			return model.rowCount(GTable.this);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int getColumnCount() {
			return model.columnCount(GTable.this);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getColumnName(int columnIndex) {
			return model.column(GTable.this, columnIndex);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return model.cellEditable(GTable.this, rowIndex, columnIndex);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setValueAt(Object value, int rowIndex, int columnIndex) {
			model.value(GTable.this, rowIndex, columnIndex, value);
		}

	}

	/**
	 * Renderizador interno que implementa o renderizador do componente
	 * 
	 * @author Tecgraf
	 */
	private final class AdapterTableCellRenderer implements TableCellRenderer {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean hasFocus, int rowIndex, int columnIndex) {
			GComponent component = renderer.render(GTable.this, rowIndex, columnIndex, value, selected);
			if (component == null) {
				return null;
			}
			return component.driver();
		}

	}

}
