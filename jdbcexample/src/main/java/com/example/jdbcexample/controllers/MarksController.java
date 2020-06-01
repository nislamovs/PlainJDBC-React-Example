package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dto.SubjectMarkDTO;
import com.example.jdbcexample.services.MarksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.lang.Long.parseLong;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
@Api(description = "The subject marks controller", name = "Marks services")
public class MarksController {

    private final MarksService marksService;

    @ApiMethod(id = "Get all subject marks")
    @GetMapping("/mark/all")
    public @ApiResponseObject
    ResponseEntity<?> getMarks() {
        return ok("dasdasasd11");
//        return ok(marksService.getAllMarks());
    }

    @ApiMethod(id = "Find subject mark by Id")
    @GetMapping(path = "/mark/{id}")
    public @ApiResponseObject
    ResponseEntity<?> getMarksById(@ApiPathParam(name = "id") @PathVariable(value="id") String pupilId) {
        return ok(marksService.retrieveMarksByPupilId(pupilId));
    }

    @ApiMethod
    @GetMapping("/mark")
    public ResponseEntity<?> getMarksPage(@RequestParam(name = "pagenum", required = true) String pagenum,
                                          @RequestParam(name = "pagesize", required = true) String pagesize,
                                          @RequestParam(name = "sort", required = true) String sort,
                                          @RequestParam(name = "group", required = true) String group) {

        return ok(marksService.retrieveMarksPage(pagenum, pagesize, sort, group));
    }

    @ApiMethod
    @GetMapping("/marks")
    public ResponseEntity<?> getMarksByDate(@RequestParam(name = "startDate", required = true) String startDate,
                                            @RequestParam(name = "endDate", required = true) String endDate) {

        return ok(marksService.getMarksByDateInterval(startDate, endDate));
    }

    @ApiMethod
    @PostMapping("/mark")
    public ResponseEntity<?> addMark(@Valid @RequestBody SubjectMarkDTO mark) {
        return ok(marksService.addNewMark(mark));
    }

    @ApiMethod
    @PutMapping("/mark/{id}")
    public ResponseEntity<?> editMark(@Valid @RequestBody SubjectMarkDTO mark, @PathVariable String id) {
        mark.setId(id);
        return ok(marksService.editMark(mark));
    }

    @ApiMethod
    @DeleteMapping("/mark/{id}")
    public ResponseEntity<?> removeMark(@PathVariable String id) {
        return ok(marksService.deleteMark(id));
    }
}