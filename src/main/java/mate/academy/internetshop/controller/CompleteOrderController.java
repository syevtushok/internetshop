package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

@WebServlet("/completeOrder")
public class CompleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bucketId = req.getParameter("bucket_id");
        Bucket bucket = bucketService.get(Long.valueOf(bucketId));
        List<Item> items = bucket.getItems();
        User user = userService.get(bucket.getUserId());
        orderService.completeOrder(items, user);
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}
