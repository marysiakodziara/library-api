package com.example.libraryapi.security;

import com.nimbusds.jose.shaded.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.Map;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class ClientResolver {

    public static String loggedUserEmailResolver() throws IOException {
        JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String accessToken = token.getToken().getTokenValue();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://library-login.us.auth0.com/userinfo")
                .get()
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        Map jsonJavaRootObject = new Gson().fromJson(responseBody, Map.class);
        return jsonJavaRootObject.get("email").toString();
    }
}