import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PushingBoxes {
	private int row; // ��ͼ����
	private int col; // ��ͼ����

	private int bBeginX, bBeginY; // ���ӿ�ʼ����
	private int pBeginX, pBeginY; // �˿�ʼ����
	private int endX, endY; // �յ�����

	private char map[][]; // ��ͼ

	private int[] dx = { -1, 1, 0, 0 };
	private int[] dy = { 0, 0, 1, -1 };

	private char[] bDirection = { 'N', 'S', 'E', 'W' }; // �����ߵķ���
	private char[] pDirection = { 'n', 's', 'e', 'w' }; // ���ߵķ���

	private int mark[][]; // ���

	private bpNode bp; // �ڵ㣬��¼�˺����ӵ����꣬�Լ�����߹��ķ���
	private pNode p; // �ڵ㣬��¼�˵����꣬�Լ����߹��ķ���

	public PushingBoxes(char[][] map, int row, int col, int bBeginX,
			int bBeginY, int pBeginX, int pBeginY, int endX, int endY) { // ��ʼ��
		this.map = map;

		this.row = row;
		this.col = col;
		this.bBeginX = bBeginX;
		this.bBeginY = bBeginY;
		this.pBeginX = pBeginX;
		this.pBeginY = pBeginY;
		this.endX = endX;
		this.endY = endY;

		bp = new bpNode();
		p = new pNode();

		mark = new int[row][col]; // ���
	}

	public boolean canGo(int x, int y) { // �жϱ߽�
		if (x >= 0 && x < row && y >= 0 && y < col)
			return true;
		return false;
	}

	public boolean personArriveBox(int pX, int pY, int hX, int hY, int bx,
			int by) { // ���ܷ񵽴����ӵĺ�һ���ڵ�(����ǰ������ķ�����)
		if (pX == hX && pY == hY) // �������Ӻ��棬����true
			return true;
		
		int[][] markPerson = new int[row][col]; // ��¼���߹��ĵ�
		markPerson[pX][pY] = 1; // �˿�ʼ�Ľڵ�
		markPerson[bx][by] = 1; // ���ӵĽڵ㣨�����ߣ�

		pNode pTemp = new pNode(pX, pY, ""); // �½�һ���ڵ㣬�洢�˵�����ͷ���

		Queue<pNode> pQueue = new LinkedList<pNode>();
		// pQueue.add(pTemp);
		pQueue.offer(new pNode(pX, pY, "")); // ���뵽����
		while (!pQueue.isEmpty()) {
			pTemp = pQueue.poll(); // ������

			for (int i = 0; i < 4; i++) { // �����˵��ڽӵ�
				p.setPersonX(pTemp.getPersonX() + dx[i]);
				p.setPersonY(pTemp.getPersonY() + dy[i]);

				if (!canGo(p.getPersonX(), p.getPersonY()) // �жϿɴ�
						|| map[p.getPersonX()][p.getPersonY()] == '#')
					continue;

				if (markPerson[p.getPersonX()][p.getPersonY()] == 0) { // û���߹�
					markPerson[p.getPersonX()][p.getPersonY()] = 1;
					p.setPath(pTemp.getPath() + pDirection[i]); // ��¼���ߵķ���

					if (p.getPersonX() == hX && p.getPersonY() == hY) { // �˵������Ӻ���
						// pTemp = p;
						return true;
					}
					// pQueue.add(p);
					pQueue.offer(new pNode(p.getPersonX(), p.getPersonY(), p
							.getPath())); // �����µĽڵ�
				}
			}
		}
		return false;
	}

	public boolean boxArriveEnd() { // �����ܷ񵽴�Ŀ��ڵ�

		bpNode bpTemp = new bpNode(bBeginX, bBeginY, pBeginX, pBeginY, ""); // �½�һ���ڵ㣬��¼���Ӻ��˵�λ��
		mark[bBeginX][bBeginY] = 1; // ��������߹���λ��
		// bpQueue.add(bpTemp);

		Queue<bpNode> bpQueue = new LinkedList<bpNode>();
		bpQueue.offer(new bpNode(bBeginX, bBeginY, pBeginX, pBeginY, "")); // ���뵽����
		while (!bpQueue.isEmpty()) {
			bpTemp = bpQueue.poll(); // ������

			for (int i = 0; i < 4; i++) { // �������ӵ��ڽӵ�
				int frontX = bpTemp.getBoxX() + dx[i]; // ���ӵ�ǰһ���ڵ����꣨��ǰ���ķ���
				int frontY = bpTemp.getBoxY() + dy[i];

				int behindX = bpTemp.getBoxX() - dx[i]; // ���ӵĺ�һ���ڵ�����
				int behindY = bpTemp.getBoxY() - dy[i];

				if (!canGo(frontX, frontY) || map[frontX][frontY] == '#'
						|| !canGo(behindX, behindY)
						|| map[behindX][behindY] == '#'
						|| mark[frontX][frontY] == 1) {
					continue;
				}

				if (personArriveBox(bpTemp.getPersonX(), bpTemp.getPersonY(),
						behindX, behindY, bpTemp.getBoxX(), bpTemp.getBoxY())) { // �ж����ܷ񵽴�����
					bp.setBoxX(frontX); // ��¼�˺����ӵ�����
					bp.setBoxY(frontY);
					bp.setPersonX(bpTemp.getBoxX());
					bp.setPersonY(bpTemp.getBoxY());

					if (bpTemp.getPersonX() == behindX
							&& bpTemp.getPersonY() == behindY) { // �������Ӻ���
						bp.setPath(bpTemp.getPath() + bDirection[i]); // ��¼�����ߵķ���
					} else {
						bp.setPath(bpTemp.getPath() + p.getPath() // ��¼���ߵķ���������ߵķ���
								+ bDirection[i]);
					}

					if (bp.getBoxX() == endX && bp.getBoxY() == endY) { // �����յ�
						return true;
					}

					mark[bp.getBoxX()][bp.getBoxY()] = 1; // ��¼�߹��ĵ�
					// bpQueue.add(bp);
					bpQueue.offer(new bpNode(bp.getBoxX(), bp.getBoxY(), bp
							.getPersonX(), bp.getPersonY(), bp.getPath())); // �����µĽڵ�
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int row = 0; // ��
		int col = 0; // ��

		int bBeginX = 0; // ���ӿ�ʼ����
		int bBeginY = 0;

		int pBeginX = 0;// �˿�ʼ����
		int pBeginY = 0;

		int endX = 0; // Ŀ������
		int endY = 0;

		char[][] map = null; // ��ͼ

		String s = "";
		int t = 1; // ��������

		while (in.hasNext()) {
			row = in.nextInt();
			col = in.nextInt();

			if (row <= 0 || col <= 0)
				break;

			map = new char[row][col];

			for (int i = 0; i < row; i++) {
				s = in.next();
				for (int j = 0; j < col; j++) {
					map[i][j] = s.charAt(j);

					if (map[i][j] == 'B') { // ���ӿ�ʼ����
						bBeginX = i;
						bBeginY = j;
					}
					if (map[i][j] == 'S') { // �˿�ʼ����
						pBeginX = i;
						pBeginY = j;
					}
					if (map[i][j] == 'T') { // Ŀ������
						endX = i;
						endY = j;
					}
				}
			}
			PushingBoxes pushingBoxes = new PushingBoxes(map, row, col,
					bBeginX, bBeginY, pBeginX, pBeginY, endX, endY);

			if (pushingBoxes.boxArriveEnd()) { // �����ܵ���
				System.out.println("Maze #" + t++);
				System.out.println(pushingBoxes.bp.getPath() + "\n"); // ����˺������߹���·������
			} else {
				System.out.println("Maze #" + t++);
				System.out.println("Impossible\n"); // ���Ӳ����ܵ���
			}
		}
	}
}