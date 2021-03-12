package ru.geekbrains.spring.product.services;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.product.model.dtos.ProductDto;
import ru.geekbrains.spring.product.model.dtos.ProductMapper;
import ru.geekbrains.spring.product.model.entities.Product;
import ru.geekbrains.spring.product.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(int min) {
        return productRepository.findProductsByPriceGreaterThan(min);
    }

    public List<Product> getAll(int min, int max) {
        return productRepository.findProductsByPriceBetween(min, max);
    }

    public Page<Product> getAllPage(int page, int size, Sort.Direction direction, String... param) {
        return productRepository.findAll(PageRequest.of(page, size, direction, param));
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getByName(String name) {
        return productRepository.findProductsByTitleContainingIgnoreCase(name);
    }

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(ProductMapper.INSTANCE::productToProductDTO);
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        if(page < 0)
            throw new RuntimeException();
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductMapper.INSTANCE::productToProductDTO);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Product save(ProductDto dto) {
        return productRepository.save(ProductMapper.INSTANCE.productDTOtoProduct(dto));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
