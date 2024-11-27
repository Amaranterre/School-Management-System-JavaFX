package home.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

import home.model.*;


public class UserInfoController implements Initializable {

    @FXML
    private BarChart creditBarChart;
    
    @FXML
    private PieChart gradePieChart;
    
    @FXML
    private LineChart gradeLineChartData;

    @FXML
    private Label userName;

    @FXML
    private Label userSex;
    
    @FXML
    private Label userBirthday;
    
    @FXML
    private Label userInstitue;
    
    @FXML
    private Label userMajor;
    
    @FXML
    private Label userRole;

    public UserInfoController() {
        // 无需特殊初始化
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        // 设置列的 CellValueFactory，用于从 UserModel 中映射数据到表格
//        studentId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        name.setCellValueFactory(new PropertyValueFactory<>("name"));
//        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
//        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
//        institute.setCellValueFactory(new PropertyValueFactory<>("institute"));
//        major.setCellValueFactory(new PropertyValueFactory<>("major"));
//        role.setCellValueFactory(new PropertyValueFactory<>("role"));

        // 加载数据到 TableView
        loadUserData();
    }

    private void loadUserData() {
    	UserModel currUser = DataBase.getCurrUser();
    	userName.setText(currUser.getName());
    	userSex.setText(currUser.getSex());
    	userBirthday.setText(currUser.getBirthday());
    	userInstitue.setText(currUser.getInstitute());
    	userMajor.setText(currUser.getMajor());
    	userRole.setText(currUser.getRole());
    	
    	List<GradeModel> grades = DataBase.findGradesByStudentId(currUser.getId());
    	
    	loadGradePieChart(grades);
    	loadCreditBarChart(grades);
    	loadGradeLineChart(grades);
    }
    private void loadGradeLineChart(List<GradeModel> grades) {
    	int semOneFirst = 0;
    	int semOneSecond = 0;
    	int semTwoFirst = 0;
    	int semTwoSecond = 0;
    	int semThreeFirst = 0;
    	int semThreeSecond = 0;
    	int semFourFirst = 0;
    	int semFourSecond = 0;
    	
    	for(GradeModel grade: grades) {
    		CourseModel course = DataBase.findCourseById(grade.getCourseId());
    		if (grade.isFinished()) {
    			if (course.getSemester().equals(CourseModel.SemOneFirst)) {
    			    semOneFirst += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemOneSecond)) {
    			    semOneSecond += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemTwoFirst)) {
    			    semTwoFirst += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemTwoSecond)) {
    			    semTwoSecond += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemThreeFirst)) {
    			    semThreeFirst += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemThreeSecond)) {
    			    semThreeSecond += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemFourFirst)) {
    			    semFourFirst += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemFourSecond)) {
    			    semFourSecond += course.getCredit();
    			}
    		}
    	}
    	float semOneFirstScore = 0;
    	float semOneSecondScore = 0;
    	float semTwoFirstScore = 0;
    	float semTwoSecondScore = 0;
    	float semThreeFirstScore = 0;
    	float semThreeSecondScore = 0;
    	float semFourFirstScore = 0;
    	float semFourSecondScore = 0;
    	
    	for(GradeModel grade: grades) {
    		CourseModel course = DataBase.findCourseById(grade.getCourseId());
    		if (grade.isFinished()) {
    			if (course.getSemester().equals(CourseModel.SemOneFirst)) {
    				semOneFirstScore += course.getCredit() / semOneFirst * grade.getGrade();
    			} else if (course.getSemester().equals(CourseModel.SemOneSecond)) {
    				semOneSecondScore += course.getCredit() / semOneSecond * grade.getGrade();
    			} else if (course.getSemester().equals(CourseModel.SemTwoFirst)) {
    				semTwoFirstScore += course.getCredit() / semTwoFirst * grade.getGrade();
    			} else if (course.getSemester().equals(CourseModel.SemTwoSecond)) {
    				semTwoSecondScore += course.getCredit() / semTwoSecond * grade.getGrade();
    			} else if (course.getSemester().equals(CourseModel.SemThreeFirst)) {
    				semThreeFirstScore += course.getCredit() / semThreeFirst * grade.getGrade();
    			} else if (course.getSemester().equals(CourseModel.SemThreeSecond)) {
    				semThreeSecondScore += course.getCredit() / semThreeSecond * grade.getGrade();
    			} else if (course.getSemester().equals(CourseModel.SemFourFirst)) {
    				semFourFirstScore += course.getCredit() / semFourFirst * grade.getGrade();
    			} else if (course.getSemester().equals(CourseModel.SemFourSecond)) {
    				semFourSecondScore += course.getCredit() / semFourSecond * grade.getGrade();
    			}
    		}
    	}
    	
    	XYChart.Series dataSeries = new XYChart.Series();
    	dataSeries.setName("各学期加权平均分");
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemOneFirst, semOneFirstScore));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemOneSecond, semOneSecondScore));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemTwoFirst, semTwoFirstScore));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemTwoSecond, semTwoSecondScore));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemThreeFirst, semThreeFirstScore));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemThreeSecond, semThreeSecondScore));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemFourFirst, semFourFirstScore));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemFourSecond, semFourSecondScore));
    	
    	
    	gradeLineChartData.getData().add(dataSeries);
    }
    private void loadGradePieChart(List<GradeModel> grades) {
    	int fail = 0;
    	int pass = 0;
    	int good = 0;
    	int excellent = 0;
    	for(GradeModel grade: grades) {
    		CourseModel course = DataBase.findCourseById(grade.getCourseId());
    		if (grade.getGrade() >= course.getExcellentThreshold()) {
    			excellent += 1;
    		} else if (grade.getGrade() >= course.getGoodThreshold()) {
    			good += 1;
    		} else if (grade.getGrade() >= course.getPassThreshold()) {
    			pass += 1;
    		} else {
    			fail += 1;
    		}
    	}
        PieChart.Data slice1 = new PieChart.Data("fail", fail);
        PieChart.Data slice2 = new PieChart.Data("pass", pass);
        PieChart.Data slice3 = new PieChart.Data("good", good);
        PieChart.Data slice4 = new PieChart.Data("excellent", excellent);

        gradePieChart.getData().add(slice1);
        gradePieChart.getData().add(slice2);
        gradePieChart.getData().add(slice3);
        gradePieChart.getData().add(slice4);
    }
    private void loadCreditBarChart(List<GradeModel> grades) {
    	int semOneFirst = 0;
    	int semOneSecond = 0;
    	int semTwoFirst = 0;
    	int semTwoSecond = 0;
    	int semThreeFirst = 0;
    	int semThreeSecond = 0;
    	int semFourFirst = 0;
    	int semFourSecond = 0;
    	
    	for(GradeModel grade: grades) {
    		CourseModel course = DataBase.findCourseById(grade.getCourseId());
    		if (grade.isFinished()) {
    			if (course.getSemester().equals(CourseModel.SemOneFirst)) {
    			    semOneFirst += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemOneSecond)) {
    			    semOneSecond += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemTwoFirst)) {
    			    semTwoFirst += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemTwoSecond)) {
    			    semTwoSecond += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemThreeFirst)) {
    			    semThreeFirst += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemThreeSecond)) {
    			    semThreeSecond += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemFourFirst)) {
    			    semFourFirst += course.getCredit();
    			} else if (course.getSemester().equals(CourseModel.SemFourSecond)) {
    			    semFourSecond += course.getCredit();
    			}
    		}
    	}
    	
    	XYChart.Series dataSeries = new XYChart.Series();
    	dataSeries.setName("各学期所获得学分");
    	// 将所有学期的数据添加到 dataSeries1 中
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemOneFirst, semOneFirst));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemOneSecond, semOneSecond));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemTwoFirst, semTwoFirst));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemTwoSecond, semTwoSecond));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemThreeFirst, semThreeFirst));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemThreeSecond, semThreeSecond));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemFourFirst, semFourFirst));
    	dataSeries.getData().add(new XYChart.Data(CourseModel.SemFourSecond, semFourSecond));

    	
    	creditBarChart.getData().add(dataSeries);

    }
}
