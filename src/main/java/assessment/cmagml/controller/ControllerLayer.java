package assessment.cmagml.controller;

import assessment.cmagml.model.TradeModel;
import assessment.cmagml.model.TradeRequest;
import assessment.cmagml.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ControllerLayer {

    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping
    public String testServer(){
        return "Running";
    }

    @GetMapping("/dividendyield")
    public double getDividendYield(@RequestParam("price") int price,
                                   @RequestParam("stocksymbol") String stockSymbol){
        return serviceLayer.calculateDividendYield(price, stockSymbol);
    }

    @GetMapping("/peratio")
    public double getPERatio(@RequestParam("price") int price,
                             @RequestParam("stocksymbol") String stockSymbol){
        return serviceLayer.calculatePERatio(price, stockSymbol);
    }

    @PostMapping("/stocktrade")
    public void postStockTrade(@RequestBody TradeRequest tradeRequestDetails){
        serviceLayer.recordTrade(tradeRequestDetails);
    }

    @GetMapping("/volumeweightedstockprice")
    public double getVolumeWeightedStockPrice(@RequestParam("stocksymbol") String stockSymbol){
        return serviceLayer.calculateVolumeWeightedStockPrice(stockSymbol);
    }

    @GetMapping("/geometricmean")
    public double getGeometricMean(){
        return serviceLayer.calculateGeometricMean();
    }

}
