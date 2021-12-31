package com.maniek.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="supla_devices")
public class SuplaDevice {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String address;
	private boolean brightness;
	
	@OneToMany(mappedBy="suplaDevice")
	private List<SuplaDeviceValue> suplaDeviceValues;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isBrightness() {
		return brightness;
	}
	public void setBrightness(boolean brightness) {
		this.brightness = brightness;
	}
	
	@Override
	public String toString() {
		return "SuplaDevice [id=" + id + ", name=" + name + ", address=" + address + ", brightness=" + brightness + "]";
	}
	
	
	

}
