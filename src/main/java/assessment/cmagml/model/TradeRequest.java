package assessment.cmagml.model;


import jakarta.validation.constraints.NotNull;

public class TradeRequest {

    @NotNull
    private String stockSymbol;
    @NotNull
    private int quantity;
    @NotNull
    private String salesIndicator;
    @NotNull
    private int price;

    public TradeRequest(String stockSymbol, int quantity, String salesIndicator, int price) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.salesIndicator = salesIndicator;
        this.price = price;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSalesIndicator() {
        return salesIndicator;
    }

    public void setSalesIndicator(String salesIndicator) {
        this.salesIndicator = salesIndicator;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
