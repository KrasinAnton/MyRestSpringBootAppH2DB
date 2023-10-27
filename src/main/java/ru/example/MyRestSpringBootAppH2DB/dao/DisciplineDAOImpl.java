package ru.example.MyRestSpringBootAppH2DB.dao;

import ru.example.MyRestSpringBootAppH2DB.entity.Discipline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Repository
public class DisciplineDAOImpl implements DisciplineDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Discipline> getAllDisciplines() {
        try {
            Query query = entityManager.createQuery("from Discipline");
            List<Discipline> allDisciplines = query.getResultList();
            log.info("getAllDisciplines: Получено " + allDisciplines.size() + " дисциплин");
            return allDisciplines;
        } catch (Exception e) {
            log.error("Ошибка при получении списка дисциплин: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Discipline saveDiscipline(Discipline discipline) {
        try {
            Discipline savedDiscipline = entityManager.merge(discipline);
            log.info("Сохранена дисциплина с ID " + savedDiscipline.getId());
            return savedDiscipline;
        } catch (Exception e) {
            log.error("Ошибка при сохранении дисциплины: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Discipline getDiscipline(int id) {
        try {
            Discipline discipline = entityManager.find(Discipline.class, id);
            if (discipline != null) {
                log.info("Получена дисциплина с ID " + id);
            } else {
                log.info("Дисциплина с ID " + id + " не найдена");
            }
            return discipline;
        } catch (Exception e) {
            log.error("Ошибка при получении дисциплины: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteDiscipline(int id) {
        try {
            Query query = entityManager.createQuery("delete from Discipline where id = :disciplineId");
            query.setParameter("disciplineId", id);
            int deletedCount = query.executeUpdate();
            if (deletedCount > 0) {
                log.info("Удалена дисциплина с ID " + id);
            } else {
                log.info("Дисциплина с ID " + id + " не найдена и не была удалена");
            }
        } catch (Exception e) {
            log.error("Ошибка при удалении дисциплины: " + e.getMessage(), e);
            throw e;
        }
    }
}
