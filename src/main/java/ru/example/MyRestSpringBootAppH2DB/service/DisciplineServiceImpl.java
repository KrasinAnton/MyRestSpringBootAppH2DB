package ru.example.MyRestSpringBootAppH2DB.service;

import ru.example.MyRestSpringBootAppH2DB.dao.DisciplineDAO;
import ru.example.MyRestSpringBootAppH2DB.entity.Discipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DisciplineServiceImpl implements DisciplineService {
    @Autowired
    private DisciplineDAO disciplineDAO;

    @Override
    @Transactional
    public List<Discipline> getAllDisciplines() {
        return disciplineDAO.getAllDisciplines();
    }

    @Override
    @Transactional
    public Discipline getDiscipline(int id) {
        return disciplineDAO.getDiscipline(id);
    }

    @Override
    @Transactional
    public Discipline saveDiscipline(Discipline discipline) {
        return disciplineDAO.saveDiscipline(discipline);
    }

    @Override
    @Transactional
    public boolean deleteDiscipline(int id) {
        disciplineDAO.deleteDiscipline(id);
        return false;
    }
}
