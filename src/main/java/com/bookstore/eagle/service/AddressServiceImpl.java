package com.bookstore.eagle.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bookstore.eagle.dto.AddressDTO;
import com.bookstore.eagle.dto.AddressResponseDTO;
import com.bookstore.eagle.model.Address;
import com.bookstore.eagle.repository.AddressRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AddressServiceImpl implements AddressService {
    @Inject
    AddressRepository addressRepository;

    @Inject
    Validator validator;

    @Override
    public List<AddressResponseDTO> listAddresses() {
        List<Address> list = addressRepository.listAll();
        return list.stream().map(AddressResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public AddressResponseDTO searchAddressById(Long id) {
        Address address = addressRepository.findById(id);
        if (address == null)
            throw new NotFoundException("The address was not found.");
        return new AddressResponseDTO(address);
    }

    @Override
    @Transactional
    public AddressResponseDTO addAddress(AddressDTO addressDTO) throws ConstraintViolationException {
        validating(addressDTO);

        Address entity = new Address();
        entity.setMain(addressDTO.main());
        entity.setStreet(addressDTO.street());
        entity.setNumber(addressDTO.number());
        entity.setAdjunct(addressDTO.adjunct());
        entity.setNeighborhood(addressDTO.neighborhood());
        entity.setZip(addressDTO.zip());
        addressRepository.persist(entity);

        return new AddressResponseDTO(entity);

    }

    @Override
    @Transactional
    public AddressResponseDTO updateAddress(Long id, AddressDTO addressDTO) throws ConstraintViolationException {
        validating(addressDTO);

        Address entity = addressRepository.findById(id);

        entity.setMain(addressDTO.main());
        entity.setStreet(addressDTO.street());
        entity.setNumber(addressDTO.number());
        entity.setAdjunct(addressDTO.adjunct());
        entity.setNeighborhood(addressDTO.neighborhood());
        entity.setZip(addressDTO.zip());
        
        return new AddressResponseDTO(entity);
    }

    private void validating(AddressDTO addressDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<AddressDTO>> violations = validator.validate(addressDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

}
