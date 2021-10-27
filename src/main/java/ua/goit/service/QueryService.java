package ua.goit.service;

import ua.goit.model.BaseEntity;
import ua.goit.model.Developer;
import java.util.List;

public interface QueryService <T extends BaseEntity> {

    String sumOfSalariesForProject(Long id);
    List<Developer> developersOfProject(Long id);
    List<Developer> developersByLanguage(String language);
    List<Developer> developersByLevel(String level);
    String projectsWithDate();
}
