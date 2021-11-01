package ua.goit.controller;

import ua.goit.model.Customer;
import ua.goit.repositoty.CustomerRepositoryImpl;
import ua.goit.service.CustomerService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerService = new CustomerService(new CustomerRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith("/findCustomer")) {
            req.getRequestDispatcher("/view/customer/find_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/createCustomer")) {
            req.getRequestDispatcher("/view/customer/create_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/updateCustomer")) {
            req.getRequestDispatcher("/view/customer/update_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteCustomer")) {
            req.getRequestDispatcher("/view/customer/delete_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/allCustomers")) {
            List<Customer> customers = customerService.findAll();
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("/view/customer/all_customers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/createCustomer")) {
            Customer customer = customerService.mapCustomer(req);
            req.getRequestDispatcher ("/view/customer/create_customer.jsp").forward (req, resp);
            customerService.create (customer);
            req.setAttribute ("message", "New customer created: " + customer);
        }
        if (action.startsWith ("/findCustomer")) {
             String id = req.getParameter ("id");
             Customer customer = customerService.findByID(Long.valueOf(id));
            if (customer.getId() == null) {
                req.setAttribute ("message", "Customer not found");
            } else {
                req.setAttribute ("message", String.format ("Customer found: %s, ", customer));
            }
            req.getRequestDispatcher ("/view/customer/find_customer.jsp").forward (req, resp);
        }

        if (action.startsWith ("/deleteCustomer")) {
            long id = Long.parseLong((req.getParameter("id")));
            Customer customer = customerService.findByID(id);
            if (customer.getId() == null) {
                req.setAttribute ("message", "Customer not found");
            } else {
                customerService.delete(id);
                req.setAttribute ("message", "Customer deleted");
            }
            req.getRequestDispatcher("/view/customer/delete_customer.jsp").forward(req, resp);
        }

        if (action.startsWith ("/updateCustomer")) {
            Long id = Long.valueOf((req.getParameter("id")));
            Customer customer = customerService.findByID(id);

            if (customer.getId() == null) {
                req.setAttribute("message", "Company not found");

                Customer customerForUpdate = customerService.mapCustomer(req);
                customerService.update(id, customerForUpdate);
                req.setAttribute("message", "Customer updated");
                req.getRequestDispatcher ("/view/customer/update_customer.jsp").forward (req, resp);
            }
        }
    }
}
