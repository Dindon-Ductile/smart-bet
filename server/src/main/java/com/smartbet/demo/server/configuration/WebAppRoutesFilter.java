package com.smartbet.demo.server.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class WebAppRoutesFilter extends OncePerRequestFilter {
    private static final Pattern WEB_APP_ROUTES_PATTERN = Pattern.compile("^/api/.*");
    private static final String WEB_APP_PATH = "/";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isWebApplicationRoute(request.getRequestURI())) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(WEB_APP_PATH);
            requestDispatcher.forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean isWebApplicationRoute(String path) {
        return !WEB_APP_ROUTES_PATTERN.matcher(path).matches();
    }
}
