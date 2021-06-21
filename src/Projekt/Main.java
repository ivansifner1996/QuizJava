package Projekt;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main extends JFrame {
    public JPanel panel;
    public static void main(String args[])
    {
        Connect.main(null);
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Main window = new Main();

                    window.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main()
    {

        setTitle("Kviz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 420);
        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        setResizable(false);
        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon(getClass().getResource("kviz.jpg")));
        add(background);
        JButton zapocni=new JButton("Zapocni kviz");
        zapocni.setBounds(630,120,100,100);
        zapocni.setSize(110, 40);
        background.add(zapocni);
        zapocni.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {
                new Prijava().setVisible(true);
                dispose();
            }

        });

        JLabel label3 = new JLabel("STATUS");
        label3.setText("Status:"+Connect.success);
        label3.setBounds(20, 310, 600, 100);
        background.add(label3);
        label3.setForeground(Color.WHITE);
        if(Connect.success.equalsIgnoreCase("Uspjesno ste spojeni na bazu")) {
            label3.setForeground(Color.green);
        }else
            label3.setForeground(Color.red);






    }


}

