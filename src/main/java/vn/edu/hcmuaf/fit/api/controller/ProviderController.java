package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.ProviderDTO;
import vn.edu.hcmuaf.fit.api.model.Provider;
import vn.edu.hcmuaf.fit.api.service.ProviderService;

import java.util.List;

@RestController
@RequestMapping("api/providers")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // Create a new Provider
    @PostMapping()
    public ResponseEntity<Provider> createProvider(@RequestBody ProviderDTO provider) {
        return new ResponseEntity<>(providerService.saveProvider(provider), HttpStatus.CREATED);
    }

    // Get all Provider
    @GetMapping
    public List<Provider> getAllProviders() {
        return providerService.getProviders();
    }

    // Get Provider by id
    @GetMapping("{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(providerService.getProviderByID(id), HttpStatus.OK);
    }

    // Update Provider by id
    @PutMapping("{id}")
    public ResponseEntity<Provider> updateProviderById(@PathVariable ("id") int id,
                                                       @RequestBody ProviderDTO providerDTO) {
        return new ResponseEntity<>(providerService.updateProviderByID(id, providerDTO), HttpStatus.OK);
    }

    // Delete Provider by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProviderById(@PathVariable ("id") int id) {
        providerService.deleteProviderByID(id);
        return new ResponseEntity<>("Provider " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
