package vn.edu.hcmuaf.fit.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @Column(name = "original_price", nullable = false)
    private double originalPrice;

    @Column(name = "new_price")
    private double newPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "color", columnDefinition = "TEXT")
    private String color;

    @Column(name = "screen", columnDefinition = "TEXT")
    private String screen;

    @Column(name = "operating_system", columnDefinition = "TEXT")
    private String operatingSystem;

    @Column(name = "camera", columnDefinition = "TEXT")
    private String camera;

    @Column(name = "cpu", columnDefinition = "TEXT")
    private String cpu;

    @Column(name = "ram", columnDefinition = "TEXT")
    private String ram;

    @Column(name = "storage", columnDefinition = "TEXT")
    private String storage;

    @Column(name = "battery", columnDefinition = "TEXT")
    private String battery;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "status")
    private byte status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties("product")
    private List<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Cart> carts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

}
