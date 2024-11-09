package assessment.cmagml.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Entity
@Table(name="trades")
public class TradeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "stock")
    private StockModel stock;

    @Column(name = "purchaselocaltime")
    private LocalTime localTime;

    @Column(name = "purchaselocaldatestamp")
    private LocalDate localDate;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "saleindicatortype")
    private String saleIndicatorType;

    @Column(name = "price")
    private int price;

    public TradeModel(StockModel stock, int quantity, String saleIndicatorType, int price) {
        this.stock = stock;
        this.localTime = LocalTime.now(ZoneId.of("UTC"));
        this.localDate = LocalDate.now(ZoneId.of("UTC"));
        this.quantity = quantity;
        this.saleIndicatorType = saleIndicatorType;
        this.price = price;
    }

    public TradeModel() {
    }

    public Long getId() {
        return id;
    }

    public StockModel getStock() {
        return stock;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSaleIndicatorType() {
        return saleIndicatorType;
    }

    public int getPrice() {
        return price;
    }
}
