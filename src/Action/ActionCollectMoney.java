package Action;

// ���� Ŭ����
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.util.regex.Pattern;

import Machine.MachineFrame;
import Machine.MachinePanelRight;
import Machine.MachineMain;
import Person.Admin;

public class ActionCollectMoney implements ActionListener{
	JTextField money;
	// ���� Ŭ���� ������
	public ActionCollectMoney(JTextField money) {
		super();
		this.money = money;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int Money = Integer.parseInt(money.getText());  // int������ ��ȯ

		if (Money <= Admin.getTotalMoney()) {
			// ������ �ݾ��� �Ѿ׺��� ������ ���� ����, �Ѿ׿��� -
			Admin.setTotalMoney(Admin.getTotalMoney() - Money);
			MachinePanelRight.totalMoneyLabel.setText("�� ����� : " + Admin.getTotalMoney());
			JOptionPane.showMessageDialog(new JFrame(), Money + "���� �����߽��ϴ�.");
			
		} 
		
		else {
			// ������ �ݾ��� �Ѿ׺��� ������ ���� �Ұ���
			JOptionPane.showMessageDialog(new JFrame(), "�ʰ��� �ݾ��� ������ �� �����ϴ�.\n "
					+ "���ݰ��ɱݾ� : " + Admin.getTotalMoney() + "��");
			
		}
	}
}
