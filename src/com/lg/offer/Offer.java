package com.lg.offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

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

	// 9.变态跳台阶
	public int JumpFloorII(int target) {
		if (target < 1)
			System.out.println("台阶输入不正确");
		int floor = 1;
		for (int i = 2; i <= target; i++) {
			floor = 2 * floor;

		}
		return floor;

	}

	// 10.二进制中1的个数
	public int NumberOf1(int n) {
		// 将该整数-1 并与原整数进行与操作，会将原整数最右边的1变为0.有多少个1，则进行多少次操作。
		int count = 0;
		while (n != 0) {
			n = (n - 1) & n;
			count++;
		}
		return count;
	}

	// 11.数值的整数次方
	boolean invalidInput = false;// 全局变量

	public double Power(double base, int exponent) {
		invalidInput = false;
		if (exponent < 0 && equal(base, 0.0)) {
			invalidInput = true;
			return 0.0;
		}
		int absExponent = exponent;
		if (exponent < 0) {
			absExponent = -exponent;
		}
		double result = PowerWithUnsignedExponent(base, absExponent);
		if (exponent < 0) {
			result = 1 / result;
		}
		return result;
	}

	double PowerWithUnsignedExponent(double base, int exponent) {
		// 递归方式实现
		if (exponent == 0)
			return 1;
		if (exponent == 1)
			return base;

		double result = PowerWithUnsignedExponent(base, exponent >> 1);// 右移表示
																		// 除以2
		result *= result;
		if ((exponent & 0x1) == 1) { // 与1与判奇偶
			result *= base;
		}
		return result;
	}

	boolean equal(double n1, double n2) {
		if ((n1 - n2 > -0.0000001) && (n1 - n2) < 0.0000001)
			return true;
		else
			return false;
	}

	// 12.调整数组顺序使奇数位于偶数前面
	public void reOrderArray(int[] a) {
		if (a == null || a.length == 0)
			return;
		int i = 0, j;
		while (i < a.length) {
			while (i < a.length && !isEven(a[i])) // i从左向右找到第一个偶数
				i++;
			j = i + 1;// j从下一个开始搜索第一个奇数
			while (j < a.length && isEven(a[j]))
				j++;
			if (j < a.length) { // 找到奇数后，将所有偶数后移，并在前面插入奇数
				int tmp = a[j];
				for (int j2 = j - 1; j2 >= i; j2--) {
					a[j2 + 1] = a[j2];
				}
				a[i++] = tmp;
			} else {
				break;
			}
		}
	}

	boolean isEven(int n) {
		if (n % 2 == 0)
			return true;
		return false;
	}

	// 13.链表中倒数第k个结点
	public ListNode FindKthToTail(ListNode head, int k) {
		if (head == null || k == 0)
			return null;

		ListNode p1 = head;
		ListNode p2 = head;

		for (int i = 0; i < k - 1; i++) {
			if (p1.next != null)
				p1 = p1.next;
			else
				return null;
		}
		while (p1.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}

	// 14.反转链表
	public ListNode ReverseList(ListNode head) {
		if (head == null)
			return null;
		ListNode pReverseNode = null;
		ListNode node = head;
		ListNode preNode = null;
		while (node != null) {
			ListNode nextNode = node.next;
			if (nextNode == null) {
				pReverseNode = node;
			}
			node.next = preNode;
			preNode = node;
			node = nextNode;
		}
		return pReverseNode;
	}

	// 15.合并两个排序的链表
	public ListNode Merge(ListNode list1, ListNode list2) {
		if (list1 == null)
			return list2;
		if (list2 == null)
			return list1;

		if (list1.val < list2.val) {
			list1.next = Merge(list1.next, list2);
			return list1;
		} else {
			list2.next = Merge(list1, list2.next);
			return list2;
		}

	}

	// 16.树的子结构
	public boolean HasSubtree(TreeNode root1, TreeNode root2) {
		boolean result = false;
		if (root1 != null && root2 != null) {
			if (root1.val == root2.val) {
				result = ifHasSubtree(root1, root2);
			}
			if (!result) {
				result = HasSubtree(root1.left, root2);
			}
			if (!result) {
				result = HasSubtree(root1.right, root2);
			}
		}
		return result;
	}

	private boolean ifHasSubtree(TreeNode root1, TreeNode root2) {
		if (root2 == null)
			return true;// 注意先判断这个。表明将B的每个节点都判断过了
		if (root1 == null)
			return false;

		if (root1.val != root2.val)
			return false;

		return ifHasSubtree(root1.left, root2.left) && ifHasSubtree(root1.right, root2.right);

	}

	// 17.二叉树的镜像
	public void Mirror(TreeNode root) {
		if (root == null)
			return;
		if (root.left == null && root.right == null)
			return;
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

		if (root.left != null)
			Mirror(root.left);
		if (root.right != null)
			Mirror(root.right);

	}

	// 18.顺时针打印矩阵
	ArrayList<Integer> result = new ArrayList<Integer>();

	public ArrayList<Integer> printMatrix(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		if (matrix == null || rows <= 0 || cols <= 0)
			return null;

		int start = 0; // 左上角开始坐标，可以理解为圈数
		while (start * 2 < rows && start * 2 < cols) {
			result = printInCircle(matrix, cols, rows, start);
			start++;
		}
		return result;
	}

	private ArrayList<Integer> printInCircle(int[][] matrix, int col, int row, int start) {
		int endX = col - start - 1;
		int endY = row - start - 1;
		// 打印第一行，从左到右横着打
		for (int i = start; i <= endX; i++)
			result.add(matrix[start][i]);

		// 从上到下打印一列
		if (start < endY) {
			for (int i = start + 1; i <= endY; i++)
				result.add(matrix[i][endX]);
		}

		// 从右到左打印一行
		if (start < endX && start < endY) {
			for (int i = endX - 1; i >= start; i--)
				result.add(matrix[endY][i]);
		}

		// 从下到上打印一列
		if (start < endX && start < endY - 1) {
			for (int i = endY - 1; i >= start + 1; i--)
				result.add(matrix[i][start]);
		}
		return result;
	}

	// 19.包含min函数的栈
	private Stack<Integer> data = new Stack<Integer>();
	private Stack<Integer> ass = new Stack<Integer>();

	public void push2(int node) {
		data.push(node);
		if (ass.empty() || ass.peek() > node)
			ass.push(node); // 辅助栈中存每次操作的最小值
		else
			ass.push(ass.peek());
	}

	public void pop2() {
		if (data.empty() || ass.empty())
			return;
		data.pop();
		ass.pop();
	}

	public int top() {
		if (data.empty())
			return (Integer) null;
		else
			return data.peek();
	}

	public int min() {
		if (ass.empty())
			return (Integer) null;
		else
			return ass.peek();
	}

	// 20.栈的压入、弹出序列
	public boolean IsPopOrder(int[] pushA, int[] popA) {
		if (pushA.length == 0 || popA.length == 0)
			return false;
		Stack<Integer> s = new Stack<Integer>();
		// 用于标识弹出序列的位置
		int popIndex = 0;
		for (int i = 0; i < pushA.length; i++) {
			s.push(pushA[i]);// 如果栈不为空，且栈顶元素等于弹出序列
			while (!s.empty() && s.peek() == popA[popIndex]) {
				// 出栈
				s.pop();
				// 弹出序列向后一位
				popIndex++;
			}
		}
		return s.empty();
	}

	// 21.从上往下打印二叉树
	public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null)
			return result;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			TreeNode treeNode = q.poll();
			if (treeNode.left != null) {
				q.offer(treeNode.left);
			}
			if (treeNode.right != null) {
				q.offer(treeNode.right);
			}
			result.add(treeNode.val);
		}
		return result;
	}
}
