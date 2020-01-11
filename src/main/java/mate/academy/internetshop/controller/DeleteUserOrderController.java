package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.OrderService;

@WebServlet("/deleteUserOrder")
public class DeleteUserOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String orderId = req.getParameter("order_id");
        Order order = orderService.get(Long.valueOf(orderId));
        orderService.delete(order);

        resp.sendRedirect(req.getContextPath() + "/orders?user_id=" + order.getUserId());
    }
}
