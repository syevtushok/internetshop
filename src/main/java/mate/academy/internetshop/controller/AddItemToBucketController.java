package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;

@WebServlet("/addItemToBucket")
public class AddItemToBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;
    private static Long userId = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String itemId = req.getParameter("item_id");
        Bucket bucket = bucketService.getByUserId(userId);
        Item item = itemService.get(Long.valueOf(itemId));
        bucketService.addItem(bucket, item);
        resp.sendRedirect(req.getContextPath() + "/allItems");
    }
}
