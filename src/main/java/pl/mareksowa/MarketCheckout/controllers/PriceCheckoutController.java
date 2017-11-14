package pl.mareksowa.MarketCheckout.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mareksowa.MarketCheckout.models.UserRequestModel;
import pl.mareksowa.MarketCheckout.models.dao.ItemModelDao;
import pl.mareksowa.MarketCheckout.models.dao.impl.ItemModelDaoImpl;

@Controller
public class PriceCheckoutController {

    //temp final strings to simulate real templates / url paths -> not recommend to use in real project
    private final String urlGetUserPriceRequest = "/"; // "/sample/url";
    private final String templateUserPriceRequest = "index"; // "sampleNameOfTemplate"

    //declarations and initializations
    private StringBuilder sb = new StringBuilder();
    private ItemModelDao itemModelDao = new ItemModelDaoImpl();

    @GetMapping(urlGetUserPriceRequest) public String getUserPriceRequest(Model model){
        model.addAttribute("userRequestModel", new UserRequestModel());
        return templateUserPriceRequest;
    }

    @PostMapping(urlGetUserPriceRequest) public String postUserPriceRequest(
            @ModelAttribute UserRequestModel userRequestModel, Model model){
        int currentResult = itemModelDao.checkPrise(userRequestModel.getItemName(), userRequestModel.getQuantity());
        String showPartOfResult = "Item: '" + userRequestModel.getItemName() + "', Qty: '" +
                userRequestModel.getQuantity() + "', Cost: $";
        model.addAttribute("result", "CURRENT QUERY: " + showPartOfResult + currentResult);
        sb.append(showPartOfResult + currentResult + " *** ");
        model.addAttribute("lastQuery", "CUMULATIVE QUERY: " + sb.toString());
        return templateUserPriceRequest;
    }

}
