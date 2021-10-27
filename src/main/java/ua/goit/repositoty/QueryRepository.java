package ua.goit.repositoty;

import ua.goit.model.Developer;

import java.util.List;

public interface QueryRepository {
    String getSumOfSalariesForProject(Long id);
    List<Developer> getDevelopersOfProject(Long id);
    List<Developer> getDevelopersByLanguage(String language);
    List<Developer> getDevelopersByLevel(String level);
    String getProjectsWithDate();
}
