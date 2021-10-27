package ua.goit.service;

import ua.goit.model.Developer;
import ua.goit.repositoty.DeveloperRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepositoryImpl repository;

    public DeveloperServiceImpl(DeveloperRepositoryImpl repository){
        this.repository = repository;
    }

    @Override
    public Developer findByID(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Developer> findAll() {
        Collection<Developer> all = repository.findAll();
        return (List<Developer>) all;
    }

    @Override
    public void create(Developer developer) {
        repository.create(developer);
    }

    @Override
    public void update(Long id, Developer developer) {
        repository.update(id, developer);
    }

    @Override
    public void delete(Long ID) {
        repository.deleteById(ID);
    }

    @Override
    public Developer findByName(String name) {
        return null;
    }

    public Developer mapDeveloper(HttpServletRequest req) {

        Long id = Long.parseLong(req.getParameter("id").trim());
        String name = req.getParameter("name").trim();
        int age = Integer.parseInt(req.getParameter("age").trim());
        String gender = req.getParameter("gender").trim();
        Double salary = Double.parseDouble(req.getParameter("salary").trim());

        Developer developer = new Developer();
        developer.setId(id);
        developer.setName(name);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setSalary(salary);

        return developer;
    }
}
