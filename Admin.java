import java.util.ArrayList;

/* 
* Lớp Amdin này thực hiện interface của AdminInterface
* Lớp này cung cấp các phương thức để thêm, xóa user và resource trong thư viện
* Kế thừa lớp LibraryUser
*/

public class Admin extends LibraryResource implements AdminInterface {
    /* Thêm một người dùng mới vào hệ thống
    * 
    * @param userName tên người dùng cần tạo
    * @param password mật khẩu của người dùng
    * @param type loại người dùng:
    *             - Constants.ADMIN: người dùng là admin.
    *             - Constants.STUDENT: người dùng là sinh viên.
    *             - Constants.FACULTY: người dùng là giảng viên.
    * @return ID duy nhất của người dùng nếu tạo thành công. trả về -1 nếu tên người dùng đã tồn tại.
    */
   public int addUser(String userName, String password, int type){
    // Lấy thể hiện duy nhất của thư viện
    Library lib = Library.getInstance("LUMS");

    // Kiểm tra xem tên người dùng đã tồn tại chưa
    if (lib.findUser(userName) != null) {
        System.out.println("This user name already exits!, Choose Another one");
        return -1;
    }

    int result = -1;
    // Tạo người dùng dựa trên loại cung cấp
    if (type == Constants.ADMIN){
        // Tạo người dùng là Admin
        Admin admin = new Admin(userName, password, type, false);
        result = admin.userID   // Lấy ID của Admin mới
        lib.users.add(admin);   // Thêm vào danh sách người dùng
    }
    else if ( type == Constants.STUDENT) {
        // Tạo người dùng mới là sinh viên
        Student student = new Student(userName, password);
        student.userID = Library.nextUserID;    // gán ID cho sinh viên
        Library.nextUserID++;                   // Tăng giá trị ID tiếp theo
        result = student.userID;                // Lấy ID của sinh viên mới
        lib.users.add(student);                 // Thêm vào danh sách người dùng
    }
    else if ( type == Constants.FACULTY) {
        // Tạo người dùng là giảng viên
        FACULTY facutly = new FACULTY(userName, password);
        facutly.userID = Library.nextUserID;    // Gán ID cho giảng viên
        Library.nextUserID++;                   // Tăng giá trị ID tiếp theo
        result = facutly.userID;                // Lấy ID của giảng viên mới
        lib.users.add(facutly);                 // Thêm vào danh sách người dùng
    }
    
    return result;  // Trả về ID người dùng mới
   }

    /**
     * Xóa người dùng khỏi hệ thống.
     * 
     * @param userID ID duy nhất của người dùng cần xóa.
     * @return true nếu người dùng tồn tại và đã được xóa, false nếu người dùng không tồn tại.
     */
    public boolean removeUser(int userID) {
        Library lib = Library.getInstance("LUMS Library");

        // Tìm người dùng theo ID
        LibraryUser user = lib.findUser(userID);
        if(user == null) {
            return false;   // Người dùng không tồn tại
        }

        // Xóa người dùng
        return lib.removeUser(userID);
    }


    /**
     * Thêm tài nguyên mới vào hệ thống.
     * 
     * @param name tên của tài nguyên (phải là duy nhất).
     * @param type loại tài nguyên:
     *             - Constants.BOOK: tài nguyên là sách.
     *             - Constants.COURSE_PACK: tài nguyên là tài liệu học tập.
     *             - Constants.MAGAZINE: tài nguyên là tạp chí.
     * @return ID duy nhất của tài nguyên mới nếu thêm thành công. Trả về -1 nếu không thể thêm.
     */
    Public int addResource(String Name, int type) {
        Library lib = Library.getInstance("LUMS library");
        int result = -1;

        // Tạo tài nguyên dựa trên loại được cung cấp
        if(type == Constants.BOOK){
            Book book = new Book(name, Library.nextResID); // Tạo sách mới
            lib.resources.add(book);                       // Thêm sách vào tài nguyên mới
            result = book.resourceID;                      // Lấy ID của sách
        }
        else if(type == Constants.COURSE_PACK){
            CoursePack pack = new CoursePack(name, Library.nextResID);  // Tạo tài liệu học tập mới
            lib.resources.add(pack);        // Thêm vào danh sách tài nguyên
            result = pack.resourceID;       // Lấy ID của pack
        }
        else if(type == Constants.MAGAZINE){
            Magazine mag = new Magazine(name, Library.nextResID);   // Tạo tạp chí mới
            lib.resources.add(mag);     // Thêm vào danh sách tài nguyên
            result = mag.resourceID;    // Lấy ID của manazine
        }

        Library.nextResID++;    // Tăng giá trị ID tài nguyên tiếp theo
        return result;          // Trả về ID của tài nguyên mới
    }

    /**
     * Xóa tài nguyên khỏi hệ thống.
     * 
     * @param resourceID ID duy nhất của tài nguyên cần xóa.
     * @return true nếu tài nguyên tồn tại và đã được xóa, false nếu không tồn tại hoặc đang được sử dụng.
     */
    public boolean removeResource(int resourceID){
        Library lib = Library.getInstance("LUMS Library");

        // Xóa tài nguyên dựa trên ID
        return lib.removeResource(resourceID);
    }

    /**
     * Constructor của lớp Admin.
     * 
     * @param user tên người dùng.
     * @param pass mật khẩu.
     * @param typ loại người dùng (Constants.ADMIN).
     * @param firstInstance chỉ báo xem đây có phải là Admin đầu tiên hay không.
     */
    Admin(String user, String pass, int typ, boolean firstInstance){
        this.userName = user;   // Gán tên người dùng
        this.password = pass;   // Gán mật khẩu
        this.type = typ;        // Gán loại người dùng
        this.userID = Library.nextUserID;   // Gán ID duy nhất cho admin
        Library.nextUserID++;   // Tăng giá trị ID tiếp theo
    }

}