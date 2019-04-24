package com.proyectofinal.web.service;

import java.util.List;

import com.proyectofinal.web.model.Project;

public interface ProjectService {

	List<Project> getProjectsByUserId(final int id);
	
	Project getProjectById(final int id);
}
