package home.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import home.model.CourseModel;
import home.model.UserModel;
import home.model.GradeModel;

public class DataBase {
    // 静态存储数据
    private static List<CourseModel> courseList = new ArrayList<>();
    private static List<UserModel> userList = new ArrayList<>();
    private static List<GradeModel> gradeList = new ArrayList<>();
    
    private static UserModel currUser = null;

    // 添加数据
    public static void addCourse(CourseModel course) {
        courseList.add(course);
    }

    public static void addUser(UserModel user) {
        userList.add(user);
    }
    
    public static void init() {
    	userList = UserModel.loadFromCSV();
    	courseList = CourseModel.loadFromCSV();
    	gradeList = GradeModel.loadFromCSV();
    	
    	currUser = userList.get(0);
    }
    
    // TODO: make the save more efficient (fine-grained)
    public static void save() {
    	UserModel.saveAllToCSV(userList);
    	CourseModel.saveAllToCSV(courseList);
        GradeModel.saveAllToCSV(gradeList);

    }
    
    public static UserModel getCurrUser() {
    	return currUser;
    }

    // 获取所有课程
    public static List<CourseModel> getAllCourses() {
        return new ArrayList<>(courseList); // 返回副本以保护原始数据
    }

    // 获取所有用户
    public static List<UserModel> getAllUsers() {
        return new ArrayList<>(userList); // 返回副本以保护原始数据
    }

    // 查询课程
    public static List<CourseModel> findCoursesByTeacherId(int teacherId) {
        return courseList.stream()
                .filter(course -> course.getTeacherId() == teacherId)
                .collect(Collectors.toList());
    }

    public static List<CourseModel> findCoursesByName(String courseName) {
        return courseList.stream()
                .filter(course -> course.getCourseName().equalsIgnoreCase(courseName))
                .collect(Collectors.toList());
    }

    // 查询用户
    public static List<UserModel> findUsersByRole(String role) {
        return userList.stream()
                .filter(user -> user.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    public static List<UserModel> findUsersByInstitute(String institute) {
        return userList.stream()
                .filter(user -> user.getInstitute().equalsIgnoreCase(institute))
                .collect(Collectors.toList());
    }

    public static UserModel findUserById(int id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static CourseModel findCourseById(int courseId) {
        return courseList.stream()
                .filter(course -> course.getCourseId() == courseId)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * 根据学生 ID 查询成绩
     * @param studentId 学生 ID
     * @return 符合条件的 GradeModel 列表
     */
    public static List<GradeModel> findGradesByStudentId(int studentId) {
//    	System.out.println("grade list: ");
//    	for (GradeModel grade: gradeList) {
//    		System.out.println(grade);
//    	}
    	
        return gradeList.stream()
                .filter(grade -> grade.getStudentId() == studentId)
                .collect(Collectors.toList());
    }

    /**
     * 根据课程 ID 查询成绩
     * @param courseId 课程 ID
     * @return 符合条件的 GradeModel 列表
     */
    public static List<GradeModel> findGradesByCourseId(int courseId) {
        return gradeList.stream()
                .filter(grade -> grade.getCourseId() == courseId)
                .collect(Collectors.toList());
    }

    /**
     * 根据成绩范围查询
     * @param minGrade 最低成绩
     * @param maxGrade 最高成绩
     * @return 符合条件的 GradeModel 列表
     */
    public static List<GradeModel> findGradesByRange(float minGrade, float maxGrade) {
        return gradeList.stream()
                .filter(grade -> grade.getGrade() >= minGrade && grade.getGrade() <= maxGrade)
                .collect(Collectors.toList());
    }

    /**
     * 根据学生 ID 和课程 ID 查询成绩
     * @param studentId 学生 ID
     * @param courseId 课程 ID
     * @return 符合条件的 GradeModel，如果没有找到则返回 null
     */
    public static GradeModel findGradeByStudentAndCourse(int studentId, int courseId) {
        return gradeList.stream()
                .filter(grade -> grade.getStudentId() == studentId && grade.getCourseId() == courseId)
                .findFirst()
                .orElse(null);
    }

    /**
     * 查询某课程的所有学生成绩
     * @param courseId 课程 ID
     * @return 符合条件的学生 UserModel 列表
     */
    public static List<UserModel> findStudentsByCourseId(int courseId) {
        return gradeList.stream()
                .filter(grade -> grade.getCourseId() == courseId)
                .map(grade -> findUserById(grade.getStudentId()))
                .collect(Collectors.toList());
    }

    /**
     * 查询某学生的所有课程成绩
     * @param studentId 学生 ID
     * @return 符合条件的课程 CourseModel 列表
     */
    public static List<CourseModel> findCoursesByStudentId(int studentId) {
        return gradeList.stream()
                .filter(grade -> grade.getStudentId() == studentId)
                .map(grade -> ById(grade.getCourseId()))
                .collect(Collectors.toList());
    }
}
