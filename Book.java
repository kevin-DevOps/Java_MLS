import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//-------------------------Class Book kế thừa Class Borrowable--------------------------

public class Book extends Borrowable {
    // Phương thức cấp phát sách cho người dùng có userID cụ thể
    // userID ID của người dùng mượn sách
    // return true nếu sách được cấp phát thành công, false nếu không thể cấp phát
    boolean issueResource(int userID){
        Library lib = Library.getInstance("LUMS Library"); // Lấy đối tượng Lubrary
        LibraryUser user = lib.findUser(userID);            // Tìm người dùng theo ID
        int daysToIssue;


        // Xác định số ngày cho phép mượn sách dựa theo loại người dùng
        if (user.type == Constants.FACULTY){
            daysToIssue = 30;   // 30 ngày cho giảng viên
        }
        else if(user.type = Constants.STUDENT){
            daysToIssue = 15;   // 15 ngày cho sinh viên
        }
        else {
            return false; // không phải là giảng viên hoặc sinh viên
        }

        // Đặt ID của người mượn và đánh dấu không còn có sẵn
        issuedTo = userID;
        available = false;

        // Thiết lập ngày mượn và ngày hết hạn
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);          // đặt dueDate là ngày hôm qua
        dueDate = cal.getTime();


        cal.add(Calendar.DAY_OF_YEAR, -daysToIssue - 1); // đặt issueDate là số ngày trước ngày hết hạn
        issueDate = cal.getTime();


        // In ra ngày mượn để kiểm tra
        System.out.println(issueDate;)
        return true;        // return true nếu cấp phát thành công
    }



    /**------------------------------------------------------------------------------------------------
     * Phương thức gia hạn ngày trả sách thêm `days` ngày nếu không có yêu cầu từ người khác
     * @param days Số ngày muốn gia hạn thêm
     * @return true nếu gia hạn thành công, false nếu có yêu cầu từ người khác
     */
    boolean renewResource(int days){
        if (requests.size() == 0){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, days);        // Cộng thêm 'days' vào ngày hết hạn
            dueDate = cal.getTime();


            System.out.println("The new due Date is: " + getReturnDate());      // In ra ngày hết hạn mới
            return true;        // true nếu gia hạn thành công
        }
        else {
            return false;       // false nếu có yêu cầu từ người khác
        }
    }


    /**------------------------------------------------------------------------------------------------
     * Constructor của lớp `Book`, khởi tạo sách với tên và ID cụ thể
     * @param resName Tên sách
     * @param resID ID của sách
     */
    Book(String resName, int resID){
        this.resourceID = resID;        // Đặt ID của sách
        this.resourceName = resName;    // Đặt tên của sách
        this.available = true;          // Đặt sách là có sẵn
        this.issuedTo = -1;             // Đặt ID người mượn là -1, nghĩa là chưa ai mượn
        this.type = Constants.BOOK;     // Đặt loại tài nguyên là BOOK
    }
}
