import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScalePanel extends JPanel implements ActionListener {
    private JLabel panelLabel, typeLabel, amountLabel;
    private JComboBox<String> typeBox;
    private JTextField amountField;
    private JButton cancelButton, confirmButton;

    private GridBagConstraints gc = new GridBagConstraints();

    private ScaleRecipeListener buttonListener;

    public ScalePanel() {
        setLayout(new GridBagLayout());

        panelLabel = new JLabel("Tính khẩu phần");
        typeLabel = new JLabel("Bạn muốn tăng hay giảm khẩu phần?");
        amountLabel = new JLabel("Bạn muốn tăng/giảm khẩu phần mấy lần?");

        typeBox = new JComboBox<String>();

        typeBox.addItem("Tăng");
        typeBox.addItem("Giảm");

        amountField = new JTextField(20);

        cancelButton = new JButton("Huỷ");
        confirmButton = new JButton("Xác nhận");

        cancelButton.addActionListener(this);
        confirmButton.addActionListener(this);
        ////////GUI

        gc.insets = new Insets(0, 20, 20, 40);

        //Panel Label
        gc.weightx = 2;
        gc.weighty = 2;
        gc.gridwidth = 5;
        gc.gridx = 0;
        gc.gridy = 0;

        add(panelLabel, gc);

        //Column 1
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 1;

        add(typeLabel, gc);

        gc.gridx = 0;
        gc.gridy = 2;

        add(amountLabel, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.fill = GridBagConstraints.BOTH;

        add(cancelButton, gc);

        //Column 2
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;

        add(typeBox, gc);

        gc.gridx = 1;
        gc.gridy = 2;

        add(amountField, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.fill = GridBagConstraints.BOTH;

        add(confirmButton, gc);
    }

    public void setButtonListener(ScaleRecipeListener buttonListener) { this.buttonListener = buttonListener; }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if(clicked == confirmButton) {
            if(buttonListener != null) {
                int amount;
                String amountStr = amountField.getText();
                String typeStr = typeBox.getSelectedItem().toString();
                boolean scaleUp;

                if (typeStr.equals("Tăng"))
                    scaleUp = true;
                else
                    scaleUp = false;

                if (amountStr == null) {
                    String text = "Bạn chưa nhập giá trị muốn tăng";
                    JOptionPane.showMessageDialog(null, text, text, JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    amount = Integer.parseInt(amountStr);
                    if (amount <= 0) {
                       String text = "Giá trị bạn nhập không hợp lệ";
                    JOptionPane.showMessageDialog(null, text, text, JOptionPane.ERROR_MESSAGE);
                    return; 
                    }
                } catch (Exception ex) {
                    String text = "Giá trị bạn nhập không hợp lệ";
                    JOptionPane.showMessageDialog(null, text, text, JOptionPane.ERROR_MESSAGE);
                    return;
                }

                buttonListener.scaleRecipe(true, scaleUp, amount);
            }
        }
        else if(clicked == cancelButton) {
            if(buttonListener != null) {
                buttonListener.scaleRecipe(false, true, 0);
            }
        }
    }
}
