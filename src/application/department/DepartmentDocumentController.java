package application.department;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.main.FXMLDocumentController;
import tree.UpdateTree;
import database.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DepartmentDocumentController implements Initializable{
	
	@FXML private TextField inputDeptName ;
	@FXML private TextField inputDeptCode ;
	@FXML private Button finish ;
	
	//Handle , Adding in the DB
	@FXML public void handleAddDepartment(ActionEvent e) throws SQLException{
		Connect.emptyQUERY("INSERT INTO `departments` VALUES(" + Connect.getNextIntegerPrimary("departments", "dept_id") + 
						   ",'" + inputDeptName.getText() + "' , '" + inputDeptCode.getText() + "')");
		inputDeptName.setText("");
		inputDeptCode.setText("");
		FXMLDocumentController.updateTree();
	}
	
	public void initialize(URL url , ResourceBundle res){
	}
}
