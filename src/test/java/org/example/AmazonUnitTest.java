package org.example;

import org.example.Amazon.Amazon;
import org.example.Amazon.Cost.*;
import org.example.Amazon.Item;
import org.example.Amazon.ShoppingCart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AmazonUnitTest {

//    public class Amazon {
//
//        private final List<PriceRule> rules;
//        private final ShoppingCart carts;
//
//        public Amazon(ShoppingCart carts, List<PriceRule> rules) {
//            this.carts = carts;
//            this.rules = rules;
//        }
//
//        public double calculate() {
//            double finalPrice = 0;
//
//            for (PriceRule rule : rules) {
//                finalPrice += rule.priceToAggregate(carts.getItems());
//            }
//
//            return finalPrice;
//        }
//
//        public void addToCart(Item item){
//            carts.add(item);
//        }
//    }

    //Specification-Based Tests

    @DisplayName("specification-based")
    @Test
    void regularCost () {
        RegularCost reg = new RegularCost();

        List<Item> cart = List.of(new Item(ItemType.ELECTRONIC,
                "Phone", 1, 799.99));

        assertEquals(799.99, reg.priceToAggregate(cart));
    }

    @DisplayName("specification-based")
    @Test
    void extraCost () {
        ExtraCostForElectronics reg = new ExtraCostForElectronics();

        List<Item> cart = List.of(new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00));

        assertEquals(7.50, reg.priceToAggregate(cart));
    }


    //Structural-Based Tests

    @DisplayName("structural-based")
    @Test
    void calculatePrice () {
        PriceRule rule1 = mock(PriceRule.class);
        when(rule1.priceToAggregate(anyList())).thenReturn(50.0);
        PriceRule rule2 = mock(PriceRule.class);
        when(rule2.priceToAggregate(anyList())).thenReturn(50.0);
        PriceRule rule3 = mock(PriceRule.class);
        when(rule3.priceToAggregate(anyList())).thenReturn(50.0);
        List<PriceRule> rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        ShoppingCart mockCart = mock(ShoppingCart.class);

        Amazon amazon = new Amazon(mockCart, rules);
        assertEquals(150.0, amazon.calculate());
    }

    @DisplayName("specification-based")
    @Test
    void deliveryPrice () {
        DeliveryPrice reg = new DeliveryPrice();

        List<Item> cart = List.of();

        List<Item> cart1 = List.of(new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00));

        List<Item> cart2 = List.of(new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00));

        List<Item> cart3 = List.of(new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00),new Item(ItemType.ELECTRONIC,
                "Phone", 1, 100.00));

        assertEquals(0, reg.priceToAggregate(cart));
        assertEquals(5, reg.priceToAggregate(cart1));
        assertEquals(12.5, reg.priceToAggregate(cart2));
        assertEquals(20, reg.priceToAggregate(cart3));
    }


}
