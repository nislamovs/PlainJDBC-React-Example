package com.example.jdbcexample.controllers;

import com.example.jdbcexample.dto.SubjectMarkDTO;
import com.example.jdbcexample.services.MarksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.lang.Long.parseLong;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class MarksController {

    private final MarksService marksService;

    @GetMapping("/mark/all")
    public ResponseEntity<?> getMarks() {
        return ok(marksService.getAllMarks());
    }

    @GetMapping(path = "/mark/{id}")
    public ResponseEntity<?> getMarksById(@PathVariable(value="id") String pupilId) {
        return ok(marksService.retrieveMarksByPupilId(pupilId));
    }

    @GetMapping("/mark")
    public ResponseEntity<?> getMarksPage(@RequestParam(name = "pagenum", required = true) String pagenum,
                                          @RequestParam(name = "pagesize", required = true) String pagesize,
                                          @RequestParam(name = "sort", required = true) String sort,
                                          @RequestParam(name = "group", required = true) String group) {

        return ok(marksService.retrieveMarksPage(pagenum, pagesize, sort, group));
    }

    @GetMapping("/marks")
    public ResponseEntity<?> getMarksByDate(@RequestParam(name = "startDate", required = true) String startDate,
                                            @RequestParam(name = "endDate", required = true) String endDate) {

        return ok(marksService.getMarksByDateInterval(startDate, endDate));
    }

    @PostMapping("/mark")
    public ResponseEntity<?> addMark(@Valid @RequestBody SubjectMarkDTO mark) {
        return ok(marksService.addNewMark(mark));
    }

    @PutMapping("/mark/{id}")
    public ResponseEntity<?> editMark(@Valid @RequestBody SubjectMarkDTO mark, @PathVariable String id) {
        mark.setId(id);
        return ok(marksService.editMark(mark));
    }

    @DeleteMapping("/mark/{id}")
    public ResponseEntity<?> removeMark(@PathVariable String id) {
        return ok(marksService.deleteMark(id));
    }
}