package market.services;

import lombok.RequiredArgsConstructor;
import market.entities.Category;
import market.exceptions.ResourceNotFoundException;
import market.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category findByName(String category) {
        return categoryRepository.findByName(category).orElseThrow(() -> new ResourceNotFoundException("Невозможно найти указанную категорию: " + category));
    }
}
