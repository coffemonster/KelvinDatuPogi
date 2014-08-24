package congcrete;

public class Department {
	private int dept_id ;
	private String dept_name ;
	private String dept_code ;
	
	public Department(int dept_id, String dept_name, String dept_code) {
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.dept_code = dept_code;
	}
	
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_code() {
		return dept_code;
	}
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}
}
