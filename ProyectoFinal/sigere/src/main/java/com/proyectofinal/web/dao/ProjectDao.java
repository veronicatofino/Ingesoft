package com.proyectofinal.web.dao;

import java.util.List;

import com.proyectofinal.web.model.Project;

public interface ProjectDao {

	List<Project> getProjectsByUserId(final int id);
	
	Project getProjectById(final int id);

}
