import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {

    // Driver điều khiển Library System. Có giao diện IO cho người dùng và control thư viện
    Scanner sc;     // scan đầu vào người dùng được khởi chạy trong hàm main
    Library myLibrary;  // thể hiện của Library khi khởi tạo trong hàm main


    // Constructor cho Driver
    Driver(String libName){
        sc = new Scanner(System.in);
        myLibrary = Library.getInstance(libName);
    }


    ///=====================================================////
    ///=============User Login IO Interface=================////
    ///=====================================================////
    void loginIO(int type){
        String name, pass;
        // template là danh sách lựa chọn cho Admin interface!
        String template = "\n\nChoose from the Following options:\n\n" +
				"- For adding an administrator account to the system, press 1\n" + "- For adding a faculty account to the system, press 2\n" +
				"- For adding a student account to the system, press 3\n" + "- For adding a book to the system, press 4\n" +
				"- For adding a course pack to the system, press 5\n" + "- For adding a magazine to the system, press 6\n" +
				"- For removing a user account from the system, press 7\n" + "- For removing a resource from the system, press 8\n" +
				"- For logging out of the system, press 9";


        // User được yêu cầu nhập username và password
        System.out.println("Enter the username: ");
        name = sc.nextLine();
        System.out.println("Enter the password: ");
        pass = sc.nextLine();
        LibraryUser user = myLibrary.findUser(name);

        // Nếu user không tìm thấy, return với massage!
        if(user == null){
            System.out.println("The username or password was not correct!\n");
            return;
        }
        else if(!user.login(name, pass)){   // nếu user login fail
            return;
        }


        // Nếu một giảng viên hoặc sinh viên đăng nhập đúng tên đăng nhập và mật khẩu,
        // nhưng loại người dùng không phải Admin, thì không cho phép tiếp tục đăng nhập.
        // Mỗi người dùng phải tuân thủ giao diện riêng của họ.
        if(user.type != type){
            System.out.println("The username or password was not correct!");
            return;
        }


        /**********************User đã login**************************/
        boolean done = false;
        int input;
        String userInput;

        if(type == Constants.ADMIN){

            // Cho Admin Login IO interface
            System.out.println("\n==============================================================\n\nWelcome "+ name+ "!" +template);

            while(!done){

                if(sc.hasNextInt()){
                    userInput = sc.nextLine();
                    continue;
                }
                input = sc.nextInt();

                //hasNextInt() takes int only but not \n character!
                userInput = sc.nextLine();

                switch(input){
                    case 1:
						addUserIO((Admin)user,Constants.ADMIN);		// Chuyển quyền kiểm soát đến giao diện thêm tài khoản Admin
                        System.out.println(template);
						break;
					case 2:
						addUserIO((Admin)user,Constants.FACULTY);	// Chuyển quyền kiểm soát đến giao diện thêm tài khoản Giảng viên
						System.out.println(template);
						break;
					case 3:
						addUserIO((Admin)user,Constants.STUDENT);	// Chuyển quyền kiểm soát đến giao diện thêm tài khoản Sinh viên
						System.out.println(template);
						break;
					case 4:
						addResourceIO((Admin)user,Constants.BOOK);	// Chuyển quyền kiểm soát đến giao diện thêm tài nguyên là Book
						System.out.println(template);
						break;
					case 5:
						addResourceIO((Admin)user,Constants.COURSE_PACK);	// Chuyển quyền kiểm soát đến giao diện thêm tài nguyên là Course Pack
						System.out.println(template);
						break;
					case 6:
						addResourceIO((Admin)user,Constants.MAGAZINE);	// Chuyển quyền kiểm soát đến giao diện thêm tài nguyên là tạp chí
						System.out.println(template);
						break;
					case 7:
						removeUserIO((Admin)user);		// Chuyển quyền kiểm soát đến giao diện xoá người dùng
						System.out.println(template);
						break;
					case 8:
						removeResourceIO((Admin)user);	// Chuyển quyền kiểm soát đến giao diện xóa tài nguyên
						System.out.println(template);
						break;
					case 9:
						System.out.println("Do you really want to log out? y/n");		// Hỏi lại người dùng có muốn đăng xuất 
						userInput = sc.nextLine();
						if((userInput.equals("y") || userInput.equals("Y"))){
							System.out.println("Thanks... logging out!");
							user.logout();      // Gọi phương thức đăng xuất cho người dùng
							done = true;        // Thoát khỏi vòng lặp
						}
						else{
                            // Người dùng quyết định tiếp tục đăng nhập
							System.out.println(user.userName + " still Logged in...");
						}
					break;
					default:
                        // default cho người dùng nhập giá trị không hợp lệ
						System.out.println("Give the correct input");
						break;
                }
            }

        }
        // Cho Giảng viên và sinh viên Login IO interface
        else{

            // Tạo template cho Giảng viên và Sinh viên
            String template_2 = "\n\nChoose from the following options:\n\n"+
				"- For borrowing a resource, press 1\n"+
				"- For returning a resource, press 2\n"+
				"- For deleting a request, press 3\n"+
				"- For viewing issued books, press 4\n"+
				"- For viewing pending requests, press 5\n"+
				"- For viewing you fines, press 6\n"+
				"- For logging out of the system, press 7\n"+
				"- For renewing a resource, press 8\n";
			System.out.println("\n***************************************\n\nWelcome "+ name+ "!" +template_2);

            while(!done){

                if(!sc.hasNextInt()){
                    System.out.println("Given the correct input: ");
                    userInput = sc.nextLine();      // Bỏ qua đầu vào sai
                    continue;
                }
                input = sc.nextInt();

                userInput = sc.nextLine();          // Đọc dòng tiếp theo để xóa kí tự xuống dòng

                switch(input){
                    case 1:
						borrowIO((Borrower)user,type);		// Chuyển quyền kiểm soát đến giao diện mượn tài nguyên
						System.out.println(template_2);
						break;
					case 2:
						returnResourceIO((Borrower)user);		// Chuyển quyền kiểm soát đến giao diện trả tài nguyên
						System.out.println(template_2);
						break;
					case 3:
						deleteRequestIO((Borrower)user);		// Chuyển quyền kiểm soát đến giao diện xóa tài nguyên
						System.out.println(template_2);
						break;
					case 4:
						((Borrower)user).viewIssued();			// Hiển thị các tài nguyên đã mượn bằng cách gọi phương thức viewIssued.
						System.out.println(template_2);
						break;
					case 5:
						((Borrower)user).viewRequests();		// Hiển thị các yêu cầu chờ xử lý bằng cách gọi phương thức viewRequests.
						System.out.println(template_2);
						break;
					case 6:
						((Borrower)user).viewFines();			// Hiển thị tiền phạt của người dùng bằng cách gọi phương thức viewFines.
						System.out.println(template_2);
						break;
					case 8:
						renewResourceIO((Borrower)user);		// Chuyển quyền kiểm soát đến giao diện gia hạn tài nguyên.
						System.out.println(template_2);
						break;
					case 7:
						System.out.println("Do you really want to log out? y/n");		// Hỏi người dùng có muốn đăng xuất không.
						userInput = sc.nextLine();
						if((userInput.equals("y") || userInput.equals("Y"))){
							System.out.println("Thanks... logging out!");
							user.logout();
							done = true;
						}
						else{
							System.out.println(user.userName + " Still Logged in...");
						}
						break;
					default:
						System.out.println("Give the correct input");
						break;
                }

            }
        }
    }
    //==========================================================================================//

    ///=====================================================////
    ///===============ADD User IO Interface=================////
    ///=====================================================////
    void addUserIO(Admin admin, int type){          // Chỉ Admin có quyền nhập method này
        // funtion này lấy userName và password của new user và gọi addUser function của Admin

        String name, pass;

        System.out.println("Enter the new username: ");
        name = sc.nextLine();
        System.out.println("Enter the password: ");
        pass = sc.nextLine();

        int id = amdin.addUser(name,pass,type);
        if(id >= 0){
            System.out.println("The new userID is: " + id + "\n");
        }
        else {
            System.out.println("New user could not be created. Please try again with different username!\n");
        }

    }
    //==========================================================================================//
    ///=====================================================////
    ///===============Remove User IO Interface==============////
    ///=====================================================////
    void removeUserIO(Admin admin){
        // function này hỏi ID và username được xóa và gọi removeUser function của Admin

        int id;
        String userInput;
        System.out.println("Enter the userID:");

        while(!sc.hasNextInt()){
            System.out.println("Given the correct integer input...");
            System.out.println("Enter the userID: ");
            userInput = sc.nextLine();
            continue;
        }

        id = sc.nextInt();
        userInput = sc.nextLine();
        if(admin.removeUser(id)){
            System.out.println("User " + id + " has been successfully removed!");
        }
        else {
            System.out.println("User " + id + "was not removed!");
        }
    }

    //==========================================================================================//
    ///=====================================================////
    ///=============IO For Removing Resource================////
    ///=====================================================////
    void removeResourceIO(Admin admin){
        // function này yêu cầu Name hoặc ID của resource được xóa và gọi removeResource function của ADMIN

        ArrayList<LibraryResource> resources = new ArrayList<LibraryResource>(); // Danh sách các tài nguyên được tìm thấy
        String name, userInput;                                 // Biến lưu tên hoặc đầu vào từ người dùng
        int id = -1;                                            // ID mặc định nếu không có ID được nhập
        // Yêu cầu người dùng nhập tên hoặc ID cần xóa của resource
        System.out.println("Enter the Resource Name or ID: ");  
        if(sc.hasNextInt()){
            // Nếu người dùng nhập ID (số nguyên), lưu giá trị ID
            id = sc.nextInt();
            userInput = sc.nextLine();  // Đọc phần còn lại 
        }
        else{
            // Nếu người dùng nhập tên, lưu giá trị tên
            name = sc.nextLine();
            // tìm tài nguyên dựa trên tên và lưu vào danh sách tài nguyên
            if(myLibrary.findResource(name) != null){
                resources = myLibrary.findResource(name);
            }
        }
        for(int i = 0; i < resources.size(); i++){
            if(admin.removeResource(resources.get(i).getResourceID())){
                System.out.println("The resource is successfully removed!");
            }
            else {
                System.out.println("The resource is not found/ removed!");
            }
        }
        
    }
    //==========================================================================================//
    ///=====================================================////
    ///=====================Borrow IO=======================////
    ///=====================================================////
    void borrowIO(Borrower borrower, int type){
        // Phương thức này yêu cầu nhập tên hoặc ID của tài nguyên cần mượn
        // và gọi phương thức tryIssue của lớp Borrower.java để xử lý việc mượn tài nguyên.
        ArrayList<LibraryResource> resources = new ArrayList<LibraryResource>();
        String name;
        int id = -1;
        System.out.println("Enter the name or ID of resource:");
        if(sc.hasNextInt()){
            id = sc.nextInt();
            name = sc.nextLine();
        }
        else{
            name = sc.nextLine();
            if(myLibrary.findResource(name) != null){
                resources = myLibrary.findResource(name);
            }
        }
        borrower.tryIssue(resources);
    }
    //==========================================================================================//
    ///=====================================================////
    ///=============IO For Resource Return==================////
    ///=====================================================////
    void returnResourceIO(Borrower borrower){
        // function này yêu cầu nhập name hoặc ID của resource được trả và goi tryReturn function của Borrower.java
        ArrayList<LibraryResource> resources = new ArrayList<LibraryResource>();
        String name;
        int id = -1;
        System.out.println("Enter the name or ID of resouce: ");
        if (sc.hasNextInt()){
            id = sc.nextInt();
            name = sc.nextLine();
        }
        else {
            name = sc.nextLine();
            if (myLibrary.findResource(name) != null){
                resources = myLibrary.findResource(name);
            }
        }

        for(int i = 0; i < resources.size(); i++){
            id = resources.get(i).getResourceID();      // gán giá trị id của tài nguyên hiện tại
            if(borrower.findIssued(id)){
                if(borrower.tryReturn(id)){
                    System.out.println("The requested resource has been successfully returned!");
					return;
                }
            }
        }

        if(borrower.findIssued(id)){
            if(borrower.tryReturn(id)){
                System.out.println("The requested resource has been successfully returned!");
            }
        }

        System.out.println("The resource was not returned! or it was not found!");
    }
    //==========================================================================================//
    ///=====================================================////
    ///=============IO For Request Deletion=================////
    ///=====================================================////    

}