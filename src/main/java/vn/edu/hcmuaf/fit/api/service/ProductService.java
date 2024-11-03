package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.ProductDTO;
import vn.edu.hcmuaf.fit.api.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(int providerId, ProductDTO productDTO);
    List<ProductDTO> getProducts();
    Product getProductByID(Integer id);
    Product updateProductByID(Integer id, ProductDTO productDTO);
    void deleteProductByID(Integer id);

}
