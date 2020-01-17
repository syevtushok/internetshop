package mate.academy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@WebServlet("/inject")
public class InjectDataController extends HttpServlet {
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
        userService.create(user);

        User admin = new User();
        admin.setName("God on the Earth");
        admin.addRole(Role.of("ADMIN"));
        admin.setLogin("ad");
        admin.setPassword("ad");
        userService.create(admin);

        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
