//package home.model;
//
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//
//public class AdministratorModel extends UserModel {
//
//    private final StringProperty sex;
//    private final StringProperty birthday;
//    private final StringProperty institute;
//    private final StringProperty major;
//
//    // 无参构造方法
//    public AdministratorModel() {
//        this("", "", "", "", "", "", "");
//    }
//
//    // 带参构造方法
//    public AdministratorModel(String id, String pwd, String name, String sex, String birthday, String institute, String major) {
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
//        return String.format("Student{id='%s', name='%s', sex='%s', birthday='%s', institute='%s', major='%s'}",
//                getId(), getName(), getSex(), getBirthday(), getInstitute(), getMajor());
//    }
//}