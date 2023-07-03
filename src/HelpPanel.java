import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {

    private JTextArea display;
    private final String displayStr =
            "Cháo mừng bạn đến với ứng dụng 'Sổ tay nấu ăn'.\n"
            + "'Sổ tay nấu ăn' cho phép bạn lưu trữ và sửa đổi các món ăn\n\n"
            + "Có 5 trang bạn có thể chuyển đến.\n\n"
            + "1. Công thức\n"
            + "Trang này giúp bạn xem được danh sách các món ăn mà bạn đã thêm. Nháy chuột vào 2 nút mũi tên 2 bên để chuyển món.\n\n"
            + "2. Thêm công thức\n"
            + "Trang này cho phép bạn thêm các món ăn để lưu trữ nó trong ứng dụng. \n "
            + "Mỗi món ăn được lưu lại dưới dạng 'txt' ở mục 'Recipes'.\n"
            + "Bạn cần điền đủ từng phần trong đó. Dùng '--' để ngăn cách giữa số lượng và tên nguyên liệu ở phần 'Nguyên liệu' để có thể lưu trữ chính xác. "
            + "3. Tìm kiếm công thức"
            + "Trang này cho phép bạn tìm kiếm món ăn theo tên của món đó hoặc tên của nguyên liệu mà món đó dùng.\n"
            + "'Sửa công thức' cho phép bạn chỉnh sửa mọi thông tin về món ăn.\n"
            + "'Xoá công thức' cho phép bạn xoá món ăn đang xem ở phần 'Công thức'.\n"
            + "'Tính khẩu phần' cho phép bạn tính số nguyên liệu cần thiết để phù hợp với số khẩu phần ăn cần thiết.";

    public HelpPanel() {
        setLayout(new BorderLayout());

        display = new JTextArea();
        display.setEditable(false);

        add(new JScrollPane(display));

        display.setText(displayStr);
    }
}
