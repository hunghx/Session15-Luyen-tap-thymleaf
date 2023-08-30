package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Catalog;
import ra.service.impl.CatalogService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;
    @GetMapping
    public String list(Model model){
        model.addAttribute("list",catalogService.findAll());
        return "category";
    }
    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("add","cat",new Catalog());
    }
    @PostMapping("/add")
    public String doAdd(@ModelAttribute Catalog cat){
        catalogService.save(cat);
        return "redirect:/catalog";
    }
}
