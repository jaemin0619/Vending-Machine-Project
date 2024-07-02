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
		// 좌측 자판기 판넬

		setPreferredSize(new Dimension(320, 630));
		List<JButton> blist = new ArrayList<JButton>();   // 버튼 리스트

		// ------------<돈 입출구>-----------//
		JPanel moneyPanel = new JPanel();   // 돈 출입구 판넬(가장큰틀)
		// 반환구(반환버튼)
		JPanel takeMoneyPanel = new JPanel();
		takeMoneytext = new JTextField(6);
		takeMoneytext.setText("0"); // 0으로 초기화
		
		  
	      JButton getMoneyButton = new JButton("반환");  // 버튼
	      getMoneyButton.addActionListener(e -> {
	          String takeMoney = takeMoneytext.getText();
	          int sumOfMoney = Integer.parseInt(takeMoney);
	          
	          int n1000 = sumOfMoney / 1000;  // 1000원 개수 계산
	          int remainder = sumOfMoney % 1000;  // 나머지 금액
	          
	          int n500 = remainder / 500;  // 500원 개수 계산
	          remainder %= 500;
	          
	          int n100 = remainder / 100;  // 100원 개수 계산
	          remainder %= 100;
	          
	          int n50 = remainder / 50;  // 50원 개수 계산
	          remainder %= 50;
	          
	          int n10 = remainder / 10;  // 10원 개수 계산
	          
	          String message = "1000원: " + n1000 + "개굈" +
	                           "500원: " + n500 + "개굈" +
	                           "100원: " + n100 + "개굈" +
	                           "50원: " + n50 + "개굈" +
	                           "10원: " + n10 + "개";
	          
	          JOptionPane.showMessageDialog(null, message, "거스름돈", JOptionPane.INFORMATION_MESSAGE);
	          takeMoneytext.setText("0");
	      });

		
		JButton takeMoneyButton = new JButton(new ImageIcon("return.png"));  // 반환 버튼 이미지아이콘
		takeMoneyButton.setBorder(BorderFactory.createEmptyBorder());
		takeMoneyButton.setContentAreaFilled(false);
		takeMoneyButton.addActionListener(new ReturnMoney(takeMoneytext, getCan, blist)); // 버튼 액션

		//투입구
		JPanel returnMoneyPanel=new JPanel();
		JPanel putMoneyPanel = new JPanel();
		putMoneytext = new JTextField(6);
		
		putMoneytext.addActionListener(new PutMoney(putMoneytext, takeMoneytext, blist));  // 버튼 액션
		JButton putMoneyButton = new JButton("투입");  // 버튼
		putMoneyButton.addActionListener(new PutMoney(putMoneytext, takeMoneytext, blist));  // 버튼 액션
		
		takeMoneytext.setEditable(false);   // 반환구 JTextField는 수정 불가능(기본값은 수정가능)
		takeMoneyPanel.add(takeMoneytext);  // 반환판넬에 추가
		takeMoneyPanel.add(takeMoneyButton);
		takeMoneyPanel.add(getMoneyButton);
		putMoneyPanel.add(putMoneytext); // 투입판넬에 추가
		putMoneyPanel.add(putMoneyButton);
		
		moneyPanel.add(takeMoneyPanel);  // 돈 출입구 판넬(가장큰틀)에 반환, 투입판넬추가
		moneyPanel.add(putMoneyPanel);

		// ----------<음료반환구>----------------//
		JPanel getCanPanel = new JPanel();
		getCan = new JButton("");  // 음료 반환 버튼(검은색음료배출구)
		getCan.addActionListener(new TakeCan(getCan)); // 버튼 액션
		getCan.setIcon(new ImageIcon("canreturn.png")); 
		getCan.setBorder(BorderFactory.createEmptyBorder());
		getCan.setContentAreaFilled(false);

		getCanPanel.add(getCan);

		// ------------<음료선택>----------//
		JPanel selectCan = new JPanel(new GridLayout(2, 1));
		selectCan.setPreferredSize(new Dimension(310, 330));  // 흰색부분

		for (int i = 0; i < CanArray.canList.size(); i++) {
			JPanel canEach = new JPanel();
			JLabel canLabel = new JLabel(CanArray.canList.get(i).getCanPrice() + "원");
			canButton = new JButton(CanArray.canList.get(i).getCanName());
			canButton.addActionListener(new ButtonAction(takeMoneytext, getCan, blist));
			canButton.setForeground(new Color(0, 0, 0));  // 음료 텍스트 색상
			canButton.setBackground(new Color(255, 255, 255));  // 음료 버튼 색상
			canEach.add(new JLabel(new ImageIcon(i + ".png")));  // 음료 이미지
			canEach.add(canLabel);  // 음료 이름
			canEach.add(canButton);
			selectCan.add(canEach);

			blist.add(canButton);  // 버튼 리스트에 버튼들 추가
		}

		add(selectCan, BorderLayout.NORTH);  // 음료 선택 북쪽
		add(moneyPanel, BorderLayout.CENTER);  // 돈 입출구 중앙
		add(getCanPanel, BorderLayout.SOUTH);  // 음료 반환구 남쪽
		
		//각 패널 배경색상 설정
		moneyPanel.setBackground(new Color(70, 152, 64));
		takeMoneyPanel.setBackground(new Color(70, 152, 64));
		putMoneyPanel.setBackground(new Color(70, 152, 64));
		getCanPanel.setBackground(new Color(70, 152, 64));
		setBackground(new Color(110, 202, 44));

	}
}
