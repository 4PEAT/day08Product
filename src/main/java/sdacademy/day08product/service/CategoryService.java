package sdacademy.day08product.service;

import sdacademy.day08product.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdacademy.day08product.entity.Category;
import sdacademy.day08product.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(int id) {

        return categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category with id " + id + " not found"));
    }

    public Category updateCategory(int id, Category updatedCategory) {
        Category existingCategory =  categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
                existingCategory.setName(updatedCategory.getName());
                return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(int id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category with id" + id + " not found"));
        categoryRepository.delete(existingCategory);
    }
}
