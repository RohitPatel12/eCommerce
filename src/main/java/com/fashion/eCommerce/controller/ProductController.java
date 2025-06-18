package com.fashion.eCommerce.controller;

import com.fashion.eCommerce.model.Product;
import com.fashion.eCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(){
        return "server is running";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        System.out.println("Inside ProductController: getAllProducts()");
        return productService.getAllProducts();
    }

    @GetMapping("/products/{prodId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String prodId) {
        Product product = productService.getProductById(prodId);
        byte[] imageData = product.getImageData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(product.getImageType())); // e.g. "image/webp"
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }



    @GetMapping("/products/{prodId}")
    public Product getProductById(@PathVariable String prodId) {
        System.out.println("Inside ProductController: getProductById(" + prodId + ")");
        return productService.getProductById(prodId);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestPart Product prod,
                                        @RequestPart MultipartFile imageFile){
        try {
            Product productSample = productService.addProduct(prod, imageFile);
            return new ResponseEntity<>(productSample,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/products/{prodId}")
    public void updateProduct(@RequestBody Product prod){
        System.out.println("I am inside putMapping");
        productService.updateProduct(prod);
    }

    @DeleteMapping("/products/{prodId}")
    public void deleteProduct(@PathVariable String prodId){
        productService.deleteProduct(prodId);
    }
}
