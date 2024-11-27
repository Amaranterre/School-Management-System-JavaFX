//package home.model;
//
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.opencsv.CSVReader;
//import com.opencsv.CSVWriter;
//
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//
//public class StudentModel extends UserModel {
//
//    private final StringProperty sex;
//    private final StringProperty birthday;
//    private final StringProperty institute;
//    private final StringProperty major;
//
//    // 无参构造方法
//    public StudentModel() {
//        this("", "", "", "", "", "", "");
//    }
//
//    // 带参构造方法
//    public StudentModel(String id, String pwd, String name, String sex, String birthday, String institute, String major) {
//        super(id, pwd, name); // 调用父类构造方法
//        this.sex = new SimpleStringProperty(sex);
//        this.birthday = new SimpleStringProperty(birthday);
//        this.institute = new SimpleStringProperty(institute);
//        this.major = new SimpleStringProperty(major);
//    }
//
//    // Getter 和 Setter（Property 形式）
//    public StringProperty sexProperty() {
//        return sex;
//    }
//
//    public String getSex() {
//        return sex.get();
//    }
//
//    public void setSex(String sex) {
//        this.sex.set(sex);
//    }
//
//    public StringProperty birthdayProperty() {
//        return birthday;
//    }
//
//    public String getBirthday() {
//        return birthday.get();
//    }
//
//    public void setBirthday(String birthday) {
//        this.birthday.set(birthday);
//    }
//
//    public StringProperty instituteProperty() {
//        return institute;
//    }
//
//    public String getInstitute() {
//        return institute.get();
//    }
//
//    public void setInstitute(String institute) {
//        this.institute.set(institute);
//    }
//
//    public StringProperty majorProperty() {
//        return major;
//    }
//
//    public String getMajor() {
//        return major.get();
//    }
//
//    public void setMajor(String major) {
//        this.major.set(major);
//    }
//
//    // 覆盖 toString 方法，便于调试
//    @Override
//    public String toString() {
//        return String.format("Student{id='%s', password='%s' name='%s', sex='%s', birthday='%s', institute='%s', major='%s'}",
//                getId(), getPwd(), getName(), getSex(), getBirthday(), getInstitute(), getMajor());
//    }
//    
//    // 将 学生信息保存到CSV文件
//    public static void savaAllToCSV(List<StudentModel> students) {
//    	String filePath = getDataPath();
//        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
//            // Write CSV header
//            writer.writeNext(new String[]{"ID", "Password", "Name", "Sex", "Birthday", "Institute", "Major"});
//
//            // Write each student's details
//            for (StudentModel student : students) {
//                writer.writeNext(new String[]{
//                    student.getId(), 
//                    student.getPwd(), 
//                    student.getName(), 
//                    student.getSex(), 
//                    student.getBirthday(), 
//                    student.getInstitute(), 
//                    student.getMajor()
//                });
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    // 从 CSV 文件加载学生列表
//    public static List<StudentModel> loadFromCSV() {
//    	String filePath = getDataPath();
//        List<StudentModel> students = new ArrayList<>();
//        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
//            String[] nextLine;
//            
//            // 跳过表头
//            if ((nextLine = reader.readNext()) != null) {
//                System.out.println("表头: " + String.join(", ", nextLine)); // 输出表头（可选）
//            }
//            
//            while ((nextLine = reader.readNext()) != null) {
//                if (nextLine.length == 7) { // 确保字段数正确
//                	students.add(new StudentModel(
//                            nextLine[0], nextLine[1], nextLine[2],
//                            nextLine[3], nextLine[4], nextLine[5], nextLine[6]
//                    ));
//                }
//            }
//        } catch (IOException | com.opencsv.exceptions.CsvValidationException e) {
//            e.printStackTrace();
//        }
//        return students;
//    }
//    
//    public static String getDataPath() {
//    	return System.getProperty("user.dir") + "/src/main/resources/data/students.csv";
//    }
//}