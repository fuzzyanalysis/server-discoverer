package com.datasciencebox.serverdiscoverer.utils;

public class Port {

	public Port() {};
	
	public Port(int portNumber, String use) {
		this.portNumber = portNumber;
		this.use = use;
	}
	
	
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getPingTime() {
		return pingTime;
	}
	public void setPingTime(String pingTime) {
		this.pingTime = pingTime;
	}
	
	public int portNumber;
	public String use;
	public String pingTime;
	
}
