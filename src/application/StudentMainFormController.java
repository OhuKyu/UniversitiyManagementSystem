package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class StudentMainFormController implements Initializable {

	@FXML
	private Label student_id;

	@FXML
	private Button studentInformation_btn;

	@FXML
	private Button logout_btn;

	@FXML
	private TableView<DataStudentHandle> table_view;

	@FXML
	private TableColumn<DataStudentHandle, String> studentInfo_col_teacherID;

	@FXML
	private TableColumn<DataStudentHandle, String> studentInfo_col_name;

	@FXML
	private TableColumn<DataStudentHandle, String> studentInfo_col_gender;

	@FXML
	private TableColumn<DataStudentHandle, String> studentInfo_col_YE;

	@FXML
	private Circle circle_image;

	@FXML
	private Label teacher_id;

	@FXML
	private Label teacher_name;

	@FXML
	private Label teacher_gender;

	@FXML
	private Label teacher_date;

	private ObservableList<DataStudentHandle> teacherListData;

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;

	AlertMessage alert = new AlertMessage();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		studentIDDisplay(); // Load student ID first
		teacherDisplayData(); // Then display teacher data
	}

	public void studentIDDisplay() {
		String sql = "SELECT * FROM users WHERE username = ?";
		connect = Database.connectDB();

		try (PreparedStatement prepare = connect.prepareStatement(sql)) {
			prepare.setString(1, ListData.student_username);
			result = prepare.executeQuery();

			if (result.next()) {
				String studentID = result.getString("student_id");
				student_id.setText(studentID);
				System.out.println("Student ID: " + studentID); // Debugging
			} else {
				System.out.println("No student found for username: " + ListData.student_username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ObservableList<DataStudentHandle> teacherSetData() {
		ObservableList<DataStudentHandle> listData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM teacher_student WHERE stud_studentID = ? AND date_delete IS NULL";
		connect = Database.connectDB();

		try (PreparedStatement prepare = connect.prepareStatement(sql)) {
			prepare.setString(1, student_id.getText());
			result = prepare.executeQuery();

			while (result.next()) {
				System.out.println("Teacher ID: " + result.getString("teacher_id")); // Debugging
				DataStudentHandle dsh = new DataStudentHandle(result.getString("teacher_id"),
						result.getString("stud_studentID"), result.getString("stud_name"),
						result.getString("stud_gender"), result.getDate("date_insert"));
				listData.add(dsh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	public void teacherDisplayData() {
		teacherListData = teacherSetData();
		System.out.println("Number of teachers: " + teacherListData.size()); // Debugging

		studentInfo_col_teacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
		studentInfo_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		studentInfo_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		studentInfo_col_YE.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));

		table_view.setItems(teacherListData);
	}

	public void teacherSelectData() {
		DataStudentHandle dsh = table_view.getSelectionModel().getSelectedItem();
		if (dsh == null) {
			return;
		}

		String sql = "SELECT * FROM teacher WHERE teacher_id = ?";
		connect = Database.connectDB();

		try (PreparedStatement prepare = connect.prepareStatement(sql)) {
			prepare.setString(1, dsh.getTeacherID());
			result = prepare.executeQuery();

			if (result.next()) {
				String path = "File:" + result.getString("image");
				try {
					Image image = new Image(path, 164, 73, false, true);
					circle_image.setFill(new ImagePattern(image));
				} catch (Exception e) {
					System.err.println("Error loading image: " + path);
					e.printStackTrace();
				}

				teacher_id.setText(result.getString("teacher_id"));
				teacher_name.setText(result.getString("full_name"));
				teacher_gender.setText(result.getString("gender"));
				teacher_date.setText(result.getString("date_insert"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logoutBtn() {
		try {
			if (alert.confirmMessage("Are you sure you want to logout?")) {
				table_view.getItems().clear(); // Clear table data
				Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

				logout_btn.getScene().getWindow().hide();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
