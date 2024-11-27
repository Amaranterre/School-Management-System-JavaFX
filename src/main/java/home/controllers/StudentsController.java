package home.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import home.model.UserModel;

public class StudentsController implements Initializable {

    @FXML
    private TableView<UserModel> tbData;

    @FXML
    private TableColumn<UserModel, Integer> studentId;

    @FXML
    private TableColumn<UserModel, String> name;

    @FXML
    private TableColumn<UserModel, String> sex;

    @FXML
    private TableColumn<UserModel, String> birthday;

    @FXML
    private TableColumn<UserModel, String> institute;

    @FXML
    private TableColumn<UserModel, String> major;

    @FXML
    private TableColumn<UserModel, String> role;

    public StudentsController() {
        // 无需特殊初始化
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 设置列的 CellValueFactory，用于从 UserModel 中映射数据到表格
        studentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        institute.setCellValueFactory(new PropertyValueFactory<>("institute"));
        major.setCellValueFactory(new PropertyValueFactory<>("major"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));

        // 加载数据到 TableView
        loadStudentsData();
    }

    private void loadStudentsData() {
        // 从 DataBase 获取所有用户并过滤角色为 "Student"
        ObservableList<UserModel> studentsModels = FXCollections.observableArrayList(
            DataBase.findUsersByRole("Student")
        );

        // 设置数据到 TableView
        tbData.setItems(studentsModels);
    }
}
