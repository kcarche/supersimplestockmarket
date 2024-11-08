package assessment.cmagml.model;

import jakarta.persistence.*;

@Entity
@Table(name="stocks")
public class StockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "stocksymbol")
    private String stockSymbol;

    @Column(name = "stocktype")
    private String stockType;

    @Column(name = "lastdividend")
    private int lastDividend;

    @Column(name = "fixeddividend")
    private int fixedDividend;

    @Column(name = "parvalue")
    private int parValue;

    public StockModel(String stockSymbol, String stockType, int lastDividend, int fixedDividend, int parValue) {
        this.stockSymbol = stockSymbol;
        this.stockType = stockType;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    public StockModel() {
    }

    public Long getId() {
        return id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getStockType() {
        return stockType;
    }

    public int getLastDividend() {
        return lastDividend;
    }

    public int getFixedDividend() {
        return fixedDividend;
    }

    public int getParValue() {
        return parValue;
    }
}
