package home.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class UserModel {

    private int id;
    private String pwd;
    private String name;
    private String sex;
    private String birthday;
    private String institute;
    private String major;
    private String role;

    public static final int AttributeNum = 8;

    // 带参构造方法
    public UserModel(int id, String pwd, String name, String sex, String birthday, String institute, String major, String role) {
        setId(id);
        setPwd(pwd);
        setName(name);
        setSex(sex);
        setBirthday(birthday);
        setInstitute(institute);
        setMajor(major);
        setRole(role);
    }

    // Getter 和 Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) { // 校验：ID 必须为正整数
            throw new IllegalArgumentException("ID must be a positive number");
        }
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        if (pwd == null || pwd.trim().isEmpty()) { // 校验：密码不能为空
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) { // 校验：姓名不能为空
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if (!sex.equalsIgnoreCase("Male") && !sex.equalsIgnoreCase("Female")) { // 校验：性别必须为 Male 或 Female
            throw new IllegalArgumentException("Sex must be 'Male' or 'Female'");
        }
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        if (birthday == null || birthday.trim().isEmpty()) { // 简单校验生日非空
            throw new IllegalArgumentException("Birthday cannot be empty");
        }
        // 可以加入更复杂的日期格式校验
        this.birthday = birthday;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        if (institute == null || institute.trim().isEmpty()) { // 校验：学院不能为空
            throw new IllegalArgumentException("Institute cannot be empty");
        }
        this.institute = institute;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if (major == null || major.trim().isEmpty()) { // 校验：专业不能为空
            throw new IllegalArgumentException("Major cannot be empty");
        }
        this.major = major;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) { // 校验：角色不能为空
            throw new IllegalArgumentException("Role cannot be empty");
        }
        this.role = role;
    }

    // 覆盖 toString 方法，便于调试
    @Override
    public String toString() {
        return String.format("User{id=%d, pwd='%s', name='%s', sex='%s', birthday='%s', institute='%s', major='%s', role='%s'}",
                id, pwd, name, sex, birthday, institute, major, role);
    }

    // 保存所有用户到 CSV 文件
    public static void saveAllToCSV(List<UserModel> users) {
        String filePath = getDataPath();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write CSV header
            writer.writeNext(new String[]{"ID", "Password", "Name", "Sex", "Birthday", "Institute", "Major", "Role"});

            // Write each user's details
            for (UserModel user : users) {
                writer.writeNext(new String[]{
                        String.valueOf(user.getId()),
                        user.getPwd(),
                        user.getName(),
                        user.getSex(),
                        user.getBirthday(),
                        user.getInstitute(),
                        user.getMajor(),
                        user.getRole(),
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从 CSV 文件加载用户列表
    public static List<UserModel> loadFromCSV() {
        String filePath = getDataPath();
        List<UserModel> users = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;

            // 跳过表头
            if ((nextLine = reader.readNext()) != null) {
                if (nextLine.length != AttributeNum) {
                    throw new com.opencsv.exceptions.CsvValidationException("Attribute number incorrect");
                }
                System.out.println("CSV Header: " + String.join(", ", nextLine)); // 输出表头（可选）
            }

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == AttributeNum) { // 确保字段数正确
                    users.add(new UserModel(
                            Integer.parseInt(nextLine[0]), // 将 ID 转换为整数
                            nextLine[1], nextLine[2], nextLine[3],
                            nextLine[4], nextLine[5], nextLine[6], nextLine[7]
                    ));
                }
            }
        } catch (IOException | com.opencsv.exceptions.CsvValidationException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static String getDataPath() {
        return System.getProperty("user.dir") + "/src/main/resources/data/users.csv";
    }
}