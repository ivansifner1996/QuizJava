package Projekt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Rezultat extends JFrame
{
    private JPanel contentPane;
    private JButton igrOpet;
    private JButton Izadji;
    private JButton Odg;

    protected static final String String = null;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Rezultat window = new Rezultat();
                    window.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }


    public Rezultat()
    {
        setTitle("Rezultati");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JLabel background=new JLabel(new ImageIcon(getClass().getResource("theEnd.jpg")));
        add(background);

        int results=Pitanja.score;
        String result=String.valueOf(results);


        JLabel username = new JLabel(Prijava.usernameq+"!");
        username.setBounds(220, 0, 200, 200);
        username.setFont(new Font("Ink Free",Font.BOLD,30));
        username.setForeground(new Color(25,255,0));
        background.add(username);
        results=Pitanja.duzina-results;
        System.out.println("Duzina iznosi");
        System.out.println(results);
        String netocno=String.valueOf(results);
        JLabel konacniRezultat = new JLabel("<html>Vas konacni rezultat je:<html>"+result+"<html><br>Broj tocnih odgovora: <html>"+result+"<html><br>Broj netocnih odgovora: <html>"+netocno);
        konacniRezultat.setBounds(200, 120, 220, 220);
        konacniRezultat.setFont(new Font("Ink Free",Font.BOLD,30));
        konacniRezultat.setForeground(new Color(25,255,0));

        background.add(konacniRezultat);


        Izadji = new JButton("Izadji");
        Izadji.setBounds(310, 360, 110, 23);
        background.add(Izadji);
        Izadji.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                System.exit(0);
            }
        });


        igrOpet = new JButton("Igraj opet");
        igrOpet.setBounds(190, 360, 110, 23);
        background.add(igrOpet);
        igrOpet.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {

                new Prijava().setVisible(true);
                dispose();
            }

        });

        JButton highscore = new JButton("Highscore");
        highscore.setBounds(60, 360, 110, 23);
        background.add(highscore);
        highscore.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Highscore().setVisible(true);
                dispose();
            }
        });


        Odg = new JButton("Odgovori");
        Odg.setBounds(195, 400, 89, 23);
        background.add(Odg);
        Odg.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {
                new Odgovori().setVisible(true);
                dispose();
            }

        });



    }}
