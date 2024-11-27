package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;

import home.model.CourseModel;
import home.model.UserModel;
import home.controllers.DataBase;

public class Main extends Application {
	public static String login_username;
	public static String login_password;
	
	private void test_csv_load() {
        // 创建初始测试数据
        List<CourseModel> courses = new ArrayList<>();

//        courses.add(new CourseModel(
//                101, "Mathematics", 1001, "Dr. Smith", 4.0f, 60.0f,
//                50.0f, 60.0f, 75.0f, 90.0f
//        ));
//        courses.add(new CourseModel(
//                102, "Physics", 1002, "Dr. Johnson", 3.5f, 45.0f,
//                40.0f, 55.0f, 70.0f, 85.0f
//        ));
//        courses.add(new CourseModel(
//                103, "Chemistry", 1003, "Dr. Brown", 3.0f, 50.0f,
//                35.0f, 50.0f, 65.0f, 80.0f
//        ));

        // 调用 saveAllToCSV 方法保存数据到 CSV 文件
        CourseModel.saveAllToCSV(courses);
        System.out.println("Course data has been saved to CSV.");
        
        // 创建初始测试数据
        List<UserModel> users = new ArrayList<>();

        users.add(new UserModel(1, "password123", "Alice", "Female", "2000-01-01", "Engineering", "Computer Science", "Student"));
        users.add(new UserModel(2, "password456", "Bob", "Male", "1999-05-15", "Business", "Management", "Teacher"));
        users.add(new UserModel(3, "password789", "Charlie", "Male", "1998-07-23", "Science", "Biology", "Admin"));

        // 调用 saveAllToCSV 方法保存数据到 CSV 文件
        UserModel.saveAllToCSV(users);
        System.out.println("User data has been saved to CSV.");
    }
	
	
    @Override
    public void start(Stage primaryStage) throws Exception{
//    	test_csv_load();
    	
    	DataBase.init();
        System.out.println("JavaFX Version: " + System.getProperty("javafx.runtime.version"));

    	
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserInfo.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        primaryStage.setTitle("KeepToo SMSys");
        primaryStage.getIcons().add(new Image("/icons/icon.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    //you can download the glyph browser - link provided.
    public static void main(String[] args) {
        launch(args);
    }
}
