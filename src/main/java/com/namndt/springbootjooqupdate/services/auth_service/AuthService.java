package com.namndt.springbootjooqupdate.services.auth_service;

import com.namndt.springbootjooq.model.tables.pojos.People;
import com.namndt.springbootjooqupdate.config.authConfig.JwtUtils;
import com.namndt.springbootjooqupdate.data.dto.UserDTO;
import com.namndt.springbootjooqupdate.data.exception.AccountNotFoundException;
import com.namndt.springbootjooqupdate.data.exception.PasswordIncorrectException;
import com.namndt.springbootjooqupdate.data.mappers.people_map.PeopleMap;
import com.namndt.springbootjooqupdate.data.requests.AuthRequest;
import com.namndt.springbootjooqupdate.repositories.people.PeopleRepository;
import com.namndt.springbootjooqupdate.repositories.role.RoleRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

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

    @Autowired
    PeopleMap peopleMap;

    @Override
    public String authenticate(AuthRequest authRequest) {
        Optional<People> people = peopleRepository.getPeopleByUser(authRequest.getUsername());

        UserDTO userDTO = new UserDTO();

        if (checkAccountExists(people) == true && checkPasswordMatch(authRequest.getPassword(), people.get().getPassword()) == true) {
            List<String> roles = roleRepository.getRoleNameByUserId(people.get().getId());
            userDTO.setData(people.get());
            userDTO.setRoles(roles);
        }
        return jwtUtils.generateToken(userDTO);
    }

    public boolean checkPasswordMatch(String inputPassword, String truePassword) {
        if (passwordEncoder.matches(inputPassword, truePassword)) {
            return true;
        } else {
            throw new PasswordIncorrectException();
        }
    }

    public boolean checkAccountExists(Optional<People> people) {
        if (!people.isPresent()) {
            throw new AccountNotFoundException("Account not exists");
        } else {
            return true;
        }
    }
}
