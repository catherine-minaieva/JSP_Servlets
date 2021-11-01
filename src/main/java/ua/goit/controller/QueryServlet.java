package ua.goit.controller;

import ua.goit.model.Developer;
import ua.goit.repositoty.QueryRepositotyImpl;
import ua.goit.service.QueryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(urlPatterns = "/query/*")
public class QueryServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -5445805071986891403L;

    private QueryServiceImpl queryService;

    @Override
    public void init() throws ServletException {
        super.init();
        queryService = new QueryServiceImpl(new QueryRepositotyImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/getDeveloperByProject")) {
            req.getRequestDispatcher ("/view/query/get_developers_by_project.jsp").forward (req, resp);
        }
        if (action.startsWith ("/getLanguage")) {
            req.getRequestDispatcher ("/view/query/get_language.jsp").forward (req, resp);
        }
        if (action.startsWith ("/getLevel")) {
            req.getRequestDispatcher ("/view/query/get_level.jsp").forward (req, resp);
        }
        if (action.startsWith ("/getSalary")) {
            req.getRequestDispatcher ("/view/query/get_salary.jsp").forward (req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/getDeveloperByProject")) {
            final String id = req.getParameter ("id");
            List<Developer> byProject = queryService.developersOfProject(Long.valueOf (id));
            req.setAttribute ("message", byProject);
            req.getRequestDispatcher ("/view/query/get_developers_by_project.jsp").forward (req, resp);
        }

        if (action.startsWith ("/getLevel")) {
            final String level = req.getParameter ("level");
            List<Developer> byLevel = queryService.developersByLevel(level);
            req.setAttribute ("message", byLevel);
            req.getRequestDispatcher ("/view/query/get_level.jsp").forward (req, resp);
        }

        if (action.startsWith ("/getLanguage")) {
            final String language = req.getParameter ("language");
            List<Developer> bySkill = queryService.developersByLanguage(language);
            req.setAttribute ("message", bySkill);
            req.getRequestDispatcher ("/view/query/get_language.jsp").forward (req, resp);
        }
        if (action.startsWith ("/getSalary")) {
            final String id = req.getParameter ("id");
            String salary = queryService.sumOfSalariesForProject(Long.valueOf(id));
            req.setAttribute ("message", salary);
            req.getRequestDispatcher ("/view/query/get_salary.jsp").forward (req, resp);
        }
    }
}
