import java.util.*;
import java.util.HashSet;


// ----------------------------Lớp Faculty kế thừa của lớp Borrower ------------------------------
//  đại diện cho một người dùng loại giảng viên trong hệ thống thư viện

class Faculty extends Borrower {

    // Constructor
    Faculty(String name, String pass){
        this.userName = name;                   // Gán tên cho giảng viên
        this.password = pass;                   // Gán mật khẩu cho giảng viên
        this.type = Constants.FACULTY;          // Xác định loại người dùng
        this.maxResources = 6;                  // Giới hạn số lượng tài nguyên mà giảng viên có thể mượn
        this.fines = new HashSet<Fine>();       // Khởi tạo một Hashset<Fine> để lưu trữ các khoảng phạt của giảng viên
    }
}