package ua.goit.service;

import ua.goit.model.Project;
import ua.goit.repositoty.ProjectRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class ProjectService implements BaseService<Project>{

    private final ProjectRepositoryImpl repository;

    public ProjectService(ProjectRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Project findByID(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Project> findAll() {
        Collection<Project> all = repository.findAll();
        return (List<Project>) all;
    }

    @Override
    public void create(Project project) {
        repository.create(project);
    }

    @Override
    public void update(Long id, Project project) {
        repository.update(id, project);
    }

    @Override
    public void delete(Long ID) {
        repository.deleteById(ID);
    }

    public Project mapProject(HttpServletRequest req) {

        Long id = Long.parseLong(req.getParameter("id").trim());
        String name = req.getParameter("name").trim();
        String baseTechnology = req.getParameter("baseTechnology").trim();
        String creationDate  = req.getParameter("creationDate").trim();
        Long cost = Long.parseLong(req.getParameter("cost").trim());

        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setBaseTechnology(baseTechnology);
        project.setCreationDate(creationDate);
        project.setCost(cost);

        return project;
    }
}
