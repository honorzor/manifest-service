package ru.honorzor.manifestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.honorzor.manifestservice.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
}
