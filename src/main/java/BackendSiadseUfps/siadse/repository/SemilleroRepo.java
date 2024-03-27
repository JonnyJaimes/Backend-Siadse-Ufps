package BackendSiadseUfps.siadse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BackendSiadseUfps.siadse.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}