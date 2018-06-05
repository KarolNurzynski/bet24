package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.BetOfferType;
import pl.coderslab.service.BetOfferTypeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/betOfferType")
public class BetOfferTypeController {

    @Autowired
    BetOfferTypeService betOfferTypeService;

    @GetMapping("/add")
    public String addBetOfferType(Model model){
        model.addAttribute("betOfferType", new BetOfferType());
        return "adminBetOfferTypeListAll";
    }

    @PostMapping("/add")
    public String addBetOfferType(@Valid @ModelAttribute BetOfferType betOfferType,
                             BindingResult result){
        if (result.hasErrors()) {
            return "adminBetOfferTypeListAll";
        }
        betOfferTypeService.saveBetOfferType(betOfferType);
        return "redirect:/betOfferType/add";
    }

    @GetMapping("/edit/{betOfferType_id}")
    public String editBetOfferType(@PathVariable Long betOfferType_id,
                                   Model model) {
        BetOfferType betOfferType = betOfferTypeService.findBetOfferTypeById(betOfferType_id);
        model.addAttribute("betOfferType", betOfferType);
        return "adminBetOfferTypeEditForm";
    }

    @PostMapping("/edit/{betOfferType_id}")
    public String editBetOfferType(@ModelAttribute BetOfferType betOfferType,
                           @PathVariable Long betOfferType_id,
                           Model model) {
        betOfferTypeService.editBetOfferType(betOfferType);
        return "redirect:/betOfferType/add";
    }

    @GetMapping("/delete/{betOfferType_id}")
    public String deleteBetOfferType(@PathVariable Long betOfferType_id, Model model) {
        betOfferTypeService.deleteBetOfferType(betOfferType_id);
        return "redirect:/betOfferType/add";
    }


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("betOfferTypes")
    public List<BetOfferType> betOfferTypes() {
        return betOfferTypeService.findAllBetOfferTypes();
    }

}
