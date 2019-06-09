//package com.baeldung.config;
//
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class MyCorsFilter implements Filter {
//
//    // This is to be replaced with a list of domains allowed to access the server
//    //You can include more than one origin here
//    private final List<String> allowedOrigins = Arrays.asList("http://localhost:8080");
//
//
//    public void destroy() {
//
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        // Lets make sure that we are working with HTTP (that is, against HttpServletRequest and HttpServletResponse objects)
//        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
//            HttpServletRequest request = (HttpServletRequest) req;
//            HttpServletResponse response = (HttpServletResponse) res;
//
//            // Access-Control-Allow-Origin
//            String origin = request.getHeader("Origin");
//           // response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
//            response.setHeader("Vary", "Origin");
//
//            // Access-Control-Max-Age
//
//            response.setHeader("Access-Control-Max-Age", "3600");
//
//            // Access-Control-Allow-Credentials
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//
//            // Access-Control-Allow-Methods
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//
//            // Access-Control-Allow-Headers
////            response.setHeader("Access-Control-Allow-Headers",
////                    "Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
//
//           // response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT");
//            response.setHeader("Access-Control-Allow-Headers",
//                    "Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, Access-Control-Request-Method, Access-Control-Request-Headers, Content-Type, Authorization, " + "X-CSRF-TOKEN");
////            if (request.getMethod().equals("OPTIONS")) {
////                response.setStatus(HttpServletResponse.SC_ACCEPTED);
////                return;
////            }
//        }
//
//
//        chain.doFilter(req, res);
//    }
//
//    public void init(FilterConfig filterConfig) {
//    }
//}