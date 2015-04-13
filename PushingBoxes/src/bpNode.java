public class bpNode {
	private int boxX; // 箱子的坐标
	private int boxY;
	private int personX; // 人的坐标
	private int personY;
	private String path; // 人和箱子走过的路径方向

	public bpNode() {
		this.boxX = 0;
		this.boxY = 0;
		this.personX = 0;
		this.personY = 0;
		this.path = "";
	}

	public bpNode(int boxX, int boxY, int personX, int personY, String path) {
		this.boxX = boxX;
		this.boxY = boxY;
		this.personX = personX;
		this.personY = personY;
		this.path = path;
	}

	public int getBoxX() {
		return boxX;
	}

	public void setBoxX(int boxX) {
		this.boxX = boxX;
	}

	public int getBoxY() {
		return boxY;
	}

	public void setBoxY(int boxY) {
		this.boxY = boxY;
	}

	public int getPersonX() {
		return personX;
	}

	public void setPersonX(int personX) {
		this.personX = personX;
	}

	public int getPersonY() {
		return personY;
	}

	public void setPersonY(int personY) {
		this.personY = personY;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
