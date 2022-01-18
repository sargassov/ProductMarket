package market.repositories;

import market.entities.Category;
import market.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.title = ?1")
    Optional<Category> findByName(String title);
}
