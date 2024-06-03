package com.namndt.springbootjooqupdate.services;

import com.namndt.springbootjooq.model.tables.pojos.Classes;
import com.namndt.springbootjooqupdate.data.exception.AccessDeniedException;
import com.namndt.springbootjooqupdate.data.exception.NotFoundException;
import com.namndt.springbootjooqupdate.data.mappers.BaseMapper;
import com.namndt.springbootjooqupdate.data.models.Pageable;
import com.namndt.springbootjooqupdate.data.responses.DFResponse;
import com.namndt.springbootjooqupdate.repositories.AbsBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;
import java.util.Optional;

public abstract class AbsService<Rq, Rp, Po, Id, Repo extends AbsBaseRepository<Po, Id, ?>, Map extends BaseMapper<Rq, Rp, Po>>
        implements BaseService<Rq, Rp, Po, Id>{
    protected Repo repository;

    protected Map mapper;

    public List<Rp> select(Pageable pageable, Authentication authentication){
        if (!checkPermissionAdmin(authentication)){
            throw new AccessDeniedException();
        }
        return mapper.toResponses(repository.select(pageable));
    }

    private boolean checkPermissionAdmin(Authentication authentication) {
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) authentication.getAuthorities();

        for (GrantedAuthority grantedAuthority: grantedAuthorities) {
            if ("ROLE_ADMIN".equals(grantedAuthority.getAuthority())){
                System.out.println("true");
                return true;
            }
        }
        return false;
    }

    @Override
    public Rp insert(Rq data, Authentication authentication) {
        if (!checkPermissionAdmin(authentication)){
            throw new AccessDeniedException();
        }
        return mapper.toResponse(repository.insertReturning(mapper.toPojo(data)));
    }

    @Override
    public int update(Rq data, Id id, Authentication authentication) {
        if (!checkPermissionAdmin(authentication)){
            throw new AccessDeniedException();
        }
        return repository.update(mapper.toPojo(data), id);
    }

    @Override
    public int delete(Id id, Authentication authentication) {
        if (!checkPermissionAdmin(authentication)){
            throw new AccessDeniedException();
        }

        Optional<Po> pojo = repository.findById(id);

        if (!pojo.isPresent()) {
            throw new NotFoundException();
        }

        return repository.deleteById(id);
    }
}
