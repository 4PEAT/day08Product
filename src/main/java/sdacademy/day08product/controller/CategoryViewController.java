package sdacademy.day08product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sdacademy.day08product.entity.Category;
import sdacademy.day08product.service.CategoryService;

@Controller
@RequestMapping("/view/categories")
public class CategoryViewController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @PostMapping("/add")
    public String addCategory (@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return "redirect:/view/categories";
    }

    @GetMapping("/add")
    public String getCategory (Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @PostMapping("/edit")
    public String editCategory (@ModelAttribute Category category) {
        categoryService.updateCategory(category.getId(), category);
        return "redirect:/view/categories";
    }
    @GetMapping("/edit/{id}")
    public String showEditCategoryForm (@PathVariable int id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "edit-category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "redirect:/view/categories";
    }
}
