package com.lg.offer;

import java.util.ArrayList;

public class Offer {
	// 1.二维数组中的查找
	public boolean Find(int target, int[][] array) {
		int rows = array.length;
		int cols = array[0].length;
		boolean find = false;
		if (rows > 0 && cols > 0 && array != null) {
			int row = 0;
			int col = cols - 1;
			while (row < rows && col >= 0) {
				if (array[row][col] == target) {
					find = true;
					break;
				} else if (array[row][col] > target) {
					--col;
				} else {
					++row;
				}
			}
		}
		return find;
	}

	// 2.替换空格
	public String replaceSpace(StringBuffer str) {

		int length = str.length();
		int spaceCount = 0, i;
		for (i = 0; i < length; i++) {
			if (str.charAt(i) == ' ')
				spaceCount++;
		}

		int newlength = length + 2 * spaceCount;
		int indexOld = length - 1;
		int indexNew = newlength - 1;
		str.setLength(newlength);

		for (; indexOld >= 0; indexOld--) {
			if (str.charAt(indexOld) == ' ') {
				str.setCharAt(indexNew--, '0');
				str.setCharAt(indexNew--, '2');
				str.setCharAt(indexNew--, '%');
			} else {
				str.setCharAt(indexNew--, str.charAt(indexOld));
			}
		}

		return str.toString();

	}

	// 3.从尾到头打印链表
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		ArrayList<Integer> outputNode = new ArrayList<Integer>();
		ListNode nextNode = listNode;
		// 使用递归来实现
		if (nextNode != null) {
			if (nextNode.next != null) {
				outputNode = printListFromTailToHead(nextNode.next);
			}
			outputNode.add(nextNode.val);
		}
		return outputNode;
	}

	// 4.重建二叉树
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		if (pre == null || in == null)
			return null;
		else
			return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	// 递归
	private TreeNode reConstructBinaryTree(int[] pre, int sPre, int ePre, int[] in, int sIn, int eIn) {

		if (sPre > ePre || sIn > eIn)
			return null;
		TreeNode root = new TreeNode(pre[sPre]); // 前序寻找根节点
		// 中序中找到根节点，前面的为左子树，后面的为柚子树
		for (int i = sIn; i <= eIn; i++) {
			if (in[i] == pre[sPre]) {
				root.left = reConstructBinaryTree(pre, sPre + 1, sPre + i - sIn, in, sIn, i - 1);
				root.right = reConstructBinaryTree(pre, sPre + i - sIn + 1, ePre, in, i + 1, eIn);
				break;
			}

		}
		return root;
	}

}
