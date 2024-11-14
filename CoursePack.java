import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

// ---------------- Lớp kế thừa từ Borrowable-----------------------------
// Đại diện cho một tài liệu học tập trong thư viện
public class Book extends Borrowable {
    
    // Phương thức để cấp tài liệu cho người dùng có ID xác định
    boolean issueResource(int userID){
        issuedTo = userID;                          // Tài liệu được mượn bơi người dùng có ID này               
        available = false;                          //  Không có sẵn để mượn
        Calender cal = Calender.getInstance();      
        cal.add(Calender.DAY_OF_YEAR, -1);          // Ngày hôm qua 
        dueDate = cal.getTime();
        cal.add(Calender.DAY_OF_YEAR, -2);          // Hai ngày trước
        issueDate = cal.getTime();
        System.out.println(issueDate);
        return true;                                // Cấp tài liệu thành công
    }


        // Constructor tạo một đối tượng cụ thể với tên tài nguyên và ID tài nguyên cụ thể
    CoursePack(String resName, int resID){
        this.resourceName = resName;
        this.resourceID = resID;
        this.available = true;                      // Mặc định là có sẵn để mượn
        this.type = Constants.COURSE_PACK;          // Loại tài liệu là CoursePack
        this.issuedTo = -1;                         // tài liệu này hiện chưa được mượn bởi bất kỳ người dùng nào.
    }
}