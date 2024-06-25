package Sistem_Zaposlenih;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PocetnaStrana extends JFrame implements ActionListener {

    JLabel l1;
    Font f, f1;
    JPanel p1;

    PocetnaStrana() {
        super("Pocetna strana");
        setLocation(0, 0);
        setSize(1550, 800);

        f = new Font("Lucida Fax", Font.BOLD, 20);
        f1 = new Font("MS UI Gothic", Font.BOLD, 18);

        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("Sistem_Zaposlenih/Icon/PocetnaStrana.jpg"));
        Image img = ic.getImage().getScaledInstance(1550, 800, Image.SCALE_DEFAULT);
        ImageIcon ic1 = new ImageIcon(img);
        l1 = new JLabel(ic1);

        JMenuBar m1 = new JMenuBar();
        JMenu menu1 = new JMenu("Zaposleni");
        JMenuItem menuItem1 = new JMenuItem("Dodaj Zaposlenog");
        JMenuItem menuItem2 = new JMenuItem("Pogledaj Zaposlenog");

        JMenu menu2 = new JMenu("Promeni");
        JMenuItem menuItem3 = new JMenuItem("Promeni Detalje");

        JMenu menu3 = new JMenu("Prisustvo");
        JMenuItem menuItem4 = new JMenuItem("Dodaj Prisustvo");
        JMenuItem menuItem5 = new JMenuItem("Pogledaj Prisustvo");

        JMenu menu4 = new JMenu("Odsustvo");
        JMenuItem menuItem6 = new JMenuItem("Podnesi Odsustvo");
        JMenuItem menuItem7 = new JMenuItem("Pogledaj Odsustva");

        JMenu menu6 = new JMenu("Izbrisi");
        JMenuItem menuItem10 = new JMenuItem("Izbrisi Zaposlenog");

        JMenu menu7 = new JMenu("Exit");
        JMenuItem menuItem11 = new JMenuItem("Izloguj se");

        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu2.add(menuItem3);
        menu3.add(menuItem4);
        menu3.add(menuItem5);
        menu4.add(menuItem6);
        menu4.add(menuItem7);
        menu6.add(menuItem10);
        menu7.add(menuItem11);

        m1.add(menu1);
        m1.add(menu2);
        m1.add(menu3);
        m1.add(menu4);
        m1.add(menu6);
        m1.add(menu7);

        menu1.setFont(f);
        menu2.setFont(f);
        menu3.setFont(f);
        menu4.setFont(f);
        menu6.setFont(f);
        menu7.setFont(f);

        menuItem1.setFont(f1);
        menuItem2.setFont(f1);
        menuItem3.setFont(f1);
        menuItem4.setFont(f1);
        menuItem5.setFont(f1);
        menuItem6.setFont(f1);
        menuItem7.setFont(f1);
        menuItem10.setFont(f1);
        menuItem11.setFont(f1);

        m1.setBackground(Color.black);

        menu1.setForeground(Color.GRAY);
        menu2.setForeground(Color.GRAY);
        menu3.setForeground(Color.GRAY);
        menu4.setForeground(Color.GRAY);
        menu6.setForeground(Color.GRAY);
        menu7.setForeground(Color.RED);

        menuItem1.setForeground(Color.black);
        menuItem2.setForeground(Color.black);
        menuItem3.setForeground(Color.black);
        menuItem4.setForeground(Color.black);
        menuItem5.setForeground(Color.black);
        menuItem6.setForeground(Color.black);
        menuItem7.setForeground(Color.black);
        menuItem10.setForeground(Color.black);
        menuItem11.setForeground(Color.black);

        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        menuItem4.addActionListener(this);
        menuItem5.addActionListener(this);
        menuItem6.addActionListener(this);
        menuItem7.addActionListener(this);
        menuItem10.addActionListener(this);
        menuItem11.addActionListener(this);

        setJMenuBar(m1);
        add(l1);
    }

    public void actionPerformed(ActionEvent e) {
        String komanda = e.getActionCommand();

        switch (komanda) {
            case "Dodaj Zaposlenog":
                new Dodaj_Zaposlenog();
                break;
            case "Pogledaj Zaposlenog":
                new Pogledaj_Zaposlenog();
                break;
            case "Promeni Detalje":
                new Promeni_Detalje();
                break;
            case "Dodaj Prisustvo":
                new Dodaj_Prisustvo();
                break;
            case "Pogledaj Prisustvo":
                new Pogledaj_Prisustvo();
                break;
            case "Podnesi Odsustvo":
                new Podnesi_Odsustvo();
                break;
            case "Pogledaj Odsustva":
                new Pogledaj_Odsustvo();
                break;
            case "Izbrisi Zaposlenog":
                new Izbrisi_Zaposlenog();
                break;
            case "Izloguj se":       
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        new PocetnaStrana().setVisible(true);
    }
}
