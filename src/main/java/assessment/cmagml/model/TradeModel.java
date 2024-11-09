package assessment.cmagml.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;


@Entity
@Table(name="trades")
public class TradeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private StockModel stock;

    @Column(name = "purchasetimestamp")
    private LocalDateTime utctimestamp;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "saleindicatortype")
    private String saleIndicatorType;

    @Column(name = "price")
    private int price;

    public TradeModel(StockModel stock, int quantity, String saleIndicatorType, int price) {
        this.stock = stock;
        this.utctimestamp = LocalDateTime.now();
        this.quantity = quantity;
        this.saleIndicatorType = saleIndicatorType.toUpperCase();
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

    public LocalDateTime getUTCTimestamp() {
        return utctimestamp;
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