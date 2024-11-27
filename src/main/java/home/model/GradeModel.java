package home.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradeModel {
    private int id;           // 成绩记录的唯一标识
    private int studentId;    // 学生 ID
    private int courseId;     // 课程 ID
    private float grade;      // 成绩
    private boolean isFinished; // 是否完成课程]

    public static final int AttributeNum = 5; // 属性个数，用于 CSV 校验

    // 构造方法
    public GradeModel(int id, int studentId, int courseId, float grade, boolean isFinished) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
        this.isFinished = isFinished;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Student ID must be a positive number.");
        }
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        if (courseId <= 0) {
            throw new IllegalArgumentException("Course ID must be a positive number.");
        }
        this.courseId = courseId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        if (grade < 0) {
            throw new IllegalArgumentException("Grade must be bigger than 0");
        }
        this.grade = grade;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    // 标记课程为完成
    public void markAsFinished() {
        this.isFinished = true;
    }

    // 标记课程为未完成
    public void markAsNotFinished() {
        this.isFinished = false;
    }

    // 覆盖 toString 方法，便于调试
    @Override
    public String toString() {
        return String.format("Grade{id=%d, studentId=%d, courseId=%d, grade=%.2f, isFinished=%b}",
                id, studentId, courseId, grade, isFinished);
    }

    // 保存所有成绩到 CSV 文件
    public static void saveAllToCSV(List<GradeModel> grades) {
        String filePath = getDataPath();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // 写入 CSV 表头
            writer.writeNext(new String[]{"ID", "Student ID", "Course ID", "Grade", "Is Finished", "Semester"});

            // 写入每条成绩记录
            for (GradeModel grade : grades) {
                writer.writeNext(new String[]{
                        String.valueOf(grade.getId()),
                        String.valueOf(grade.getStudentId()),
                        String.valueOf(grade.getCourseId()),
                        String.valueOf(grade.getGrade()),
                        String.valueOf(grade.isFinished())
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从 CSV 文件加载成绩列表
    public static List<GradeModel> loadFromCSV() {
        String filePath = getDataPath();
        List<GradeModel> grades = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;

            // 跳过表头
            if ((nextLine = reader.readNext()) != null) {
                if (nextLine.length != AttributeNum) {
                    throw new IllegalArgumentException("Incorrect CSV header format.");
                }
                System.out.println("CSV Header: " + String.join(", ", nextLine)); // 可选，打印表头
            }

            // 读取每条记录
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == AttributeNum) { // 确保字段数正确
                    grades.add(new GradeModel(
                            Integer.parseInt(nextLine[0]),  // ID
                            Integer.parseInt(nextLine[1]),  // Student ID
                            Integer.parseInt(nextLine[2]),  // Course ID
                            Float.parseFloat(nextLine[3]),  // Grade
                            Boolean.parseBoolean(nextLine[4])  // Is Finished
                    ));
                }
            }
        } catch (IOException | NumberFormatException | com.opencsv.exceptions.CsvValidationException e) {
            e.printStackTrace();
        }
        return grades;
    }

    // 获取数据文件路径
    public static String getDataPath() {
        return System.getProperty("user.dir") + "/src/main/resources/data/grades.csv";
    }
}
