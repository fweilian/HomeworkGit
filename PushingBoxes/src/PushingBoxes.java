import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PushingBoxes {
	private int row; // 地图行数
	private int col; // 地图列数

	private int bBeginX, bBeginY; // 箱子开始坐标
	private int pBeginX, pBeginY; // 人开始坐标
	private int endX, endY; // 终点坐标

	private char map[][]; // 地图

	private int[] dx = { -1, 1, 0, 0 };
	private int[] dy = { 0, 0, 1, -1 };

	private char[] bDirection = { 'N', 'S', 'E', 'W' }; // 箱子走的方向
	private char[] pDirection = { 'n', 's', 'e', 'w' }; // 人走的方向

	private int mark[][]; // 标记

	private bpNode bp; // 节点，记录人和箱子的坐标，以及最后走过的方向
	private pNode p; // 节点，记录人的坐标，以及人走过的方向

	public PushingBoxes(char[][] map, int row, int col, int bBeginX,
			int bBeginY, int pBeginX, int pBeginY, int endX, int endY) { // 初始化
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

		mark = new int[row][col]; // 标记
	}

	public boolean canGo(int x, int y) { // 判断边界
		if (x >= 0 && x < row && y >= 0 && y < col)
			return true;
		return false;
	}

	public boolean personArriveBox(int pX, int pY, int hX, int hY, int bx,
			int by) { // 人能否到达箱子的后一个节点(箱子前进方向的反方向)
		if (pX == hX && pY == hY) // 人在箱子后面，返回true
			return true;
		
		int[][] markPerson = new int[row][col]; // 记录人走过的点
		markPerson[pX][pY] = 1; // 人开始的节点
		markPerson[bx][by] = 1; // 箱子的节点（不能走）

		pNode pTemp = new pNode(pX, pY, ""); // 新建一个节点，存储人的坐标和方向

		Queue<pNode> pQueue = new LinkedList<pNode>();
		// pQueue.add(pTemp);
		pQueue.offer(new pNode(pX, pY, "")); // 插入到队列
		while (!pQueue.isEmpty()) {
			pTemp = pQueue.poll(); // 出队列

			for (int i = 0; i < 4; i++) { // 遍历人的邻接点
				p.setPersonX(pTemp.getPersonX() + dx[i]);
				p.setPersonY(pTemp.getPersonY() + dy[i]);

				if (!canGo(p.getPersonX(), p.getPersonY()) // 判断可达
						|| map[p.getPersonX()][p.getPersonY()] == '#')
					continue;

				if (markPerson[p.getPersonX()][p.getPersonY()] == 0) { // 没有走过
					markPerson[p.getPersonX()][p.getPersonY()] = 1;
					p.setPath(pTemp.getPath() + pDirection[i]); // 记录人走的方向

					if (p.getPersonX() == hX && p.getPersonY() == hY) { // 人到达箱子后面
						// pTemp = p;
						return true;
					}
					// pQueue.add(p);
					pQueue.offer(new pNode(p.getPersonX(), p.getPersonY(), p
							.getPath())); // 插入新的节点
				}
			}
		}
		return false;
	}

	public boolean boxArriveEnd() { // 箱子能否到达目标节点

		bpNode bpTemp = new bpNode(bBeginX, bBeginY, pBeginX, pBeginY, ""); // 新建一个节点，记录箱子和人的位置
		mark[bBeginX][bBeginY] = 1; // 标记箱子走过的位置
		// bpQueue.add(bpTemp);

		Queue<bpNode> bpQueue = new LinkedList<bpNode>();
		bpQueue.offer(new bpNode(bBeginX, bBeginY, pBeginX, pBeginY, "")); // 插入到队列
		while (!bpQueue.isEmpty()) {
			bpTemp = bpQueue.poll(); // 出队列

			for (int i = 0; i < 4; i++) { // 遍历箱子的邻接点
				int frontX = bpTemp.getBoxX() + dx[i]; // 箱子的前一个节点坐标（按前进的方向）
				int frontY = bpTemp.getBoxY() + dy[i];

				int behindX = bpTemp.getBoxX() - dx[i]; // 箱子的后一个节点坐标
				int behindY = bpTemp.getBoxY() - dy[i];

				if (!canGo(frontX, frontY) || map[frontX][frontY] == '#'
						|| !canGo(behindX, behindY)
						|| map[behindX][behindY] == '#'
						|| mark[frontX][frontY] == 1) {
					continue;
				}

				if (personArriveBox(bpTemp.getPersonX(), bpTemp.getPersonY(),
						behindX, behindY, bpTemp.getBoxX(), bpTemp.getBoxY())) { // 判断人能否到达箱子
					bp.setBoxX(frontX); // 记录人和箱子的坐标
					bp.setBoxY(frontY);
					bp.setPersonX(bpTemp.getBoxX());
					bp.setPersonY(bpTemp.getBoxY());

					if (bpTemp.getPersonX() == behindX
							&& bpTemp.getPersonY() == behindY) { // 人在箱子后面
						bp.setPath(bpTemp.getPath() + bDirection[i]); // 记录箱子走的方向
					} else {
						bp.setPath(bpTemp.getPath() + p.getPath() // 记录人走的方向和箱子走的方向
								+ bDirection[i]);
					}

					if (bp.getBoxX() == endX && bp.getBoxY() == endY) { // 到达终点
						return true;
					}

					mark[bp.getBoxX()][bp.getBoxY()] = 1; // 记录走过的点
					// bpQueue.add(bp);
					bpQueue.offer(new bpNode(bp.getBoxX(), bp.getBoxY(), bp
							.getPersonX(), bp.getPersonY(), bp.getPath())); // 插入新的节点
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int row = 0; // 行
		int col = 0; // 列

		int bBeginX = 0; // 箱子开始坐标
		int bBeginY = 0;

		int pBeginX = 0;// 人开始坐标
		int pBeginY = 0;

		int endX = 0; // 目标坐标
		int endY = 0;

		char[][] map = null; // 地图

		String s = "";
		int t = 1; // 用例个数

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

					if (map[i][j] == 'B') { // 箱子开始坐标
						bBeginX = i;
						bBeginY = j;
					}
					if (map[i][j] == 'S') { // 人开始坐标
						pBeginX = i;
						pBeginY = j;
					}
					if (map[i][j] == 'T') { // 目标坐标
						endX = i;
						endY = j;
					}
				}
			}
			PushingBoxes pushingBoxes = new PushingBoxes(map, row, col,
					bBeginX, bBeginY, pBeginX, pBeginY, endX, endY);

			if (pushingBoxes.boxArriveEnd()) { // 箱子能到达
				System.out.println("Maze #" + t++);
				System.out.println(pushingBoxes.bp.getPath() + "\n"); // 输出人和箱子走过的路径方向
			} else {
				System.out.println("Maze #" + t++);
				System.out.println("Impossible\n"); // 箱子不可能到达
			}
		}
	}
}