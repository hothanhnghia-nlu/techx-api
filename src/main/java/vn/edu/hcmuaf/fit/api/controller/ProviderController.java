package vn.edu.hcmuaf.fit.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.api.dto.ProviderDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.Provider;
import vn.edu.hcmuaf.fit.api.service.ProviderService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/providers")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // Create a new Provider
    @PostMapping()
    public ResponseEntity<Provider> createProvider(@RequestParam("provider") String providerJson,
                                                   @RequestParam("image") MultipartFile imageFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProviderDTO providerDTO = objectMapper.readValue(providerJson, ProviderDTO.class);

            Provider savedProvider = providerService.saveProvider(providerDTO, imageFile);
            return ResponseEntity.ok(savedProvider);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
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
            @RequestParam("provider") String providerJson,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProviderDTO providerDTO = objectMapper.readValue(providerJson, ProviderDTO.class);

            Provider updatedProvider = providerService.updateProviderByID(id, providerDTO, imageFile);
            return ResponseEntity.ok(updatedProvider);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Delete Provider by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProviderById(@PathVariable ("id") int id) {
        providerService.deleteProviderByID(id);
        return new ResponseEntity<>("Provider " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
