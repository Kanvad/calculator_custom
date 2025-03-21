import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class cal1 extends JFrame {
	private static final long serialVersionUID = 1L; //tắt cảnh báo về serialVersionUID dù không dùng

    private JTable table;
    private DefaultTableModel model;
    
    double numN = 0.0;
    int soHang = 0;
    double numX = 0.0;
    double soMu = 0.0;
    double numS2 = 0.0;

    public cal1() {
        setTitle("Cal1");
        setSize(600, 600);
        setLocation(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel mainPanel = new JPanel(null);
        
        JLabel lbln = new JLabel("n: ");
        lbln.setBounds(25, 25, 100, 25);
        JTextField txtn = new JTextField(10);
        txtn.setBounds(75, 25, 100, 25);
        txtn.setEditable(false);
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
        JLabel lblnote1 = new JLabel("Note: Tính x trước rồi tính s'^2 và s'");
        lblnote1.setBounds(25, 115, 400, 25);
        JButton btnTinh = new JButton("Tính x");
        btnTinh.setBounds(25, 150, 100, 25);
        JTextField txtTinhX = new JTextField(10);
        txtTinhX.setBounds(25, 175, 100, 25);
        txtTinhX.setEditable(false);
        JButton btnTinhS = new JButton("Tính s'^2 và s");
        btnTinhS.setBounds(200, 150, 150, 25);
        JTextField txtTinhS = new JTextField(10);
        txtTinhS.setBounds(300, 175, 100, 25);
        txtTinhS.setEditable(false);
        JTextField txtTinhS2 = new JTextField(10);
        txtTinhS2.setBounds(200, 175, 100, 25);
        txtTinhS2.setEditable(false);
        
        mainPanel.add(lbln);
        mainPanel.add(txtn);
        mainPanel.add(lblSomu);
        mainPanel.add(txtSomu);
        mainPanel.add(lblSoHang);
        mainPanel.add(txtSoHang);
        mainPanel.add(btnThem);
        mainPanel.add(btnTinh);
        mainPanel.add(lblnote1);
        mainPanel.add(txtTinhX);
        mainPanel.add(btnTinhS);
        mainPanel.add(txtTinhS);
        mainPanel.add(txtTinhS2);
        
        JPanel tablePanel = new JPanel(new BorderLayout());
        model = new DefaultTableModel();
        model.addColumn("Chiều cao");
        model.addColumn("Số lượng");
        table = new JTable(model);
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        mainPanel.add(tablePanel);
        tablePanel.setBounds(25, 225, 500, 250);
        
        tabbedPane.addTab("Input", mainPanel);
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(btnExit, "Do you want to exit?", "Exit",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        btnThem.addActionListener(e -> {
            soHang = Integer.parseInt(txtSoHang.getText());
            soMu = Double.parseDouble(txtSomu.getText());
            for (int i = 0; i < soHang; i++) {
                model.addRow(new Object[2]);
            }
            tablePanel.revalidate();
            tablePanel.repaint();
        });

        btnTinh.addActionListener(e -> {
            Double sum = 0.0;
            numN = 0.0;
            for (int i = 0; i < soHang; i++) {
                sum += Double.parseDouble(table.getValueAt(i, 0).toString())
                        * Double.parseDouble(table.getValueAt(i, 1).toString());
                numN += Double.parseDouble(table.getValueAt(i, 1).toString());
            }
            numX = sum / numN;
            txtn.setText(String.valueOf(numN));
            txtTinhX.setText(String.format("%.2f", numX));
        });

        btnTinhS.addActionListener(e -> {
            Double sumS2 = 0.0;
            for (int i = 0; i < soHang; i++) {
                sumS2 += ((Math.pow((Double.parseDouble(table.getValueAt(i, 0).toString()) - numX), soMu))
                        * Double.parseDouble(table.getValueAt(i, 1).toString()));
            }
            numS2 = sumS2 / numN;
            txtTinhS2.setText(String.format("%.2f", numS2));
            txtTinhS.setText(String.format("%.2f", Math.sqrt(numS2)));
        });
        
        add(tabbedPane, BorderLayout.CENTER);
        add(btnExit, BorderLayout.SOUTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new cal1();
    }
}
