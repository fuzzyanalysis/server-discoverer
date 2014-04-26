package com.datasciencebox.serverdiscoverer.dao.server;

import java.util.Date;
import java.util.List;

import com.datasciencebox.serverdiscoverer.utils.Port;

public class Server {
	
	public String ip;
	public String domainName;
	public List<Port> ports;
	public Date dateScanned;
	private float lattitude;
	private float longitude;
	private String country;
	
	
	public Server() {}
	
	public Server(String ip, String domainName, float lattitude, float longitude, String country, Date dateScanned) {
		
		this.ip = ip;
		this.domainName = domainName;
		this.lattitude = lattitude;
		this.longitude = longitude;
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

	public float getLattitude() {
		return lattitude;
	}

	public void setLattitude(float latitude) {
		this.lattitude = latitude;
	}

	public float getLongtitude() {
		return longitude;
	}

	public void setLongtitude(float longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
