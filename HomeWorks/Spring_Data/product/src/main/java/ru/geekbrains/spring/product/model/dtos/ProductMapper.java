package ru.geekbrains.spring.product.model.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.spring.product.model.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Named("productToProductDTO")
    @Mappings({
            @Mapping(source="entity.id", target="id"),
            @Mapping(source="entity.title", target="title"),
            @Mapping(source="entity.price", target="price")
    })
    ProductDto productToProductDTO(Product entity);

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="title", source="dto.title"),
            @Mapping(target="price", source="dto.price")
    })
    Product productDTOtoProduct(ProductDto dto);

}
