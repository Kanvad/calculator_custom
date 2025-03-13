import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class tinh_s extends JFrame {
    public tinh_s() {
        setTitle("Tính S");
        setSize(600, 400);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel lbln = new JLabel("Nhập n: ");
        JTextField txtn = new JTextField(10);
        JButton btnN = new JButton("Them n");
        btnN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 1; i <= Integer.parseInt(txtn.getText()); i++) {
                    System.out.println("S = " + i);
                    JLabel lbli = new JLabel("{i}");
                    add(lbli);
                }
                setVisible(true);
            }
        });
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(btnExit, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        

        add(lbln);
        add(txtn);
        add(btnN);
        add(btnExit);
        setVisible(true);
    }

    public static void main(String[] args) {
        new tinh_s();
    }
}
