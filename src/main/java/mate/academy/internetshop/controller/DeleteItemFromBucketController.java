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

@WebServlet("/deleteFromBucket")
public class DeleteItemFromBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String bucketId = req.getParameter("bucket_id");
        String itemId = req.getParameter("item_id");
        Optional<Bucket> bucket = bucketService.get(Long.valueOf(bucketId));
        Optional<Item> item = itemService.get(Long.valueOf(itemId));
        bucketService.deleteItem(bucket.get(), item.get());
        resp.sendRedirect(req.getContextPath() + "/bucket");
    }
}
