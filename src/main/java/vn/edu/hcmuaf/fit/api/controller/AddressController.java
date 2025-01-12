package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.AddressDTO;
import vn.edu.hcmuaf.fit.api.exception.ApiRequestException;
import vn.edu.hcmuaf.fit.api.model.Address;
import vn.edu.hcmuaf.fit.api.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("api/v1/addresses")
@Tag(name = "Address Controller")
public class AddressController {
    @Autowired
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Create a new Address
    @PostMapping()
    public ResponseEntity<Address> createAddress(@ModelAttribute AddressDTO address) {
        return new ResponseEntity<>(addressService.saveAddress(address), HttpStatus.CREATED);
    }

    // Get all Address
    @GetMapping(path = "/all-addresses")
    public List<AddressDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // Get Address by user
    @GetMapping(path = "/by-user")
    public ResponseEntity<List<AddressDTO>> getAddressesByUser() {
        return new ResponseEntity<>(addressService.getAddressesByUser(), HttpStatus.OK);
    }

    // Get Address by id
    @GetMapping("{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable("id") int id) {
        return new ResponseEntity<>(addressService.getAddressByID(id), HttpStatus.OK);
    }

    // Get Address by id
    @GetMapping("/default")
    public ResponseEntity<AddressDTO> getAddressDefault() {
        try {
            return new ResponseEntity<>(addressService.getAddressDefault(), HttpStatus.OK);
        } catch (ApiRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Update Address by id
    @PutMapping("{id}")
    public ResponseEntity<Address> updateAddressById(@PathVariable("id") int id,
                                                     @ModelAttribute AddressDTO AddressDTO) {
        return new ResponseEntity<>(addressService.updateAddressByID(id, AddressDTO), HttpStatus.OK);
    }

    // Update Address by id
    @PutMapping()
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        try {
            return new ResponseEntity<>(addressService.updateAddress(address), HttpStatus.OK);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    // Delete Address by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable("id") int id) {
        addressService.deleteAddressByID(id);
        return new ResponseEntity<>("Address " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
