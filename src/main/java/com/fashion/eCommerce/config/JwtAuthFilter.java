package com.fashion.eCommerce.config;

import com.fashion.eCommerce.model.User;
import com.fashion.eCommerce.repository.UserRepository;
import com.fashion.eCommerce.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthFilter extends OncePerRequestFilter {
        private final JwtUtil jwtUtil;
        private final UserRepository userRepo;

        public JwtAuthFilter(JwtUtil jwtUtil, UserRepository userRepo) {
            this.jwtUtil = jwtUtil;
            this.userRepo = userRepo;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain)
                throws ServletException, IOException {
            String authHeader = request.getHeader("Authorization");
            System.out.println("Request URI: " + request.getRequestURI());
//            System.out.println("Authorization Header: " + request.getHeader("Authorization"));
            String token = null;
            String email = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                email = jwtUtil.extractUsername(token);
            }

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtUtil.validateToken(token)) {
                    User user = userRepo.findByEmail(email).orElse(null);
                    if (user != null) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
        }
    }


