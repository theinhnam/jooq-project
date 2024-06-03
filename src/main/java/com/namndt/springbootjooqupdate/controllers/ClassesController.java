package com.namndt.springbootjooqupdate.controllers;

import com.namndt.springbootjooqupdate.data.models.Pageable;
import com.namndt.springbootjooqupdate.data.requests.ClassesRequest;
import com.namndt.springbootjooqupdate.data.responses.ClassesResponse;
import com.namndt.springbootjooqupdate.data.responses.DFResponse;
import com.namndt.springbootjooqupdate.services.classes_service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
public class ClassesController {

    ClassesService classesService;

    @Autowired
    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping("/cms/classes")
    public ResponseEntity<DFResponse<Object>> select(@RequestBody Pageable pageable, Authentication authentication){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DFResponse<>("OK", "Get data successful", classesService.select(pageable, authentication)));
    }

    @PostMapping("/cms/classes")
    public ResponseEntity<DFResponse<Object>> insert(@RequestBody ClassesRequest classesRequest, Authentication authentication){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DFResponse<>("OK", "Insert data successful", classesService.insert(classesRequest, authentication)));
    }

    @PutMapping("/cms/classes/{id}")
    public ResponseEntity<DFResponse<Object>> update(@RequestBody ClassesRequest classesRequest, @PathVariable("id") int id, Authentication authentication){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DFResponse<>("OK", "Update data successful", classesService.update(classesRequest, id, authentication)));

    }

    @DeleteMapping("/cms/classes/{id}")
    public ResponseEntity<DFResponse<Object>> delete(@PathVariable("id") int id, Authentication authentication){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DFResponse<>("OK", "Delete data successful", classesService.delete(id, authentication)));
    }
}
