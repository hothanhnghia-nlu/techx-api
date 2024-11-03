package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.ProductDTO;
import vn.edu.hcmuaf.fit.api.model.Product;
import vn.edu.hcmuaf.fit.api.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create a new Product
    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestParam int providerId,
                                                 @RequestBody ProductDTO product) {
        return new ResponseEntity<>(productService.saveProduct(providerId, product), HttpStatus.CREATED);
    }

    // Get all Product
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getProducts();
    }

    // Get Product by id
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(productService.getProductByID(id), HttpStatus.OK);
    }

    // Update Product by id
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable ("id") int id,
                                                       @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProductByID(id, productDTO), HttpStatus.OK);
    }

    // Delete Product by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable ("id") int id) {
        productService.deleteProductByID(id);
        return new ResponseEntity<>("Product " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
