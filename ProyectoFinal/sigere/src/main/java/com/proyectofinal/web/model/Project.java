package com.proyectofinal.web.model;

import java.sql.Blob;

public class Project {
	
	private final int id;
	private final String name;
	private final String description;
	private final Blob contextDiagram;
	
	public Project(final int id, final String name, final String description, final Blob contextDiagram) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.contextDiagram = contextDiagram;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Blob getContextDiagram() {
		return contextDiagram;
	}
}
