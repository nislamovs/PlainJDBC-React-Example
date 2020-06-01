package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dto.SubjectDTO;
import com.example.jdbcexample.services.SubjectsService;
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
public class SubjectController {

    private final SubjectsService subjectsService;

    @RequestMapping("/subject/all")
    public ResponseEntity<?> getSubjects() {
        return ok(subjectsService.getAllSubjects());
    }

    @RequestMapping("/subject")
    public ResponseEntity<?> getSubjectsPage(@RequestParam(name = "pagenum", required = true) String pagenum,
                                                        @RequestParam(name = "pagesize", required = true) String pagesize,
                                                        @RequestParam(name = "sort", required = true) String sort,
                                                        @RequestParam(name = "group", required = true) String group) {

        return ok(subjectsService.getSubjectsPage(pagenum, pagesize, sort, group));
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable("id") String id) {
        return ok(subjectsService.getSubjectById(id));
    }

    @PostMapping("/subject")
    public ResponseEntity<?> newSubject(@Valid @RequestBody SubjectDTO newSubject) {
        return ok(subjectsService.addNewSubject(newSubject));
    }

    @PutMapping("/subject")
    public ResponseEntity<?> editSubject(@Valid @RequestBody SubjectDTO subject) {
        return ok(subjectsService.editSubjectData(subject));
    }

    @DeleteMapping("/subject/{id]")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") String id) {
        return ok(subjectsService.deleteSubject(id));
    }

}
