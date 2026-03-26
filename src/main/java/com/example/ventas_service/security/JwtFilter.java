package com.example.ventas_service.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private String SECRET = "clave123";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // 🔓 Permitir login sin token
        if (path.equals("/auth/login")) {
            chain.doFilter(request, response);
            return;
        }

        System.out.println("FILTRO ACTIVO");

        String header = request.getHeader("Authorization");

        // 🔴 CAMBIO 1 (VALIDACIÓN MÁS ESTRICTA)
        if(header == null || !header.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("No autorizado"); // 👈 agregado
            return;
        }

        String token = header.substring(7);

        try{
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
        }catch(Exception e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Token inválido");
            return;
        }

        chain.doFilter(request, response);
    }
}