package Action;

// 금액 투입 클래스 
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
	int billCount; // 투입한 1000원 지폐 개수를 저장하는 변수

	// 금액 투입 클래스 생성자
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
		// 정규식 조건문
		if (putMoneytext.getText().equals("")) {
			JOptionPane.showMessageDialog(new JFrame(), "돈을 넣지 않았습니다.");
		} else if (Pattern.matches("^[0-9]*$", putMoneytext.getText())) {
			
			int money = Integer.parseInt(putMoneytext.getText());
if(money!=10&&money!=50&&money!=100&&money!=500&&money!=1000)
{
	JOptionPane.showMessageDialog(new JFrame(),"올바른 화폐 단위를 입력하세요.");
	return;
}
			if (money == 1000) {
				if (billCount >= 3) { // 1000원 지폐 투입 가능 개수(3장)를 초과한 경우
					JOptionPane.showMessageDialog(new JFrame(), "1000원짜리는 3장만 넣을 수 있습니다.");
					return;
				} else {
					billCount++;
				}
			} else if (money < 100 || money > 5000) {
				// 투입 가능 금액 범위를 벗어나는 경우
				pattern = false;
			} else {
				pattern = Pattern.matches("[1-9]((\\d){0,2}[0,5])?0$", putMoneytext.getText());
			}

			 if (!pattern) { // 투입 가능 금액 범위를 벗어나는 경우
				JOptionPane.showMessageDialog(new JFrame(), "100원 이상 5000원 이하로 투입할 수 있습니다.");
			} else {

				takeMoneytext.setText(String
						.valueOf(Integer.parseInt(takeMoneytext.getText()) + Integer.parseInt(putMoneytext.getText())));
				for (int i = 0; i < blist.size(); i++) {
					if (blist.get(i).getLabel().equals(CanArray.canList.get(i).getCanName())) {
						if (CanArray.canList.get(i).getCanNum() == 0) { // 재고가 없으면
							blist.get(i).setForeground(new Color(255, 255, 255));
							blist.get(i).setBackground(new Color(204, 61, 61)); // 빨간색
						}
						if (CanArray.canList.get(i).getCanPrice() <= Integer.parseInt(takeMoneytext.getText())
								&& CanArray.canList.get(i).getCanNum() > 0) { // 재고가 있고 투입금액이 충분하면
							blist.get(i).setForeground(new Color(255, 255, 255));
							blist.get(i).setBackground(new Color(20, 175, 100));
						}
					}
				}

				// 총매출액 다시 Admin에서 설정 후 총 RightPanel에 총매출액 다시 세팅
				Admin.setTotalMoney(Admin.getTotalMoney() + Integer.parseInt(putMoneytext.getText()));
				MachinePanelRight.totalMoneyLabel.setText("총 매출액 : " + Admin.getTotalMoney());

				putMoneytext.setText("");
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "숫자 형식으로 입력하세요");
		}
	}
}
