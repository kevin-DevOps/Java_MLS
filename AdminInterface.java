/* 
Interface này phải được thực hiện bởi Admin Class
AdminInterface này define các phương thức yêu cầu cho quản lý người dùng và resources trong hệ thống,
bao gồm adding, removing users và resource, handling unique identifiers cho mỗi thực thể
*/

public interface AdminInterface {
    /* tạo user với username của user mới, password, và type
     * @param userName của user mới phải là unique
     * @param password cho user mới
     * @param loại của user được tạo:
     *        1 - Admin user,
     *        2 - Faculty user,
     *        3 - Student user.
     * @return một số nguyên dương duy nhất là ID cho user đươc tạo nếu thành công.
     *         Returns -1 if user không được tạo thành công (e.g., username already exists).
     */
    int addUser(String userName, String password, int type);



        /* 
        * Xóa a user từ hệ thống
        * @param userID là unique ID của user được xóa
        * @return true nếu user tồn tại và đã xóa thành công
        * 
        */
    boolean removeUser(int userID);



    /* Thêm resource với name
    * @param name của resource cái cần được thêm vào. Phải unique
    @ param type nếu type là 1, tạo một book type object, nếu là 2 tạo một course pack type object, nếu là 3 tạo manazine
    * @return tạo một số nguyên dương duy nhất (resourceID) nếu resource được thêm vào. return -1 nếu thất bại
    */
   int addResource(String name, int type);

   /* 
   * Xóa resource từ hệ thống 
   * @param resourceID là số nguyên dương duy nhất của resource
   * @ return false nếu resources không tồn tại và có vấn đề.
   */
   boolean remove(int resourceID);


}