package com.proyectofinal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyectofinal.web.dao.ProjectDao;
import com.proyectofinal.web.model.Project;

public class ProjectServiceImpl implements ProjectService {

	@Autowired
	public ProjectDao projectDao;
	
	@Override
	public List<Project> getProjectsByUserId(int id) {
		return projectDao.getProjectsByUserId(id);
	}

	@Override
	public Project getProjectById(int id) {
		return projectDao.getProjectById(id);
	}

}
