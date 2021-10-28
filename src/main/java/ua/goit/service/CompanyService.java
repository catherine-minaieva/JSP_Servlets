package ua.goit.service;

import ua.goit.model.Company;
import ua.goit.repositoty.CompanyRepositoryImpl;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class CompanyService implements ua.goit.service.BaseService<Company> {

    private final CompanyRepositoryImpl repository;

    public CompanyService(CompanyRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Company findByID(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Company> findAll() {
        Collection<Company> all = repository.findAll();
        return (List<Company>) all;
    }

    @Override
    public void create(Company company) {
        repository.create(company);    }

    @Override
    public void update(Long id, Company company) {
        repository.update(id, company);
    }

    @Override
    public void delete(Long ID) {
        repository.deleteById(ID);
    }

    public Company mapCompany(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id").trim());
        String companyName = req.getParameter("name").trim();
        String headOffice = req.getParameter("headOffice").trim();

        Company company = new Company();
        company.setId(id);
        company.setName(companyName);
        company.setHeadOffice(headOffice);
        return company;
    }

    public Company mapEditCompany(Company company, HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id").trim());
        String companyName = req.getParameter("companyName").trim();
        String headOffice = req.getParameter("head_office").trim();

        company.setId(id);
        company.setName(companyName);
        company.setHeadOffice(headOffice);
        return company;
    }

//    public String validateCompany(HttpServletRequest req) {
//        String companyName = req.getParameter("companyName").trim();
//        if (get(companyName) != null) {
//            return String.format("Company with title %s already exist", companyName);
//        }
//        return "";
//    }
//
//    public String validateEditCompany(HttpServletRequest req) {
//        String oldName = req.getParameter("oldName").trim();
//        String newName = req.getParameter("companyName").trim();
//        if (oldName.equals(newName)) {
//            return "";
//        } else if (get(newName) != null) {
//            return String.format("Company with title %s already exist", newName);
//        }
//        return "";
//    }
}
