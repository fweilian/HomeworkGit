public class pNode {

	private int personX; // �˵�����
	private int personY;
	private String path; // ���߹���·������

	public pNode() {
		this.personX = 0;
		this.personY = 0;
		this.path = "";
	}

	public pNode(int x, int y, String path) {
		this.personX = x;
		this.personY = y;
		this.path = path;
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
