package com.example.fitwithme.jwt;

import com.example.fitwithme.common.enums.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {

        String accessToken = request.getHeader(TokenType.ACCESS_TOKEN.toString());
        String refreshToken = request.getHeader(TokenType.REFRESH_TOKEN.toString());

        if (accessToken != null) {
            if (jwtUtil.validateToken(accessToken)) {
                return true;
            }
        } else {
            if (refreshToken != null && jwtUtil.validateToken(refreshToken)) {
                String newAccessToken = jwtUtil.refreshAccessToken(refreshToken);
                if (newAccessToken != null) {
                    response.setHeader(TokenType.ACCESS_TOKEN.toString(), newAccessToken);
                    return true;
                }
            }
        }

        response.setStatus(401);
        response.setHeader(TokenType.ACCESS_TOKEN.toString(), accessToken);
        response.setHeader(TokenType.REFRESH_TOKEN.toString(), refreshToken);
        return false;
    }

}
