package com.fashion.eCommerce.service;

import com.fashion.eCommerce.model.Product;
import com.fashion.eCommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service  // Marks this class as a Spring Service (for business logic)
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    // Get all products
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

//     Get a single product by ID
    public Product getProductById(String prodId) {
        return productRepo.findById(prodId).orElse(null);
    }

    public Product addProduct(Product prod, MultipartFile imageFile) throws IOException {
        prod.setImageName(imageFile.getOriginalFilename());
        prod.setImageType(imageFile.getContentType());
        prod.setImageData(imageFile.getBytes());
        return productRepo.save(prod);
    }

    public void updateProduct(Product prod) {
        productRepo.save(prod);
    }


    public void deleteProduct(String prodId) {
        productRepo.deleteById(prodId);
    }
}

//    public Product addProduct(Product prod, MultipartFile imageFile) {
//    }
