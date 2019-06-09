package com.baeldung.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.util.UriComponentsBuilder;

class Oauth2ClientContextFilterWithPath extends OAuth2ClientContextFilter {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void redirectUser(UserRedirectRequiredException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("request url: " + request.getRequestURL());
        final String redirectUri = e.getRedirectUri();
        System.out.println("redirect uri: " + redirectUri);
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(redirectUri);
        final Map<String, String> requestParams = e.getRequestParams();
        for (Map.Entry<String, String> param : requestParams.entrySet()) {
            builder.queryParam(param.getKey(), param.getValue());
        }

        if (e.getStateKey() != null) {
            builder.queryParam("state", e.getStateKey());
        }

        String url = getBaseUrl(request) + builder.build().encode().toUriString();
        System.out.println("url: " + url);
        //this.redirectStrategy.sendRedirect();
        System.out.println("request: " + request.getRequestURL());
        //System.out.println("response: " + response.toString());
        this.redirectStrategy.sendRedirect(request, response, url);
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    private String getBaseUrl(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        return url.substring(0, url.length() - request.getRequestURI().length() + request.getContextPath().length());
    }
}
