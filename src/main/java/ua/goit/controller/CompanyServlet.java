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
            req.setAttribute("companies", companies);
            req.getRequestDispatcher("/view/company/all_companies.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action.startsWith("/createCompany")) {
            Company company = companyService.mapCompany(req);
            req.getRequestDispatcher("/view/company/create_company.jsp").forward(req, resp);
            companyService.create(company);
            req.setAttribute("message", "New Company created ");
        }
        if (action.startsWith("/findCompany")) {
             String id = req.getParameter("id");
             Company company = companyService.findByID(Long.valueOf(id));
            if (company.getId() == null) {
                req.setAttribute("message", "Company not found");
            } else {
                req.setAttribute("message", String.format("Company found: %s", company));
            }
            req.getRequestDispatcher("/view/company/find_company.jsp").forward(req, resp);
        }

        if (action.startsWith ("/deleteCompany")) {
            Long id = Long.valueOf((req.getParameter("id")));
            Company company = companyService.findByID(id);
            if (company.getId() == null) {
                req.setAttribute("message", "Company not found");
            } else {
                companyService.delete(id);
                req.setAttribute("message", "Company deleted");
            }
            req.getRequestDispatcher("/view/company/delete_company.jsp").forward(req, resp);
        }

        if (action.startsWith("/updateCompany")) {
            Long id = Long.valueOf((req.getParameter("id")));
            Company company = companyService.findByID(id);

            if (company.getId() == null) {
                req.setAttribute("message", "Company not found");
            } else{
                Company companyForUpdate = companyService.mapCompany(req);
                companyService.update(id, companyForUpdate);
                req.setAttribute("message", "Company updated");
                req.getRequestDispatcher ("/view/company/update_company.jsp").forward (req, resp);
            }
        }
    }
}
