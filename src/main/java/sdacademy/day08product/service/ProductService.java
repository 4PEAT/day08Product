package sdacademy.day08product.service;

import sdacademy.day08product.exception.CategoryNotFoundException;
import sdacademy.day08product.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdacademy.day08product.entity.Category;
import sdacademy.day08product.entity.Product;
import sdacademy.day08product.repository.CategoryRepository;
import sdacademy.day08product.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        if (product.getCategory() != null && product.getCategory().getId() != 0){
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new CategoryNotFoundException(("Category with id " + product.getCategory().getId() + " not found")));
            product.setCategory(category);
        }
        return productRepository.save(product);
    }

    public Product getProductById(int id)
    {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " not found!"));
    }

    public Product updateProduct (int id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + "not found and can't be updated"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        if (updatedProduct.getCategory() != null && updatedProduct.getCategory().getId() != 0) {
            Category category = categoryRepository.findById(updatedProduct.getCategory().getId())
                    .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
            existingProduct.setCategory(category);

        }
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(int id)
    {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product to be deleted with id " + id + " not found"));
        productRepository.delete(product);
    }
}
