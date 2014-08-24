package application.main;

import javafx.event.Event;
import javafx.fxml.* ;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import NodeUtils.* ;
import tree.ImageGetter;
import tree.UpdateTree;

import java.net.* ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle ;

import database.Connect;
import adder.node.Adder;
public class FXMLDocumentController implements Initializable{
	
	@FXML private BorderPane root;
	@FXML private Image backup ;
	@FXML private ImageView departmentImageView ;
	@FXML private ImageView teacherImageView ;
	@FXML private ImageView roomImageView ;
	@FXML private ImageView courseImageView ;
	@FXML private ImageView yearImageView ;
	@FXML private ImageView sectionImageView ;
	@FXML private BorderPane workplacePane ;
	@FXML private TreeView<String> treeView ;
	private static TreeView<String> staticTreeView ;
	private ScaleAnimationProperty scaleProperty ;
	private NodeAnimation animation ;
	
	
	
	/*
	 * MENU CONTROLS HANDLER
	 */
	//Show Add in center pane
	@FXML public void handleAddToCenter(MouseEvent e) throws Exception{
		String resourceURL = null ;
		ImageView ev = (ImageView)e.getSource() ;
		
		if(ev.getId().equals("departmentImageView")){
			resourceURL = "/application/department/addDepartmentDocument.fxml" ;
		}else if(ev.getId().equals("teacherImageView")){
			resourceURL = "/application/etacher/addTeacherDocument.fxml" ;
		}else if(ev.getId().equals("roomImageView")){
			resourceURL = "/application/room/addRoomDocument.fxml" ;
		}else if(ev.getId().equals("courseImageView")){
			resourceURL = "/application/course/addCourseDocument.fxml" ;
		}else if(ev.getId().equals("yearImageView")){
			resourceURL = "/application/year/addYearDocument.fxml" ;
		}else if(ev.getId().equals("sectionImageView")){
			resourceURL = "/application/year/addSectionDocument.fxml" ;
		}else if(ev.getId().equals("subjectImageView")){
			resourceURL = "/application/subject/addSubjectDocument.fxml" ;
		}
		
		Pane child = FXMLLoader.load(getClass().getResource(resourceURL)) ;
		
		Adder.addToCenter(workplacePane , child);
		
		animation = new ScaleAnimation(scaleProperty);
		animation.animate(child);
	}
	
	/*
	 *MENU CUSTOMIZE
	 */	
	//change the image
	private void changeImage(Event e , String option){
		if(e instanceof MouseEvent){
			//get the source of event
			ImageView ev = (ImageView)e.getSource() ;
			String loc = null ;
			
			if(ev.getId().equals("departmentImageView")){
				loc = "/images/school7" + option  + ".png" ;
			}else if(ev.getId().equals("teacherImageView")){
				loc = "/images/teach"  + option  +  ".png" ;
			}else if(ev.getId().equals("roomImageView")){
				loc = "/images/classroom1"  + option  +  ".png" ;
			}else if(ev.getId().equals("courseImageView")){
				loc = "/images/university2"  + option  +  ".png" ;
			}else if(ev.getId().equals("yearImageView")){
				loc = "/images/amount"  + option  +  ".png" ;
			}else if(ev.getId().equals("sectionImageView")){
				loc = "/images/homework"  + option  +  ".png" ;
			}else if(ev.getId().equals("subjectImageView")){
				loc = "/images/open3" + option + ".png" ;
			}
		
			//set the image base on LOC
			ev.setImage(new Image(getClass().getResourceAsStream(loc)));
		}
	}

	//hover at the menu
	@FXML public void menuHover(MouseEvent e){
		changeImage(e , "-h") ;
		ImageView ev = (ImageView)e.getSource() ; 
		animation = new ScaleAnimation(1 , 1.5 , 1 , 1.5 , Duration.millis(50) , 2) ;
		animation.animate(ev);
	}
	
	//exit at the menu
	@FXML public void menuRestore(MouseEvent e){
		changeImage(e , "") ;
	}

	@Override public void initialize(URL url , ResourceBundle rs){
		//set Property for adding at worplacePane
		scaleProperty = new ScaleAnimationProperty(.1 , 1 , .1 , 1 , Duration.millis(200) , 1) ;
		//Setting School as TreeView root
		Node img = new ImageView(new ImageGetter("school47.png").getImage()) ;
		treeView.setRoot(new TreeItem<String>("School" , img));
		staticTreeView = treeView ;
		updateTree();
		
	}
	
	public static void updateTree(){
		
			if(Connect.getConnection() != null){
				//Adding The Departments
				ResultSet result = Connect.QUERY("SELECT `dept_name` FROM `departments`") ;
				//for counting
				int count = 1 ;
				//emptying TreeView
				staticTreeView.getRoot().getChildren().clear();
				try{
					while(result.next()){
						Node deptImg = new ImageView(new ImageGetter("school7-s.png").getImage()) ;
						TreeItem<String> dept = new TreeItem<String>(result.getString(count) , deptImg);
						staticTreeView.getRoot().getChildren().add(dept);
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
			
		}
	}
	
}