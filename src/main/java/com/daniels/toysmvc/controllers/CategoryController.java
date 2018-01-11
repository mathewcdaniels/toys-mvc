package com.daniels.toysmvc.controllers;

import com.daniels.toysmvc.models.Category;
import com.daniels.toysmvc.models.Toy;
import com.daniels.toysmvc.models.data.CategoryDao;
import com.daniels.toysmvc.models.data.ToyDao;
import com.daniels.toysmvc.models.forms.AddCategoryItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ToyDao toyDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(defaultValue = "0") int id) {
        model.addAttribute("title", "Categories");
        model.addAttribute("categories", categoryDao.findAll());
        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Category());
        model.addAttribute("title", "Add Category");

        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "category/add";
        }
        categoryDao.save(category);

        return "redirect:";
    }

    @RequestMapping(value = "view/{categoryId}", method = RequestMethod.GET)
    public String viewCategory(Model model, @PathVariable int categoryId) {
        Category category = categoryDao.findOne(categoryId);
        model.addAttribute("title", category.getName());
        model.addAttribute("toys", category.getToys());
        model.addAttribute("categoryId", category.getId());
        return "category/view";
    }

    @RequestMapping(value = "add-item/{categoryId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int categoryId) {

        //TODO-mcd: Create any .html necessary ("view")
        Category category = categoryDao.findOne(categoryId);
        AddCategoryItemForm form = new AddCategoryItemForm(category, toyDao.findAll());
        model.addAttribute("form", form);
        model.addAttribute("title", "Add item to category: " + category.getName());
        return "category/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddCategoryItemForm form, Errors errors) {

        if(errors.hasErrors()) {
            model.addAttribute("form", form);
            return "category/add-item";
        }

        Toy thetoy = toyDao.findOne(form.getToyId());
        Category theCategory = categoryDao.findOne(form.getCategoryId());
        theCategory.addItem(thetoy);
        categoryDao.save(theCategory);
        return "redirect:/category/view/" + theCategory.getId();

    }

}
