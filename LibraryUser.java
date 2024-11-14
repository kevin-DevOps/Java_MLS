// Tạo Lớp User cho Library
public class LibraryUser {
    // Đặt public để có thể truy cập từ Driver
    public String userName;     // Tên của User
    public String password;     // mật khẩu
    public int userID;          // User id
    public int type;            // admin/ user

    // ----------------------Phương thức lấy Id người dùng---------------------------
    public int getUserID() {
        return userID;
    }

    

    // ----------------------Phương thức đăng nhập-----------------------------------
    public boolean login(String userName, String password) {
        // Kiểm tra tên người dùng và mật khẩu
        if (this.userName.equals(userName) && this.password.equals(password)) {
            return true;
        } else {
            // Thông báo người dùng nhập không đúng
            System.out.println("The username or password entered was not correct!");
            return false;
        }
    }


    // ----------------------Phương thức đăng xuất------------------------------------
    public boolean logout() {
        System.out.println("Logout successful!");
        return true;
    }
}