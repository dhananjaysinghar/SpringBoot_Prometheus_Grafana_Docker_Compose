package com.ps.util;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.ps.model.AuthToken;

@Slf4j
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	public JwtAuthenticationTokenFilter(RequestMatcher requestMatcher) {
		super(requestMatcher);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
		try{
			String authenticationToken= StringUtils.isNotEmpty(httpServletRequest.getHeader(AUTHORIZATION))
					? httpServletRequest.getHeader(AUTHORIZATION) : "";
					
			if (authenticationToken == null || !authenticationToken.startsWith(JwtUtil.BEARER_TOKEN)) {
				log.error("No JWT token found in request headers");
				throw new RuntimeException("No JWT token found in request headers");
			}
			authenticationToken= StringUtils.removeStart(authenticationToken, JwtUtil.BEARER_TOKEN).trim();
			AuthToken token = new AuthToken(authenticationToken);
			return getAuthenticationManager().authenticate(token);
		}catch (RuntimeException e){
			log.error("error occurred during authentication : {}", e.getMessage());
			httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
}
