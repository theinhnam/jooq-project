package com.namndt.springbootjooqupdate.services;

import com.namndt.springbootjooq.model.tables.pojos.Classes;
import com.namndt.springbootjooqupdate.data.mappers.BaseMapper;
import com.namndt.springbootjooqupdate.data.models.Pageable;
import com.namndt.springbootjooqupdate.data.responses.DFResponse;
import com.namndt.springbootjooqupdate.repositories.AbsBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;

public abstract class AbsService<Rq, Rp, Po, Id, Repo extends AbsBaseRepository<Po, Id, ?>, Map extends BaseMapper<Rq, Rp, Po>>
        implements BaseService<Rq, Rp, Po, Id>{
    protected Repo repository;

    protected Map mapper;

    public DFResponse<Object> select(Pageable pageable, Authentication authentication){
        if (!checkPermissionAdmin(authentication)){
            return new DFResponse<>("OK", "You don't have permission to access !", null);
        }
        return new DFResponse<>("OK", "Get data successful", mapper.toResponses(repository.select(pageable)));
    }

    private boolean checkPermissionAdmin(Authentication authentication) {
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) authentication.getAuthorities();

        for (GrantedAuthority grantedAuthority: grantedAuthorities) {
            if ("ROLE_ADMIN".equals(grantedAuthority.getAuthority())){
                return true;
            }
        }
        return false;
    }

    @Override
    public DFResponse<Object> insert(Rq data, Authentication authentication) {
        if (!checkPermissionAdmin(authentication)){
            return new DFResponse<>("OK", "You don't have permission to access !", null);
        }
        return new DFResponse<>("OK", "Create successful", mapper.toResponse(repository.insertReturning(mapper.toPojo(data))));
    }

    @Override
    public DFResponse<Object> update(Rq data, Id id, Authentication authentication) {
        if (!checkPermissionAdmin(authentication)){
            return new DFResponse<>("OK", "You don't have permission to access !", null);
        }
        repository.update(mapper.toPojo(data), id);
        return new DFResponse<>("OK", "Update succesful", mapper.toResponse(repository.findById(id).get()));
    }

    @Override
    public DFResponse<Object> delete(Id id, Authentication authentication) {
        if (!checkPermissionAdmin(authentication)){
            return new DFResponse<>("OK", "You don't have permission to access !", null);
        }

        Classes classesDelete = (Classes) repository.findById(id).orElse(null);

        if (classesDelete == null) {
            return new DFResponse<>("OK", "Classes not found", null);
        }

        return new DFResponse<>("OK", "Delete succesful", null);
    }
}
