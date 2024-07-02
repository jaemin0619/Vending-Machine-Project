package Action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import Can.CanArray;
import Action.PutMoney;
import Machine.MachinePanelRight;
import Person.Admin;

// ���� �߰� Ŭ����

public class ActionAddCan implements ActionListener {

	JFrame frame;
	DefaultTableModel canModel;
	List<JCheckBox> checkList;
	List<JComboBox> selectList;
	List<JButton> blist;
	JButton chechAll;
	
	// ���� �߰� Ŭ���� ������
	public ActionAddCan(JFrame frame, JButton chechAll, List<JCheckBox> checkList, List<JComboBox> selectList){
		super();
		this.frame = frame;
		this.checkList = checkList;
		this.selectList = selectList; 
		this.chechAll=chechAll;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		// ���� ��� �߰�
		canModel = (DefaultTableModel)MachinePanelRight.canTable.getModel();
		if (canModel.getRowCount() != 0) {
			canModel.setRowCount(0);
		}
		
		for(int i=0; i<checkList.size();i++){
			if(checkList.get(i).isSelected()){
				if(selectList.get(i).getSelectedItem().equals("10��")){
					// 5�� ��ư ������ ��� ��� 5�� �߰�
					CanArray.canList.get(i).setCanNum(CanArray.canList.get(i).getCanNum() + 10);
				} // 10�� ��ư ������ ��� ��� 10�� �߰�
				else if(selectList.get(i).getSelectedItem().equals("15��")){
					CanArray.canList.get(i).setCanNum(CanArray.canList.get(i).getCanNum() + 15);
				} // 20�� ��ư ������ ��� ��� 20�� �߰�
				else if(selectList.get(i).getSelectedItem().equals("20��")){
					CanArray.canList.get(i).setCanNum(CanArray.canList.get(i).getCanNum() + 20);
				}
				checkList.get(i).setSelected(false);
				MachinePanelRight.totalMoneyLabel.setText("�� ����� : " + Admin.getTotalMoney());
				
			}
		}
		
		for(int i=0;i<selectList.size();i++){
			selectList.get(i).setVisible(false);
			chechAll.setLabel("��ü10���߰�");
		}
		

		for (int i=0; i<CanArray.canList.size(); i++) {
String arr[] = { CanArray.canList.get(i).getCanName(), Integer.toString
		(CanArray.canList.get(i).getCanNum()),
					Integer.toString(CanArray.canList.get(i).getCanPrice()) };
			canModel.addRow(arr);
		}

		frame.dispose();
	}

}
