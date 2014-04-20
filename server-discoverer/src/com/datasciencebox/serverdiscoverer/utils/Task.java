package com.datasciencebox.serverdiscoverer.utils;

import java.util.concurrent.Callable;

class Task implements Runnable {
	
	public String call() throws Exception {
		Thread.sleep(4000); // Just to demo a long running task of 4 seconds.
		return "Ready!";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
