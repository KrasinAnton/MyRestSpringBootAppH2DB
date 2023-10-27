package ru.example.MyRestSpringBootAppH2DB.controller;

import ru.example.MyRestSpringBootAppH2DB.entity.Discipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.MyRestSpringBootAppH2DB.service.DisciplineService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class DisciplineController {
    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("/disciplines")
    public ResponseEntity<List<Discipline>> showAllDisciplines() {
        List<Discipline> allDisciplines = disciplineService.getAllDisciplines();
        if (!allDisciplines.isEmpty()) {
            return new ResponseEntity<>(allDisciplines, HttpStatus.OK); // Возвращаем статус 200 (OK)
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Если список пуст, возвращаем статус 404 (Not Found)
        }
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<Discipline> getDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline != null) {
            return new ResponseEntity<>(discipline, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/disciplines")
    public ResponseEntity<Discipline> saveDiscipline(@RequestBody Discipline discipline) {
        Discipline savedDiscipline = disciplineService.saveDiscipline(discipline);
        if (savedDiscipline != null) {
            return new ResponseEntity<>(savedDiscipline, HttpStatus.CREATED); // Возвращаем статус 201 (Created)
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Если сохранение не удалось, возвращаем статус 400 (Bad Request)
        }
    }

    @PutMapping("disciplines/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@RequestBody Discipline discipline) {
        Discipline updatedDiscipline = disciplineService.saveDiscipline(discipline);
        if (updatedDiscipline != null) {
            return new ResponseEntity<>(updatedDiscipline, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/disciplines/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable("id") int id) {
        boolean deleted = disciplineService.deleteDiscipline(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Возвращаем статус 204 (No Content) после успешного удаления
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
