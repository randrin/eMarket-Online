package com.eMarket.online.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("************************* Logic Authorization *************************");
		
		/*
		 * To be Completed
		 */
		
		String jwt = request.getHeader("Authorization");
		if (jwt == null) throw new RuntimeException("User not authorized.");
				
		filterChain.doFilter(request, response);
		
	}

}
