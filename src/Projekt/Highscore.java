package Projekt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Highscore extends JFrame {
    private JPanel contentPane;
    private JButton igrOpet;
    private JButton Izadji;
    private JLabel prvoMj;
    private JLabel drugoMj;
    private JLabel treceMj;
    private JLabel highscoreLabel;

    static final String DB_URL = "jdbc:mysql://localhost:3306/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";


    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Highscore window = new Highscore();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }
    public Highscore(){
        setTitle("Highscore");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        Connect.main(null);
        ImageIcon image = new ImageIcon(getClass().getResource("gold.jpg"));
        prvoMj = new JLabel("");
        prvoMj.setIcon(image);
        prvoMj.setBounds(50, 110, 50, 40);
        prvoMj.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("gold.jpg")).getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH)));
        contentPane.add(prvoMj);
        drugoMj = new JLabel("");
        drugoMj.setBounds(50, 160, 50, 40);
        drugoMj.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("silver.png")).getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH)));
        contentPane.add(drugoMj);
        treceMj = new JLabel("");
        treceMj.setBounds(50, 210, 50, 40);
        treceMj.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("bronze.png")).getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH)));
        contentPane.add(treceMj);
        highscoreLabel = new JLabel("Highscore", SwingConstants.CENTER);
        highscoreLabel.setBorder( new MatteBorder(0,0,5,0, Color.gray));
        highscoreLabel.setFont(new Font("Ink Free",Font.BOLD,50));
        highscoreLabel.setBounds(0, 0, 500, 50);
        contentPane.add(highscoreLabel);
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Ink Free",Font.BOLD,30));
        usernameLabel.setBounds(100, 70, 300, 20);
        contentPane.add(usernameLabel);
        JLabel scoreLabel = new JLabel("Rezultat");
        scoreLabel.setFont(new Font("Ink Free",Font.BOLD,30));
        scoreLabel.setBounds(400, 70, 300, 20);
        contentPane.add(scoreLabel);

        List<String> user_name = new ArrayList<>();
        List<Integer> high_score = new ArrayList<>();
        String h_score = "SELECT user_name, highscore FROM sys.quiz\n" +
                "ORDER BY highscore desc\n" +
                "LIMIT 10;";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = (Statement) conn.createStatement();
            stmt.executeQuery(h_score);
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                user_name.add(rs.getString("user_name"));
                high_score.add(rs.getInt("highscore"));
            }
            String[] user_nameArray = user_name.toArray(new String[user_name.size()]);
            Integer [] high_scoreArray = high_score.toArray(new Integer[high_score.size()]);
            for(int i= 0, j=1; i < user_nameArray.length; i++){
                System.out.println(user_nameArray[i]);
                JLabel name = new JLabel((i+1)+". "+user_nameArray[i]);
                JLabel score = new JLabel(Integer.toString(high_scoreArray[i]));
                name.setFont(new Font("Ink Free",Font.BOLD,30));
                score.setFont(new Font("Ink Free",Font.BOLD,30));
                name.setBounds(100, 30+j, 200, 200);
                score.setBounds(400, 30+j, 200, 200);
                contentPane.add(name);
                contentPane.add(score);
                j = j+40;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Izadji = new JButton("Izadji");
        Izadji.setBounds(270, 530, 89, 23);
        contentPane.add(Izadji);
        Izadji.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                System.exit(0);
            }
        });


        igrOpet = new JButton("Igraj opet");
        igrOpet.setBounds(180, 530, 89, 23);
        contentPane.add(igrOpet);

        igrOpet.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {

                Connect connect=new Connect();
                new Prijava().setVisible(true);

                dispose();
            }

        });
    }
}