package Action;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Coin.CoinArray;


public class AddCoinFrame extends JFrame implements ActionListener{
	JCheckBox coinCheck;
	JButton coinOk, coinClose;
	JTable coinTable;
	DefaultTableModel coinModel;
	public AddCoinFrame (JTable coinTable){
		this.coinTable  = coinTable;
	}
	
	public void addCoinFrame(JTable coinTable){
		this.coinTable = coinTable;
		
		List<JCheckBox> coinCheckList = new ArrayList<JCheckBox>();
		List<JComboBox> coinSelectList = new ArrayList<JComboBox>();
		
		setTitle("�ܵ��߰��˾�");
		setSize(320, 300);
		setLocation(200,200);

		// title
		JLabel label = new JLabel("��ܵ��� �����ϽŰ���?");
		JPanel toPanel = new JPanel();
		toPanel.add(label);
		add(toPanel, BorderLayout.NORTH);
		
		// üũ�ڽ� �߰�
		JPanel checkPanel = new JPanel();
		JButton chechAll = new JButton("��ü10���߰�");
		chechAll.setPreferredSize(new Dimension(120, 30));
		chechAll.addActionListener(new ActionSelectAll(coinCheckList));
		
		String[] coinSelectNum = {"10��","20��","50��"};  // �ܵ� �米 �߰� ���� ���
		
		
		
		for(int i=0; i<CoinArray.coinList.size();i++){
			JPanel checkEach = new JPanel();
			coinCheck = new JCheckBox(CoinArray.coinList.get(i).getCoinName());  // �ܵ� �̸� üũ�ڽ�
			JComboBox<String> coinSelectBox = new JComboBox();  // �ܵ� ��� �߰� ���� ����� �޺��ڽ��� ������
			for(int j=0; j<coinSelectNum.length; j++){
				coinSelectBox.addItem(coinSelectNum[j]);
			}
			
			
			coinCheckList.add(coinCheck);
			coinSelectList.add(coinSelectBox);
			
			checkEach.add(coinCheck);
			checkEach.add(coinSelectBox);
			coinSelectBox.setVisible(false);
			
			checkPanel.add(checkEach);
			coinCheck.addActionListener(new ActionCheck(coinSelectList));
			checkEach.setPreferredSize(new Dimension(160, 30));
		}
		
		// 10���߰�, �޺��ڽ� �߰� ���̾ƿ� ����
		add(chechAll, BorderLayout.WEST);
		add(checkPanel,BorderLayout.CENTER);
		
		//�߰�, ��� ��ư �Ʒ��� ��ġ, ���̾ƿ� ����
		coinOk = new JButton("�߰�");
		coinClose = new JButton("���");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(coinOk);
		buttonPanel.add(coinClose);
		add(buttonPanel, BorderLayout.SOUTH);
		coinOk.addActionListener(new ActionAddCoin(this, chechAll, coinCheckList, coinSelectList));
		coinClose.addActionListener(new ActionCancle(this));
	
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		addCoinFrame(coinTable);
	}
}