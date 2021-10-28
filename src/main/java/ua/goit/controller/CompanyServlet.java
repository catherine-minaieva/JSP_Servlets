package ua.goit.controller;

import ua.goit.model.Company;
import ua.goit.repositoty.CompanyRepositoryImpl;
import ua.goit.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/company/*")
public class CompanyServlet extends HttpServlet {

    private CompanyService companyService;

        @Override
    public void init() throws ServletException {
        super.init();
        companyService = new CompanyService(new CompanyRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/findCompany")) {
            req.getRequestDispatcher ("/view/company/find_company.jsp").forward (req, resp);
        }
        if (action.startsWith ("/createCompany")) {
            req.getRequestDispatcher ("/view/company/create_company.jsp").forward (req, resp);
        }
        if (action.startsWith ("/deleteCompany")) {
            req.getRequestDispatcher ("/view/company/delete_company.jsp").forward (req, resp);
        }
        if (action.startsWith ("/updateCompany")) {
            req.getRequestDispatcher ("/view/company/update_company.jsp").forward (req, resp);

        }
        if (action.startsWith ("/allCompanies")) {
            List<Company> companies = companyService.findAll();
            req.setAttribute ("companies", companies);
            req.getRequestDispatcher ("/view/company/all_companies.jsp").forward (req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/createCompany")) {
            Company company = companyService.mapCompany(req);
            req.getRequestDispatcher ("/view/company/create_company.jsp").forward (req, resp);
            companyService.create (company);
            req.setAttribute ("message", "New Company created ");
        }
        if (action.startsWith ("/findCompany")) {
            final String id = req.getParameter ("id");
            final Company company = companyService.findByID (Long.valueOf (id));
            if (company.getID() == null) {
                req.setAttribute ("message", "Company not found");
            } else {
                req.setAttribute("message", String.format("Company found: %s", company));
            }
            req.getRequestDispatcher ("/view/company/find_company.jsp").forward (req, resp);
        }

        if (action.startsWith ("/updateCompany")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Company company = companyService.findByID (id);
            String name = req.getParameter("name");
            String newOffice = req.getParameter ("headOffice");
            company.setName(name);
            company.setHeadOffice (newOffice);
            companyService.update (id, company);
            req.setAttribute ("message", String.format("Company updated"));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/deleteCompany")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Company company = companyService.findByID (id);
            if (company.getID() == null) {
                req.setAttribute ("message", "Company not found");
            } else {companyService.delete (id);
                req.setAttribute ("message", "Company deleted");
            }
            resp.sendRedirect("/ProjectManagementSystem/company/showCompanies");
        }
    }
}
