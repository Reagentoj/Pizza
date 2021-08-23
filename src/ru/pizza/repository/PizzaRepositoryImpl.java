package ru.pizza.repository;

import ru.pizza.models.Order;
import ru.pizza.models.Pizza;
import ru.pizza.models.Seller;
import ru.pizza.repository.data.PizzaDataSource;

import java.util.*;

public class PizzaRepositoryImpl implements PizzaRepository {
    private final PizzaDataSource dataSource;

    public PizzaRepositoryImpl(PizzaDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /** Получить стоимость ингредиентов в заказе
     * @return стоимость ингредиентов в заказе
     * */
    // Проблема: включает в цену стоимость стандартных ингредиентов--------------------
    // Нужно отнять цену стандартных ингредиентов--------------------------------------
    @Override
    public double getIngredientsCostInOrder(long id) {
        List<Order> orders = dataSource.getOrders();
        double getIngredientsCost = 0.0;
        if (!dataSource.getOrders().isEmpty()) {
            for (Order order : orders) {
                List<Pizza> orderPizzas = order.getPizzas();
                for (Pizza pizza : orderPizzas) {
                    getIngredientsCost = pizza.getIngredientCost();
                }
            }
        }

        return getIngredientsCost;
    }

    /** получить количество проданных пицц
     * @return количество пицц в заказе*/
    @Override
    public int getCountSalesOfPizza() {
        List<Order> orders = dataSource.getOrders();
        int countOfPizzas = 0;
        if (!dataSource.getOrders().isEmpty()) {
            for (Order order : orders) {
                List<Pizza> pizzas = order.getPizzas();
                countOfPizzas = pizzas.size();
            }
        }
        return countOfPizzas;
    }

    /**  Получить продавца с наибольшим количеством продаж */
    @Override
    public Seller getSellerWithMostSells() {
        List<Seller> sellers = dataSource.getSellers();
        Map<Long, Integer> countOfSales = new HashMap<Long, Integer>();
        int countOfSell = 0;
        long sellerId = 0;
        Seller sellerNameWithMostSells;

        if (!dataSource.getOrders().isEmpty()){
            for (Seller seller : sellers) {
                for (Order order : dataSource.getOrders()) {
                    if (seller.getId() == order.getSellerId()) {
                        countOfSell++;
                    }
                    countOfSales.put(seller.getId(), countOfSell);
                }
                countOfSell = 0;
            }
        }

        return null;
    }

    /** Получить старшего продавца */
    @Override
    public Seller getOldestSeller() {
        return null;
    }

    /** Получить заказ в котором более двух пицц */
    @Override
    public Order getOrderWithMoreThanTwoPizzas() {
        return null;
    }
}
