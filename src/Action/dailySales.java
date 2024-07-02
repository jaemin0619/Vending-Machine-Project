package Action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;

import Person.Admin;

public class dailySales extends JFrame {
    public dailySales() throws IOException {
        JFrame frame = new JFrame("�Ϻ� ����");
        frame.setBounds(400, 150, 800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);
        panel.setBackground(new Color(255, 241, 232));
        frame.add(panel);

        // ���� �����
        File file = new File("dailySales.txt");
        if (!file.exists())
            file.createNewFile();

        BufferedWriter file_in = new BufferedWriter(new FileWriter(file, true));
        PrintWriter file_out = new PrintWriter(file_in, true);
        BufferedReader read = new BufferedReader(new FileReader(file));

        // ��¥ ���
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        JLabel date = new JLabel(format.format(calendar.getTime()));

        // ��Ʈ
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 23);

        Panel N_menu = new Panel();
        N_menu.setBackground(new Color(255, 241, 232));
        N_menu.setLayout(null);
        N_menu.setSize(0, 100);
        N_menu.setFont(font);

        // �ڷΰ���
        JButton back_bt = new JButton("�ڷΰ���");
        back_bt.setBounds(650, 10, 110, 30);
        N_menu.add(back_bt);

        back_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // ���� â �ݱ�
                new Admin(); // ������������ ����
            }
        });

        // ����Ű�� ��ư Ŭ��
        JRootPane rootPane = frame.getRootPane();
        rootPane.setDefaultButton(back_bt);

        JTextArea text = new JTextArea();
        text.setBackground(Color.white);
        text.setEditable(false);
        text.setFont(font);
        text.setText("�n   ��¥�t�t�ݾׂn");

        frame.add(N_menu, BorderLayout.NORTH);
        frame.add(text, BorderLayout.CENTER);

        // ȭ�� ���
        int todayTotal = ReturnProfit.getProfit();

        text.append("========================�n");
        file_in.write(format.format(calendar.getTime()) + " : " + todayTotal + "���n");
        file_in.close();

        String line;
        while ((line = read.readLine()) != null) {
            text.append(line + "�n");
        }
        read.close();

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
