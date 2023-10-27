package ru.example.MyRestSpringBootAppH2DB.service;

import ru.example.MyRestSpringBootAppH2DB.entity.Discipline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DisciplineService {
    List<Discipline> getAllDisciplines();
    Discipline getDiscipline(int id);
    Discipline saveDiscipline(Discipline discipline);
    boolean deleteDiscipline(int id);
}
