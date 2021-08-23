package ru.pizza.ui;

import ru.pizza.models.*;
import ru.pizza.models.pizzas.Margarita;
import ru.pizza.repository.PizzaRepositoryImpl;
import ru.pizza.repository.data.PizzaDataSource;

import java.security.Key;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        double getIngredientsCost = 0.0;
        List<Ingredient> ingredients = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        orders.add(new Order(11, 12,
                Arrays.asList(PizzaMaker.createDefaultCarbonara(Dough.Thick, Sauce.Cheese,
                        Arrays.asList(Ingredient.Mozzarella, Ingredient.Capers)),
                        PizzaMaker.createDefaultPepperoni(Dough.Thick, Sauce.Cheese, null))));
        orders.add(new Order(12, 13, Arrays.asList(PizzaMaker.createDefaultPepperoni(Dough.Thick,Sauce.Cheese, null))));
        orders.add(new Order(13, 12, Arrays.asList(PizzaMaker.createDefaultPepperoni(Dough.Thick,Sauce.Cheese, null))));
        orders.add(new Order(14, 12, Arrays.asList(PizzaMaker.createDefaultPepperoni(Dough.Thick,Sauce.Cheese, null))));
        orders.add(new Order(15, 12, Arrays.asList(PizzaMaker.createDefaultPepperoni(Dough.Thick,Sauce.Cheese, null))));
        orders.add(new Order(16, 14, Arrays.asList(PizzaMaker.createDefaultPepperoni(Dough.Thick,Sauce.Cheese, null))));
        orders.add(new Order(17, 14, Arrays.asList(PizzaMaker.createDefaultPepperoni(Dough.Thick,Sauce.Cheese, null))));

        for (Order order : orders) {
            List<Pizza> orderPizzas = order.getPizzas();
            for (Pizza pizza : orderPizzas) {
                getIngredientsCost = pizza.getIngredientCost();
                for (Ingredient ingredient : pizza.getIngredients()) {
                    ingredients.add(ingredient);
                }
            }
        }

        System.out.println(getIngredientsCost);
        System.out.println(ingredients);


        int countOfPizzas = 0;
        for (Order order : orders) {
            List<Pizza> pizzas = order.getPizzas();
            countOfPizzas = pizzas.size();
        }
        System.out.println(countOfPizzas);



        List<Seller> sellers = new ArrayList<>();
        sellers.add(new Seller(12, "Oleg", 24));
        sellers.add(new Seller(13, "Kostya", 25));
        sellers.add(new Seller(14, "Lena", 25));
        int countOfSell = 0;
        long bestSellerId = 0;
        Map<Long, Integer> countOfSales = new HashMap<Long, Integer>();
        String bestSellerName = null;
        for (Seller seller : sellers) {
            for (Order order : orders) {
                if (seller.getId() == order.getSellerId()) {
                    countOfSell++;
                }
                countOfSales.put(seller.getId(), countOfSell);
            }
            countOfSell = 0;
        }
        for (Map.Entry<Long, Integer> pair: countOfSales.entrySet()) {
            if (pair.getValue() == Collections.max(countOfSales.values())) {
                bestSellerId = pair.getKey();
            }
            for (Seller seller : sellers) {
                if(bestSellerId == seller.getId()){
                    bestSellerName = seller.getName();
                }
            }
        }

        System.out.println(bestSellerName);
    }
}
