package market.converters;


import market.dto.ProductDto;
import market.entities.Product;
import market.services.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    private CategoryService categoryService;
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice(), categoryService.findByName(productDto.getCategory()));
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }
}
