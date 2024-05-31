package com.namndt.springbootjooqupdate.data.mappers;

import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.List;

public abstract class BaseMapper <Rq, Rp, Po>{

    @Named("toResponse")
    public abstract Rp toResponse(Po data);

    @IterableMapping(qualifiedByName = "toResponse")
    public abstract List<Rp> toResponses(List<Po> data);

    public abstract Po toPojo(Rq data);

}
