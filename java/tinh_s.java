import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class tinh_s extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    // Tạo biến
    double numN = 0.0;
    int soHang = 0;
    double numX = 0.0;
    double soMu = 0.0;
    double numS2 = 0.0;
    
    public tinh_s() {
        setTitle("Tính S");
        setSize(600, 600);
        setLocation(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        

        // Tạo các component
        JLabel lbln = new JLabel("Nhập n: ");
        lbln.setBounds(25, 25, 100, 25);
        JTextField txtn = new JTextField(10);
        txtn.setBounds(75, 25, 100, 25);
        JLabel lblSomu = new JLabel("Số mũ: ");
        lblSomu.setBounds(25, 75, 100, 25);
        JTextField txtSomu = new JTextField(10);
        txtSomu.setBounds(75, 75, 100, 25);
        JLabel lblSoHang = new JLabel("Số hàng:");
        lblSoHang.setBounds(200, 75, 75, 25);
        JTextField txtSoHang = new JTextField(10);
        txtSoHang.setBounds(275, 75, 100, 25);
        JButton btnThem = new JButton("Thêm");
        btnThem.setBounds(200, 25, 100, 25);
        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(475, 25, 100, 25);
        JButton btnTinh = new JButton("Tính x");
//        btnTinh.setBounds(350, 25, 100, 25);
        btnTinh.setBounds(25, 450, 100, 25);
        
        // Tinh x
        JTextField txtTinhX = new JTextField(10);
        txtTinhX.setBounds(25, 475, 100, 25);

        // Tinh S^2 va S
        JButton btnTinhS = new JButton("Tính S^2 và S");
        btnTinhS.setBounds(200, 450, 150, 25);
        JTextField txtTinhS = new JTextField(10);
        txtTinhS.setBounds(300, 475, 100, 25);
        JTextField txtTinhS2 = new JTextField(10);
        txtTinhS2.setBounds(200, 475, 100, 25);

        // Tạo tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(25, 125, 550, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tabbedPane.addTab("Tinh S va S^2", panel);

        // Tạo table model và table
        model = new DefaultTableModel();
//        model.addColumn("STT");
        model.addColumn("Chiều cao");
//        model.addColumn("So mu");
        model.addColumn("Số lượng");
        table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Thêm action listener cho các button
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(btnExit, "Do you want to exit?", "Exit",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	numN = Double.parseDouble(txtn.getText());
                soHang = Integer.parseInt(txtSoHang.getText());
                soMu = Double.parseDouble(txtSomu.getText());

                for (int i = 0; i < soHang; i++) {
                    Object[] row = new Object[4];
//                    row[0] = i + 1;
//                    row[2] = txtSomu.getText();
                    model.addRow(row);
                }

                panel.revalidate();
                panel.repaint();
                
            }
        });

        btnTinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Lặp qua từng hàng
                Double sum = 0.0;
                for (int i = 0; i < soHang; i++) {
                	sum += Double.parseDouble(table.getValueAt(i, 0).toString()) * Double.parseDouble(table.getValueAt(i, 1).toString());
                }
                numX = Double.parseDouble(String.format("%.2f", sum/numN));
                txtTinhX.setText(String.valueOf(numX));
            }
        });

        btnTinhS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Lặp qua từng hàng
                Double sumS2 = 0.0;
                for (int i = 0; i < soHang; i++) {
                    sumS2 += ((Math.pow((Double.parseDouble(table.getValueAt(i, 0).toString()) - numX), soMu)) * Double.parseDouble(table.getValueAt(i, 1).toString()));
//                    System.out.println(sumS2);
                }
                numS2 = sumS2 / numN;
                txtTinhS2.setText(String.format("%.2f", numS2));
                txtTinhS.setText(String.format("%.2f", Math.sqrt(numS2)));
                System.out.println(numS2 + "///" + Math.sqrt(numS2));

            }
        });

        // Thêm các component vào frame
        add(tabbedPane);
        add(lbln);
        add(txtn);
        add(lblSomu);
        add(txtSomu);
        add(lblSoHang);
        add(txtSoHang);
        add(btnThem);
        add(btnExit);
        add(btnTinh);
        add(txtTinhX);
        add(btnTinhS);
        add(txtTinhS);
        add(txtTinhS2);

        setVisible(true);
    }

    public static void main(String[] args) {
        new tinh_s();
    }
}
