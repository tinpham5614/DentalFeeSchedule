import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Dental Viewer Class extends JFrame
 */
public class DentalViewer extends JFrame {
    /**
     * Dental Hash Map contains dental service information
     */
    private final HashMap<String, Dental> dentalHashMap = new HashMap<>();

    /**
     * Dental Viewer Constructor
     */
    public DentalViewer() {
        //JFrame
        setTitle("Dental Schedule Fee Search");
        setSize(700, 700);
        setResizable(false);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //JPanel
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        //JLabel
        JLabel hintLabel = new JLabel("Please enter code to search");
        hintLabel.setBounds(35, 100, 400, 30);
        panel.add(hintLabel);

        JLabel cdtCodeLabel = new JLabel("CPT Code:");
        cdtCodeLabel.setBounds(35, 150, 150, 30);
        panel.add(cdtCodeLabel);

        JLabel proceduresLabel = new JLabel("Procedures:");
        proceduresLabel.setBounds(35, 200, 150, 30);
        panel.add(proceduresLabel);

        JLabel beginDateLabel = new JLabel("Begin Date:");
        beginDateLabel.setBounds(35, 250, 150, 30);
        panel.add(beginDateLabel);

        JLabel endDataLabel = new JLabel("End Date:");
        endDataLabel.setBounds(35, 300, 150, 30);
        panel.add(endDataLabel);

        JLabel maxPayAmountLabel = new JLabel("Max Pay Amount:");
        maxPayAmountLabel.setBounds(35, 350, 150, 30);
        panel.add(maxPayAmountLabel);

        //JTextField
        JTextField cdtCodeField = new JTextField();
        cdtCodeField.setBounds(200, 150, 100, 30);
        panel.add(cdtCodeField);

        JTextField proceduresField = new JTextField();
        proceduresField.setBounds(200, 200, 400, 30);
        panel.add(proceduresField);
        proceduresField.setEditable(false);

        JTextField beginDateField = new JTextField();
        beginDateField.setBounds(200, 250, 100, 30);
        panel.add(beginDateField);
        beginDateField.setEditable(false);

        JTextField endDateField = new JTextField();
        endDateField.setBounds(200, 300, 100, 30);
        panel.add(endDateField);
        endDateField.setEditable(false);

        JTextField maxPayAmountField = new JTextField();
        maxPayAmountField.setBounds(200, 350, 100, 30);
        panel.add(maxPayAmountField);
        maxPayAmountField.setEditable(false);

        cdtCodeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = cdtCodeField.getText();

                if(!dentalHashMap.containsKey(code)){
                    hintLabel.setText("Code not FOUND! Please try again!");
                    cdtCodeField.setText("");
                    proceduresField.setText("");
                    beginDateField.setText("");
                    endDateField.setText("");
                    maxPayAmountField.setText("");
                } else {
                    hintLabel.setText("Code FOUND!");
                    proceduresField.setText(dentalHashMap.get(code).procedures());
                    beginDateField.setText(dentalHashMap.get(code).beginDate());
                    endDateField.setText(dentalHashMap.get(code).endDate());
                    maxPayAmountField.setText(dentalHashMap.get(code).maxPayAmount());
                }
            }
        });

        setVisible(true);

        /* Read data from file */
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("DentalFeeSchedule.csv"));
        } catch (FileNotFoundException e) {
            JOptionPane jOptionPane = new JOptionPane();
            JOptionPane.showMessageDialog(null, "File not found, please check try again!");
            panel.add(jOptionPane);
            cdtCodeField.setEditable(false);
        }
        if (scanner != null) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] dentalData = scanner.nextLine().split("~");
                Dental dental = new Dental(
                        dentalData[0],
                        dentalData[1],
                        dentalData[2],
                        dentalData[3],
                        dentalData[4]
                );
                dentalHashMap.put(dentalData[0], dental);
            }
            scanner.close();
        }
    }
    public static void main(String[] args){
        new DentalViewer();
    }
}
