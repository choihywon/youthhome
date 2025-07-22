package io.youth.home.domain.auth.jwt;


import io.jsonwebtoken.ExpiredJwtException;
import io.youth.home.domain.auth.dto.CustomUserDetails;
import io.youth.home.domain.auth.entity.TokenType;
import io.youth.home.domain.user.entity.User;
import io.youth.home.domain.user.repository.UserRepository;
import io.youth.home.global.ErrorCode;
import io.youth.home.global.UserException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    public JwtFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("JWT URI : " + request.getRequestURI());
        String accessToken = request.getHeader(TokenType.ACCESS.getValue());
        if (accessToken == null) {
            log.info("token is null");
            filterChain.doFilter(request, response);
            return;
        }
        log.info("accessToken : " + accessToken);
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            log.info("token is expired");

            PrintWriter writer = response.getWriter();
            writer.print("token is expired");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String category = jwtUtil.getCategory(accessToken);
        if (!category.equals(TokenType.ACCESS.getValue())) {

            log.info("token is not access");

            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String email = jwtUtil.getEmail(accessToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        log.info("user : " + user);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}