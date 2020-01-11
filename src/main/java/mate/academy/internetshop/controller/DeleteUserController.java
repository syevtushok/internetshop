package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.UserService;

@WebServlet("/deleteUser")
public class DeleteUserController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("user_id");
        userService.deleteById(Long.valueOf(userId));
        resp.sendRedirect(req.getContextPath() + "/allUsers");
    }
}
