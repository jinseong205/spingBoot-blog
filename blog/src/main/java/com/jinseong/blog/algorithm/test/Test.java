package com.jinseong.blog.algorithm.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Integer> list = new ArrayList<Integer>();
		
		
		/*queue*/
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(1);
		queue.add(2);
		queue.add(3);
		System.out.println("queue poll: " + queue.poll());
		queue.add(4);
		queue.add(5);
		
		System.out.print("queue :");
		for(int i : queue)
			System.out.print(" " + i);
		System.out.println();
		
		/*stack*/
		Stack<Integer> stack = new Stack<Integer>();
		
		stack.add(1);
		stack.add(2);
		stack.add(3);
		System.out.println("stack pop: " + stack.pop());
		stack.add(4);
		stack.add(5);
		
		System.out.print("stack :");
		for(int i: stack)
			System.out.print(" " + i);
		System.out.println();
		
	}

}
