package vn.edu.hcmuaf.fit.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.api.dto.ProductDTO;
import vn.edu.hcmuaf.fit.api.dto.ProviderDTO;
import vn.edu.hcmuaf.fit.api.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.api.model.Product;
import vn.edu.hcmuaf.fit.api.model.Provider;
import vn.edu.hcmuaf.fit.api.service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create a new Product
    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestParam int providerId,
                                                 @ModelAttribute ProductDTO productDTO,
                                                 @RequestParam(value = "imageFile") MultipartFile imageFile) throws IOException, IOException {
        Product savedProduct = productService.saveProduct(providerId, productDTO, imageFile);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Get all Product
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getProducts();
    }

    // Get all Product by Provider
    @GetMapping("/by-provider")
    public List<ProductDTO> getAllProductsByProvider(@RequestParam("providerId") int providerId) {
        return productService.getProductsByProvider(providerId);
    }

    // Get Product by id
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(productService.getProductByID(id), HttpStatus.OK);
    }

    // Update Product by id
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable ("id") int id,
                                                     @ModelAttribute ProductDTO productDTO,
                                                     @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        Product updatedProduct = productService.updateProductByID(id, productDTO, imageFile);
        return new ResponseEntity<>(updatedProduct, HttpStatus.CREATED);
    }

    // Delete Product by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable ("id") int id) {
        productService.deleteProductByID(id);
        return new ResponseEntity<>("Product " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
