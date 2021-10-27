package ua.goit.service;

import ua.goit.model.Developer;
import ua.goit.repositoty.QueryRepositotyImpl;
import java.util.List;

public class QueryServiceImpl implements QueryService{

    final QueryRepositotyImpl repository;

    public QueryServiceImpl(QueryRepositotyImpl repository) {
        this.repository = repository;
    }

    @Override
    public String sumOfSalariesForProject(Long id) {
        return repository.getSumOfSalariesForProject(id);
    }

    @Override
    public List<Developer> developersOfProject(Long id) {
        return repository.getDevelopersOfProject(id);
    }

    @Override
    public List<Developer> developersByLanguage(String language) {
        return repository.getDevelopersByLanguage(language);
    }

    @Override
    public List<Developer> developersByLevel(String level) {
        return repository.getDevelopersByLevel(level);
    }

    @Override
    public String projectsWithDate() {
        return repository.getProjectsWithDate();
    }
}
