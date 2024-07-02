package Machine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Action.PutMoney;
import Action.ReturnMoney;
import Action.TakeCan;
import Action.ButtonAction;
import Can.CanArray;

public class MachinePanelLeft extends JPanel {
	JButton getCan, canButton;
	JTextField putMoneytext, takeMoneytext;

	public MachinePanelLeft() {
		// ���� ���Ǳ� �ǳ�

		setPreferredSize(new Dimension(320, 630));
		List<JButton> blist = new ArrayList<JButton>();   // ��ư ����Ʈ

		// ------------<�� ���ⱸ>-----------//
		JPanel moneyPanel = new JPanel();   // �� ���Ա� �ǳ�(����ūƲ)
		// ��ȯ��(��ȯ��ư)
		JPanel takeMoneyPanel = new JPanel();
		takeMoneytext = new JTextField(6);
		takeMoneytext.setText("0"); // 0���� �ʱ�ȭ
		
		  
	      JButton getMoneyButton = new JButton("��ȯ");  // ��ư
	      getMoneyButton.addActionListener(e -> {
	          String takeMoney = takeMoneytext.getText();
	          int sumOfMoney = Integer.parseInt(takeMoney);
	          
	          int n1000 = sumOfMoney / 1000;  // 1000�� ���� ���
	          int remainder = sumOfMoney % 1000;  // ������ �ݾ�
	          
	          int n500 = remainder / 500;  // 500�� ���� ���
	          remainder %= 500;
	          
	          int n100 = remainder / 100;  // 100�� ���� ���
	          remainder %= 100;
	          
	          int n50 = remainder / 50;  // 50�� ���� ���
	          remainder %= 50;
	          
	          int n10 = remainder / 10;  // 10�� ���� ���
	          
	          String message = "1000��: " + n1000 + "���n" +
	                           "500��: " + n500 + "���n" +
	                           "100��: " + n100 + "���n" +
	                           "50��: " + n50 + "���n" +
	                           "10��: " + n10 + "��";
	          
	          JOptionPane.showMessageDialog(null, message, "�Ž�����", JOptionPane.INFORMATION_MESSAGE);
	          takeMoneytext.setText("0");
	      });

		
		JButton takeMoneyButton = new JButton(new ImageIcon("return.png"));  // ��ȯ ��ư �̹���������
		takeMoneyButton.setBorder(BorderFactory.createEmptyBorder());
		takeMoneyButton.setContentAreaFilled(false);
		takeMoneyButton.addActionListener(new ReturnMoney(takeMoneytext, getCan, blist)); // ��ư �׼�

		//���Ա�
		JPanel returnMoneyPanel=new JPanel();
		JPanel putMoneyPanel = new JPanel();
		putMoneytext = new JTextField(6);
		
		putMoneytext.addActionListener(new PutMoney(putMoneytext, takeMoneytext, blist));  // ��ư �׼�
		JButton putMoneyButton = new JButton("����");  // ��ư
		putMoneyButton.addActionListener(new PutMoney(putMoneytext, takeMoneytext, blist));  // ��ư �׼�
		
		takeMoneytext.setEditable(false);   // ��ȯ�� JTextField�� ���� �Ұ���(�⺻���� ��������)
		takeMoneyPanel.add(takeMoneytext);  // ��ȯ�ǳڿ� �߰�
		takeMoneyPanel.add(takeMoneyButton);
		takeMoneyPanel.add(getMoneyButton);
		putMoneyPanel.add(putMoneytext); // �����ǳڿ� �߰�
		putMoneyPanel.add(putMoneyButton);
		
		moneyPanel.add(takeMoneyPanel);  // �� ���Ա� �ǳ�(����ūƲ)�� ��ȯ, �����ǳ��߰�
		moneyPanel.add(putMoneyPanel);

		// ----------<�����ȯ��>----------------//
		JPanel getCanPanel = new JPanel();
		getCan = new JButton("");  // ���� ��ȯ ��ư(������������ⱸ)
		getCan.addActionListener(new TakeCan(getCan)); // ��ư �׼�
		getCan.setIcon(new ImageIcon("canreturn.png")); 
		getCan.setBorder(BorderFactory.createEmptyBorder());
		getCan.setContentAreaFilled(false);

		getCanPanel.add(getCan);

		// ------------<���ἱ��>----------//
		JPanel selectCan = new JPanel(new GridLayout(2, 1));
		selectCan.setPreferredSize(new Dimension(310, 330));  // ����κ�

		for (int i = 0; i < CanArray.canList.size(); i++) {
			JPanel canEach = new JPanel();
			JLabel canLabel = new JLabel(CanArray.canList.get(i).getCanPrice() + "��");
			canButton = new JButton(CanArray.canList.get(i).getCanName());
			canButton.addActionListener(new ButtonAction(takeMoneytext, getCan, blist));
			canButton.setForeground(new Color(0, 0, 0));  // ���� �ؽ�Ʈ ����
			canButton.setBackground(new Color(255, 255, 255));  // ���� ��ư ����
			canEach.add(new JLabel(new ImageIcon(i + ".png")));  // ���� �̹���
			canEach.add(canLabel);  // ���� �̸�
			canEach.add(canButton);
			selectCan.add(canEach);

			blist.add(canButton);  // ��ư ����Ʈ�� ��ư�� �߰�
		}

		add(selectCan, BorderLayout.NORTH);  // ���� ���� ����
		add(moneyPanel, BorderLayout.CENTER);  // �� ���ⱸ �߾�
		add(getCanPanel, BorderLayout.SOUTH);  // ���� ��ȯ�� ����
		
		//�� �г� ������ ����
		moneyPanel.setBackground(new Color(70, 152, 64));
		takeMoneyPanel.setBackground(new Color(70, 152, 64));
		putMoneyPanel.setBackground(new Color(70, 152, 64));
		getCanPanel.setBackground(new Color(70, 152, 64));
		setBackground(new Color(110, 202, 44));

	}
}
