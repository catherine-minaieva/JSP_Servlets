package ua.goit.service;

import ua.goit.model.Customer;
import ua.goit.repositoty.CustomerRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class CustomerService implements BaseService<Customer> {

    private final CustomerRepositoryImpl repository;

    public CustomerService(CustomerRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Customer findByID(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        Collection<Customer> all = repository.findAll();
        return (List<Customer>) all;
    }

    @Override
    public void create(Customer customer) {
        repository.create(customer);
    }

    @Override
    public void update(Long id, Customer customer) {
        repository.update(id, customer);
    }

    @Override
    public void delete(Long ID) {
        repository.deleteById(ID);
    }

    public Customer mapCustomer(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id").trim());
        String name = req.getParameter("customerName").trim();
        String taxCode = req.getParameter("taxCode".trim());
        String headOffice = req.getParameter("headOffice").trim();

        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setTaxCode(taxCode);
        customer.setHeadOffice(headOffice);

        return customer;
    }
}
