package com.ps.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ps.model.Response;

@ControllerAdvice
public class ControllerAdviceImpl {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleAuthorize(AccessDeniedException e) {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(new Response( authorities+" has not authorization to access this operation , please send ADMIN jwt token in header"));

	}
}
