package com.namndt.springbootjooqupdate.config.authConfig;


import com.namndt.springbootjooq.model.tables.pojos.People;
import com.namndt.springbootjooqupdate.data.dto.UserDTO;
import com.namndt.springbootjooqupdate.data.mappers.people_map.PeopleMap;
import com.namndt.springbootjooqupdate.repositories.people.PeopleRepository;
import com.namndt.springbootjooqupdate.repositories.role.RoleRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    PeopleMap peopleMap;

    @Autowired
    RoleRepository roleRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (StringUtils.hasText(token) && jwtUtils.validateToken(token)){
            String user = jwtUtils.getUsernameFromToken(token);

            UserDTO userDetails = peopleMap.toUserDTO(peopleRepository.getPeopleByUser(user));

            if (userDetails != null){
                userDetails.setRoles(roleRepository.getRoleNameByUserId(userDetails.getId()));

                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, getGrantedAuthorities(userDetails.getRoles()));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

    public String getTokenFromRequest(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        if (StringUtils.hasText(token) && token.startsWith("Bearer")){
            return token.substring(7);
        }else{
            return null;
        }
    }

    public List<GrantedAuthority> getGrantedAuthorities(List<String> role){
        return role.stream()
                .map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName.toUpperCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
}
