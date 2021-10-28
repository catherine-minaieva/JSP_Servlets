package ua.goit.controller;

import ua.goit.model.Developer;
import ua.goit.repositoty.DeveloperRepositoryImpl;
import ua.goit.service.DeveloperServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/developer/*")
public class DeveloperServlet extends HttpServlet {
    private DeveloperServiceImpl developerService;

    @Override
    public void init() throws ServletException {
        super.init();
        developerService = new DeveloperServiceImpl(new DeveloperRepositoryImpl());
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith("/findDeveloper")) {
            req.getRequestDispatcher("/view/developer/find_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/createDeveloper")) {
            req.getRequestDispatcher("/view/developer/create_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/updateDeveloper")) {
            req.getRequestDispatcher("/view/developer/update_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteDeveloper")) {
            req.getRequestDispatcher("/view/developer/delete_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/allDeveloper")) {
            List<Developer> developerList = developerService.findAll ();
            req.setAttribute("developers", developerList);
            req.getRequestDispatcher("/view/developer/all_developers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/createDeveloper")) {
            Developer developer = developerService.mapDeveloper(req);
            req.getRequestDispatcher ("/view/developer/create_developer.jsp").forward (req, resp);
            developerService.create (developer);
            req.setAttribute ("message", "New developer created: "+ developer);
        }
        if (action.startsWith ("/findDeveloper")) {
            final String id = req.getParameter ("id").trim ();
            final Developer developer = developerService.findByID (Long.valueOf (id));
            if (developer == null) {
                req.setAttribute ("message", String.format ("Developer found: %s", developer));
                req.getRequestDispatcher ("/view/developer/find_developer.jsp").forward (req, resp);
            } else {
                req.setAttribute ("message", "Developer not found");
                req.getRequestDispatcher ("/view/developer/find_developer.jsp").forward (req, resp);
            }
        }
        if (action.startsWith ("/deleteDeveloper")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Developer developer = developerService.findByID(id);
            if (developer == null) {
                req.setAttribute ("message", "Developer not found");
            } else {
                developerService.delete (id);
                req.setAttribute ("message", "Developer deleted");
            }
            req.getRequestDispatcher("/view/developer/delete_developer.jsp").forward(req, resp);
        }
        if (action.startsWith ("/updateDeveloper")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Developer developer = developerService.findByID (id);
            String newSalary = req.getParameter ("salary");
            developer.setSalary(Double.valueOf (newSalary));
            developerService.update (id, developer);
            req.setAttribute ("message", "Developer  updated");

            req.getRequestDispatcher ("/view/developer/update_developer.jsp").forward (req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
