package com.dib.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Beer")
public class Beer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long beerId;

	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;
	private int meshTemp;

	public Beer() {
		// TODO Auto-generated constructor stub
	}

	public Beer(Long beerId, String name, String description, int meshTemp) {
		super();
		this.beerId = beerId;
		this.name = name;
		this.description = description;
		this.meshTemp = meshTemp;
	}

	public Long getBeerId() {
		return beerId;
	}

	public void setBeerId(Long bearId) {
		this.beerId = bearId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMeshTemp() {
		return meshTemp;
	}

	public void setMeshTemp(int meshTemp) {
		this.meshTemp = meshTemp;
	}

}
