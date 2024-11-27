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
//public class TeacherModel extends UserModel {
//
//    private final StringProperty sex;
//    private final StringProperty birthday;
//    private final StringProperty institute;
//    private final StringProperty major;
//
//    // 无参构造方法
//    public TeacherModel() {
//        this("", "", "", "", "", "", "");
//    }
//
//    // 带参构造方法
//    public TeacherModel(String id, String pwd, String name, String sex, String birthday, String institute, String major) {
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
//        return String.format("Teacher{id='%s', name='%s', sex='%s', birthday='%s', institute='%s', major='%s'}",
//                getId(), getName(), getSex(), getBirthday(), getInstitute(), getMajor());
//    }
//    
//    // 将 教师信息保存到CSV文件
//    public static void savaAllToCSV(List<TeacherModel> teachers) {
//    	String filePath = getDataPath();
//        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
//            // Write CSV header
//            writer.writeNext(new String[]{"ID", "Password", "Name", "Sex", "Birthday", "Institute", "Major"});
//
//            // Write each teacher's details
//            for (TeacherModel teacher : teachers) {
//                writer.writeNext(new String[]{
//                		teacher.getId(), 
//                		teacher.getPwd(), 
//                		teacher.getName(), 
//                		teacher.getSex(), 
//                		teacher.getBirthday(), 
//                		teacher.getInstitute(), 
//                		teacher.getMajor()
//                });
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    // 从 CSV 文件加载教师列表
//    public static List<TeacherModel> loadFromCSV() {
//    	String filePath = getDataPath();
//        List<TeacherModel> teachers = new ArrayList<>();
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
//                	teachers.add(new TeacherModel(
//                            nextLine[0], nextLine[1], nextLine[2],
//                            nextLine[3], nextLine[4], nextLine[5], nextLine[6]
//                    ));
//                }
//            }
//        } catch (IOException | com.opencsv.exceptions.CsvValidationException e) {
//            e.printStackTrace();
//        }
//        return teachers;
//    }
//    
//    public static String getDataPath() {
//    	return System.getProperty("user.dir") + "/src/main/resources/data/teachers.csv";
//    }
//}