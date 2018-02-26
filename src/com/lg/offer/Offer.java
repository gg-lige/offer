package com.lg.offer;

import java.util.ArrayList;
import java.util.Stack;

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

	// 5.用两个栈实现队列
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	// 入队
	public void push(int node) {
		stack1.push(node);
	}

	public int pop() {
		if (stack2.empty()) {
			while (!stack1.empty()) {
				int temp = stack1.pop();
				stack2.push(temp);
			}
		}
		if (stack2.empty())
			System.out.println("queue is empty");

		return stack2.pop();

	}

	// 6.旋转数组的最小数字
	public int minNumberInRotateArray(int[] array) {
		if (array == null || array.length == 0)
			return 0;
		// 旋转数组主要查找递增数组突然出现一个下降的数字时，下降的数字即为所查。
		int index1 = 0, index2 = array.length - 1;
		int mid = -1;

		while (array[index1] >= array[index2]) {
			if (index2 - index1 == 1) {
				mid = index2;
				break;
			}
			mid = (index1 + index2) / 2;

			// 如果下标1、下标2、中间标的指向数字相同，则需要顺序查找。
			if (array[index1] == array[index2] && array[index1] == array[mid]) { // ??短路操作
				return minInOrder(array, index1, index2);
			}
			if (array[mid] >= array[index1])
				index1 = mid;
			if (array[mid] <= array[index2])
				index2 = mid;

		}
		return array[mid];
	}

	private int minInOrder(int[] array, int index1, int index2) {
		int result = array[index1];
		for (int i = index1 + 1; i <= index2; i++) {
			if (array[i] < result)
				result = array[i];

		}
		return result;
	}

	// 7.斐波那契数列
	public int Fibonacci(int n) {
		// 用循环方式来求解时间复杂度会好些，而递归方式的由于每次函数调用过程都会使用栈来存储函数变量、参数、返回地址等
		int[] result = { 0, 1 };
		if (n < 0)
			System.out.println("范围不正确");
		else if (n < 2) {
			return result[n];
		}

		int first = 0;
		int second = 1;
		int fib = 0;
		for (int i = 2; i <= n; i++) {
			fib = first + second;
			first = second;
			second = fib;
		}
		return fib;
	}

	// 8.跳台阶，矩形覆盖
	public int JumpFloor(int target) {
		int[] result = { 1, 2 };
		if (target < 1)
			System.out.println("台阶输入不正确");
		else if (target < 3) {
			return result[target - 1];
		}

		int first = 1;
		int second = 2;
		int floor = 0;
		for (int i = 3; i <= target; i++) {
			floor = first + second;
			first = second;
			second = floor;

		}
		return floor;
	}

	//9.变态跳台阶
	  public int JumpFloorII(int target) {
			if (target < 1)
				System.out.println("台阶输入不正确");
			  int floor = 1;
	            for (int i = 2; i <= target; i++) {
	                floor = 2* floor;

	            }	
	        return floor;
			
	    }
	  
	//10.二进制中1的个数
	  public int NumberOf1(int n) {
      //将该整数-1 并与原整数进行与操作，会将原整数最右边的1变为0.有多少个1，则进行多少次操作。        
      int count=0;
      while(n!=0){
          n=(n-1)&n;
          count++;
      }        
      return count;
  }
}
