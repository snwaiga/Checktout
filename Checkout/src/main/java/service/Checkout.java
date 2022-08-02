package service;

import model.Item;
import model.Rules;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Checkout {

    double checkout(List<Item> items, Map<Character, Rules> rules);
}
