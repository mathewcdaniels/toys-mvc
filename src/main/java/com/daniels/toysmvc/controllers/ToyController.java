package com.daniels.toysmvc.controllers;

import com.daniels.toysmvc.models.Category;
import com.daniels.toysmvc.models.Toy;
import com.daniels.toysmvc.models.data.CategoryDao;
import com.daniels.toysmvc.models.data.ToyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("toys")
public class ToyController {

    @Autowired
    ToyDao toyDao;

    @Autowired
    CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

       model.addAttribute("toys", toyDao.findAll());
       model.addAttribute("title", "Gavin's Toys!");

       return "toys/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddToyForm(Model model) {
        model.addAttribute("title", "Add a toy!");
        model.addAttribute(new Toy());
        model.addAttribute("categories", categoryDao.findAll());
        return "toys/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddToyForm(Model model, @ModelAttribute @Valid Toy newToy,
                                    @RequestParam int categoryId, Errors errors) {
       if (errors.hasErrors()) {
           model.addAttribute("title", "Add a toy!");
           model.addAttribute("categories", categoryDao.findAll());
           return "toys/add";
       }
       // TODO-mcd: This might be the spot to add the checkboxes
       Category category = categoryDao.findOne(categoryId);
       //newToy.setCategory(category);
       toyDao.save(newToy);

       return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveToyForm(Model model) {
        model.addAttribute("toys", toyDao.findAll());
        model.addAttribute("title", "Donate a toy");

        return "toys/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveToyForm(@RequestParam int[] ids) {
        for (int id : ids) {
            toyDao.delete(id);
        }
        return "redirect:";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id) {
        Category category = categoryDao.findOne(id);
        List<Toy> toys = category.getToys();
        model.addAttribute("toys", toys);
        model.addAttribute("title", "Toys in category: " + category.getName());
        return "toys/index";
    }
}
