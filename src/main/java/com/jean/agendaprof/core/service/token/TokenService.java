package com.jean.agendaprof.core.service.token;

public interface TokenService {

    String gerarAccessToken(String subject);
    String getSubjectdoToken(String accessToken);
    String gerarRefreshToken(String subject);
    String getSubjectDoRefreshtoken(String refreshToken);
    void invalidarTokens(String... tokens);
}
