package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.api.dto.ProviderDTO;
import vn.edu.hcmuaf.fit.api.model.Provider;
import vn.edu.hcmuaf.fit.api.service.ProviderService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/providers")
@Tag(name = "Provider Controller")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // Create a new Provider
    @PostMapping()
    public ResponseEntity<Provider> createProvider(@ModelAttribute ProviderDTO providerDTO,
                                                   @RequestParam(value = "imageFile") MultipartFile imageFile) throws IOException {
        Provider savedProvider = providerService.saveProvider(providerDTO, imageFile);
        return new ResponseEntity<>(savedProvider, HttpStatus.CREATED);
    }

    // Get all Provider
    @GetMapping
    public List<ProviderDTO> getAllProviders() {
        return providerService.getProviders();
    }

    // Get Provider by id
    @GetMapping("{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(providerService.getProviderByID(id), HttpStatus.OK);
    }

    // Update Provider by id
    @PutMapping("{id}")
    public ResponseEntity<Provider> updateProviderById(
            @PathVariable("id") Integer id,
            @ModelAttribute ProviderDTO providerDTO,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        Provider updatedProvider = providerService.updateProviderByID(id, providerDTO, imageFile);
        return new ResponseEntity<>(updatedProvider, HttpStatus.CREATED);
    }

    // Delete Provider by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProviderById(@PathVariable ("id") int id) {
        providerService.deleteProviderByID(id);
        return new ResponseEntity<>("Provider " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
