package com.jean.agendaprof.core.service.token.providers.jjwt;

import com.jean.agendaprof.core.models.TokenInvalido;
import com.jean.agendaprof.core.repositories.TokenInvalidoRepository;
import com.jean.agendaprof.core.service.token.TokenService;
import com.jean.agendaprof.core.service.token.TokenServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class JjwtTokenService implements TokenService {

    private final JjwtConfigProperties jjwtConfigProperties;
    private final TokenInvalidoRepository tokenInvalidoRepository;

    @Override
    public String gerarAccessToken(String subject) {
        return gerarToken(subject, jjwtConfigProperties.getAccessTokenExpirationInSeconds(), jjwtConfigProperties.getAccessTokenSigingKey());
    }


    @Override
    public String getSubjectdoToken(String accessToken) {
        return getSubject(accessToken, jjwtConfigProperties.getAccessTokenSigingKey()).getSubject();
    }

    @Override
    public String gerarRefreshToken(String subject) {
        return gerarToken(subject, jjwtConfigProperties.getRefreshTokenExpirationInSeconds(), jjwtConfigProperties.getRefreshTokenSigingKey());
    }

    @Override
    public String getSubjectDoRefreshtoken(String refreshToken) {
        return getSubject(refreshToken, jjwtConfigProperties.getRefreshTokenSigingKey()).getSubject();
    }

    @Override
    public void invalidarTokens(String... tokens) {
        var tokenInvalidos = Stream.of(tokens)
                .map(s -> TokenInvalido.builder().token(s).build()).toList();
        tokenInvalidoRepository.saveAll(tokenInvalidos);
    }

    private String gerarToken(String subject, Long expirationInSeconds, String sigingKey) {
        var dataHoraAtual = Instant.now();
        var dataHoraExpiration = dataHoraAtual.plusSeconds(expirationInSeconds);


        return Jwts.builder()
                .setClaims(new HashMap<String, Object>())
                .setSubject(subject)
                .setIssuedAt(Date.from(dataHoraAtual))
                .setExpiration(Date.from(dataHoraExpiration))
                .signWith(Keys.hmacShaKeyFor(sigingKey.getBytes()))
                .compact();
    }

    private Claims getSubject(String token, String sigingKey) {
        try {
            return tryGetClaims(token, sigingKey);
        }catch (JwtException e){
            throw new TokenServiceException(e.getLocalizedMessage());
        }
    }

    private Claims tryGetClaims(String token, String sigingKey) {
        if (tokenInvalidoRepository.existsByToken(token)){
            throw new TokenServiceException("Token invalido");
        }
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(sigingKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
