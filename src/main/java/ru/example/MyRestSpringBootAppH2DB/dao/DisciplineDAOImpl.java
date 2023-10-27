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
        private final EntityManager entityManager;

        @Autowired
        public DisciplineDAOImpl(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

    @Override
    public List<Discipline> getAllDisciplines() {
        try {
            Query query = entityManager.createQuery("from Discipline");
            List<Discipline> allDisciplines = query.getResultList();
            log.info("getAllDisciplines: Received " + allDisciplines.size() + " disciplines");
            return allDisciplines;
        } catch (Exception e) {
            log.error("Error when getting the list of disciplines: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Discipline saveDiscipline(Discipline discipline) {
        try {
            Discipline savedDiscipline = entityManager.merge(discipline);
            log.info("Saved discipline with ID " + savedDiscipline.getId());
            return savedDiscipline;
        } catch (Exception e) {
            log.error("Error while maintaining discipline: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Discipline getDiscipline(int id) {
        try {
            Discipline discipline = entityManager.find(Discipline.class, id);
            if (discipline != null) {
                log.info("Received discipline with ID " + id);
            } else {
                log.info("Discipline with ID " + id + " not found");
            }
            return discipline;
        } catch (Exception e) {
            log.error("Error in obtaining discipline: " + e.getMessage(), e);
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
                log.info("Discipline with ID removed " + id);
            } else {
                log.info("Discipline with ID " + id + " not found and has not been deleted");
            }
        } catch (Exception e) {
            log.error("Error when deleting discipline: " + e.getMessage(), e);
            throw e;
        }
    }
}
