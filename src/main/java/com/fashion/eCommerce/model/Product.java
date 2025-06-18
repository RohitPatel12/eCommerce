package com.fashion.eCommerce.model;


//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Component
//@Entity
@Document
public class Product {

    @Id
    private String prodId;
    private String prodName;
    private int price;
    private String category;
    private boolean isAvailable;
    //    @Lob
//    @Column(name = "image_data")
    private byte[] imageData;
    private String imageType;
    @Getter
    private String imageName;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Product() {}  // Required by Spring for deserialization


    public Product(String prodId, String prodName, int price,byte[] imageData, String imageName, String imageType) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.price = price;
        this.imageData = imageData;
        this.imageName = imageName;
        this.imageType = imageType;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", price=" + price +
                '}';
    }
}
