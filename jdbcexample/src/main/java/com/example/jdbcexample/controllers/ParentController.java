package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dto.ParentDTO;
import com.example.jdbcexample.services.ParentsService;
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
public class ParentController {

    private final ParentsService parentsService;

    @GetMapping("/parent/all")
    public ResponseEntity<?> getParents() {
        return ok(parentsService.getAllParents());
    }

    @GetMapping("/parent")
    public ResponseEntity<?> getParentsPage(@RequestParam(name = "pagenum", required = true) String pagenum,
                                                        @RequestParam(name = "pagesize", required = true) String pagesize,
                                                        @RequestParam(name = "sort", required = true) String sort,
                                                        @RequestParam(name = "group", required = true) String group) {

        return ok(parentsService.getParentsPage(pagenum, pagesize, sort, group));
    }

    @GetMapping("/parents")
    public ResponseEntity<?> getParentByNameSurname(@RequestParam(name = "name", required = true) String name,
                                                   @RequestParam(name = "surname", required = true) String surname) {

        return ok(parentsService.getParentsByFirstnameLastname(name, surname));
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<?> getParentById(@PathVariable("id") String id) {
        return ok(parentsService.getParentById(id));
    }

    @PostMapping("/parent")
    public ResponseEntity<?> newParent(@Valid @RequestBody ParentDTO newParent) {

        return ok(parentsService.addNewParent(newParent));
    }

    @PutMapping("/parent")
    public ResponseEntity<?> editParent(@Valid @RequestBody ParentDTO parent) {

        return ok(parentsService.editParentData(parent));
    }

    @DeleteMapping("/parent/{id]")
    public ResponseEntity<?> deleteParent(@PathVariable("id") String id) {

        return ok(parentsService.deleteParent(id));
    }
}
