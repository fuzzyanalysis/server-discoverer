package com.datasciencebox.serverdiscoverer.utils;

import java.util.Date;
import java.util.List;

public class Server {
	
	public String ip;
	public String domainName;
	public List<Port> ports;
	public Date dateScanned;
	private String lattitude;
	private String longtitude;
	private String country;
	
	
	public Server() {}
	
	public Server(String ip, String domainName, String lattitude, String longtitude, String country, Date dateScanned) {
		
		this.ip = ip;
		this.domainName = domainName;
		this.lattitude = lattitude;
		this.longtitude = longtitude;
		this.country = country;
		this.dateScanned = dateScanned;
		
	}	
	
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
	

	public Date getDateScanned() {
		return dateScanned;
	}

	public void setDateScanned(Date dateScanned) {
		this.dateScanned = dateScanned;
	}

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	
}
