package assessment.cmagml.service;

import assessment.cmagml.model.StockModel;
import assessment.cmagml.model.TradeModel;
import assessment.cmagml.model.TradeRequest;
import assessment.cmagml.repository.StockRepository;
import assessment.cmagml.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        try{
            return stockRepository.findByStockSymbol(stockSymbol);
        }catch(Exception error){
            System.out.println("An error has occurred: " + error);
            return null;
        }

    }

    public double calculatePERatio(int price, String stockSymbol) {

        try{
            StockModel stock = getStockDetailsBySymbol(stockSymbol);
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
        try{
            StockModel stock = validateStockSymbol(tradeRequestDetails.getStockSymbol());
            if (stock == null) {
                return "Trade Incomplete: Stock Symbol Invalid";
            }

            TradeModel trade = new TradeModel(stock, tradeRequestDetails.getQuantity(), tradeRequestDetails.getSalesIndicator(), tradeRequestDetails.getPrice());

            tradeRepository.save(trade);

            return "Trade Completed";
        }catch(Exception error){
            System.out.println("An error has occurred: " + error);
            return "Trade Incomplete";
        }

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

        public Map<String, List<TradeModel>> getRecentTradesMappedByStockSymbol() {
            try{
                List<TradeModel> trades = tradeRepository.findAll();
                LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);
                return trades.stream()
                        .filter(trade -> trade.getUTCTimestamp().isAfter(fiveMinutesAgo))
                        .collect(Collectors.groupingBy(trade -> trade.getStock().getStockSymbol()));
            }catch(Exception error){
                System.out.println("An error has occurred: " + error);
                return null;
            }

        }

        public double calculateVolumeWeightedStockPrice (String stockSymbol) {

            try {
                Map<String, List<TradeModel>> recentTradesMap = getRecentTradesMappedByStockSymbol();
                List<TradeModel> recentTrades = recentTradesMap.get(stockSymbol);

                if (recentTrades == null || recentTrades.isEmpty()) {
                    return 0.0;
                }

                double totalTradePriceQuantity = recentTrades.stream()
                        .mapToDouble(trade -> trade.getPrice() * trade.getQuantity())
                        .sum();
                int totalQuantity = recentTrades.stream()
                        .mapToInt(TradeModel::getQuantity)
                        .sum();

                return totalTradePriceQuantity / totalQuantity;
            } catch (Exception error) {
                System.out.println("An error has occurred: " + error);
                return 0.0;

            }
        }

    public List<String> findUniqueStockSymbols() {
        try {
            return stockRepository.findAll().stream()
                    .map(StockModel::getStockSymbol)
                    .distinct()
                    .collect(Collectors.toList());
        } catch (Exception error) {
            System.out.println("An error has occurred: " + error);
            return Collections.emptyList();
        }
    }

    public double calculateGeometricMean(){
        int uniqueStockSymbols = findUniqueStockSymbols().size();
        double volumeWeightedStockPriceTotal = 0.0;
        for (String stockSymbol : findUniqueStockSymbols()) {
            volumeWeightedStockPriceTotal += calculateVolumeWeightedStockPrice(stockSymbol);
        }
        return Math.pow(volumeWeightedStockPriceTotal, 1.0 / uniqueStockSymbols);
    }

}
