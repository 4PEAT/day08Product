package sdacademy.day08product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sdacademy.day08product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // findAll() - returneaza produsele
    // findById(Integer Id) - returneaza produsele dupa un anumit ID
    // save(Product product) - primeste si insearaza/actualizeaza un obiect product
    // deleteById(Integer Id) - sterge un produs dupa un anumit ID
}
