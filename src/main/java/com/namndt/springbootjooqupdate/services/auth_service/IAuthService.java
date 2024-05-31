package com.namndt.springbootjooqupdate.services.auth_service;

import com.namndt.springbootjooqupdate.data.requests.AuthRequest;

public interface IAuthService {

    public String authenticate(AuthRequest authRequest);

}
