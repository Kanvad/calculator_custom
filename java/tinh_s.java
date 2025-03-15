import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class tinh_s extends JFrame {
    private JTable table;
    private DefaultTableModel model;

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
        JLabel lblSomu = new JLabel("So mu: ");
        lblSomu.setBounds(25, 75, 100, 25);
        JTextField txtSomu = new JTextField(10);
        txtSomu.setBounds(75, 75, 100, 25);
        JTextField txtSoHang = new JTextField(10);
        JLabel lblSoHang = new JLabel("So hang:");
        lblSoHang.setBounds(200, 75, 50, 25);
        txtSoHang.setBounds(250, 75, 100, 25);
        JButton btnThem = new JButton("Them");
        btnThem.setBounds(200, 25, 100, 25);
        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(475, 25, 100, 25);
        JButton btnTinh = new JButton("Tinh");
        btnTinh.setBounds(350, 25, 100, 25);

        // Result x
        JLabel lblTinhX = new JLabel("Ket qua:");
        lblTinhX.setBounds(25, 450, 100, 25);
        JTextField txtTinhX = new JTextField(10);
        txtTinhX.setBounds(75, 450, 100, 25);

        // Tinh S^2 va S
        JButton btnTinhS = new JButton("Tinh S^2 va S");
        btnTinhS.setBounds(200, 450, 100, 25);
        JTextField txtTinhS = new JTextField(10);
        txtTinhS.setBounds(300, 450, 100, 25);
        JTextField txtTinhS2 = new JTextField(10);
        txtTinhS2.setBounds(400, 450, 100, 25);

        // Tạo tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(25, 125, 550, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tabbedPane.addTab("Tinh S va S^2", panel);

        // Tạo table model và table
        model = new DefaultTableModel();
        model.addColumn("STT");
        model.addColumn("Chieu cao");
        model.addColumn("So mu");
        model.addColumn("So luong");
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
                int soDong = Integer.parseInt(txtSoHang.getText());

                for (int i = 0; i < soDong; i++) {
                    Object[] row = new Object[4];
                    row[0] = i + 1;
                    row[2] = txtSomu.getText(); // Đặt giá trị mặc định cho cột 3
                    model.addRow(row);
                }

                panel.revalidate();
                panel.repaint();
                
            }
        });

        btnTinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = table.getRowCount();
                int cols = table.getColumnCount();

                // Lặp qua từng hàng và cột
                Double sum = 0.0;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        Object value = table.getValueAt(i, j);
                        if (j == 3) {
                            sum += Double.parseDouble(value.toString()) * Double.parseDouble(table.getValueAt(i, 1).toString());
                        }
                    }
                }
                txtTinhX.setText(String.format("%.2f", sum/Double.parseDouble(txtn.getText())));
            }
        });

        btnTinhS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = table.getRowCount();

                // Lặp qua từng hàng
                Double sumS2 = 0.0;
                Double numX = Double.parseDouble(txtTinhX.getText());
                Double numSomu = Double.parseDouble(txtSomu.getText());

                for (int i = 1; i < rows; i++) {
                    Object value = table.getValueAt(i, 3);
                    sumS2 += (Math.pow((Double.parseDouble(table.getValueAt(i, 1).toString()) - numX), numSomu)) * Double.parseDouble(value.toString());
                }
                Double sumS2new = sumS2 / Double.parseDouble(txtn.getText());
                txtTinhS2.setText(String.format("%.2f", sumS2new));
                txtTinhS.setText(String.format("%.2f", Math.sqrt(sumS2new)));

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
        add(lblTinhX);
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
