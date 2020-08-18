package pl.sebastian.model;

import lombok.Value;

@Value
public class Product {
    String name;
    int amount;
    float price;
    String description;
    String imageUrl;
}
