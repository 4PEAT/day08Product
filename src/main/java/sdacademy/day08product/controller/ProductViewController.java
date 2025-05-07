package sdacademy.day08product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sdacademy.day08product.entity.Product;
import sdacademy.day08product.service.CategoryService;
import sdacademy.day08product.service.ProductService;

@Controller
@RequestMapping("view/products")
public class ProductViewController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/view/products";
    }

    @GetMapping("/add")
    public String getProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add-product";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        productService.updateProduct(product.getId(), product);
        return "redirect:/view/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductName(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit-product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return "redirect:/view/products";
    }
}
