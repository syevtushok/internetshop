package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.BucketService;

@WebServlet("/bucket")
public class BucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    private static Long userId = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = bucketService.getAll()
                .stream()
                .filter(b -> b.getUserId().equals(userId))
                .findFirst()
                .orElse(bucketService.create(new Bucket(userId)));

        req.setAttribute("bucket", bucket);

        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
