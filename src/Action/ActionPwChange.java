package Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JFrame;
import java.util.regex.Pattern;

import Machine.MachineFrame;
import Machine.MachinePanelRight;
import Machine.MachineMain;
import Person.Admin;

// ��й�ȣ ���� Ŭ����
public class ActionPwChange implements ActionListener {
    JPasswordField passwordField;

    // ��й�ȣ ���� ������
    public ActionPwChange(JPasswordField passwordField) {
        super();
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char[] password = passwordField.getPassword();
        String passwordString = new String(password);

        if (passwordString.equals("")) {
            // �ƹ��͵� �Է����� �ʾ��� ���
            JOptionPane.showMessageDialog(new JFrame(), "�ٲٽ� ��й�ȣ�� �Է����ּ���");
        } else if (passwordString.length() < 8) {
            // 8�ڸ� �̻� �Է����� �ʾ��� ���
            JOptionPane.showMessageDialog(new JFrame(), "8�ڸ� �̻� �Է����ּ���");
        } else {
            // 8�ڸ� �̻��̰�
boolean pattern = 
Pattern.matches("^(?=.*[A-Za-z])(?=.*��d)(?=.*[~!@#$%^&*()+|=])[A-Za-z��d~!@#$%^&*()+|=]{8,16}$",
                    passwordString);
            if (pattern == false) { // ���Խ� ���ǿ� �������� ������
                // Ư�����ڳ� ���ڰ� ���Ե��� ������ ���Ե��� �ʾ����� �˸�
                JOptionPane.showMessageDialog(new JFrame(), "Ư�����ڳ� ���ڸ� �ϳ� �̻� ������ �ּ���");
            } else {
                // 8�ڸ� �̻��̰� ���Խ� ���ǿ� �����ϸ�
                Admin.setPassword(passwordString); // ��й�ȣ�� ����
                JOptionPane.showMessageDialog(new JFrame(), // ���������� �˸�
                        "��й�ȣ�� ����Ǿ����ϴ�");
            }
        }

        // �Էµ� ��й�ȣ �ʵ� �ʱ�ȭ
        passwordField.setText("");
    }
}
