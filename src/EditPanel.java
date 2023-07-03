import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.gc;

public class EditPanel extends JPanel implements ActionListener {
    private JLabel panelLabel, titleLabel, introductionLabel, ingredientsLabel, directionsLabel;
    private JTextField titleField;
    private JTextArea introductionArea, ingredientsArea, directionsArea;
    private JButton confirmButton, cancelButton;

    private GridBagConstraints gc = new GridBagConstraints();
    private EditRecipeListener buttonListener;

    public EditPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));

        panelLabel = new JLabel("Thêm món ăn");
        panelLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panelLabel.setForeground(new Color(102, 102, 102));

        titleLabel = new JLabel("Tên món: ");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        introductionLabel = new JLabel("Giới thiệu: ");
        introductionLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        ingredientsLabel = new JLabel("Nguyên liệu: ");
        ingredientsLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        directionsLabel = new JLabel("Cách chế biến: ");
        directionsLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        titleField = new JTextField();
        titleField.setFont(new Font("Arial", Font.PLAIN, 14));

        introductionArea = new JTextArea();
        introductionArea.setFont(new Font("Arial", Font.PLAIN, 14));

        ingredientsArea = new JTextArea();
        ingredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));

        directionsArea = new JTextArea();
        directionsArea.setFont(new Font("Arial", Font.PLAIN, 14));

        confirmButton = new JButton("Sửa");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(0, 153, 51));
        confirmButton.setForeground(Color.white);
        confirmButton.setFocusPainted(false);

        cancelButton = new JButton("Huỷ");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(255, 102, 102));
        cancelButton.setForeground(Color.white);
        cancelButton.setFocusPainted(false);
        
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);

        ////////////GUI

        gc.insets = new Insets(10, 10, 10, 10);

        //Add Panel Label
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 5;
        gc.anchor = GridBagConstraints.CENTER;

        add(panelLabel, gc);

        //Column 1
        gc.gridwidth = 1; //reset gridwidth to default

        gc.weightx = 0.2;
        gc.weighty = 0.2;
        gc.anchor = GridBagConstraints.LINE_END;

        gc.gridx = 0;
        gc.gridy = 1;
        add(titleLabel, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(introductionLabel, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        add(ingredientsLabel, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        add(directionsLabel, gc);

        //Column 2
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.fill = GridBagConstraints.BOTH;

        gc.gridx = 1;
        gc.gridy = 1;
        add(titleField, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(new JScrollPane(introductionArea), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        add(new JScrollPane(ingredientsArea), gc);

        gc.gridx = 1;
        gc.gridy = 4;
        add(new JScrollPane(directionsArea), gc);

        //Buttons
        gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.fill = GridBagConstraints.BOTH;

        gc.gridx = 0;
        gc.gridy = 5;
        add(cancelButton, gc);

        gc.gridx = 1;
        gc.gridy = 5;
        add(confirmButton, gc);
    }

    public void setButtonListener(EditRecipeListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void editPanel(Recipe r) {
        titleField.setText(r.getTitle());
        introductionArea.setText(r.getIntroduction());
        ingredientsArea.setText(r.getIngredientsForEdit());
        directionsArea.setText(r.getDirections());
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == confirmButton) {
            if (buttonListener != null) {
                String title = titleField.getText();
                String intro = introductionArea.getText();
                String ingredients = ingredientsArea.getText();
                String directions = directionsArea.getText();

                buttonListener.editRecipe(true, title, intro, ingredients, directions);
            }
        } else if (clicked == cancelButton) {
            if (buttonListener != null) {
                buttonListener.editRecipe(false, null, null, null, null);
            }
        }
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(80, 30));
        return button;
    }
}
