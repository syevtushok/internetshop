package mate.academy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet("/inject")
public class InjectDataController extends HttpServlet {
    private static Logger logger = LogManager.getLogger(InjectDataController.class);
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        user.setName("Guest");
        user.addRole(Role.of("USER"));
        user.setLogin("user");
        user.setPassword("123");
        try {
            userService.create(user);
        } catch (DataProcessingException e) {
            logger.error(e);
        }

        User admin = new User();
        admin.setName("God on the Earth");
        admin.addRole(Role.of("ADMIN"));
        admin.setLogin("ad");
        admin.setPassword("ad");
        try {
            userService.create(admin);
        } catch (DataProcessingException e) {
            logger.error(e);
            req.setAttribute("error_msg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbError.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
