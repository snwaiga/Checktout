package com.itv.supermarket;

import model.Item;
import model.Rules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.SupermarketCheckoutImpl;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupermarketCheckoutTest {

    SupermarketCheckoutImpl superCheckout = new SupermarketCheckoutImpl();
    List<Item> testItems;
    List<Item> testItems2;
    List<Item> testItemsMissingQuantity;
    List<Item> testItemsMissingPrice;

    List<Item> emptyItems;

    Map<Character, Rules> testPricingRules;
    Map<Character,Rules> testPricingRules2;

    @BeforeEach
    void setup(){
        testPricingRules = new HashMap<>();
        testPricingRules2=new HashMap<>();

        testPricingRules.put('A',new Rules('A',1.30,3));
        testPricingRules.put('B', new Rules('B',0.45,2));

        testItems=List.of(
                new Item('A',0.50,1),
                new Item ('B',0.30,1),
                new Item('C',0.20,1),
                new Item('A',0.50,2)
        );

        testPricingRules2.put('C',new Rules('C',0.70,4));
        testPricingRules2.put('D',new Rules('D',0.30,3));

        testItems2=List.of(
                new Item('A',0.50,4),
                new Item ('B',0.30,6),
                new Item ('B',0.30,1),
                new Item ('B',0.30,1),
                new Item('C',0.20,3),
                new Item('A',0.50,1),
                new Item('C',0.20,3),
                new Item('D',0.15,1)
        );

        emptyItems= new ArrayList<>();

        testItemsMissingQuantity=List.of(
                new Item('A',0.50,0),
                new Item ('B',0.30,2),
                new Item('C',0.20,4)
        );

        testItemsMissingPrice= List.of(
                new Item('C',0.0,2),
                new Item('A',0.50,4),
                new Item('D',0.15,4)
        );
    }

    @Test
    void testCheckout() {
        assertThat(1.8,equalTo(superCheckout.checkout(testItems, testPricingRules)));
        assertThat(6.15,equalTo(superCheckout.checkout(testItems2,testPricingRules2)));
    }

    @Test
    void testItems(){
        assertThat(-1.0,equalTo(superCheckout.checkout(emptyItems,testPricingRules)));
    }

    @Test
    void testMissingQuantity(){
        assertThat(1.25,equalTo(superCheckout.checkout(testItemsMissingQuantity,testPricingRules)));
    }

    @Test
    void testMissingPrice(){
        assertThat(2.45,equalTo(superCheckout.checkout(testItemsMissingPrice,testPricingRules2)));
    }
}
