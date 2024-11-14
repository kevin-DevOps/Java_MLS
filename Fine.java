import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


// ------------------ Lớp Fine quản lý các khoản phạt cho các tài nguyên trong thư viện----------------------------------------
public class Fine {

    int fineID;             // ID duy nhất cho mỗi khoản phạt, được tăng tự động thông qua
    int resourceID;         // ID của tài nguyên bị phạt (sách hoặc tài liệu học tập mà người dùng không trả đúng hạn)
    int userID;             // ID của người bị phạt
    int fine;               // số tiền phạt

    // Constructor cho Fine Class
    // Khởi tạo một đối tượng Fine với resourceID, userID, và số tiền phạt ban đầu _fine.
    Fine(int _resID, int userID, int _fine){
        fineID = Library.nextFineID;
        Library.nextFineID++;
        this.resourceID = _resID;
        this.userID = userID;
        this.fine = _fine;
    }


    // Cập nhật khoản phạt
    void updateFine(int fine){
        this.fine = fine;
    }
}