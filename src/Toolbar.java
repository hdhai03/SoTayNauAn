import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Toolbar extends JPanel implements ActionListener {
    private final JButton viewButton;
    private final JButton addButton;
    private final JButton editButton;
    private final JButton deleteButton;
    private final JButton sortButton;
    private final JButton scaleButton;
    private final JButton helpButton;

    private final JLabel panelLabel;
    private final JLabel actionLabel;

    private ToolbarListener buttonListener;

    public Toolbar() {
        viewButton = createButton("Công thức");
        addButton = createButton("Thêm công thức");
        editButton = createButton("Sửa công thức");
        deleteButton = createButton("Xoá công thức");
        sortButton = createButton("Tìm kiếm công thức");
        scaleButton = createButton("Tính khẩu phần");
        helpButton = createButton("Hướng dẫn sử dụng");

        panelLabel = new JLabel("");
        actionLabel = new JLabel("");

        viewButton.addActionListener(this);
        addButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        sortButton.addActionListener(this);
        scaleButton.addActionListener(this);
        helpButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(panelLabel);
        add(viewButton);
        add(addButton);
        add(editButton);
        add(deleteButton);
        add(sortButton);
        add(scaleButton);
        add(helpButton);
        add(actionLabel);
    }

    public void setButtonListener(ToolbarListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == viewButton) {
            if (buttonListener != null) {
                buttonListener.changePanel("viewPanel");
            }
        } else if (clicked == addButton) {
            if (buttonListener != null) {
                buttonListener.changePanel("addPanel");
            }
        } else if (clicked == editButton) {
            if (buttonListener != null) {
                buttonListener.changePanel("editPanel");
            }
        } else if (clicked == deleteButton) {
            if (buttonListener != null) {
                buttonListener.changePanel("delete");
            }
        } else if (clicked == scaleButton) {
            if (buttonListener != null) {
                buttonListener.changePanel("scalePanel");
            }
        } else if (clicked == sortButton) {
            if (buttonListener != null) {
                buttonListener.changePanel("sortPanel");
            }
        } else if (clicked == helpButton) {
            if (buttonListener != null) {
                buttonListener.changePanel("helpPanel");
            }
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }
}
