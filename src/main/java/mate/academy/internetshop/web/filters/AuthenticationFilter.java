package mate.academy.internetshop.web.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.controller.DeleteItemFromBucketController;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {
    private static Logger logger = LogManager.getLogger(DeleteItemFromBucketController.class);

    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getCookies() == null) {
            processUnAuthenticated(req, resp);
            return;
        }
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("MATE")) {
                try {
                    Optional<User> user = userService.getByToken(cookie.getValue());
                    if (user.isPresent()) {
                        chain.doFilter(request, response);
                        return;
                    }
                } catch (DataProcessingException e) {
                    logger.error(e);
                }
            }
        }
        processUnAuthenticated(req, resp);
    }

    @Override
    public void destroy() {

    }

    private void processUnAuthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");

    }
}
