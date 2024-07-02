package Action;

// �ݾ� ���� Ŭ���� 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Can.CanArray;
import Machine.MachinePanelRight;
import Person.Admin;

public class PutMoney implements ActionListener {

	JTextField putMoneytext, takeMoneytext;
	List<JButton> blist;
	int billCount; // ������ 1000�� ���� ������ �����ϴ� ����

	// �ݾ� ���� Ŭ���� ������
	public PutMoney(JTextField putMoneytext, JTextField takeMoneytext, List<JButton> blist) {
		super();
		this.putMoneytext = putMoneytext;
		this.takeMoneytext = takeMoneytext;
		this.blist = blist;
		this.billCount = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean pattern = true;
		// ���Խ� ���ǹ�
		if (putMoneytext.getText().equals("")) {
			JOptionPane.showMessageDialog(new JFrame(), "���� ���� �ʾҽ��ϴ�.");
		} else if (Pattern.matches("^[0-9]*$", putMoneytext.getText())) {
			
			int money = Integer.parseInt(putMoneytext.getText());
if(money!=10&&money!=50&&money!=100&&money!=500&&money!=1000)
{
	JOptionPane.showMessageDialog(new JFrame(),"�ùٸ� ȭ�� ������ �Է��ϼ���.");
	return;
}
			if (money == 1000) {
				if (billCount >= 3) { // 1000�� ���� ���� ���� ����(3��)�� �ʰ��� ���
					JOptionPane.showMessageDialog(new JFrame(), "1000��¥���� 3�常 ���� �� �ֽ��ϴ�.");
					return;
				} else {
					billCount++;
				}
			} else if (money < 100 || money > 5000) {
				// ���� ���� �ݾ� ������ ����� ���
				pattern = false;
			} else {
				pattern = Pattern.matches("[1-9]((\\d){0,2}[0,5])?0$", putMoneytext.getText());
			}

			 if (!pattern) { // ���� ���� �ݾ� ������ ����� ���
				JOptionPane.showMessageDialog(new JFrame(), "100�� �̻� 5000�� ���Ϸ� ������ �� �ֽ��ϴ�.");
			} else {

				takeMoneytext.setText(String
						.valueOf(Integer.parseInt(takeMoneytext.getText()) + Integer.parseInt(putMoneytext.getText())));
				for (int i = 0; i < blist.size(); i++) {
					if (blist.get(i).getLabel().equals(CanArray.canList.get(i).getCanName())) {
						if (CanArray.canList.get(i).getCanNum() == 0) { // ��� ������
							blist.get(i).setForeground(new Color(255, 255, 255));
							blist.get(i).setBackground(new Color(204, 61, 61)); // ������
						}
						if (CanArray.canList.get(i).getCanPrice() <= Integer.parseInt(takeMoneytext.getText())
								&& CanArray.canList.get(i).getCanNum() > 0) { // ��� �ְ� ���Աݾ��� ����ϸ�
							blist.get(i).setForeground(new Color(255, 255, 255));
							blist.get(i).setBackground(new Color(20, 175, 100));
						}
					}
				}

				// �Ѹ���� �ٽ� Admin���� ���� �� �� RightPanel�� �Ѹ���� �ٽ� ����
				Admin.setTotalMoney(Admin.getTotalMoney() + Integer.parseInt(putMoneytext.getText()));
				MachinePanelRight.totalMoneyLabel.setText("�� ����� : " + Admin.getTotalMoney());

				putMoneytext.setText("");
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "���� �������� �Է��ϼ���");
		}
	}
}
