package Projekt;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Wrapper;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Registracija extends JFrame
{
    private JPanel contentPane;
    private JTextField txtUser;
    private JTextField txtName;
    private JTextField txtMail;
    private JTextField txtPassword1;
    private JButton btnReg;
    private JButton btnPrijava;
    protected java.lang.String Spassword;

    static final String DB_URL = "jdbc:mysql://localhost:3306/sys?useUnicode=true&useJDBCComplian" +
            "tTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";

    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args)
    {
        Connect.main(null);


        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Registracija window = new Registracija();
                    window.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }


    public Registracija()
    {
        setTitle("Dobrodosli na kviz:"+Connect.success);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        contentPane = new JPanel();
        JLabel label1 = new JLabel("Registracija");
        label1.setText("REGISTRACIJA");
        label1.setBounds(250, 10, 75, 60);
        label1.setSize(90, 20);
        contentPane.add(label1);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon(getClass().getResource("regBack.jpg")));
        add(background);

        txtName = new JTextField();
        txtName.setBounds(250, 51, 99, 20);
        background.add(txtName);
        txtName.setColumns(10);
        txtMail = new JTextField();
        txtMail.setBounds(250, 102, 99, 20);
        background.add(txtMail);
        txtMail.setColumns(10);
        txtUser = new JTextField();
        txtUser.setBounds(250, 153, 99, 20);
        background.add(txtUser);
        txtUser.setColumns(10);
        txtPassword1 = new JTextField();
        txtPassword1.setBounds(250, 204, 99, 20);
        background.add(txtPassword1);
        txtPassword1.setColumns(10);

        JLabel lblName = new JLabel("Puno ime");
        lblName.setBounds(170,54,86,14);
        background.add(lblName);
        JLabel lblMail = new JLabel("E-mail");
        lblMail.setBounds(170, 105, 86, 14);
        background.add(lblMail);
        JLabel lblUserName = new JLabel("Korisnicko ime");
        lblUserName.setBounds(170, 156, 86, 14);
        background.add(lblUserName);
        JLabel lblPassword = new JLabel("Lozinka");
        lblPassword.setBounds(170, 207, 86, 14);
        background.add(lblPassword);



        btnReg = new JButton("Registracija");

        btnReg.setBounds(250, 251, 120, 23);
        background.add(btnReg);
        btnReg.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Connect.main(null);
                if(Connect.success.equals("Uspjesno ste spojeni na bazu")) {
                    Wrapper conn = null;
                    try
                    {
                        String full_name= "";
                        String g_mail= "";
                        String user_name = "";
                        String passwd = "";

                        full_name = txtName.getText().trim();
                        g_mail = txtMail.getText().trim();
                        user_name = txtUser.getText().trim();
                        passwd = txtPassword1.getText().trim();

                        if (user_name.equals("")|| passwd.equals(""))
                        {
                            JOptionPane.showMessageDialog(null," ime ili sifra su netocni","Pogreska",JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            String IQuery = "INSERT INTO `quiz`(`full_name`,`g_mail`,`user_name`,`passwd`) VALUES" +
                                    "('"+full_name+"','"+g_mail+"','"+user_name+"', '"+passwd+"')";
                            System.out.println(IQuery);
                            System.out.println("Spajanje na bazu podataka...");

                            conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            System.out.println("Uspjesno ste se spojili na bazu...");
                            ((Connection)conn).createStatement().execute(IQuery);
                            String SMessage = "Uspjesno ste registrirani kao :  "+user_name;

                            JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
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
                else {
                    String SMessage="Greska, morate podesiti bazu";
                    JOptionPane.showMessageDialog(null,SMessage,"About",JOptionPane.ERROR_MESSAGE);
                    new Prijava().setVisible(true);
                    dispose();
                }

            }



        });


        btnPrijava = new JButton("Prijava");

        btnPrijava.setBounds(160, 251, 89, 23);
        background.add(btnPrijava);

        btnPrijava.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {
                new Prijava().setVisible(true);
                dispose();
            }

        });
    }


}
