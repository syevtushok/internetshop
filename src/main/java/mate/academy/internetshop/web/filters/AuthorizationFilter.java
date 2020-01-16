package mate.academy.internetshop.web.filters;

import static mate.academy.internetshop.model.Role.RoleName.ADMIN;
import static mate.academy.internetshop.model.Role.RoleName.USER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@WebFilter("/servlet/*")
public class AuthorizationFilter implements Filter {
    @Inject
    private static UserService userService;

    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/servlet/allUsers", ADMIN);
        protectedUrls.put("/servlet/addItems", ADMIN);
        protectedUrls.put("/servlet/deleteItem", ADMIN);

        protectedUrls.put("/servlet/addItemToBucket", USER);
        protectedUrls.put("/servlet/bucket", USER);
        protectedUrls.put("/servlet/deleteUserOrder", USER);
        protectedUrls.put("/servlet/orders", USER);
        protectedUrls.put("/servlet/completeOrder", USER);
        protectedUrls.put("/servlet/deleteFromBucket", USER);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestedUrl = req.getServletPath();
        Role.RoleName roleNameAdmin = protectedUrls.get(requestedUrl);
        Role.RoleName roleNameUser = protectedUrls.get(requestedUrl);
        if (roleNameAdmin == null
                && roleNameUser == null) {
            processDenied(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute("userId");
        User user = userService.get(userId);
        if (verifyRole(user, roleNameAdmin)
                || verifyRole(user, roleNameUser)) {
            processAuthenticated(chain, req, resp);
        } else {
            processDenied(req, resp);
        }
    }

    @Override
    public void destroy() {

    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/denied.jsp").forward(req, resp);
    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        return user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals(roleName));
    }

    private void processAuthenticated(
            FilterChain chain, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        chain.doFilter(req, resp);
    }
}
