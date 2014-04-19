package com.datasciencebox.serverdiscoverer.utils;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// We will store the threads so that we can check if they are done
		List<Thread> threads = new ArrayList<Thread>();
		// We will create 500 threads
		for (int i = 0; i < 4; i++) {

			Runnable task = new Scan("www.a" + i + ".com");
			Thread worker = new Thread(task);
			// We can set the name of the thread
			worker.setName(String.valueOf(i));
			// Start the thread, never call method run() direct
			worker.start();
			// Remember the thread for later usage
			threads.add(worker);
		}
		long startTime = System.nanoTime();
		long endTime = 0;
		long totalTime = 0;
		int running = 0;
		do {
			running = 0;
			for (Thread thread : threads) {
				if (thread.isAlive()) {
					try {
						endTime = System.nanoTime();
						totalTime = endTime - startTime;
						if(totalTime > 50000) {
							thread.currentThread().stop();
						} else {
							thread.currentThread().sleep(5000);	
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("Time taken : " + totalTime);			
		} while (running > 0);

	}
}
