package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;

@WebServlet("/addItemInBucket")
public class AddItemInBucket extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;
    private static Long userId = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String itemId = req.getParameter("item_id");
        Bucket bucket = bucketService.getAll().stream()
                .filter(bucket1 -> bucket1.getUserId().equals(userId))
                .findFirst()
                .orElse(bucketService.create(new Bucket(userId)));
        Optional<Item> item = itemService.get(Long.valueOf(itemId));
        bucketService.addItem(bucket, item);
        resp.sendRedirect(req.getContextPath() + "/allItems");
    }
}
