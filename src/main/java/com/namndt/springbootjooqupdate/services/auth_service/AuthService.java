package com.namndt.springbootjooqupdate.services.auth_service;

import com.namndt.springbootjooq.model.tables.pojos.People;
import com.namndt.springbootjooqupdate.config.authConfig.JwtUtils;
import com.namndt.springbootjooqupdate.data.dto.UserDTO;
import com.namndt.springbootjooqupdate.data.requests.AuthRequest;
import com.namndt.springbootjooqupdate.repositories.people.PeopleRepository;
import com.namndt.springbootjooqupdate.repositories.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements IAuthService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public String authenticate(AuthRequest authRequest) {
        People people = peopleRepository.getPeopleByUser(authRequest.getUsername());

        if (people == null){
            return null;
        }

        if(!passwordEncoder.matches(authRequest.getPassword(), people.getPassword())){
            return null;
        }

        List<String> roles = roleRepository.getRoleNameByUserId(people.getId());
        UserDTO userDTO = new UserDTO(people.getUsername(), roles);

        return jwtUtils.generateToken(userDTO);
    }
}
