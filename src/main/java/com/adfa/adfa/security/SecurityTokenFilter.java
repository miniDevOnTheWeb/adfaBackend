package com.adfa.adfa.security;

import com.adfa.adfa.service.JwtService;
import com.adfa.adfa.service.SecurityUserDetails;
import com.adfa.adfa.web.exceptions.JwtAuthenticationEntryPoint;
import com.adfa.adfa.web.exceptions.JwtTokenAuthentication;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityTokenFilter extends OncePerRequestFilter {

    private final SecurityUserDetails securityUserDetails;
    private final JwtService jwtService;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityTokenFilter(
            SecurityUserDetails securityUserDetails,
            JwtService jwtService, JwtAuthenticationEntryPoint authenticationEntryPoint
    ) {
        this.securityUserDetails = securityUserDetails;
        this.jwtService = jwtService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            String username = jwtService.extractUsername(token);

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        securityUserDetails.loadUserByUsername(username);

                if (!jwtService.isTokenValid(token, userDetails)) {
                    throw new JwtTokenAuthentication("Token inválido o expirado");
                }

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                filterChain.doFilter(request, response);
            }

        } catch (SignatureException ex) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, new JwtTokenAuthentication("Error de token"));
        } catch (ExpiredJwtException ex) {
            SecurityContextHolder.clearContext();
            throw new JwtTokenAuthentication("Token expirado");

        } catch (AuthenticationException ex) {
            SecurityContextHolder.clearContext();
            throw ex;
        }
    }
}
