package controller;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import library.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;



public class MainController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    private TextField idStField;

    @FXML
    private TextField nameStField;

    @FXML
    private TextField surnameStField;

    @FXML
    private TextField groupStField;
    @FXML
    private TableView<Students> TableViewSt;
    
    @FXML
    private TableColumn<Students, String> st_id;
    
    @FXML
    private TableColumn<Students, String> st_name;

    @FXML
    private TableColumn<Students, String> st_surname;

    @FXML
    private TableColumn<Students, String> group_name;

    @FXML
    private TextField idDnField;

    @FXML
    private TextField nameDnField;

    @FXML
    private TextField max_scoreDnField;

    @FXML
    private TextField num_lessonsDnField;

    @FXML
    private TableView<Disciplines> TableViewDn;

    @FXML
    private TableColumn<Disciplines, Integer> dn_id;

    @FXML
    private TableColumn<Disciplines, String> dn_name;

    @FXML
    private TableColumn<Disciplines, Integer> dn_max_score;

    @FXML
    private TableColumn<Disciplines, Integer> dn_num_lessons;

    @FXML
    private TextField passField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField mgPassField;

    @FXML
    private TextField mgLoginField;

    @FXML
    private TextField mgAdm_rulesField;

    @FXML
    private PasswordField mgSuperRuleField;

    @FXML
    private Label loginFailed;

    @FXML
    private TextField loginRegField;

    @FXML
    private TextField passRegField;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField oldPassword;
    @FXML
    private TextField oldLogin;
    @FXML
    private Label userNotFound;

    @FXML
    private  Label registerMessage;

    @FXML
    private TextField admPassword;

    @FXML
    private RadioButton admStatus;

    @FXML
    public Label addUserMessage;

    @FXML
    private TableColumn<Users, String> login;
    @FXML
    private TableColumn<Users, Integer> adm_rules;
    private String secretWord = "123456789";
    private String superRule = "987654321";
    @FXML
    private TableView<Users> TableViewUs;
    @FXML
    private TableView<Schedule> TableViewSl;
    @FXML
    private TableColumn sl_idSl;
    @FXML
    private TableColumn dn_idSl;
    @FXML
    private TableColumn group_nameSl;
    @FXML
    private TableColumn schedule_dateSl;

    @FXML
    public TableColumn att_idAt;
    @FXML
    public TableColumn st_idAt;
    @FXML
    public TableColumn dn_idAt;
    @FXML
    public TableColumn sl_idAt;
    @FXML
    public TableColumn att_stAt;

    @FXML
    private TableView<Attendance> TableViewAt;
    @FXML
    private  TableColumn grades_idGr;
    @FXML
    private  TableColumn st_idGr;
    @FXML
    private  TableColumn dn_idGr;
    @FXML
    private  TableColumn gradeGr;
    @FXML
    private  TableColumn gr_dateGr;
    @FXML
    private TableView<Grades> TableViewGr;
    @FXML
    private TextField idGrField;
    @FXML
    private TextField st_idGrField;
    @FXML
    private TextField dn_idGrField;
    @FXML
    private TextField gradeGrField;
    @FXML
    private TextField dateGrField;
    @FXML
    private TextField idAtField;
    @FXML
    private TextField st_idAtField;
    @FXML
    private TextField dn_idAtField;
    @FXML
    private TextField sl_idAtField;
    @FXML
    private TextField statusAtField;
    @FXML
    private TextField idSlField;
    @FXML
    private TextField dn_idSLField;
    @FXML
    private TextField gnSLField;
    @FXML
    private TextField dateSLField;
    @FXML
    private TableView<Requests> TableViewReq1;
    @FXML
    private TableColumn<Requests, String> full_nameReq1;
    @FXML
    private TableColumn<Requests, String> full_nameReq2;
    @FXML
    private TableColumn<Requests, Integer> gradeReq2;
    @FXML
    private TableView<Requests> TableViewReq2;
    @FXML
    private TableColumn<Requests, String> full_nameReq3;
    @FXML
    private TableColumn<Requests, Integer> numReq3;
    @FXML
    private TableView<Requests> TableViewReq3;
    @FXML
    private TableColumn<Requests, String> full_nameReq4;
    @FXML
    private TableColumn<Requests, Integer> gradeReq4;
    @FXML
    private TableView<Requests> TableViewReq4;
    @FXML
    private TableColumn<Requests, String> full_nameReq5;
    @FXML
    private TableView<Requests> TableViewReq5;
    @FXML
    private TableColumn<Requests, String> full_nameReq6;
    @FXML
    private TableColumn<Requests, String> gradeReq6;
    @FXML
    private TableView<Requests> TableViewReq6;

    @FXML
    private Label admMessage;

    @FXML
    private void showLabelForFiveSeconds() {
        admMessage.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            admMessage.setVisible(false);
        });
        pause.play();
    }

    @FXML
    private void insertButtonDn() {
        String query = "INSERT INTO disciplines (dn_id, dn_name, dn_max_score, dn_num_lessons) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, idDnField.getText());
            statement.setString(2, nameDnField.getText());
            statement.setString(3, max_scoreDnField.getText());
            statement.setString(4, num_lessonsDnField.getText());
            statement.executeUpdate();
            showDisciplines();
        }catch (SQLException e) {
                e.printStackTrace();
                showLabelForFiveSeconds();
        }

    }

    @FXML
    private void updateButtonDn()  {
        String query = "UPDATE disciplines SET dn_name = ?, dn_max_score = ?, dn_num_lessons = ? WHERE dn_id = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, nameDnField.getText());
            statement.setString(2, max_scoreDnField.getText());
            statement.setString(3, num_lessonsDnField.getText());
            statement.setString(4, idDnField.getText());
            statement.executeUpdate();
            showDisciplines();
        }catch (SQLException e) {
        e.printStackTrace();
        showLabelForFiveSeconds();
        }
    }

    @FXML
    private void deleteButtonDn()  {
        String query = "DELETE FROM disciplines WHERE dn_id = ?";
        try {
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, idDnField.getText());
        statement.executeUpdate();
        showDisciplines();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }

    @FXML
    private void insertButtonSt()  {
        String query = "INSERT INTO students (st_id, st_name, st_surname, group_name) VALUES (?, ?, ?, ?)";
        try {
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, idStField.getText());
        statement.setString(2, nameStField.getText());
        statement.setString(3, surnameStField.getText());
        statement.setString(4, groupStField.getText());
        statement.executeUpdate();
        showStudents();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }

    @FXML
    private void updateButtonSt() {
        String query = "UPDATE students SET st_name = ?, st_surname = ?, group_name = ? WHERE st_id = ?";
        try{
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, nameStField.getText());
        statement.setString(2, surnameStField.getText());
        statement.setString(3, groupStField.getText());
        statement.setString(4, idStField.getText());
        statement.executeUpdate();
        showStudents();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }

    @FXML
    private void deleteButtonSt() {
        String query = "DELETE FROM students WHERE st_id = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, idStField.getText());
            statement.executeUpdate();
            showStudents();
        }catch (SQLException e) {
        e.printStackTrace();
        showLabelForFiveSeconds();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    private void admCheck() {
        if (admStatus.isSelected()){
            admPassword.setVisible(true);
        }
        else {
            admPassword.setVisible(false);
        }
}

    @FXML
    private void changePassHyperLink(javafx.event.ActionEvent event) throws IOException {
        parent = (Parent) FXMLLoader.load(getClass().getResource(
                "/view/ChangePassword.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Change Password Page");
        stage.show();
    }

    public void updatePassword() throws SQLException {
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        String query = "SELECT * FROM authentication WHERE login = ? AND pass = ?";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, oldLogin.getText());
        statement.setString(2, oldPassword.getText());
        ResultSet rs = statement.executeQuery();
            Users users;
            while(rs.next()) {
                users = new Users(rs.getString("login"), rs.getString("pass"));
                usersList.add(users);
            }
            Boolean match = false;
            for (int i = 0; i< usersList.size(); i++) {
                if (usersList.get(i).getLogin().equals(oldLogin.getText()) && usersList.get(i).getPass().equals(oldPassword.getText())){
                     query = "UPDATE authentication SET pass = ? WHERE login = ?";
                     statement = getConnection().prepareStatement(query);
                    statement.setString(1, newPassword.getText());
                    statement.setString(2, oldLogin.getText());
                    statement.executeUpdate();
                   match = true;
                    userNotFound.setText("Password changed");
                    userNotFound.textFillProperty().bind(new SimpleObjectProperty<>(Color.GREEN));
                    userNotFound.setVisible(true);
                   break;
                }
            }
            if (!match){
                userNotFound.setVisible(true);
            }
    }

    public void register(javafx.event.ActionEvent event) throws IOException {
        parent =  FXMLLoader.load(getClass().getResource(
                "/view/Registration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Registration Page");
        stage.show();

    }

    public void addNewUser() throws SQLException {
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        String query = "SELECT * FROM authentication WHERE login = ?";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, loginRegField.getText());
        ResultSet rs;
            rs = statement.executeQuery();;
            Users users;
            while(rs.next()) {
                users = new Users(rs.getString("login"), rs.getString("pass"));
                usersList.add(users);
            }
        if (usersList.isEmpty()) {
                if (admStatus.isSelected()) {
                    if (admPassword.getText().equals(secretWord)) {
                         query = "INSERT INTO authentication (login, pass, adm_rules) VALUES (?, ?, 1)";
                         statement = getConnection().prepareStatement(query);
                        statement.setString(1, loginRegField.getText());
                        statement.setString(2, passRegField.getText());
                        statement.executeUpdate();
                        registerMessage.setTextFill(Color.GREEN);
                        registerMessage.setText("Successful registration");
                        registerMessage.setVisible(true);
                    } else {
                        registerMessage.setTextFill(Color.RED);
                        registerMessage.setText("Wrong Admin password");
                        registerMessage.setVisible(true);
                    }
                } else {
                    query = "INSERT INTO authentication (login, pass, adm_rules) VALUES (?, ?, 0)";
                    statement = getConnection().prepareStatement(query);
                    statement.setString(1, loginRegField.getText());
                    statement.setString(2, passRegField.getText());
                    statement.executeUpdate();
                    registerMessage.setTextFill(Color.GREEN);
                    registerMessage.setText("Successful registration");
                    registerMessage.setVisible(true);
                }
        }
        else {
            registerMessage.setTextFill(Color.RED);
            registerMessage.setText("Login is taken");
            registerMessage.setVisible(true);
        }
    }

    public void authenticationCheck(javafx.event.ActionEvent event){
    ObservableList<Users> usersList = FXCollections.observableArrayList();
    Connection connection = getConnection();
    String query = "SELECT * FROM authentication;";
    Statement dn;
    ResultSet rs;
    try {
        dn = connection.createStatement();
        rs = dn.executeQuery(query);
        Users users;
        while(rs.next()) {
            users = new Users(rs.getString("login"), rs.getString("pass"), rs.getInt("adm_rules"));
            usersList.add(users);
        }

        for (int i = 0; i< usersList.size(); i++) {
            if (usersList.get(i).getLogin().equals(loginField.getText()) && usersList.get(i).getPass().equals(passField.getText())){
                if(usersList.get(i).getAdm_rules().equals(1)){
                    parent =  FXMLLoader.load(getClass().getResource(
                            "/view/AdmPage.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle("Admin Page");
                    stage.show();
                    break;
                }
                else{
                    parent =  FXMLLoader.load(getClass().getResource(
                            "/view/UserPage.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle("User page");
                    stage.show();
                    break;
                }
                }
        }
        loginFailed.setVisible(true);

    } catch (Exception e) {

        e.printStackTrace();
    }
}

    public void switchToAuthentication(javafx.event.ActionEvent event) throws IOException {
        parent =  load(getClass().getResource(
                "/view/Authentication.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    public Connection getConnection() {
    	try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/paper_work","root","Password123");
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public ObservableList<Students> getStudentsList(){
    	ObservableList<Students> studentsList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM STUDENTS;";
        Statement st;
    	ResultSet rs;
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);

			Students students;
			while(rs.next()) {
				students = new Students(rs.getString("st_id"), rs.getString("st_name"),rs.getString("st_surname"),rs.getString("group_name"));
				studentsList.add(students);
				}
		} catch (Exception e) {

			e.printStackTrace();
		}
    	return studentsList;
    }

    public ObservableList<Disciplines> getDisciplinesList(){
        ObservableList<Disciplines> disciplinesList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM Disciplines;";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);

            Disciplines disciplines;
            while(rs.next()) {
                disciplines = new Disciplines(rs.getInt("dn_id"), rs.getString("dn_name"), rs.getInt("dn_max_score"),rs.getInt("dn_num_lessons"));
                disciplinesList.add(disciplines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplinesList;
    }

    public void showDisciplines() {
        ObservableList<Disciplines> list = getDisciplinesList();
        dn_id.setCellValueFactory(new PropertyValueFactory<Disciplines,Integer>("dn_id"));
        dn_name.setCellValueFactory(new PropertyValueFactory<Disciplines,String>("dn_name"));
        dn_max_score.setCellValueFactory(new PropertyValueFactory<Disciplines,Integer>("dn_max_score"));
        dn_num_lessons.setCellValueFactory(new PropertyValueFactory<Disciplines,Integer>("dn_num_lessons"));
        TableViewDn.setItems(list);
    }

    public void showStudents() {
    	ObservableList<Students> list = getStudentsList();
        st_id.setCellValueFactory(new PropertyValueFactory<Students,String>("id"));
        st_name.setCellValueFactory(new PropertyValueFactory<Students,String>("st_name"));
        st_surname.setCellValueFactory(new PropertyValueFactory<Students,String>("st_surname"));
        group_name.setCellValueFactory(new PropertyValueFactory<Students,String>("group_name"));
    	TableViewSt.setItems(list);
    }

    @FXML
    public void insertUserButton() throws SQLException {
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from authentication where login = ?;";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, mgLoginField.getText());
        ResultSet rs;
        rs = statement.executeQuery();
        Users users;
        while(rs.next()) {
            users = new Users(rs.getString("login"), rs.getString("pass"));
            usersList.add(users);
        }
        if (usersList.isEmpty()){
        Integer num = Integer.parseInt(mgAdm_rulesField.getText());
             query = "INSERT INTO authentication (login, pass, adm_rules) VALUES (?, ?, ?)";
             statement = getConnection().prepareStatement(query);
            statement.setString(1, mgLoginField.getText());
            statement.setString(2, mgPassField.getText());
            statement.setString(3, mgAdm_rulesField.getText());
            if (num.equals(1)){
            if (mgSuperRuleField.getText().equals(superRule)){
                statement.executeUpdate();
                addUserMessage.setText("Completed");
                addUserMessage.setTextFill(Color.GREEN);
                addUserMessage.setVisible(true);
            }
            else {
                addUserMessage.setText("Wrong Super Admin Password");
                addUserMessage.setTextFill(Color.RED);
                addUserMessage.setVisible(true);
            }
        }
        else{
                statement.executeUpdate();
                addUserMessage.setText("Completed");
                addUserMessage.setTextFill(Color.GREEN);
                addUserMessage.setVisible(true);
        }
        }
        else {
            addUserMessage.setText("Login is taken");
            addUserMessage.setTextFill(Color.RED);
            addUserMessage.setVisible(true);
        }
         showUsers();
    }

    @FXML
    public void updateUserButton() throws SQLException {
        ObservableList<Users> list = getUsersList();
        Boolean userFound = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLogin().equals(mgLoginField.getText())){
                userFound = true;
                String query = "UPDATE authentication SET pass = ?, adm_rules = ? WHERE login = ?";
                PreparedStatement statement = getConnection().prepareStatement(query);
                    statement.setString(1, mgPassField.getText());
                    statement.setString(2, mgAdm_rulesField.getText());
                    statement.setString(3, mgLoginField.getText());

                if(list.get(i).getAdm_rules().equals(1)){
                    if (mgSuperRuleField.getText().equals(superRule)){
                        statement.executeUpdate();
                        addUserMessage.setText("Completed");
                        addUserMessage.setTextFill(Color.GREEN);
                        addUserMessage.setVisible(true);
                        break;
                    }
                    else
                        addUserMessage.setText("Wrong Super Admin Password");
                        addUserMessage.setTextFill(Color.RED);
                        addUserMessage.setVisible(true);
                        break;
                    }
                else{
                    statement.executeUpdate();
                    addUserMessage.setText("Completed");
                    addUserMessage.setTextFill(Color.GREEN);
                    addUserMessage.setVisible(true);
                    break;
                }
                }
                if(i == (list.size()-1) && userFound == false ){
                    addUserMessage.setText("No user found");
                    addUserMessage.setTextFill(Color.RED);
                    addUserMessage.setVisible(true);
                }
        }
        showUsers();
    }

    @FXML
    public void deleteUserButton() throws SQLException {
        ObservableList<Users> list = getUsersList();
        Boolean userFound = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLogin().equals(mgLoginField.getText())){
                String query = "DELETE FROM authentication WHERE login = ?";
                PreparedStatement statement = getConnection().prepareStatement(query);
                statement.setString(1, mgLoginField.getText());
                userFound = true;
                if(list.get(i).getAdm_rules().equals(1)){
                    if (mgSuperRuleField.getText().equals(superRule)){
                        statement.executeUpdate();
                        addUserMessage.setText("Completed");
                        addUserMessage.setTextFill(Color.GREEN);
                        addUserMessage.setVisible(true);
                        break;
                    }
                    else {
                        addUserMessage.setText("Wrong Super Admin Password");
                        addUserMessage.setTextFill(Color.RED);
                        addUserMessage.setVisible(true);
                        break;
                    }
                }
                else{
                    statement.executeUpdate();
                    addUserMessage.setText("Completed");
                    addUserMessage.setTextFill(Color.GREEN);
                    addUserMessage.setVisible(true);
                    break;
                }
            }
            if(i == (list.size()-1) && userFound == false ){
                addUserMessage.setText("No user found");
                addUserMessage.setTextFill(Color.RED);
                addUserMessage.setVisible(true);
            }
        }
        showUsers();
    }

    public ObservableList<Users> getUsersList(){
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM authentication;";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);
            Users users;
            while(rs.next()) {
                users = new Users(rs.getString("login"), rs.getString("pass"), rs.getInt("adm_rules"));
                usersList.add(users);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return usersList;
    }

    public void showUsers() {
        ObservableList<Users> list = getUsersList();
        login.setCellValueFactory(new PropertyValueFactory<Users,String>("login"));
        adm_rules.setCellValueFactory(new PropertyValueFactory<Users,Integer>("adm_rules"));
        TableViewUs.setItems(list);
    }

    public void manageUsers(javafx.event.ActionEvent event) throws IOException {
        parent = (Parent) FXMLLoader.load(getClass().getResource(
                "/view/ManageUsers.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Manage Users Page");
        stage.show();
    }

    @FXML
    public void insertButtonAt() throws ParseException {
        String query = "INSERT INTO attendance (att_id, st_id, dn_id, sl_id, att_status) VALUES (?, ?, ?, ?, ?)";
        try{
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, idAtField.getText());
        statement.setString(2, st_idAtField.getText());
        statement.setString(3, dn_idAtField.getText());
        statement.setString(4, sl_idAtField.getText());
        statement.setString(5, statusAtField.getText());
        statement.executeUpdate();
        showAttendance();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }

    @FXML
    public void updateButtonAt() throws ParseException {
        String query = "UPDATE attendance SET st_id = ?, dn_id = ?, att_status = ?, sl_id = ? WHERE att_id = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, st_idAtField.getText());
            statement.setString(2, dn_idAtField.getText());
            statement.setString(3, statusAtField.getText());
            statement.setString(4, sl_idAtField.getText());
            statement.setString(5, idAtField.getText());
            statement.executeUpdate();
            showAttendance();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }

    @FXML
    public void deleteButtonAt() throws ParseException {
        String query = "DELETE FROM attendance WHERE att_id = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, idAtField.getText());
            statement.executeUpdate();
            showAttendance();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }

    @FXML
    public void insertButtonSL() throws ParseException {
        String query = "INSERT INTO Schedule (sl_id, dn_id, group_name, schedule_date) VALUES (?, ?, ?, ?)";
        try{
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, idSlField.getText());
        statement.setString(2, dn_idSLField.getText());
        statement.setString(3, gnSLField.getText());
        statement.setString(4, dateSLField.getText());
        statement.executeUpdate();
        showSchedule();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }
    @FXML
    public void updateButtonSL() throws ParseException {
        String query = "UPDATE Schedule SET dn_id = ?, group_name = ?, schedule_date = ? WHERE sl_id = ?";
        try{
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, dn_idSLField.getText());
        statement.setString(2, gnSLField.getText());
        statement.setString(3, dateSLField.getText());
        statement.setString(4, idSlField.getText());
        statement.executeUpdate();
        showSchedule();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }
    @FXML
    public void deleteButtonSL() throws ParseException {
        String query = "DELETE FROM Schedule WHERE sl_id = ?";
        try{
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, idSlField.getText());
        statement.executeUpdate();
        showSchedule();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }

    public ObservableList<Schedule> getScheduleList() {
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM schedule;";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);
            Schedule schedule;
            while(rs.next()) {
                String dateString = rs.getString("schedule_date");
                LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                schedule = new Schedule(rs.getInt("sl_id"), rs.getInt("dn_id"), rs.getString("group_name"), dateTime);
                scheduleList.add(schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scheduleList;
    }

    public void showSchedule() {
        ObservableList<Schedule> list = getScheduleList();
        sl_idSl.setCellValueFactory(new PropertyValueFactory<>("sl_id"));
        dn_idSl.setCellValueFactory(new PropertyValueFactory<>("dn_id"));
        group_nameSl.setCellValueFactory(new PropertyValueFactory<>("group_name"));
        schedule_dateSl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Schedule, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Schedule, String> cellData) {
                Schedule schedule = cellData.getValue();
                String date = schedule.getSchedule_dat().toString();
                return new SimpleStringProperty(date);
            }
        });
        TableViewSl.setItems(list);
    }

    public ObservableList<Attendance> getAttendanceList() {
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM Attendance;";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);
            Attendance schedule;
            while(rs.next()) {
                schedule = new Attendance(rs.getInt("att_id"), rs.getInt("st_id"), rs.getInt("dn_id"), rs.getInt("att_status"), rs.getInt("sl_id") );
                attendanceList.add(schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendanceList;
    }

    public void showAttendance ()  {
        ObservableList<Attendance > list = getAttendanceList();
        att_idAt.setCellValueFactory(new PropertyValueFactory<>("att_id"));
        st_idAt.setCellValueFactory(new PropertyValueFactory<>("st_id"));
        dn_idAt.setCellValueFactory(new PropertyValueFactory<>("dn_id"));
        sl_idAt.setCellValueFactory(new PropertyValueFactory<>("sl_id"));
        att_stAt.setCellValueFactory(new PropertyValueFactory<>("att_status"));
        TableViewAt.setItems(list);
    }

    public ObservableList<Grades> getGradesList()  {
        ObservableList<Grades> gradesList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM Grades;";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);
            Grades grades;
            while(rs.next()) {
                String dateString = rs.getString("gr_date");
                LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                grades = new Grades(rs.getInt("grades_id"), rs.getInt("st_id"), rs.getInt("dn_id"), rs.getInt("grade"), dateTime);
                gradesList.add(grades);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gradesList;
    }

    public void showGrades() {
        ObservableList<Grades> list = getGradesList();
        grades_idGr.setCellValueFactory(new PropertyValueFactory<>("grades_id"));
        st_idGr.setCellValueFactory(new PropertyValueFactory<>("st_id"));
        dn_idGr.setCellValueFactory(new PropertyValueFactory<>("dn_id"));
        gradeGr.setCellValueFactory(new PropertyValueFactory<>("grade"));
        gr_dateGr.setCellValueFactory(new PropertyValueFactory<>("gr_date"));
        TableViewGr.setItems(list);
    }

    @FXML
    public void insertButtonGr() {
        String query = "INSERT INTO grades (grades_id, st_id, dn_id, grade, gr_date) VALUES (?, ?, ?, ?, ?)";
        try{
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, idGrField.getText());
        statement.setString(2, st_idGrField.getText());
        statement.setString(3, dn_idGrField.getText());
        statement.setString(4, gradeGrField.getText());
        statement.setString(5, dateGrField.getText());
        statement.executeUpdate();
        showGrades();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }
    @FXML
    public void updateButtonGr()  {
        String query = "UPDATE grades SET st_id = ?, dn_id = ?, grade = ?, gr_date = ? WHERE grades_id = ?";
        try{
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, st_idGrField.getText());
        statement.setString(2, dn_idGrField.getText());
        statement.setString(3, gradeGrField.getText());
        statement.setString(4, dateGrField.getText());
        statement.setString(5, idGrField.getText());
        statement.executeUpdate();
        showGrades();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }

    @FXML
    public void deleteButtonGr()  {
        String query = "DELETE FROM grades WHERE grades_id = ?";
        try{
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, idGrField.getText());
        statement.executeUpdate();
        showGrades();
        } catch (SQLException e) {
            e.printStackTrace();
            showLabelForFiveSeconds();
        }
    }

    @FXML
    public void showRequestsHyperLink(ActionEvent actionEvent) throws IOException {
        parent = (Parent) load(getClass().getResource(
                "/view/Requests.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Requests Page");
        stage.show();
    }

    public ObservableList<Requests> getReq1List() throws ParseException {
        ObservableList<Requests> req1List = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select concat(st_name, \" \", st_surname ) as full_name from Students where group_name in (select group_name from schedule where dn_id = 3);";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);
            Requests req1;
            while(rs.next()) {
                req1 = new Requests(rs.getString("full_name"));
                req1List.add(req1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req1List;
    }

    public void showReq1() throws ParseException {
        ObservableList<Requests> list = getReq1List();
        full_nameReq1.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        TableViewReq1.setItems(list);
    }

    public ObservableList<Requests> getReq2List() throws ParseException {
        ObservableList<Requests> req1List = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select concat(st_name, \" \", st_surname )  as full_name, g.grade  from Students join Grades g using (st_id) where dn_id = 3 order by g.grade desc;";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);
            Requests req2;
            while(rs.next()) {
                req2 = new Requests(rs.getString("full_name"), rs.getInt("grade"));
                req1List.add(req2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req1List;
    }

    public void showReq2() throws ParseException {
        ObservableList<Requests> list = getReq2List();
        full_nameReq2.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        gradeReq2.setCellValueFactory(new PropertyValueFactory<>("grade"));
        TableViewReq2.setItems(list);
    }

    public ObservableList<Requests> getReq3List() throws ParseException {
        ObservableList<Requests> req1List = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select concat(st_name, \" \", st_surname )  as full_name, count(g.grade) as num from Students join Grades g using (st_id) where g.grade < 60 group by concat(st_name, \" \", st_surname )  order by count(g.grade) desc LIMIT 1;";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);
            Requests req3;
            while(rs.next()) {
                req3 = new Requests(rs.getString("full_name"), rs.getInt("num"));
                req1List.add(req3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req1List;
    }

    public void showReq3() throws ParseException {
        ObservableList<Requests> list = getReq3List();
        full_nameReq3.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        numReq3.setCellValueFactory(new PropertyValueFactory<>("grade"));
        TableViewReq3.setItems(list);
    }

    public ObservableList<Requests> getReq4List() throws ParseException {
        ObservableList<Requests> req1List = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT CONCAT(st.st_name, ' ', st.st_surname) AS full_name, g.grade\n" +
                "FROM Students st\n" +
                "JOIN Grades g ON st.st_id = g.st_id\n" +
                "JOIN Attendance a ON st.st_id = a.st_id\n" +
                "JOIN Schedule s ON a.sl_id = s.sl_id\n" +
                "WHERE a.dn_id = 1\n" +
                "  AND s.sl_id = 12\n" +
                "AND a.att_status = 0 \n" +
                "  AND g.grade IS NOT NULL;";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);
            Requests req4;
            while(rs.next()) {
                req4 = new Requests(rs.getString("full_name"), rs.getInt("grade"));
                req1List.add(req4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req1List;
    }

    public void showReq4() throws ParseException {
        ObservableList<Requests> list = getReq4List();
        full_nameReq4.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        gradeReq4.setCellValueFactory(new PropertyValueFactory<>("grade"));
        TableViewReq4.setItems(list);
    }

    public ObservableList<Requests> getReq5List() throws ParseException {
        ObservableList<Requests> req1List = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT CONCAT(s.st_name, ' ', s.st_surname) AS full_name\n" +
                "FROM Students s\n" +
                "JOIN Attendance a ON s.st_id = a.st_id\n" +
                "JOIN Schedule sc ON a.sl_id = sc.sl_id\n" +
                "WHERE a.dn_id = 3\n" +
                "  AND sc.schedule_date >= DATE_SUB(NOW(), INTERVAL 4 WEEK)\n" +
                "GROUP BY s.st_id, full_name\n" +
                "HAVING SUM(a.att_status = 0) > 0.25 * COUNT(*);";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);
            Requests req5;
            while(rs.next()) {
                req5 = new Requests(rs.getString("full_name"));
                req1List.add(req5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req1List;
    }

    public void showReq5() throws ParseException {
        ObservableList<Requests> list = getReq5List();
        full_nameReq5.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        TableViewReq5.setItems(list);
    }

    public ObservableList<Requests> getReq6List() throws ParseException {
        ObservableList<Requests> req1List = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT CONCAT(s.st_name, ' ', s.st_surname) AS full_name,\n" +
                "  CASE\n" +
                "    WHEN g.grade >= 90 THEN 'A'\n" +
                "    WHEN g.grade >= 82 THEN 'B'\n" +
                "    WHEN g.grade >= 75 THEN 'C'\n" +
                "    WHEN g.grade >= 64 THEN 'D'\n" +
                "    WHEN g.grade >= 60 THEN 'E'\n" +
                "    WHEN g.grade >= 35 THEN 'FX'\n" +
                "    ELSE 'F'\n" +
                "  END AS grade\n" +
                "FROM Students s\n" +
                "JOIN Grades g ON s.st_id = g.st_id\n" +
                "JOIN Disciplines d ON g.dn_id = d.dn_id\n" +
                "WHERE g.dn_id = 3;";
        Statement dn;
        ResultSet rs;
        try {
            dn = connection.createStatement();
            rs = dn.executeQuery(query);
            Requests req6;
            while(rs.next()) {
                req6 = new Requests(rs.getString("full_name"), rs.getString("grade"));
                req1List.add(req6);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req1List;
    }

    public void showReq6() throws ParseException {
        ObservableList<Requests> list = getReq6List();
        full_nameReq6.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        gradeReq6.setCellValueFactory(new PropertyValueFactory<>("gradeStr"));
        TableViewReq6.setItems(list);
    }

}
