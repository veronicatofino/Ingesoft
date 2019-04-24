package com.proyectofinal.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.proyectofinal.web.model.Project;

public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;
	  
	@Override
	public List<Project> getProjectsByUserId(int id) {
		String sql = "SELECT * FROM projectsxuser INNER JOIN projects ON(projectsxuser.projectId = projects.id) WHERE userId = '" + id + "'";
		return jdbcTemplate.query(sql, new ProjectMapper());
	}

	@Override
	public Project getProjectById(int id) {
		String sql = "SELECT * FROM projects WHERE id = '" + id + "'";
		return jdbcTemplate.query(sql, new ProjectMapper()).get(0);
	}

}

class ProjectMapper implements RowMapper<Project> {

	@Override
	public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Project(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getBlob("contextDiagram"));
	}
	
}