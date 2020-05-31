package com.example.jdbcexample.controllers;

import com.example.jdbcexample.dao.PupilDAO;
import com.example.jdbcexample.dto.PupilDTO;
import com.example.jdbcexample.services.PupilsService;
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
public class PupilController {

    private final PupilsService pupilsService;

    @GetMapping("/pupil/all")
    public ResponseEntity<?> getPupils() {
        return ok(pupilsService.getAllPupils());
    }

    @GetMapping("/pupil")
    public ResponseEntity<?> getPupilsPage(@RequestParam(name = "pagenum", required = true) String pagenum,
                                                        @RequestParam(name = "pagesize", required = true) String pagesize,
                                                        @RequestParam(name = "sort", required = true) String sort,
                                                        @RequestParam(name = "group", required = true) String group) {

        return ok(pupilsService.getPupilsPage(pagenum, pagesize, sort, group));
    }

    @GetMapping("/pupils")
    public ResponseEntity<?> getPupilByNameSurname(@RequestParam(name = "name", required = true) String name,
                                                   @RequestParam(name = "surname", required = true) String surname) {

        return ok(pupilsService.getPupilsByFirstnameLastname(name, surname));
    }

    @GetMapping("/pupil/{id}")
    public ResponseEntity<?> getPupilById(@PathVariable("id") String id) {
        return ok(pupilsService.getPupilById(id));
    }

    @PostMapping("/pupil")
    public ResponseEntity<?> newPupil(@Valid @RequestBody PupilDTO newPupil) {

        return ok(pupilsService.addNewPupil(newPupil));
    }

    @PutMapping("/pupil")
    public ResponseEntity<?> editPupil(@Valid @RequestBody PupilDTO pupil) {

        return ok(pupilsService.editPupilData(pupil));
    }

    @DeleteMapping("/pupil/{id]")
    public ResponseEntity<?> deletePupil(@PathVariable("id") String id) {

        return ok(pupilsService.deletePupil(id));
    }
}
