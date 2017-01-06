//Timothy Poon P1 10/29/16

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class P1_Poon_Timothy_StringCraft {
	//The attributes are solely for the challenge portion of the lab.
	private static String ansString = "";
	private static int ansValue = 0;
	private static ArrayList<int[]> ansHistory = new ArrayList<int[]>();
	private static HashMap<String, Integer> checked = new HashMap<String, Integer>();
	public static void main(String[] args) {
		System.out.println("What String length do you want?");
		Scanner in = new Scanner(System.in);
		int len = in.nextInt();
		int moves = 0;
		String str = "";
		do {
			str = randomString(len);
		} while(!hasValidMoves(str));
		String original = str;
		System.out.println(str);
		System.out.println("The value of the word is " + wordValue(str));
		while (hasValidMoves(str)) {
			System.out.println("Type in the indices of the two characters you want to switch (separated by a space)");
			int a = in.nextInt();
			int b = in.nextInt();
			if (isLegal(str, a, b)) {
				str = switchStr(str, a, b);
				moves ++;
				System.out.println(str);
				System.out.println("The value of the word is " + (wordValue(str) - moves));
			}
			else {
				System.out.println("Illegal move. Word value must increase.");
			}
		}
		in.close();
		System.out.println("Game over! Your score is " + (wordValue(str) - moves));
		optimize(original, new ArrayList<int[]>(), 0);
		System.out.println("Best answer is string " + ansString + " with value " + ansValue + " going through the moves:");
		printList(ansHistory);
	}
	private static boolean hasValidMoves(String str) {
		for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (isLegal(str, i, j)) {
					return true;
				}
			}
		}
		return false;
	}
	private static String randomString(int length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			str += (char) ('A' + (int) (Math.random()*26));
		}
		return str;
	}
	private static int wordValue(String str) {
		int ans = 1;
		int lastValue = 1;
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i-1) == str.charAt(i)) {
				lastValue += 2;
			}
			else if (str.charAt(i - 1) == str.charAt(i) - 1 || (str.charAt(i) == 'A' && str.charAt(i - 1) == 'Z')) {
				lastValue++;
			}
			else {
				lastValue = 1;
			}
			ans += lastValue;
		}
		return ans;
	}
	private static boolean isLegal(String str, int a, int b) {
		if (a == b) {
			return false;
		}
		if (wordValue(switchStr(str, a, b)) > wordValue(str)) {
			return true;
		}
		return false;
	}
	private static String switchStr(String str, int a, int b) {
		char[] arr = str.toCharArray();
		char temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
		return new String(arr);
	}

	private static void optimize(String str, ArrayList<int[]> hist, int moves) {
		boolean legal = false;
		if (checked.containsKey(str) && checked.get(str) <= moves) {
			return;
		}
		checked.put(str, moves);
		for (int i = 0; i < str.length() - 1; i++) {
			for (int j = i + 1; j < str.length()	; j++) {
				if (isLegal(str, i, j)) {
					legal = true;
					int[] temp = new int[2];
					temp[0] = i;
					temp[1] = j;
					hist.add(temp);
					optimize(switchStr(str, i, j), hist, moves+1);
					hist.remove(hist.size() - 1);
				}
			}
		}
		if (!legal) {
			if (wordValue(str) - moves >= ansValue) {
				ansString = str;
				ansValue = wordValue(str) - moves;
				ansHistory = (ArrayList<int[]>) hist.clone();
			}
		}
	}
	private static void printList(ArrayList<int[]> list) {
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println("[" + list.get(i)[0] + "," + list.get(i)[1] + "]");
		}
		System.out.println();
	}

}
