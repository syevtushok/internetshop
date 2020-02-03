package mate.academy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet("/servlet/deleteUserOrder")
public class DeleteUserOrderController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserOrderController.class);
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try {
            String orderId = req.getParameter("order_id");
            Order order = orderService.get(Long.valueOf(orderId));
            orderService.delete(order);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.setAttribute("error_msg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbError.jsp").forward(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/servlet/orders");
    }
}
