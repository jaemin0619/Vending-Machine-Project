package Machine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Action.AddCanFrame;
import Action.AddCoinFrame;
import Action.ActionPwChange;
import Action.ActionCollectMoney;
import Action.ChangeCanInfo;
import Action.dailySales;
import Can.Can;
import Can.CanArray;
import Coin.CoinArray;
import Person.Admin;

public class MachinePanelRight extends JPanel implements ActionListener {
    JTextField collectMoney;
    JPasswordField adminPass, changePW;
    JPanel canAdminPanel, moneyAdminPanel, moneyTotalPanel, PWPanel, collectPanel;
    JButton btnAdminIn, btnAddCanStart, btnAddCan, btnChangeCanInfo;
    JLabel label;
    String password;
    DefaultTableModel canModel;
    public static JLabel totalMoneyLabel, PWLabel, collectLabel;
    public static JTable canTable, moneyTable;
    JButton dailySalesButton; // 일별 매출 확인 버튼

    public MachinePanelRight(String password) {
        this.password = password;

        setPreferredSize(new Dimension(280, 630));

        label = new JLabel("관리자모드 - 비밀번호를 입력해주세요");
        label.setVisible(true);
        dailySalesButton = new JButton("일별 매출 확인");
        dailySalesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new dailySales();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        dailySalesButton.setVisible(false); // 초기에는 숨김 상태로 설정

        JPanel inAdminPanel = new JPanel();

        canAdminPanel = new JPanel(new BorderLayout());

        String[] canColName = { "음료이름", "재고", "개당 판매가격" };
        canModel = new DefaultTableModel(canColName, 0);
        canTable = new JTable(canModel);
        JScrollPane canScrollPanel = new JScrollPane(canTable);
        canScrollPanel.setPreferredSize(new Dimension(230, 150));

        btnAddCan = new JButton("음료추가");
        btnAddCan.addActionListener(new AddCanFrame(canTable));

        btnChangeCanInfo = new JButton("음료정보변경");
        btnChangeCanInfo.addActionListener(this);

        canAdminPanel.add(new JLabel("음료관리"), BorderLayout.WEST);
        canAdminPanel.add(btnAddCan, BorderLayout.EAST);
        canAdminPanel.add(btnChangeCanInfo, BorderLayout.SOUTH);
        canAdminPanel.add(canScrollPanel, BorderLayout.CENTER);
        canAdminPanel.setVisible(false);

        for (int i = 0; i < CanArray.canList.size(); i++) {
            String arr[] = { CanArray.canList.get(i).getCanName(),
                    Integer.toString(CanArray.canList.get(i).getCanNum()),
                    Integer.toString(CanArray.canList.get(i).getCanPrice()) };
            canModel.addRow(arr);
        }

        moneyAdminPanel = new JPanel(new BorderLayout());

        String[] moneyColName = { "동전 종류", "남은 갯수" };
        DefaultTableModel moneyModel = new DefaultTableModel(moneyColName, 0);
        moneyTable = new JTable(moneyModel);
        JScrollPane moneyScrollPanel = new JScrollPane(moneyTable);
        moneyScrollPanel.setPreferredSize(new Dimension(230, 150));

        JButton btnAddMoney = new JButton("잔돈추가");
        btnAddMoney.addActionListener(new AddCoinFrame(moneyTable));

        moneyAdminPanel.add(new JLabel("잔돈관리"), BorderLayout.CENTER);
        moneyAdminPanel.add(btnAddMoney, BorderLayout.EAST);
        moneyAdminPanel.add(moneyScrollPanel, BorderLayout.SOUTH);
        moneyAdminPanel.setVisible(false);

        moneyModel = (DefaultTableModel) moneyTable.getModel();

        for (int i = 0; i < CoinArray.coinList.size(); i++) {
            String arr[] = { CoinArray.coinList.get(i).getCoinName(),
                    Integer.toString(CoinArray.coinList.get(i).getCoinNum()) };
            moneyModel.addRow(arr);
        }

        moneyTotalPanel = new JPanel(new BorderLayout());
        totalMoneyLabel = new JLabel("총 매출액 : " + Admin.getTotalMoney());
        moneyTotalPanel.add(totalMoneyLabel);
        moneyTotalPanel.setVisible(false);

        PWPanel = new JPanel(new BorderLayout());

        JPanel PW1 = new JPanel();
        JPanel PW2 = new JPanel();
        PWLabel = new JLabel("(특수문자 및 숫자 하나 이상 포함, 8자리 이상)");
        changePW = new JPasswordField(10);
        changePW.setText("Ogu1208!");
        changePW.setEchoChar('*');
        changePW.setEditable(true);
        JButton PWButton = new JButton("비밀번호 변경");

        JPasswordField passwordField = new JPasswordField(10);
        changePW.addActionListener(new ActionPwChange(passwordField));
        PWButton.addActionListener(new ActionPwChange(passwordField));

        PW1.add(PWLabel);
        PW2.add(changePW);
        PW2.add(PWButton);

        PWPanel.add(PW2, BorderLayout.NORTH);
        PWPanel.add(PW1, BorderLayout.SOUTH);

        PWPanel.setVisible(false);

        collectPanel = new JPanel(new BorderLayout());

        JPanel collect1 = new JPanel();
        JPanel collect2 = new JPanel();
        collectLabel = new JLabel("수금할 금액을 입력하세요(원)");
        collectMoney = new JTextField(10);

        JButton collectButton = new JButton("수금");

        collectMoney.addActionListener(new ActionCollectMoney(collectMoney));
        collectButton.addActionListener(new ActionCollectMoney(collectMoney));

        collect1.add(collectLabel);
        collect2.add(collectMoney);
        collect2.add(collectButton);

        collectPanel.add(collect2, BorderLayout.NORTH);
        collectPanel.add(collect1, BorderLayout.SOUTH);

        collectPanel.setVisible(false);

        adminPass = new JPasswordField(10);
        btnAdminIn = new JButton("접속");
        adminPass.addActionListener(this);
        btnAdminIn.addActionListener(this);

        inAdminPanel.add(new JLabel("관리자"));
        inAdminPanel.add(adminPass);
        inAdminPanel.add(btnAdminIn);

        add(inAdminPanel);  // 관리자 패널 추가
        add(canAdminPanel);  // 음료 관리 패널 추가
        add(label);  // 라벨 추가
        add(moneyAdminPanel);  // 잔돈 관리 패널 추가
        add(moneyTotalPanel);  // 총 매출액 표시 패널 추가
        add(PWPanel);  // 비밀번호 변경 패널 추가
        add(collectPanel);  // 수금 패널 추가
        add(dailySalesButton); // 일별 매출 확인 버튼 추가
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnChangeCanInfo) {
            int selectedRow = canTable.getSelectedRow();
            if (selectedRow >= 0) {
                String newName = JOptionPane.showInputDialog("변경할 음료 이름을 입력하세요:");
                if (newName != null && !newName.isEmpty()) {
                    String newPriceStr = JOptionPane.showInputDialog("변경할 음료 가격을 입력하세요:");
                    try {
                        int newPrice = Integer.parseInt(newPriceStr);
                        CanArray.canList.get(selectedRow).setCanName(newName);
                        CanArray.canList.get(selectedRow).setCanPrice(newPrice);
                        canModel.setValueAt(newName, selectedRow, 0);
                        canModel.setValueAt(newPrice, selectedRow, 2);
                    } catch (NumberFormatException ex) {
     JOptionPane.showMessageDialog(null, "가격은 숫자로 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
       JOptionPane.showMessageDialog(null, "변경할 음료를 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnAdminIn) {
            if (!canAdminPanel.isVisible()) {
                if (String.valueOf(adminPass.getPassword()).equals(Admin.getPassword())) {
                    label.setVisible(false);
                    canAdminPanel.setVisible(true);
                    moneyAdminPanel.setVisible(true);
                    moneyTotalPanel.setVisible(true);
                    PWPanel.setVisible(true);
                    collectPanel.setVisible(true);
                    dailySalesButton.setVisible(true); // 버튼 표시

                    btnAdminIn.setText("접속해제");
                    adminPass.setText("");
                    adminPass.setVisible(false);

                } else if (String.valueOf(adminPass.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "비밀번호를 입력해주세요");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "비밀번호가 틀렸습니다!");
                }
            } else if (canAdminPanel.isVisible()) {
                label.setVisible(true);
                canAdminPanel.setVisible(false);
                moneyAdminPanel.setVisible(false);
                moneyTotalPanel.setVisible(false);
                PWPanel.setVisible(false);
                collectPanel.setVisible(false);
                dailySalesButton.setVisible(false); // 버튼 숨김

                btnAdminIn.setText("접속");
                adminPass.setVisible(true);
            }
        }
    }
}
