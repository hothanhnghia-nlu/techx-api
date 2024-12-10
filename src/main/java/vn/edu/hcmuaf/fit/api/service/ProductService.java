package vn.edu.hcmuaf.fit.api.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.api.dto.ProductDTO;
import vn.edu.hcmuaf.fit.api.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product saveProduct(int providerId, ProductDTO productDTO, MultipartFile imageFile) throws IOException;
    List<ProductDTO> getProducts();
    List<ProductDTO> getProductsByProvider(int providerId);
    List<ProductDTO> getNewProducts();
    List<ProductDTO> getPromotionProducts();
    Product getProductByID(Integer id);
    Product updateProductByID(Integer id, ProductDTO productDTO, MultipartFile imageFile) throws IOException;
    void deleteProductByID(Integer id);

}
