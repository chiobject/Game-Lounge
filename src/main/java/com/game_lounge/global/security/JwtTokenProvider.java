package com.game_lounge.global.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long expiration;

    public JwtTokenProvider(
            @Value("${spring.jwt.secret}") String secret,
            @Value("${spring.jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    // 1. 토큰 생성
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(username)          // 토큰에 담을 정보 (username)
                .issuedAt(now)              // 발급 시각
                .expiration(expiryDate)     // 만료 시각
                .signWith(secretKey)        // 서명
                .compact();                 // 문자열로 변환
    }

    // 2. 토큰에서 username 추출
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)      // 서명 검증용 키 설정
                .build()
                .parseSignedClaims(token)   // 토큰 파싱 + 검증
                .getPayload()               // 페이로드(내용) 꺼내기
                .getSubject();              // subject(username) 반환
    }

    // 3. 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            // 만료, 위조, 형식 오류 등 모든 실패 → false
            return false;
        }
    }
}
