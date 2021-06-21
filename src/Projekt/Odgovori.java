package Projekt;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Odgovori extends JFrame
{
    private JPanel contentPane;
    private JButton igrajOpet;
    private JButton Izadji;

    public static void main(String[] args)
    {		Connect.main(null);

        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Odgovori window = new Odgovori();
                    window.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }


    public Odgovori() {
        setTitle("Odgovori");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        setResizable(false);
        contentPane.setLayout(new BorderLayout());
        Pitanja pitanjaObj = new Pitanja();
        pitanjaObj.setVisible(false);
        String[][] pitanjaa = pitanjaObj.getPitanja();
        Pitanja odgovori = new Pitanja();
        odgovori.setVisible(false);
        String[][] odgArray = odgovori.getOdgovori();
        JLabel pitanja = new JLabel("Tocni odgovori", SwingConstants.CENTER);
        pitanja.setFont(new Font("Ink Free",Font.BOLD,35));
        contentPane.add(pitanja, BorderLayout.NORTH);
        JPanel contentPane1 = new JPanel();
        contentPane1.setLayout(new BoxLayout(contentPane1, BoxLayout.PAGE_AXIS));
        for(int i=0,j=0;i<pitanjaa.length;i++) {

            JLabel pitanje = new JLabel((i+1)+") "+pitanjaa[i][0]);
            pitanje.setBounds(185, 10+j, 200, 200);
            pitanje.setFont(new Font("Ink Free",Font.BOLD,15));
            pitanje.setAlignmentX(Component.CENTER_ALIGNMENT);

            contentPane1.add(pitanje);

            JLabel odgovor = new JLabel("Odg) "+odgArray[i][0]);
            odgovor.setBounds(190, 30+j, 200, 200);
            odgovor.setFont(new Font("Ink Free",Font.BOLD,15));
            odgovor.setAlignmentX(Component.CENTER_ALIGNMENT);

            contentPane1.add(odgovor);
            j=j+40;

        }
        odgovori.dispose();
        pitanjaObj.dispose();
        JScrollPane scrollPane = new JScrollPane(contentPane1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        JPanel buttonGroup = new JPanel();

        contentPane.add(scrollPane);
        Izadji = new JButton("Izadji");
        Izadji.setPreferredSize(new Dimension(100, 20));
        Izadji.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                System.exit(0);
            }
        });


        igrajOpet = new JButton("Igraj opet");
        igrajOpet.setPreferredSize(new Dimension(100, 20));
        igrajOpet.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {

                Connect connect=new Connect();
                new Prijava().setVisible(true);

                dispose();
            }

        });
        buttonGroup.add(Izadji);
        buttonGroup.add(igrajOpet);
        contentPane.add(buttonGroup, BorderLayout.SOUTH, SwingConstants.CENTER);
    }

}
