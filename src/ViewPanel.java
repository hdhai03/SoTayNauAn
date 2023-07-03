import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ViewPanel extends JPanel implements ActionListener {
    private JTextArea textArea;
    private JButton leftButton;
    private JButton rightButton;

    private ViewRecipeListener buttonListener;

    public ViewPanel() {
        textArea = new JTextArea();
        leftButton = createButton("<");
        rightButton = createButton(">");

        leftButton.addActionListener(this);
        rightButton.addActionListener(this);

        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);

        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateViewPanel(Recipe r) {
        textArea.setText("");

        if (r != null) {
            appendText("Tên món", true);
            appendText(r.getTitle(), false);
            appendText("Giới thiệu", true);
            appendText(r.getIntroduction(), false);
            appendText("Nguyên liệu", true);
            appendText(r.getIngredients(), false);
            appendText("Cách chế biến", true);
            appendText(r.getDirections(), false);
        } else {
            appendText("Không có món ăn nào trong danh sách.", false);
        }

        textArea.setCaretPosition(0);
    }

    private void appendText(String text, boolean isSectionHeader) {
        if (isSectionHeader) {
            textArea.append("----------------\n");
            textArea.append(text);
            textArea.append("\n----------------\n");
        } else {
            textArea.append(text);
            textArea.append("\n");
        }
    }

    public void setButtonListener(ViewRecipeListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == leftButton) {
            if (buttonListener != null) {
                buttonListener.switchRecipe("left");
            }
        } else if (clicked == rightButton) {
            if (buttonListener != null) {
                buttonListener.switchRecipe("right");
            }
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(80, 30));
        return button;
    }
}
