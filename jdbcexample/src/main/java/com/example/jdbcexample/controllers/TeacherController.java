package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dto.TeacherDTO;
import com.example.jdbcexample.services.TeachersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {

    private final TeachersService teachersService;

    @RequestMapping("/teacher/all")
    public ResponseEntity<?> getTeachers() {
        return ok(teachersService.getAllTeachers());
    }

    @RequestMapping("/teacher")
    public ResponseEntity<?> getTeachersPage(@RequestParam(name = "pagenum", required = true) String pagenum,
                                                            @RequestParam(name = "pagesize", required = true) String pagesize,
                                                            @RequestParam(name = "sort", required = true) String sort,
                                                            @RequestParam(name = "group", required = true) String group) {

        return ok(teachersService.getTeachersPage(pagenum, pagesize, sort, group));
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable("id") String id) {
        return ok(teachersService.getTeacherById(id));
    }

    @PostMapping("/teacher")
    public ResponseEntity<?> newTeacher(@Valid @RequestBody TeacherDTO newTeacher) {
        return ok(teachersService.addNewTeacher(newTeacher));
    }

    @PutMapping("/teacher")
    public ResponseEntity<?> editTeacher(@Valid @RequestBody TeacherDTO teacher) {
        return ok(teachersService.editTeacherData(teacher));
    }

    @DeleteMapping("/teacher/{id]")
    public ResponseEntity<?> deleteTeacher(@PathVariable("id") String id) {
        return ok(teachersService.deleteTeacher(id));
    }
}
