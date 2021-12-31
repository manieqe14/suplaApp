package com.maniek.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="supla_device_value")
public class SuplaDeviceValue {
	
	@Id
	@GeneratedValue
	private int id;
	
	private boolean state;
	private int brightness;
	
	@ManyToOne
	private SuplaDevice suplaDevice;
	
	@ManyToOne
	private SuplaScene suplaScene;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getBrightness() {
		return brightness;
	}
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	public SuplaDevice getSuplaDevice() {
		return suplaDevice;
	}
	public void setSuplaDevice(SuplaDevice suplaDevice) {
		this.suplaDevice = suplaDevice;
	}
	
	
	
	
	

}
