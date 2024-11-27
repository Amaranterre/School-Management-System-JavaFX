package home.controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.*;

import java.net.URL;
import java.util.ResourceBundle;
import home.model.*;

public class StudentGradeController implements Initializable {
//    private int courseId; // 修改为 int 类型
//    private String courseName;
//    private int teacherId;
//    private String teacherName;
//    private float credit;
//    private float hour;
//    private float failThreshold; // 修改为 float 类型
//    private float passThreshold; // 修改为 float 类型
//    private float goodThreshold; // 修改为 float 类型
//    private float excellentThreshold; // 修改为 float 类型

    @FXML
    private TableView<CourseView> tbData;

    @FXML
    private TableColumn<CourseView, String> studentName;

    @FXML
    private TableColumn<CourseView, String> courseName;

    @FXML
    private TableColumn<CourseView, String> teacherName;
    
    @FXML
    private TableColumn<CourseView, Float> credit;

    @FXML
    private TableColumn<CourseView, Float> hour;

    @FXML
    private TableColumn<CourseView, Float> passThreshold; //及格线
    
    @FXML
    private TableColumn<CourseView, Float> grade;
    
    @FXML
    private TableColumn<CourseView, String> finish;
    
    @FXML
    private TableColumn<CourseView, String> semester;
    
    @FXML
    private Label allCourses;
    
    @FXML
    private Label finishedCourses;
    
    @FXML
    private Label havaCredits;
    
    @FXML
    private Label havaHours;
    
    public StudentGradeController() {
        // 无需特殊初始化
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 设置列的 CellValueFactory，用于从 UserModel 中映射数据到表格
    	studentName.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
    	courseName.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
    	teacherName.setCellValueFactory(cellData -> cellData.getValue().teacherNameProperty());
    	credit.setCellValueFactory(cellData -> cellData.getValue().creditProperty().asObject());
    	hour.setCellValueFactory(cellData -> cellData.getValue().hourProperty().asObject());
    	passThreshold.setCellValueFactory(cellData -> cellData.getValue().passThresholdProperty().asObject());
    	grade.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());
    	finish.setCellValueFactory(cellData -> cellData.getValue().finishProperty());
    	semester.setCellValueFactory(cellData -> cellData.getValue().semesterProperty());
        // 加载数据到 TableView
        loadStudentsData();
    }

    private void loadStudentsData() {
    	List<CourseView> courses = CourseView.getCourseViewsByStuID(1);
    	int coursesCount = 0;
    	int validCoursesCount = 0;
    	int creditsCount = 0;
    	int hourCount = 0;
    	for (CourseView course: courses) {
    		coursesCount += 1;
    		if (course.getFinish() == CourseView.FINISH) {
    			validCoursesCount += 1;
    			creditsCount += course.getCredit();
    			hourCount += course.getHour();
    		}
    	}
    	allCourses.setText(String.valueOf(coursesCount));
    	finishedCourses.setText(String.valueOf(validCoursesCount));
    	havaCredits.setText(String.valueOf(creditsCount));
    	havaHours.setText(String.valueOf(hourCount));
    	
    	
        // 从 DataBase 获取所有用户并过滤角色为 "Student"
        ObservableList<CourseView> coursesToView = FXCollections.observableArrayList(
        		courses
        );

        // 设置数据到 TableView
        tbData.setItems(coursesToView);
        
    }
}
class CourseView {
    private final StringProperty studentName;
    private final StringProperty courseName;
    private final StringProperty teacherName;
    private final FloatProperty credit;
    private final FloatProperty hour;
    private final FloatProperty passThreshold;
    private final FloatProperty grade;
    private final StringProperty finish;
    private final StringProperty semester;

    public final static String FINISH = "已完成";
    public final static String UNFINISH = "未完成";

    // 构造方法
    public CourseView(String studentName, String courseName, String teacherName,
                      float credit, float hour, float passThreshold, float grade, boolean isFinished, String semester) {
        this.studentName = new SimpleStringProperty(studentName);
        this.courseName = new SimpleStringProperty(courseName);
        this.teacherName = new SimpleStringProperty(teacherName);
        this.credit = new SimpleFloatProperty(credit);
        this.hour = new SimpleFloatProperty(hour);
        this.passThreshold = new SimpleFloatProperty(passThreshold);
        this.grade = new SimpleFloatProperty(grade);
        this.finish = new SimpleStringProperty(isFinished ? FINISH : UNFINISH);
        this.semester = new SimpleStringProperty(semester);
        
    }

    // Getter 和 Setter 方法
    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public String getCourseName() {
        return courseName.get();
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName.get();
    }

    public void setTeacherName(String teacherName) {
        this.teacherName.set(teacherName);
    }

    public StringProperty teacherNameProperty() {
        return teacherName;
    }

    public float getCredit() {
        return credit.get();
    }

    public void setCredit(float credit) {
        this.credit.set(credit);
    }

    public FloatProperty creditProperty() {
        return credit;
    }

    public float getHour() {
        return hour.get();
    }

    public void setHour(float hour) {
        this.hour.set(hour);
    }

    public FloatProperty hourProperty() {
        return hour;
    }

    public float getPassThreshold() {
        return passThreshold.get();
    }

    public void setPassThreshold(float passThreshold) {
        this.passThreshold.set(passThreshold);
    }

    public FloatProperty passThresholdProperty() {
        return passThreshold;
    }
    
    public float getGrade() {
        return grade.get();
    }

    public void setGrade(float grade) {
        this.grade.set(grade);
    }

    public FloatProperty gradeProperty() {
        return grade;
    }

    public String getFinish() {
        return finish.get();
    }

    public void setFinish(String finish) {
        this.finish.set(finish);
    }

    public StringProperty finishProperty() {
        return finish;
    }

    public String getSemester() {
        return semester.get();
    }

    public void setSemester(String semester) {
        this.semester.set(semester);
    }

    public StringProperty semesterProperty() {
        return semester;
    }

    // 静态方法：根据学生 ID 获取 CourseView 列表
    public static List<CourseView> getCourseViewsByStuID(int stuId) {
        UserModel user = DataBase.findUserById(stuId);
        List<GradeModel> grades = DataBase.findGradesByStudentId(stuId);
        List<CourseView> courseViews = new ArrayList<>();

        for (GradeModel grade : grades) {
            CourseModel course = DataBase.findCourseById(grade.getCourseId());
            System.out.println(grade);
            if (course != null) {
                courseViews.add(new CourseView(
                        user.getName(),
                        course.getCourseName(),
                        course.getTeacherName(),
                        course.getCredit(),
                        course.getHour(),
                        course.getPassThreshold(),
                        grade.getGrade(),
                        grade.isFinished(),
                        course.getSemester() // 从 GradeModel 获取 semester
                ));
            }
        }

        return courseViews;
    }
}
