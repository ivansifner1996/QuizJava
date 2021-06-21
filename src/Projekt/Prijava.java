package Projekt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Prijava extends JFrame
{
    private JPanel contentPane;
    private JTextField txtUser;
    private JTextField txtPassword1;
    private JButton btnReg;
    private JButton btnPrijava;
    public static String usernameq;
    public static int ID;
    private boolean login=false;
    protected java.lang.String Spassword;

    static final String DB_URL = "jdbc:mysql://localhost:3306/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";

    static final String USER = "root";
    static final String PASS = "root";
    protected static final String String = null;

    public static void main(String[] args)
    {
        Connect.main(null);
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Prijava window = new Prijava();

                    window.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }


    public Prijava()
    {
        setTitle("Kviz: Prijava "+Connect.success);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        JLabel label1 = new JLabel("Prijava");
        label1.setText("Prijava");
        label1.setBounds(250, 100, 50, 60);
        label1.setSize(50, 20);

        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon(getClass().getResource("background_image.png")));
        add(background);
        txtUser = new JTextField();
        txtUser.setBounds(250, 153, 99, 20);
        background.add(txtUser);
        txtUser.setColumns(10);
        txtPassword1 = new JTextField();
        txtPassword1.setBounds(250, 204, 99, 20);
        background.add(txtPassword1);
        txtPassword1.setColumns(10);


        JLabel korIme = new JLabel("Korisnicko ime");
        korIme.setBounds(156, 156, 86, 14);
        background.add(korIme);
        JLabel lblLozinka = new JLabel("Lozinka");
        lblLozinka.setBounds(170, 207, 86, 14);
        background.add(lblLozinka);


        btnPrijava = new JButton("Prijava");

        btnPrijava.setBounds(275, 251, 89, 23);
        background.add(btnPrijava);
        btnPrijava.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Connect.main(null);

                Wrapper conn = null;
                try
                {

                    String username = "";
                    String password = "";

                    username = txtUser.getText().trim();
                    password = txtPassword1.getText().trim();

                    if (username.equals("")|| password.equals(""))
                    {
                        JOptionPane.showMessageDialog(null," Upisite korisnicko ime i lozinku","Greska",JOptionPane.ERROR_MESSAGE);
                    }

                    else
                    {
                        if(Connect.success.equals("greska, niste spojeni na bazu, user -> root, password -> root"))
                            login=true;

                        else {
                            try {

                                Class.forName("com.mysql.jdbc.Driver").newInstance();
                                Connection con = DriverManager.getConnection(DB_URL, "root", "root");
                                Statement stmt = (Statement) con.createStatement();
                                String query = "SELECT user_name, passwd FROM sys.quiz;";
                                stmt.executeQuery(query);
                                ResultSet rs = stmt.getResultSet();
                                while(rs.next()){

                                    String db_username = rs.getString("user_name");
                                    String db_password = rs.getString("passwd");

                                    if(username.equals(db_username) && password.equals(db_password)){
                                        System.out.println("OK");
                                        login = true;
                                    }
                                }
                            } catch (InstantiationException e1) {
                                e1.printStackTrace();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                            conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            System.out.println("Uspjesno ste spojeni na bazu..");
                        }
                        if(login == true)
                        {
                            String SMessage = "Prijavljeni ste kao "+username;
                            usernameq=username;
                            JOptionPane.showMessageDialog(null,SMessage,"Uspjesno",JOptionPane.PLAIN_MESSAGE);
                            new Pitanja().setVisible(true);
                            dispose();
                        }
                        else
                        {
                            String SMessage = "Netocna sifra ili korisnicko ime";
                            JOptionPane.showMessageDialog(null,SMessage,"Greska",JOptionPane.ERROR_MESSAGE);
                        }

                        ((java.sql.Connection)conn).close();
                    }
                }
                catch (SQLException se)
                {
                    se.printStackTrace();
                }
                catch (Exception a)
                {
                    a.printStackTrace();
                }
            }
        });


        btnReg = new JButton("Registracija");
        btnReg.setBounds(160, 251, 120, 23);
        background.add(btnReg);

        btnReg.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {
                new Registracija().setVisible(true);
                dispose();
            }

        });


    }}