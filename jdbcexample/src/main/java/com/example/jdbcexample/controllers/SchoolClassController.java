package com.example.jdbcexample.controllers;

import com.example.jdbcexample.dto.PupilDTO;
import com.example.jdbcexample.dto.SchoolClassDTO;
import com.example.jdbcexample.dto.SubjectDTO;
import com.example.jdbcexample.services.ClassesService;
import com.example.jdbcexample.services.SubjectsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class SchoolClassController {

    private final ClassesService classesService;

    @RequestMapping("/class/all")
    public ResponseEntity<?> getClasses() {
        return ok(classesService.getAllClasses());
    }

    @RequestMapping("/class")
    public ResponseEntity<?> getClassesPage(@RequestParam(name = "pagenum", required = true) String pagenum,
                                                                    @RequestParam(name = "pagesize", required = true) String pagesize,
                                                                    @RequestParam(name = "sort", required = true) String sort,
                                                                    @RequestParam(name = "group", required = true) String group) {

        return ok(classesService.getClassesPage(pagenum, pagesize, sort, group));
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<?> getSchoolClassById(@PathVariable("id") String id) {
        return ok(classesService.getSchoolClassById(id));
    }

    @PostMapping("/class")
    public ResponseEntity<?> newSchoolClass(@Valid @RequestBody SchoolClassDTO newSchoolClass) {
        return ok(classesService.addNewSchoolClass(newSchoolClass));
    }

    @PutMapping("/class")
    public ResponseEntity<?> editSchoolClass(@Valid @RequestBody SchoolClassDTO schoolClass) {
        return ok(classesService.editSchoolClassData(schoolClass));
    }

    @DeleteMapping("/class/{id]")
    public ResponseEntity<?> deleteSchoolClass(@PathVariable("id") String id) {
        return ok(classesService.deleteSchoolClass(id));
    }
}
