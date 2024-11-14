public class Magazine extends LibraryResource {

    /**------------------------------------------------------------------------------------------
     * Constructor khởi tạo một đối tượng `Magazine` với tên và ID cụ thể.
     * @param name Tên của tạp chí.
     * @param resID ID của tạp chí trong hệ thống thư viện.
     */
    Magazine(String name, int resID) {
        this.resourceName = name;            // Đặt tên cho tạp chí
        this.resourceID = resID;             // Đặt ID duy nhất cho tạp chí
        this.type = Constants.MAGAZINE;      // Đặt loại tài nguyên là MAGAZINE
    }
}
