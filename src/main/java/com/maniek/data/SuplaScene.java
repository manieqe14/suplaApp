package com.maniek.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="supla_scenes")
public class SuplaScene {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="nazwa")
	private String name;
	
	@OneToMany
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
	
	
	
	public List<SuplaDeviceValue> getSuplaDeviceValues() {
		return suplaDeviceValues;
	}

	public void setSuplaDevicesValues(List<SuplaDeviceValue> suplaDeviceValues) {
		this.suplaDeviceValues = suplaDeviceValues;
	}

	@Override
	public String toString() {
		return "SuplaScene [id=" + id + ", name=" + name + ", suplaDevicesValues=" + suplaDeviceValues + "]";
	}
	
	
	

}
