package com.dashboard.model;

public class InstanceModel {

	private String name;
	private String id;
	private String subnetId;
	private String instanceType;
	private String publicIP;
	private boolean isStarted;
	
	public boolean isStarted() {
		return isStarted;
	}
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubnetId() {
		return subnetId;
	}
	public void setSubnetId(String subnetId) {
		this.subnetId = subnetId;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getPublicIP() {
		return publicIP;
	}
	public void setPublicIP(String publicIP) {
		this.publicIP = publicIP;
	}
	
	
	
}
