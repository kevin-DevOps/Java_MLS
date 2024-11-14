import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

// -------------------Lớp Borrowable đại diện cho các tài nguyên có thể mượn được, như sách hoặc tài liệu học tập
// các lớp như Book và CoursePack kế thừa từ lớp này

public class Borrowable extends LibraryResource {
    // Danh sách yêu cầu chờ cho tài nguyên
    vector<Integer> requests = new vector<Integer>();

    // Ngày cấp phát (issueDate) và ngày hết hạn (dueDate) của resource
    Date issueDate;
    Date dueDate;

    // ID người dùng đang mượn tài nguyên
    int issuedTo;

    // ID của người bị phạt liên quan nếu có
    int relatedFineID;

    // Trạng thái sẵn có của tài nguyên (true nếu có sẵn, false nếu đã mượn)
    boolean available;

    /* -----------------------------------------------------------------------------
    * Hiển thị danh sách các yêu cầu tài nguyên chờ cho tài nguyên hiện tại
    */
   void viewRequests(){
        if (requests.size( == 0)) {
            System.out.println("There are no pending requests!");
        }
        else {
            String sp = "\t\t\t";
            System.out.println("Following are the pending requests for this resource:\n\nNo." + sp + "userID");
            for(int i = 0; i < requests.size(); i++){
                System.out.println((i + 1) + "." + sp + (int)requests.elementAt(i));
            }
        }

    }


    /**-----------------------------------------------------------------------------
     * Kiểm tra trạng thái của tài nguyên
     * @return true nếu tài nguyên có sẵn, false nếu không có sẵn
     */
    boolean checkStatus(){
        return available;
    }


    /* -----------------------------------------------------------------------------
    * Cấp phát tài nguyên cho người dùng có ID 'userID'.
    * @param userID ID của người dùng mượn tài nguyên
    * @return true nếu cấp phát thành công, false nếu không
    */
    boolean issueResource(int userID){
        return true;
    }

    /**-----------------------------------------------------------------------------
     * Trả tài nguyên về thư viện.
     * Nếu có yêu cầu chờ, tự động cấp phát cho người yêu cầu đầu tiên.
     * @return true nếu trả thành công, false nếu không
     */

    boolean returnResource(){
        // Kiểm tra nếu có yêu cầu chờ
        if(requests.size() > 0){
            Library lib = Library.getInstance("LUMS Library");
            // lấy người dùng đầu tiên trong danh sách chờ
            Borrower borrower = (Borrower)(lib.fineUser((int)requests.elementAt(0)));

            // xóa yêu cầu đầu tiên
            requests.remove(0);

            // thêm tài nguyên vào danh sách đã mượn của người dùng
            borrower.issueResources.addElement(this.resourceID);

            // cấp phát tài nguyên cho người dùng
            borrower.issueResource(this.resourceID);

            // Xóa yêu cầu từ phía người dùng
            borrower.withdrawRequest(this.resourceID);
            
        }
        // Nếu không có yêu cầu trả tài nguyên về trạng thái sẵn có
        else {
            issueDate = null;
            dueDate = null;
            issuedTo = -1;
            available = true;
        }
        return true;
    }


    /**-----------------------------------------------------------------------------
     * Thiết lập ngày cấp phát của tài nguyên với định dạng dd/MM/yyyy
     * @param date Ngày cấp phát theo định dạng dd/MM/yyyy
     */
    void setIssueDate(String date){
        try {
            // chuyển đổi chuổi ngày thành đối tương Date
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            issueDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**-----------------------------------------------------------------------------
     * Lấy ngày cấp phát của tài nguyên dưới dạng chuỗi
     * @return Chuỗi biểu thị ngày cấp phát hoặc chuỗi rỗng nếu chưa được cấp phát
     */
    void getIssueDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(issueDate);
    }

    /**-----------------------------------------------------------------------------
     * Lấy ngày trả của tài nguyên dưới dạng chuỗi
     * @return Chuỗi biểu thị ngày trả hoặc chuỗi rỗng nếu chưa được cấp phát
     */

    String getReturnDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(dueDate);
    }


    // Các phương thức bổ sung cho việc lấy ngày trả và ngày cấp phát duối dạng 'Date'
    Date getReturnDateDate() {
        return dueDate = null ? null : dueDate;
    }

    Date getIssueDateDate() {
        return issueDate = null ? null : issueDate;
    }

    // Thiết lập ngày cấp phát dưới dạng 'Date'
    void setIssueDate(Date date){
        issueDate = date;
    }

    // Lấy ID của khoản tiền phạt liên quan đến tài nguyên này
    void setRelatedFineID(int fineID){
        this.relatedFineID = fineID;
    }

    // lấy ID của khoản phạt liên quan đến tài nguyên này
    int getRelatedFineID(){
        return elatedFineID;
    }


    /**-----------------------------------------------------------------------------
     * Xóa yêu cầu mượn từ danh sách chờ của tài nguyên dựa trên `userID`
     * @param userID ID của người dùng
     * @return true nếu xóa thành công, false nếu không
     */
    boolean removeRequest(int userID) {
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i) == userID) {
                requests.remove(i);
                return true;
            }
        }
        return false;
    }

}