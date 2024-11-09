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

        try{
            StockModel stock = getStockDetailsBySymbol(stockSymbol);

            double dividendYield = stock.getStockType().equals("Preferred")
                    ? (((double) stock.getFixedDividend() / 100) * stock.getParValue()) / price
                    : stock.getLastDividend() / price;
            return dividendYield;

        }catch(Exception error){
            System.out.println("An error has occurred: " + error);
            return 0.0;
        }finally {
            System.out.println("Dividend Yield calculation completed");
        }


    }

    public StockModel getStockDetailsBySymbol(String stockSymbol) {

        return stockRepository.findByStockSymbol(stockSymbol);
    }

    public double calculatePERatio(int price, String stockSymbol) {

        StockModel stock = getStockDetailsBySymbol(stockSymbol);

        try{
            if (stock.getLastDividend() == 0) {
                return 0.0;
            }

            double peRatio = (double) price / stock.getLastDividend();

            return peRatio;

        }catch(Exception error) {
            System.out.println("An error has occurred: " + error);
            return 0.0;
        }finally {
            System.out.println("PE Ratio calculation completed");
        }
    }
}
