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

// 비밀번호 변경 클래스
public class ActionPwChange implements ActionListener {
    JPasswordField passwordField;

    // 비밀번호 변경 생성자
    public ActionPwChange(JPasswordField passwordField) {
        super();
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char[] password = passwordField.getPassword();
        String passwordString = new String(password);

        if (passwordString.equals("")) {
            // 아무것도 입력하지 않았을 경우
            JOptionPane.showMessageDialog(new JFrame(), "바꾸실 비밀번호를 입력해주세요");
        } else if (passwordString.length() < 8) {
            // 8자리 이상 입력하지 않았을 경우
            JOptionPane.showMessageDialog(new JFrame(), "8자리 이상 입력해주세요");
        } else {
            // 8자리 이상이고
boolean pattern = 
Pattern.matches("^(?=.*[A-Za-z])(?=.*굚d)(?=.*[~!@#$%^&*()+|=])[A-Za-z굚d~!@#$%^&*()+|=]{8,16}$",
                    passwordString);
            if (pattern == false) { // 정규식 조건에 부합하지 않으면
                // 특수문자나 숫자가 포함되지 않으면 포함되지 않았음을 알림
                JOptionPane.showMessageDialog(new JFrame(), "특수문자나 숫자를 하나 이상 포함해 주세요");
            } else {
                // 8자리 이상이고 정규식 조건에 부합하면
                Admin.setPassword(passwordString); // 비밀번호를 변경
                JOptionPane.showMessageDialog(new JFrame(), // 변경했음을 알림
                        "비밀번호가 변경되었습니다");
            }
        }

        // 입력된 비밀번호 필드 초기화
        passwordField.setText("");
    }
}
