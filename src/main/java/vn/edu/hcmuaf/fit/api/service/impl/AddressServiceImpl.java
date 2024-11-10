package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.AddressDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.Address;
import vn.edu.hcmuaf.fit.api.repository.AddressRepository;
import vn.edu.hcmuaf.fit.api.service.AddressService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address saveAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setStatus((byte) 1);
        address.setCreatedAt(LocalDateTime.now());

        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressByID(Integer id) {
        return addressRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Address", "Id", id));
    }

    @Override
    public Address updateAddressByID(Integer id, AddressDTO addressDTO) {
        Address existingAddress = addressRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Address", "Id", id));

//        existingAddress.setName(addressDTO.getName() != null ? addressDTO.getName() : existingAddress.getName());
        existingAddress.setStatus(addressDTO.getStatus() != 0 ? addressDTO.getStatus() : existingAddress.getStatus());
        existingAddress.setUpdatedAt(LocalDateTime.now());

        return addressRepository.save(existingAddress);
    }

    @Override
    public void deleteAddressByID(Integer id) {
        addressRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Address", "Id", id));

        addressRepository.deleteById(id);
    }
}
