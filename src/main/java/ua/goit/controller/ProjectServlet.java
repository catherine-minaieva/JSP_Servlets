package ua.goit.controller;

import ua.goit.model.Project;
import ua.goit.repositoty.ProjectRepositoryImpl;
import ua.goit.service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/project/*")
public class ProjectServlet extends HttpServlet {
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        super.init();
        projectService = new ProjectService(new ProjectRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action.startsWith("/findProject")) {
            req.getRequestDispatcher("/view/project/find_project.jsp").forward(req, resp);
        }
        if (action.startsWith("/createProject")) {
            req.getRequestDispatcher("/view/project/create_project.jsp").forward(req, resp);
        }
        if (action.startsWith("/updateProject")) {
            req.getRequestDispatcher("/view/project/update_project.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteProject")) {
            req.getRequestDispatcher("/view/project/delete_project.jsp").forward(req, resp);
        }
        if (action.startsWith("/allProject")) {
            List<Project> projectList = projectService.findAll();
            req.setAttribute("projects", projectList);
            req.getRequestDispatcher("/view/project/all_projects.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action.startsWith("/createProject")) {
            Project project = projectService.mapProject(req);
            req.getRequestDispatcher("/view/project/create_project.jsp").forward(req, resp);
            projectService.create(project);
            req.setAttribute("message", "New project created: " + project);
            req.getRequestDispatcher("/view/project/create_project.jsp").forward(req, resp);
        }

        if (action.startsWith("/findProject")) {
            String id = req.getParameter("id");
            Project project = projectService.findByID(Long.valueOf(id));
            if (project.getId() == null) {
                req.setAttribute("message", "Project not found");
                req.getRequestDispatcher("/view/project/find_project.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", String.format("Project found: %s", project));
                req.getRequestDispatcher("/view/project/find_project.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/deleteProject")) {
            Long id = Long.valueOf((req.getParameter("id")));
            Project project = projectService.findByID(id);
            if (project.getId() == null) {
                req.setAttribute("message", "Project not found");
            } else {
                projectService.delete(id);
                req.setAttribute("message", "Project deleted");
            }
            req.getRequestDispatcher("/view/project/delete_project.jsp").forward(req, resp);
        }

        if (action.startsWith("/updateProject")) {
            Long id = Long.valueOf((req.getParameter("id")));
            Project project = projectService.findByID(id);
            if (project.getId() == null) {
                req.setAttribute("message", "Project not found");
            } else {
                Project projectForUpdate = projectService.findByID(id);
                projectService.update(id, projectForUpdate);
                req.setAttribute("message", "Project  updated");
                req.getRequestDispatcher("/view/project/update_project.jsp").forward(req, resp);
            }
        }
    }
}
