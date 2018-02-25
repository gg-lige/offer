package com.lg.offer;

public class Satine {
	// 1.确定字符互异
	public boolean checkDifferent(String iniString) {
		if (iniString.length() > 256)
			return false;

		boolean[] char_set = new boolean[65530];
		for (int i = 0; i < iniString.length(); i++) {
			int val = iniString.charAt(i);
			if (char_set[val])
				return false;
			char_set[val] = true;
		}
		return true;

	}

	// 2.原串翻转
	public String reverseString(String inString) {
		char[] iniString = inString.toCharArray();
		int i = 0;
		int j = iniString.length - 1;
		char c;
		while (i < j) {
			c = iniString[i];
			iniString[i] = iniString[j];
			iniString[j] = c;
			i++;
			j--;
		}
		return new String(iniString);
	}

}
