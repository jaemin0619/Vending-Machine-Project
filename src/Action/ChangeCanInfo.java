package Action;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Can.Can;
import Can.CanArray;

public class ChangeCanInfo {
    private JTable canTable;

    public ChangeCanInfo(JTable canTable) {
        this.canTable = canTable;
    }

    public void changeCanInfo(int rowIndex, String newName, int newPrice) {
        DefaultTableModel model = (DefaultTableModel) canTable.getModel();
        if (rowIndex >= 0 && rowIndex < model.getRowCount()) {
            Can can = CanArray.canList.get(rowIndex);
            can.setCanName(newName);
            can.setCanPrice(newPrice);
            model.setValueAt(newName, rowIndex, 0);
            model.setValueAt(newPrice, rowIndex, 2);
        } else {
JOptionPane.showMessageDialog(null, "음료 정보 변경에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
