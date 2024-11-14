//---------------- Một interface của constants, thực hiện nó khi được yêu cầu sử dụng người dùng hoặc loại tài nguyên được define trước----------------------
public interface Constants {
    // define các loại user
    static final int ADMIN = 1;
    static final int FACULTY = 2;
    static final int STUDENT = 3;

    // define các loại tài nguyên
    static final int BOOK = 1;
    static final int COURSE_PACK = 2;
    static final int MAGAZINE = 3;
}