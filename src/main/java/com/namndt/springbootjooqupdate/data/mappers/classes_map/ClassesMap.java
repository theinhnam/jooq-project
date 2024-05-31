package com.namndt.springbootjooqupdate.data.mappers.classes_map;

import com.namndt.springbootjooq.model.tables.pojos.Classes;
import com.namndt.springbootjooqupdate.data.mappers.BaseMapper;
import com.namndt.springbootjooqupdate.data.requests.ClassesRequest;
import com.namndt.springbootjooqupdate.data.responses.ClassesResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.Authentication;

@Mapper(componentModel = "spring")
public abstract class ClassesMap extends BaseMapper<ClassesRequest, ClassesResponse, Classes> {

    @AfterMapping
    public void afterMapToPojo(ClassesRequest classesRequest, @MappingTarget Classes classes){
        classes.setCreateBy("DBA");
    }

}
