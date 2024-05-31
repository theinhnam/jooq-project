package com.namndt.springbootjooqupdate.services.classes_service;

import com.namndt.springbootjooq.model.tables.pojos.Classes;
import com.namndt.springbootjooqupdate.data.mappers.classes_map.ClassesMap;
import com.namndt.springbootjooqupdate.data.requests.ClassesRequest;
import com.namndt.springbootjooqupdate.data.responses.ClassesResponse;
import com.namndt.springbootjooqupdate.repositories.classes.ClassesRepository;
import com.namndt.springbootjooqupdate.services.AbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassesService extends AbsService<ClassesRequest, ClassesResponse, Classes, Integer, ClassesRepository, ClassesMap> implements IClassesService {

    @Autowired
    public ClassesService(ClassesRepository classesRepository, ClassesMap classesMap) {
        this.repository = classesRepository;
        this.mapper = classesMap;
    }
}
