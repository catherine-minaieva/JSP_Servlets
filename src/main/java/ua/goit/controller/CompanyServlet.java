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

    private CompanyService service;

        @Override
    public void init() throws ServletException {
        super.init();
        service = new CompanyService(new CompanyRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = getAction(req);

        if (action.startsWith("/showCompanies")) {
            List<Company> companies = service.findAll();
            req.setAttribute("companies", companies);
            req.getRequestDispatcher("/view/company/showCompanies.jsp").forward(req, resp);
        }
        else if (action.startsWith("/findCompany")) {
            req.getRequestDispatcher("/view/company/findCompany.jsp").forward(req, resp);
        }
        /*
        else if (action.startsWith("/find")) {
            String companyName = req.getParameter("companyName");
            Company company = service.get(companyName);
        */
        /*if (company == null) {
                String message = String.format("Company with name %s not exist", companyName);
                req.setAttribute("message", message);
                req.getRequestDispatcher("/view/company/findCompany.jsp").forward(req, resp);
            } else {
                req.setAttribute("company", company);
                req.getRequestDispatcher("/view/company/companyDetails.jsp").forward(req, resp);
            }
        }*/
        else if (action.startsWith("/createCompany")) {
            req.getRequestDispatcher("/view/company/createCompany.jsp").forward(req, resp);
        }
        else if (action.startsWith("/get")) {
            String id = req.getParameter("id");
            Company company = service.findByID(Long.parseLong(id));
            req.setAttribute("company", company);
            req.getRequestDispatcher("/view/company/companyDetails.jsp").forward(req, resp);
        }
        else if (action.startsWith("/edit")) {
            String id = req.getParameter("id");
            Company company = service.findByID(Long.parseLong(id));
            req.setAttribute("company", company);
            req.getRequestDispatcher("/view/company/editCompany.jsp").forward(req, resp);
        }
        else if (action.startsWith("/delete")) {
            doDelete(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String action = getAction(req);
//
//        if (action.startsWith("/createCompany")) {
//            String validate = service.validateCompany(req);
//            if (!validate.isEmpty()) {
//                req.setAttribute("validate", validate);
//            req.getRequestDispatcher("/view/company/createCompany.jsp").forward(req, resp);
//        } else {
//            Company company = service.mapCompany(req);
//            service.create(company);
//            req.setAttribute("company", company);
//            req.getRequestDispatcher("/view/company/companyDetails.jsp").forward(req, resp);
//        }
//    }
//        else if (action.startsWith("/edit")) {
//            String id = req.getParameter("companyID");
//            Company company = service.get(Integer.parseInt(id));
//            String validate = service.validateEditCompany(req);
//            if (!validate.isEmpty()) {
//                req.setAttribute("company", company);
//                req.setAttribute("validate", validate);
//                req.getRequestDispatcher("/view/company/editCompany.jsp").forward(req, resp);
//            } else {
//                company = service.mapEditCompany(company, req);
//                service.update(company);
//                req.setAttribute("company", company);
//                req.getRequestDispatcher("/view/company/companyDetails.jsp").forward(req, resp);
//            }
//        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        service.delete(Long.parseLong(id));
        resp.sendRedirect("/ProjectManagementSystem/company/showCompanies");
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String requestPathWithServletContext = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletContext.length());
    }
}
