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

    @Column(name = "timestamp")
    private LocalTime localTime;

    @Column(name = "datestamp")
    private LocalDate localDate;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "indicator")
    private String indicator;

    @Column(name = "price")
    private int price;

    public TradeModel(StockModel stock, int quantity, String indicator, int price) {
        this.stock = stock;
        this.localTime = LocalTime.now(ZoneId.of("UTC"));
        this.localDate = LocalDate.now(ZoneId.of("UTC"));
        this.quantity = quantity;
        this.indicator = indicator;
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

    public String getIndicator() {
        return indicator;
    }

    public int getPrice() {
        return price;
    }
}
