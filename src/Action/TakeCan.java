package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// ���� ��ȯ�� Action
public class TakeCan implements ActionListener {

	JButton getCan;
	
	public TakeCan(JButton getCan) {
		super();
		this.getCan = getCan;
	}


	@Override
	public void actionPerformed(ActionEvent e) {  // ���� ��ȯ�� ��ư���� ���Ḧ ��������
		getCan.setIcon(new ImageIcon("canreturn.png"));  //�⺻ ������ ȭ������ ����
		JOptionPane.showMessageDialog(new JFrame(), "�����մϴ�.");  // �����մϴ� ���� �޽���
	}

}
