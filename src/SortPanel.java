import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SortPanel extends JPanel implements ActionListener {
    private JLabel panelLabel, typeLabel, amountLabel;
    private JComboBox<String> typeBox;
    private JTextField nameField;
    private JTextArea displayArea;
    private JButton clearButton, confirmButton;

    private SortRecipeListener buttonListener;

    private ArrayList<Recipe> recipeArr;

    public SortPanel() {
        setLayout(new GridBagLayout());

        panelLabel = new JLabel("Tìm kiếm món ăn");
        typeLabel = new JLabel("Tìm kiếm bằng nguyên liệu/tên món ăn?");
        amountLabel = new JLabel("Điền tên nguyên liệu / món ăn: ");

        typeBox = new JComboBox<>();
        typeBox.addItem("Nguyên liệu");
        typeBox.addItem("Tên món");

        nameField = new JTextField(20);

        displayArea = new JTextArea();
        clearButton = new JButton("Xoá bộ lọc");
        confirmButton = new JButton("Tìm kiếm");

        displayArea.setEditable(false);
        clearButton.addActionListener(this);
        confirmButton.addActionListener(this);

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.WEST;
        add(panelLabel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(typeLabel, gc);

        gc.gridx = 1;
        add(typeBox, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(amountLabel, gc);

        gc.gridx = 1;
        add(nameField, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        add(clearButton, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        add(confirmButton, gc);

        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, gc);
    }

    public void setButtonListener(SortRecipeListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void updateRecipeArr(ArrayList<Recipe> recipeArr) {
        this.recipeArr = new ArrayList<>(recipeArr);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == confirmButton) {
            if (buttonListener != null) {
                buttonListener.sortRecipe();
                sortArray();
            }
        }

        if (clicked == clearButton) {
            if (buttonListener != null) {
                displayArea.setText(null);
            }
        }
    }

    public void sortArray() {
        String typeStr = typeBox.getSelectedItem().toString();
        String searchStr = nameField.getText();
        if (searchStr.isEmpty()) {
            String text = "Bạn chưa nhập tên nguyên liệu/món ăn cần tìm";
            JOptionPane.showMessageDialog(null, text, text, JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int searchInt = Integer.parseInt(searchStr);
            String text = "Tên món bạn vừa nhập không hợp lệ (Tên món không phải là 1 số)";
            JOptionPane.showMessageDialog(null, text, text, JOptionPane.ERROR_MESSAGE);
            return;
        } catch (NumberFormatException e) {
        
        }
        if (typeStr.equals("Nguyên liệu")) {
            String noIngredient = "";

            displayArea.setText(null);
            for (Recipe r : recipeArr) {
                String amountStr = r.amountOfIngredient(searchStr);
                if (amountStr != null) {
                    displayArea.append("\"" + r.getTitle() + "\" cần " + amountStr + " " + searchStr + "\n");
                } else {
                    noIngredient += "\"" + r.getTitle() + "\" không cần " + searchStr + "\n";
                }
            }

            displayArea.append(noIngredient);
        } else if (typeStr.equals("Tên món")) {
            boolean swapped;
            int n = recipeArr.size();

            for (int i = 0; i < n - 1; i++) {
                swapped = false;
                for (int j = 0; j < n - i - 1; j++) {
                    String firstTitle = recipeArr.get(j).getTitle();
                    String secondTitle = recipeArr.get(j + 1).getTitle();

                    if (Math.abs(searchStr.compareTo(firstTitle)) > Math.abs(searchStr.compareTo(secondTitle))) {
                        Recipe temp = recipeArr.get(j);
                        recipeArr.set(j, recipeArr.get(j + 1));
                        recipeArr.set(j + 1, temp);
                        swapped = true;
                    }
                }

                if (!swapped) {
                    break;
                }
            }

            displayArea.setText(null);
            for (Recipe r : recipeArr) {
                displayArea.append(r.getTitle() + "\n");
            }
        }
    }
}
