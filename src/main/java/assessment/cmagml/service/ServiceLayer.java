package assessment.cmagml.service;

import assessment.cmagml.model.StockModel;
import assessment.cmagml.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLayer {


    private final StockRepository stockRepository;

    @Autowired
    public ServiceLayer(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public double calculateDividendYield(double price, String stockSymbol) {

        StockModel stock = getStockDetailsBySymbol(stockSymbol);

        double dividendYield = stock.getStockType().equals("Preferred")
                ? (((double) stock.getFixedDividend() / 100) * stock.getParValue()) / price
                : stock.getLastDividend() / price;
        return dividendYield;


    }

    public StockModel getStockDetailsBySymbol(String stockSymbol) {

        return stockRepository.findByStockSymbol(stockSymbol);
    }

}
