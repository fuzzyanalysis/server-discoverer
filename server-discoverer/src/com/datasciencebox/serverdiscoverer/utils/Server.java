package com.datasciencebox.serverdiscoverer.utils;

import java.util.List;

public class Server {

	public Server() {}
	public Server(String ip, String domainName, List<Port> ports ) {
		
		this.ip = ip;
		this.domainName = domainName;
		this.ports = ports;		
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public List<Port> getPorts() {
		return ports;
	}
	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}
	
	public String ip;
	public String domainName;
	public List<Port> ports;
	
}
