package Projekt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.awt.Color;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;



public class Pitanja extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/sys?useUnicode=true&useJDBCCompliantTimezoneShift=" +
            "true&useLegacyDatetimeCode=false&serverTimezone=CET";


    static final String USER = "root";
    static final String PASS = "root";

    private JPanel contentPane;
    private JButton Next;
    private JButton opcija1;
    private JButton opcija2;
    private JButton opcija3;
    private JButton opcija4;
    private JLabel pitanje;
    private JLabel username;
    String[][] a;
    String[][] q;
    static int score = 0;
    int count = 0;
    int c = 0;
    private int inicijalne_sekunde = 10;
    private JLabel preostale_sekunde;
    private String odgovor = " ";
    private List<JButton> buttoni = new ArrayList<JButton>();
    static int duzina = 0;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Pitanja window = new Pitanja();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

    public Pitanja() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        score = 0;
        Igra();
        q = QuestionsModel.getq1();
        a = QuestionsModel.geta1();
        duzina = q.length;
        ZapocniKviz(0);
    }

    public String[][] getPitanja() {
        return q;
    }

    public String[][] getOdgovori() {
        return a;
    }

    private void Igra() {
        setTitle("Kviz:Pitanja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650,650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        opcija1 = new JButton();
        opcija2 = new JButton();
        opcija3 = new JButton();
        opcija4 = new JButton();
        pitanje = new JLabel();
        username = new JLabel();
        Next = new JButton();
        buttoni.addAll(Arrays.asList(opcija1, opcija2, opcija3, opcija4));
        preostale_sekunde = new JLabel("10");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Timer timer1 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicijalne_sekunde--;
                preostale_sekunde.setText(String.valueOf(inicijalne_sekunde));
                if(inicijalne_sekunde <= 0 && c != q.length){
                    inicijalne_sekunde = 10;
                    provjeriOdgovor(odgovor);
                }
            }
        });
        timer1.start();


        pitanje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pitanje.setText("question");
        username.setText("Igrac: " + Prijava.usernameq);
        Next.setText("Next");

        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SljedeciKlik(evt);
            }

        });

        opcija1.setBounds(0, 180, 650, 80);
        opcija1.setFont(new Font("MV Boli",Font.BOLD,35));
        opcija1.setFocusable(false);
        opcija1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                odgovor = opcija1.getText();
                provjeriOdgovor(odgovor);
            }
        });

        opcija2.setBounds(0, 260, 650, 80);
        opcija2.setFont(new Font("MV Boli",Font.BOLD,35));
        opcija2.setFocusable(false);
        opcija2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                odgovor = opcija2.getText();
                provjeriOdgovor(odgovor);
            }
        });

        opcija3.setBounds(0, 340, 650, 80);
        opcija3.setFont(new Font("MV Boli",Font.BOLD,35));
        opcija3.setFocusable(false);
        opcija3.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                odgovor = opcija3.getText();
                provjeriOdgovor(odgovor);
            }
        });

        opcija4.setBounds(0, 420, 650, 80);
        opcija4.setFont(new Font("MV Boli",Font.BOLD,35));
        opcija4.setFocusable(false);
        opcija4.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                odgovor = opcija4.getText();
                provjeriOdgovor(odgovor);
            }
        });

        pitanje.setBounds(0,50,650,80);
        pitanje.setBackground(new Color(25,25,25));
        pitanje.setForeground(new Color(25,255,0));
        pitanje.setFont(new Font("Ink Free",Font.BOLD,30));
        pitanje.setBorder(BorderFactory.createBevelBorder(1));
        pitanje.setHorizontalAlignment(JTextField.CENTER);

        username.setBounds(0, 0, 650, 50);
        username.setBackground(new Color(25,25,25));
        username.setForeground(new Color(25,255,0));
        username.setFont(new Font("Ink Free",Font.BOLD,30));


        preostale_sekunde.setBounds(280, 130, 50, 50);
        preostale_sekunde.setFont(new Font("Ink Free",Font.BOLD,30));

        Next.setBounds(265, 520, 100, 50);
        Next.setBorder(BorderFactory.createBevelBorder(1));
        Next.setBackground(new Color(25,25,25));
        Next.setForeground(new Color(25,255,0));




        add(Next);
        add(pitanje);
        add(username);
        add(opcija1);
        add(opcija2);
        add(opcija3);
        add(opcija4);
        add(preostale_sekunde);
        setVisible(true);

    }




    protected void SljedeciKlik(ActionEvent evt) {
        odgovor = "";
        opcija1.setEnabled(true);
        opcija2.setEnabled(true);
        opcija3.setEnabled(true);
        opcija4.setEnabled(true);
        Next.setEnabled(false);
        provjeriOdgovor(odgovor);
    }


    public void ZapocniKviz(int i) {
        pitanje.setText("<html>"+q[i][0]+"<html>");
        opcija1.setText(q[i][1]);
        opcija2.setText(q[i][2]);
        opcija3.setText(q[i][3]);
        opcija4.setText(q[i][4]);
    }

    private void provjeriOdgovor(String odgovor) {
        System.out.println(odgovor);
        for(JButton button : buttoni){
            if(button.getText().equals(a[c][0])){
                button.setForeground(new Color(25,255,0));
            }else{
                button.setForeground(new Color(255,0,0));
            }
        }
        if (odgovor.equals(a[c][0])) {
            score++;
            count++;
            c++;
        } else {
            c++;
            count++;
        }

        Timer pauziraj = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcija1.setForeground(new Color(25,255,0));
                opcija2.setForeground(new Color(25,255,0));
                opcija3.setForeground(new Color(25,255,0));
                opcija4.setForeground(new Color(25,255,0));

                inicijalne_sekunde=10;
                preostale_sekunde.setText(String.valueOf(inicijalne_sekunde));
                provjeriPitanja();
                opcija1.setEnabled(true);
                opcija2.setEnabled(true);
                opcija3.setEnabled(true);
                opcija4.setEnabled(true);
                Next.setEnabled(true);
            }
        });
        pauziraj.setRepeats(false);
        pauziraj.start();
    }

    private void provjeriPitanja(){
        if (c != q.length) {
            ZapocniKviz(c);
            System.out.println(score);
        }else {
            int ID = 0;
            int Highscore = 0;
            Connect.main(null);
            String findId = "SELECT ID, highscore FROM `sys`.`quiz` WHERE (`user_name` = '"+Prijava.usernameq+"');";
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = (Statement) conn.createStatement();
                stmt.executeQuery(findId);
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    ID = rs.getInt("ID");
                    Highscore = rs.getInt("highscore");
                    System.out.println(ID);
                    System.out.println(Highscore);
                }
                if(score > Highscore){
                    String insertScore = "UPDATE `quiz` SET `highscore` = '"+score+"' WHERE (ID = '"+ID+"')";
                    stmt.executeUpdate(insertScore);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            System.out.println(score);
            new Rezultat().setVisible(true);
            this.dispose();
        }
    }


}
