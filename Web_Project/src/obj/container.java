package obj;

public class container {
	private String containerNum;
	private String check;
	private String type;
	public container(String containerNum,String type) {
		this.containerNum=containerNum;
		this.type=type;
		check="0";
	}
	public String getContainerNum() {
		return containerNum;
	}
	public void setContainerNum(String containerNum) {
		this.containerNum = containerNum;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "('"+containerNum+"','"+check+"','"+type+"')";
	}
	
}
