package application.controller.cart;

import application.controller.user.LoginController;
import application.lib.Injector;
import application.model.ShoppingCart;
import application.service.ProductService;
import application.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cart/add-product")
public class AddProductToCartController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("application");
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        Long productId = Long.parseLong(req.getParameter("id"));
        ShoppingCart cart = shoppingCartService.getByUserId(id);
        shoppingCartService.addProduct(cart, productService.get(productId));
        resp.sendRedirect(req.getContentType() + "/product/all");
    }
}
