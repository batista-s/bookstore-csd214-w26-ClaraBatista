package csd214.bookstore.pojos;

public class GiftWrap implements SaleableItem{
    // because giftwrap implements saleableitem, it must implement the saleableitem methods
    public void sellItem(){
        System.out.println("Gift wrapping service sold!");
    }

    public double getPrice(){
        return 2.00;
    }
}
