package service;

import model.Item;
import model.Rules;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class SupermarketCheckoutImpl implements Checkout {

    /**
     * @param items
     * @param rules
     * @return
     */
    @Override
    public double checkout(List<Item> items, Map<Character, Rules> rules) {

        if(items.isEmpty()){
            return -1.0;
        }

        //used to hold each item that we scan- mainly used to keep track of quantity of items
        List<Item> basket=new ArrayList<>();
        for(Item item:items){

            char itemId=item.getItemId();
            int quantity=item.getQuantity();
            double price= item.getPrice();

            if(quantity<=0){
                continue;
            }
            if(price<=0.0){
                continue;
            }

            if(basket.isEmpty() || !basket.stream().filter(i->i.getItemId()==itemId).findFirst().isPresent()){
                basket.add(item);
            }
            //if the basket already contains item update the quantity
            else{
                basket.stream().filter(i->itemId==i.getItemId()).forEach(i->i.setQuantity(i.getQuantity()+quantity));
            }

        }
        return calculate(basket,rules);
    }

    private double calculate(List <Item> basket,Map<Character,Rules> rules){
        double total=0.0;

        for(Item item:basket){
            double subtotal;
            char itemId=item.getItemId();

            //here we check the rules, update price if we hit a threshold and add to the total
            if(rules.containsKey(itemId) && !rules.isEmpty() ){
                Rules rule=rules.get(itemId);

                if(item.getQuantity()< rule.getQuantity()){
                    subtotal =item.getQuantity() * item.getPrice();
                }
                else if(item.getQuantity()==rule.getQuantity()){
                    subtotal=rule.getPrice();
                }
                else{
                    int quantity=item.getQuantity();
                    int ruleQ=rule.getQuantity();

                    int totalItemsSpecialPrice = quantity /ruleQ;
                    int totalItemsNoSpecialPrice = quantity % ruleQ;

                    subtotal=totalItemsSpecialPrice * rule.getPrice();
                    subtotal+=totalItemsNoSpecialPrice *item.getPrice();
                }
            }

            // no rules attached with an/any item(s) so we can do multiplication with normal price
            else{
                subtotal =item.getQuantity() * item.getPrice();
            }
            total+=subtotal;
        }
        return total;
    }
}
