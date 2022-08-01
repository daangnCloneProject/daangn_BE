package com.example.daangn.security;

import com.example.daangn.model.User;
import com.example.daangn.security.jwt.JwtTokenUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
        // Token 생성
        final String token = JwtTokenUtils.generateJwtToken(userDetails);
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);

        //body로도 토큰값 보내기
        response.setContentType("application/json;charset=UTF-8");

        User user = userDetails.getUser();
        JSONObject responseJson = new JSONObject();
        responseJson.put("response", true);
        responseJson.put("token", TOKEN_TYPE + " " + token);
        responseJson.put("userId", user.getId());
        responseJson.put("username", user.getUsername());
        responseJson.put("nickname", user.getNickname());
        response.getWriter().print(responseJson);
    }

}
