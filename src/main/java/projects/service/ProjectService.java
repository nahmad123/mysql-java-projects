package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

public class ProjectService {
	
	private ProjectDao projectDao = new ProjectDao();

	/**
	 * This method simply calls the DAO class to insert a project row.
	 * 
	 * @param project The {@link Project} object.
	 * @return The Project object with the newly generated primary key value.
	 */
	public Project addProject(Project project) {
		return projectDao.insertProject(project);
	}

	//reusable code for getting the first column in ascending order (1,2,3,)
	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects()
				.stream()
				.sorted((p1, p2) -> p1.getProjectId()- p2.getProjectId())
				.collect(Collectors.toList());
				
		
	}

	public Project fetchProjectById(Integer projectId) {
		return projectDao.fetchProjectById(projectId).orElseThrow(() -> new NoSuchElementException("Project with project ID=" + projectId + " does not exist."));
	}

//	public void updateProjectDetails(Project project) {
//		if(!projectDao.updateProjectDetails(project)) {
//			throw new DbException("Project with ID=" + project.getProjectId() + " does not exist.");
//		}
		
//	}

	public void deleteProject(Integer projectId) {
		if(!projectDao.deleteProject(projectId)) {
			throw new DbException("Project with ID=" + projectId + " does not exist.");
		}
	}

	public void modifyProjectDetails(Project project) {
		if(!projectDao.modifyProjectDetails(project)) {
			throw new DbException("Project with ID=" + project.getProjectId() + " does not exist.");
		}
}
}
