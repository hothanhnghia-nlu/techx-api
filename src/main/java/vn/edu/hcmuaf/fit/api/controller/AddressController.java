package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.AddressDTO;
import vn.edu.hcmuaf.fit.api.model.Address;
import vn.edu.hcmuaf.fit.api.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Create a new Address
    @PostMapping()
    public ResponseEntity<Address> createAddress(@RequestBody AddressDTO address) {
        return new ResponseEntity<>(addressService.saveAddress(address), HttpStatus.CREATED);
    }

    // Get all Address
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAddresses();
    }

    // Get Address by id
    @GetMapping("{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(addressService.getAddressByID(id), HttpStatus.OK);
    }

    // Update Address by id
    @PutMapping("{id}")
    public ResponseEntity<Address> updateAddressById(@PathVariable ("id") int id,
                                                       @RequestBody AddressDTO AddressDTO) {
        return new ResponseEntity<>(addressService.updateAddressByID(id, AddressDTO), HttpStatus.OK);
    }

    // Delete Address by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable ("id") int id) {
        addressService.deleteAddressByID(id);
        return new ResponseEntity<>("Address " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
