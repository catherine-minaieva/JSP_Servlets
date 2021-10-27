package ua.goit.service;

import ua.goit.model.Skill;
import ua.goit.repositoty.SkillRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class SkillService implements BaseService<Skill> {

    private final SkillRepositoryImpl repository;

    public SkillService(SkillRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Skill findByID(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        Collection<Skill> all = repository.findAll();
        return (List<Skill>) all;
    }

    @Override
    public void create(Skill skill) {
        repository.create(skill);
    }

    @Override
    public void update(Long id, Skill skill) {
        repository.update(id, skill);
    }

    @Override
    public void delete(Long ID) {
        repository.deleteById(ID);
    }

    public Skill mapSkill(HttpServletRequest req) {

        Long id = Long.parseLong(req.getParameter("id").trim());
        String language = req.getParameter("language").trim();
        String level = req.getParameter("level").trim();

        Skill skill = new Skill();
        skill.setId(id);
        skill.setLanguage(language);
        skill.setLevel(level);

        return skill;
    }
}
