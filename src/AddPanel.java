import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPanel extends JPanel implements ActionListener {
    private JLabel panelLabel, titleLabel, introductionLabel, ingredientsLabel, directionsLabel;
    private JTextField titleField;
    private JTextArea introductionArea, ingredientsArea, directionsArea;
    private JButton confirmButton, clearButton;

    private GridBagConstraints gc = new GridBagConstraints();

    private AddRecipeListener buttonListener;

    private final String TITLE_DEFAULT = "Nhập tên món ăn";
    private final String INTRODUCTION_DEFAULT = "Nhập giới thiệu";
    private final String INGREDIENTS_DEFAULT = "Nhập nguyên liệu cần thiết bằng cú pháp dưới đây \n" +
            "'Số lượng' + '--' + 'Tên nguyên liệu'.\n" +
            "Ví dụ:\n" +
            "3 Cốc--Nước\n2--Trứng";
    private final String DIRECTIONS_DEFAULT = "Nhập các bước chế biến theo ví dụ dưới đây. VD.\n" +
            "1. Vo gạo\n2. Nấu cơm.";

    public AddPanel() {
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

        confirmButton = new JButton("Thêm");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(0, 153, 51));
        confirmButton.setForeground(Color.white);
        confirmButton.setFocusPainted(false);

        clearButton = new JButton("Xoá");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBackground(new Color(255, 102, 102));
        clearButton.setForeground(Color.white);
        clearButton.setFocusPainted(false);

        clearText();

        confirmButton.addActionListener(this);
        clearButton.addActionListener(this);

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
        add(clearButton, gc);

        gc.gridx = 1;
        gc.gridy = 5;
        add(confirmButton, gc);
    }

    public void setButtonListener(AddRecipeListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void clearText() {
        titleField.setText(TITLE_DEFAULT);
        introductionArea.setText(INTRODUCTION_DEFAULT);
        ingredientsArea.setText(INGREDIENTS_DEFAULT);
        directionsArea.setText(DIRECTIONS_DEFAULT);

        titleField.setForeground(Color.GRAY);
        introductionArea.setForeground(Color.GRAY);
        ingredientsArea.setForeground(Color.GRAY);
        directionsArea.setForeground(Color.GRAY);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == confirmButton) {
            if (buttonListener != null) {
                String title = titleField.getText();
                String intro = introductionArea.getText();
                String ingredients = ingredientsArea.getText();
                String directions = directionsArea.getText();

                buttonListener.addRecipe(title, intro, ingredients, directions);

                clearText();
            }
        } else if (clicked == clearButton) {
            clearText();
        }
    }
}
