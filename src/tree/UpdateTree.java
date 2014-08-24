package tree;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import database.Connect;

public class UpdateTree {
	public static void Update(TreeView<String> tv){
		if(Connect.getConnection() != null){
			//Adding The Departments
			Node deptImg = new ImageView(new ImageGetter("school7-s.png").getImage()) ;
			ResultSet result = Connect.QUERY("SELECT `dept_name` FROM `departments`") ;
			//for counting
			int count = 1 ;
			try{
				while(result.next()){
					TreeItem<String> dept = new TreeItem<String>(result.getString(count) , deptImg);
					tv.getRoot().getChildren().add(dept);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
