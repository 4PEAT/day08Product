package sdacademy.day08product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sdacademy.day08product.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
