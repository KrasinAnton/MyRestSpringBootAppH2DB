package ru.example.MyRestSpringBootAppH2DB.dao;

import ru.example.MyRestSpringBootAppH2DB.entity.Discipline;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineDAO {
    List<Discipline> getAllDisciplines();
    Discipline saveDiscipline(Discipline discipline);
    Discipline getDiscipline(int id);
    void deleteDiscipline(int id);
}
