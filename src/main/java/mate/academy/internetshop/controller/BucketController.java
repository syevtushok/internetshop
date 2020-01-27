package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.BucketService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet("/servlet/bucket")
public class BucketController extends HttpServlet {
    private static Logger logger = LogManager.getLogger(BucketController.class);

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Bucket bucket = null;
        try {
            bucket = bucketService.getByUserId(userId);
        } catch (DataProcessingException e) {
            logger.error(e);
            req.setAttribute("error_msg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dbError.jsp").forward(req, resp);
        }
        req.setAttribute("bucket", bucket);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
