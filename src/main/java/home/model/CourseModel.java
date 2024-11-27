package home.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileInputStream; // 用于文件输入流
import java.io.InputStreamReader; // 用于指定文件编码
import java.nio.charset.StandardCharsets; // 用于指定 UTF-8 编码
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CourseModel {
    private int courseId; // 修改为 int 类型
    private String courseName;
    private int teacherId;
    private String teacherName;
    private float credit;
    private float hour;
    private float failThreshold; // 修改为 float 类型
    private float passThreshold; // 修改为 float 类型
    private float goodThreshold; // 修改为 float 类型
    private float excellentThreshold; // 修改为 float 类型
    private String semester;

    public static final int AttributeNum = 11;
    
    public static final String SemOneFirst = "大一上"; 
    public static final String SemOneSecond = "大一下"; 
    public static final String SemTwoFirst = "大二上"; 
    public static final String SemTwoSecond = "大二下"; 
    public static final String SemThreeFirst = "大三上"; 
    public static final String SemThreeSecond = "大三下"; 
    public static final String SemFourFirst = "大四上"; 
    public static final String SemFourSecond = "大四下"; 
    public static final String SemUnknown = "未知"; 

    // 带参构造方法
    public CourseModel(int courseId, String courseName, int teacherId,
                       String teacherName, float credit, float hour,
                       float failThreshold, float passThreshold,
                       float goodThreshold, float excellentThreshold,
                       String semester) {
        setCourseId(courseId);
        setCourseName(courseName);
        setTeacherId(teacherId);
        setTeacherName(teacherName);
        setCredit(credit);
        setHour(hour);
        setFailThreshold(failThreshold);
        setPassThreshold(passThreshold);
        setGoodThreshold(goodThreshold);
        setExcellentThreshold(excellentThreshold);
        setSemester(semester);
    }
    
    // 默认值构造方法
    public CourseModel() {
        this(0, "Default Course", 0, "Default Teacher", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, SemUnknown);
    }

    // Getter 和 Setter 方法
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        if (courseId <= 0) { // ID 必须为正整数
            throw new IllegalArgumentException("Course ID must be a positive number");
        }
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) { // 课程名称不能为空
            throw new IllegalArgumentException("Course name cannot be empty");
        }
        this.courseName = courseName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        if (teacherId <= 0) { // 教师 ID 不能为空
            throw new IllegalArgumentException("Teacher ID cannot be empty");
        }
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        if (teacherName == null || teacherName.trim().isEmpty()) { // 教师姓名不能为空
            throw new IllegalArgumentException("Teacher name cannot be empty");
        }
        this.teacherName = teacherName;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        if (credit <= 0) { // 学分不能为空
            throw new IllegalArgumentException("Credit cannot lower than 0");
        }
        this.credit = credit;
    }

    public float getHour() {
        return hour;
    }

    public void setHour(float hour) {
        if (hour <= 0) { // 学时不能为空
            throw new IllegalArgumentException("Hour cannot lower than  0");
        }
        this.hour = hour;
    }

    public float getFailThreshold() {
        return failThreshold;
    }

    public void setFailThreshold(float failThreshold) {
        if (failThreshold < 0) { // 阈值必须大于 0
            throw new IllegalArgumentException("Fail threshold must be bigger than 0");
        }
        this.failThreshold = failThreshold;
    }

    public float getPassThreshold() {
        return passThreshold;
    }

    public void setPassThreshold(float passThreshold) {
        if (passThreshold < 0) {  // 阈值必须大于 0
            throw new IllegalArgumentException("Pass threshold must be bigger than 0");
        }
        this.passThreshold = passThreshold;
    }

    public float getGoodThreshold() {
        return goodThreshold;
    }

    public void setGoodThreshold(float goodThreshold) {
        if (goodThreshold < 0) { // 阈值必须大于 0
            throw new IllegalArgumentException("Good threshold must be bigger than 0");
        }
        this.goodThreshold = goodThreshold;
    }

    public float getExcellentThreshold() {
        return excellentThreshold;
    }

    public void setExcellentThreshold(float excellentThreshold) {
        if (excellentThreshold < 0) {  // 阈值必须大于 0
            throw new IllegalArgumentException("Excellent threshold must be bigger than 0");
        }
        this.excellentThreshold = excellentThreshold;
    }
    
    private static final Set<String> validSemesters = Set.of(
	    SemOneFirst,
	    SemOneSecond,
	    SemTwoFirst,
	    SemTwoSecond,
	    SemThreeFirst,
	    SemThreeSecond,
	    SemFourFirst,
	    SemFourSecond,
	    SemUnknown
	);

	public static boolean isValidSemester(String sem) {
	    return validSemesters.contains(sem);
	}
    	
    // Getter 和 Setter 方法
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        if (semester == null || semester.trim().isEmpty() || !isValidSemester(semester)) {
//        if (semester == null || semester.trim().isEmpty()) {
            this.semester = SemUnknown;
        } else {
            this.semester = semester;
        }
    }

    // 生成课程详细信息字符串
    @Override
    public String toString() {
        return String.format(
                "Course ID: %d, Name: %s, Teacher ID: %d, Teacher Name: %s, Credit: %.2f, Hour: %.2f, Fail Threshold: %.2f, Pass Threshold: %.2f, Good Threshold: %.2f, Excellent Threshold: %.2f, Semester: %s",
                courseId, courseName, teacherId, teacherName, credit, hour, failThreshold, passThreshold, goodThreshold, excellentThreshold, semester
        );
    }

    // 保存课程列表到 CSV
    public static void saveAllToCSV(List<CourseModel> courses) {
        String filePath = getDataPath();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"Course ID", "Course Name", "Teacher ID", "Teacher Name", "Credit", "Hour", "Fail Threshold", "Pass Threshold", "Good Threshold", "Excellent Threshold", "Semester"});
            for (CourseModel course : courses) {
                writer.writeNext(new String[]{
                        String.valueOf(course.getCourseId()),
                        course.getCourseName(),
                        String.valueOf(course.getTeacherId()),
                        course.getTeacherName(),
                        String.valueOf(course.getCredit()),
                        String.valueOf(course.getHour()),
                        String.valueOf(course.getFailThreshold()),
                        String.valueOf(course.getPassThreshold()),
                        String.valueOf(course.getGoodThreshold()),
                        String.valueOf(course.getExcellentThreshold()),
                        course.getSemester()
                });
            }
        } catch (IOException e) {
            System.err.println("Error saving to CSV: " + e.getMessage());
        }
    }

    // 从 CSV 加载课程列表
    public static List<CourseModel> loadFromCSV() {
        String filePath = getDataPath();
        List<CourseModel> courses = new ArrayList<>();
//        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
    	try (CSVReader reader = new CSVReader(
    	        new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String[] nextLine;
            if ((nextLine = reader.readNext()) != null) {
                System.out.println("CSV Header: " + String.join(", ", nextLine));
            }
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == AttributeNum) {
                    courses.add(new CourseModel(
                            Integer.parseInt(nextLine[0]),
                            nextLine[1],
                            Integer.parseInt(nextLine[2]),
                            nextLine[3],
                            Float.parseFloat(nextLine[4]),
                            Float.parseFloat(nextLine[5]),
                            Float.parseFloat(nextLine[6]),
                            Float.parseFloat(nextLine[7]),
                            Float.parseFloat(nextLine[8]),
                            Float.parseFloat(nextLine[9]),
                            nextLine[10]
                    ));
                    System.out.println(nextLine[10]);
                }
            }
        } catch (IOException | NumberFormatException | com.opencsv.exceptions.CsvValidationException e) {
            System.err.println("Error loading from CSV: " + e.getMessage());
        }
        return courses;
    }

    public static String getDataPath() {
        return System.getProperty("user.dir") + "/src/main/resources/data/courses.csv";
    }
}