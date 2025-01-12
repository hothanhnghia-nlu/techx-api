package vn.edu.hcmuaf.fit.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.*;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.*;
import vn.edu.hcmuaf.fit.api.repository.AddressRepository;
import vn.edu.hcmuaf.fit.api.repository.UserRepository;
import vn.edu.hcmuaf.fit.api.service.AddressService;
import vn.edu.hcmuaf.fit.api.service.AuthenticationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Address saveAddress(AddressDTO addressDTO) {
        System.out.println(addressDTO.toString());
        int userId = authenticationService.getCurrentUserId();

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", addressDTO.getId()));

        Address address = new Address();
        address.setUser(user);
        address.setFullName(addressDTO.getFullName());
        address.setPhoneNumber(addressDTO.getPhone());
        address.setProvince(addressDTO.getProvince());
        address.setCity(addressDTO.getCity());
        address.setWard(addressDTO.getWard());
        address.setDetail(addressDTO.getDetail());
        address.setNote(addressDTO.getNote());
        address.setStatus((byte) 1);
        address.setCreatedAt(LocalDateTime.now());

        return addressRepository.save(address);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();

        return addresses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AddressDTO> getAddressesByUser() {
        int id = authenticationService.getCurrentUserId();
        List<Address> addresses = addressRepository.findByUserId(id);
        return addresses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AddressDTO getAddressDefault() {
        int userId = authenticationService.getCurrentUserId();
        Address addresses = addressRepository.findByStatusAndUserId(0, userId).orElse(null);
        if (addresses == null) {
            addresses = addressRepository.findTopByUserIdOrderByCreatedAtDesc(userId).orElseThrow(() -> new ResourceNotFoundException("Address", "userID", userId));
        }
        return convertToDTO(addresses);
    }

    private AddressDTO convertToDTO(Address address) {
        UserDTO userDTO = null;
        User user = address.getUser();

        if (user != null) {
            userDTO = new UserDTO(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber()
            );
        }

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setUser(userDTO);
        addressDTO.setFullName(address.getFullName());
        addressDTO.setPhone(address.getPhoneNumber());
        addressDTO.setProvince(address.getProvince());
        addressDTO.setCity(address.getCity());
        addressDTO.setWard(address.getWard());
        addressDTO.setDetail(address.getDetail());
        addressDTO.setNote(address.getNote());
        addressDTO.setStatus(address.getStatus());
        addressDTO.setCreatedAt(address.getCreatedAt());
        addressDTO.setUpdatedAt(address.getUpdatedAt());

        return addressDTO;
    }


    @Override
    public AddressDTO getAddressByID(Integer id) {
        Address address = addressRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Address", "Id", id));
        return convertToDTO(address);
    }

    @Override
    public Address updateAddressByID(Integer id, AddressDTO addressDTO) {
        Address existingAddress = addressRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Address", "Id", id));

        existingAddress.setProvince(addressDTO.getProvince() != null ? addressDTO.getProvince() : existingAddress.getProvince());
        existingAddress.setCity(addressDTO.getCity() != null ? addressDTO.getCity() : existingAddress.getCity());
        existingAddress.setWard(addressDTO.getWard() != null ? addressDTO.getWard() : existingAddress.getWard());
        existingAddress.setDetail(addressDTO.getDetail() != null ? addressDTO.getDetail() : existingAddress.getDetail());
        existingAddress.setNote(addressDTO.getNote() != null ? addressDTO.getNote() : existingAddress.getNote());
        existingAddress.setStatus(addressDTO.getStatus() != 0 ? addressDTO.getStatus() : existingAddress.getStatus());
        existingAddress.setUpdatedAt(LocalDateTime.now());

        return addressRepository.save(existingAddress);
    }

    @Override
    public void deleteAddressByID(Integer id) {
        addressRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Address", "Id", id));

        int userId = authenticationService.getCurrentUserId();

        userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));

        addressRepository.deleteById(id);
    }

    @Override
    public Address updateAddress(Address address) {
        System.out.println(address.toString());
        int userId = authenticationService.getCurrentUserId();
        Address existingAddress = addressRepository.findByIdAndUserId(address.getId(), userId).orElse(null);
        existingAddress.setFullName(address.getFullName());
        existingAddress.setPhoneNumber(address.getPhoneNumber());
        existingAddress.setProvince(address.getProvince());
        existingAddress.setCity(address.getCity());
        existingAddress.setWard(address.getWard());
        existingAddress.setDetail(address.getDetail());
        existingAddress.setNote(address.getNote());
        existingAddress.setStatus(address.getStatus());
        existingAddress.setUpdatedAt(LocalDateTime.now());
        return addressRepository.save(existingAddress);
    }
}
