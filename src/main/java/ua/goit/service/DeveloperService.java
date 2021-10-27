package ua.goit.service;

import ua.goit.model.Developer;

public interface DeveloperService extends BaseService<Developer> {
    Developer findByName(String name);
}
