import product.Product;
import product.ProductManager;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TaxCalculatorGUI extends JFrame {
    private final ProductManager productManager = new ProductManager();
    private final JPanel panOutput;
    private final JTextField name;
    private final JTextField gross;
    private final JTextArea textArea;
    private final String CsvTitle = "Name,Gross,Tax,Net";
    public TaxCalculatorGUI() {
        JPanel panOuter = new JPanel(new BorderLayout());
        JPanel panLeft = new JPanel(new BorderLayout());
        panLeft.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel panRight = new JPanel(new BorderLayout());
        panRight.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel panBottom = new JPanel(new GridLayout(1, 3));
        panBottom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panInput = new JPanel(new BorderLayout());
        panInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panOutput = new JPanel(new BorderLayout());

        Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border insideBorder = BorderFactory.createTitledBorder("The results");
        Border theBorder = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
        panOutput.setBorder(theBorder);

        panInput.add(panLeft, BorderLayout.WEST);
        panInput.add(panRight, BorderLayout.EAST);
        panInput.add(panBottom, BorderLayout.SOUTH);

        panOuter.add(panInput, BorderLayout.NORTH);
        panOuter.add(panOutput, BorderLayout.CENTER);

        //Add the ubiquitous "Hello World" label.
        JLabel nameLabel = new JLabel("name:");
        name = new JTextField(16);
        JLabel grossLabel = new JLabel("gross:");
        gross = new JTextField(16);

        JButton calculateButton = new JButton("Calculate");
        JButton exportCsvButton = new JButton("Export CSV");
        JButton resetButton = new JButton("Reset");

        JLabel firstNameRow = new JLabel("Name    Gross    Tax    Net");
        textArea = new JTextArea(5, 10);
        JScrollPane srcPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        calculateButton.addActionListener(this::calculateTax);

        exportCsvButton.addActionListener(this::exportCsvFile);

        resetButton.addActionListener(this::clearProducts);

        panLeft.add(nameLabel, BorderLayout.WEST);
        panLeft.add(name, BorderLayout.EAST);
        panRight.add(grossLabel, BorderLayout.WEST);
        panRight.add(gross, BorderLayout.EAST);

        panBottom.add(calculateButton);
        panBottom.add(exportCsvButton);
        panBottom.add(resetButton);

        panOutput.add(firstNameRow, BorderLayout.NORTH);
        panOutput.add(srcPane, BorderLayout.CENTER);

        // Display the window.
        this.setContentPane(panOuter);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void calculateTax(ActionEvent env) {
        if (!name.getText().trim().isEmpty() && !gross.getText().trim().isEmpty()) {
            try {
                Double.parseDouble(gross.getText());
                textArea.setText("");
                productManager.addProduct(name.getText(), Double.parseDouble(gross.getText()));
            } catch (NumberFormatException e) {
                gross.setText("Invalid input");
            }
            List<Product> products = productManager.getProducts();
            Collections.reverse(products);
            for (Product product : products) {
                textArea.append(product.toString().replace(",", "    ") + "\n");
            }
            updateOutput();
        }
    }
    private void updateOutput() {
        panOutput.revalidate();
        panOutput.repaint();
        textArea.setCaretPosition(0);
    }
    private void exportCsvFile(ActionEvent env) {
        try {
            FileWriter writer = new FileWriter("products.csv");
            writer.write(CsvTitle);
            writer.write("\n");
            for (Product product : productManager.getProducts()) {
                writer.write(product.toString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void clearProducts(ActionEvent env) {
        File csvFile = new File("products.csv");
        csvFile.delete();
        productManager.removeAllProducts();
        textArea.setText("");
        updateOutput();
    }
}
