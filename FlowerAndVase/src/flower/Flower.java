package flower;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Flower {

	private int[][] flowerAndVase; // store the values of the flowers in each
									// vases
	private int[][] tempArray; // remember the values
	private int flowersNum; // the number of the flower
	private int vasesNum; // the number of the vase
	private int tempMaxValue; // in order to get the vases when the value is max

	private int getMaxValue() { // get the max values
		if (flowersNum > vasesNum)
			return 1;
		return getMaxValue(flowersNum, vasesNum);

	}

	private int getMaxValue(int flower, int vase) {
		int maxValue = 0;
		if (flower <= 0 || vase < 0) {
			return 0; // boundary conditions
		}

		if (flower > vase) {
			return -10000; // the situation is not possible, give a number as
							// large as possible
		}

		if (tempArray[flower][vase] > 0) { // remember the values
			return tempArray[flower][vase];
		}

		// get the max value using recursion
		maxValue = max(
				flowerAndVase[flower][vase] + getMaxValue(flower - 1, vase - 1),
				getMaxValue(flower, vase - 1));

		if (tempMaxValue < maxValue) { // record the vase when the max value
										// gets
			tempMaxValue = maxValue;
			flowerAndVase[flower][0] = vase;
		}
		return tempArray[flower][vase] = maxValue;

	}

	private int max(int m1, int m2) { // compare
		return m1 < m2 ? m2 : m1;
	}

	public void read(String fileName) { // read the value of each flower in each
										// vase from a file
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			try {
				String s;
				s = br.readLine(); // the first line record the number of the
									// flowers and the vases
				flowersNum = Integer.valueOf(s.split(" ")[0]);
				vasesNum = Integer.valueOf(s.split(" ")[1]);

				flowerAndVase = new int[flowersNum + 1][vasesNum + 1]; // initialize
																		// the
																		// array
				tempArray = new int[flowersNum + 1][vasesNum + 1];

				int i = 1;
				while ((s = br.readLine()) != null) { // read the remain line
														// which record the
														// values
					String[] strArr = s.split(" ");
					for (int j = 1; j <= strArr.length; j++) {
						flowerAndVase[i][j] = Integer.valueOf(strArr[j - 1]);
						tempArray[i][j] = 0;
					}
					i++;
				}
			} finally {
				br.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void write() {

		if (getMaxValue() == 1) { // flowersNum > vasesNum
			System.out.println("the input is error");
			return;
		}

		System.out.println(getMaxValue()); // output the max value

		for (int i = 1; i < flowerAndVase.length; i++) { // output the vase
															// which has flower
			System.out.print(flowerAndVase[i][0] + " ");
		}
	}

	public static void main(String[] args) {
		Flower flower = new Flower();
		flower.read("input.txt"); // the file record the information
		flower.write(); // output
	}
}
