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

    //String stockSymbol, int quantity, String salesIndicator, int price
    @PostMapping("/stocktrade")
    public void postStockTrade(@RequestBody TradeRequest tradeRequestDetails){
        serviceLayer.recordTrade(tradeRequestDetails);
    }

    @GetMapping("/volumeweightedstockprice")
    public double getVolumeWeightedStockPrice(@RequestParam("stocksymbol") String stockSymbol){
        return serviceLayer.calculateVolumeWeightedStockPrice(stockSymbol);
    }

}
