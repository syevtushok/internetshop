package mate.academy.internetshop.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet("/inject")
public class InjectDataController extends HttpServlet {
    private static Logger logger = LogManager.getLogger(InjectDataController.class);
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Item item = new Item("Clean Code", BigDecimal.valueOf(240));
        Item item1 = new Item("English Grammar in Use", BigDecimal.valueOf(500));
        User user = new User();
        user.setName("Guest");
        user.addRole(Role.of("USER"));
        user.setLogin("Mike");
        user.setPassword("123");
        Bucket bucket = new Bucket();
        try {
            itemService.create(item);
            itemService.create(item1);
            userService.create(user);
            bucket.setUserId(user.getId());
            bucketService.create(bucket);
        } catch (DataProcessingException e) {
            logger.error(e);
        }

        User admin = new User();
        admin.setName("God on the Earth");
        admin.addRole(Role.of("ADMIN"));
        admin.setLogin("John");
        admin.setPassword("adm");
        try {
            userService.create(admin);
        } catch (DataProcessingException e) {
            logger.error(e);
            req.setAttribute("error_msg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbError.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
