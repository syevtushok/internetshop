package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet("/allItems")
public class GetAllItemsController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(GetAllItemsController.class);
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Item> items = itemService.getAll();
            req.setAttribute("items", items);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.setAttribute("error_msg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbError.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/views/allItems.jsp").forward(req, resp);
    }
}
