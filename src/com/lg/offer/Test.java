package com.lg.offer;

import static org.junit.Assert.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

	@org.junit.Test
	public void test() {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);//不设置的话，默认为Integer.MAX_VALUE
		Consumer consumer = new Consumer(queue);
		Producer producer = new Producer(queue);
		
		for(int i=0;i<5;i++){
			new Thread(producer,"Producer"+(i+1)).start();
			new Thread(consumer,"Consumer"+(i+1)).start();
		}
	}
	
	
	

}
