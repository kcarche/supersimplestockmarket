package assessment.cmagml.service;

import assessment.cmagml.model.StockModel;
import assessment.cmagml.model.TradeModel;
import assessment.cmagml.model.TradeRequest;
import assessment.cmagml.repository.StockRepository;
import assessment.cmagml.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLayer {


    private final StockRepository stockRepository;
    private final TradeRepository tradeRepository;
    @Autowired
    public ServiceLayer(StockRepository stockRepository, TradeRepository tradeRepository) {
        this.stockRepository = stockRepository;
        this.tradeRepository = tradeRepository;
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

    public String recordTrade(TradeRequest tradeRequestDetails) {

        StockModel stock = validateStockSymbol(tradeRequestDetails.getStockSymbol());
        if (stock == null) {
            return "Trade Incomplete: Stock Symbol Invalid";
        }

        TradeModel trade = new TradeModel(stock, tradeRequestDetails.getQuantity(), tradeRequestDetails.getSalesIndicator(), tradeRequestDetails.getPrice());

        tradeRepository.save(trade);

        return "Trade Completed";
    }

    public StockModel validateStockSymbol(String stockSymbol) {
        try {
            StockModel stock = getStockDetailsBySymbol(stockSymbol);
            if (stock == null) {
                System.out.println("Stock Symbol Invalid");
            }
            return stock;
        } catch (Exception error) {
            System.out.println("An error has occurred: " + error);
            return null;
        }
    }

}
