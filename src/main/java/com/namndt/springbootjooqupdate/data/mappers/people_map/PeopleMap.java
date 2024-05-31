package com.namndt.springbootjooqupdate.data.mappers.people_map;

import com.namndt.springbootjooq.model.tables.pojos.People;
import com.namndt.springbootjooqupdate.data.dto.UserDTO;
import com.namndt.springbootjooqupdate.data.mappers.BaseMapper;
import com.namndt.springbootjooqupdate.data.requests.PeopleRequest;
import com.namndt.springbootjooqupdate.data.responses.PeopleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PeopleMap extends BaseMapper<PeopleRequest, PeopleResponse, People> {

    public abstract UserDTO toUserDTO(People people);

}
