package application.dao.impl;

import application.dao.ShoppingCartDao;
import application.db.Storage;
import application.lib.Dao;
import application.model.Product;
import application.model.ShoppingCart;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        Storage.getShoppingCarts().stream()
                .filter(cart -> cart.equals(shoppingCart))
                .forEach(cart -> cart.getProducts().add(product));
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long userId) {
        return Storage.getShoppingCarts().stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.getShoppingCarts();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(i -> Storage.shoppingCarts.get(i).getId().equals(shoppingCart.getId()))
                .forEach(i -> Storage.shoppingCarts.set(i, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return Storage.getShoppingCarts().removeIf(cart -> cart.getId()
                .equals(shoppingCart.getId()));
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        Storage.getShoppingCarts().stream()
                .filter(cart -> cart.equals(shoppingCart))
                .forEach(cart -> cart.getProducts().remove(product));
        return true;
    }
}