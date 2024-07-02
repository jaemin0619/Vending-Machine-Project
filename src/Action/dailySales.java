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
        JFrame frame = new JFrame("일별 매출");
        frame.setBounds(400, 150, 800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);
        panel.setBackground(new Color(255, 241, 232));
        frame.add(panel);

        // 파일 입출력
        File file = new File("dailySales.txt");
        if (!file.exists())
            file.createNewFile();

        BufferedWriter file_in = new BufferedWriter(new FileWriter(file, true));
        PrintWriter file_out = new PrintWriter(file_in, true);
        BufferedReader read = new BufferedReader(new FileReader(file));

        // 날짜 출력
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        JLabel date = new JLabel(format.format(calendar.getTime()));

        // 폰트
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 23);

        Panel N_menu = new Panel();
        N_menu.setBackground(new Color(255, 241, 232));
        N_menu.setLayout(null);
        N_menu.setSize(0, 100);
        N_menu.setFont(font);

        // 뒤로가기
        JButton back_bt = new JButton("뒤로가기");
        back_bt.setBounds(650, 10, 110, 30);
        N_menu.add(back_bt);

        back_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // 현재 창 닫기
                new Admin(); // 관리자페이지 띄우기
            }
        });

        // 엔터키로 버튼 클릭
        JRootPane rootPane = frame.getRootPane();
        rootPane.setDefaultButton(back_bt);

        JTextArea text = new JTextArea();
        text.setBackground(Color.white);
        text.setEditable(false);
        text.setFont(font);
        text.setText("굈   날짜굏굏금액굈");

        frame.add(N_menu, BorderLayout.NORTH);
        frame.add(text, BorderLayout.CENTER);

        // 화면 출력
        int todayTotal = ReturnProfit.getProfit();

        text.append("========================굈");
        file_in.write(format.format(calendar.getTime()) + " : " + todayTotal + "원굈");
        file_in.close();

        String line;
        while ((line = read.readLine()) != null) {
            text.append(line + "굈");
        }
        read.close();

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
