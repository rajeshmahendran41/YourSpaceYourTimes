package com.ysyt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ysyt.service.IAuthService;

@RestController(value="api/auth")
public class AuthController {
	
	private IAuthService iAuthService;

}
