package com.bookstore.eagle.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bookstore.eagle.dto.RequestedItemDTO;
import com.bookstore.eagle.dto.RequestedItemResponseDTO;
import com.bookstore.eagle.model.RequestedItem;
import com.bookstore.eagle.repository.RequestedItemRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class RequestedItemServiceImpl implements RequestedItemService {
    @Inject
    RequestedItemRepository requestedItemRepository;

    @Inject
    Validator validator;

    @Override
    public List<RequestedItemResponseDTO> listRequestedItems() {
        List<RequestedItem> list = requestedItemRepository.listAll();
        return list.stream().map(RequestedItemResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public RequestedItemResponseDTO searchRequestedItemById(Long id) {
        RequestedItem requestedItem = requestedItemRepository.findById(id);
        if (requestedItem == null)
            throw new NotFoundException("The requested item was not found.");
        return new RequestedItemResponseDTO(requestedItem);
    }

    @Override
    @Transactional
    public RequestedItemResponseDTO addRequestedItem(RequestedItemDTO requestedItemDTO) throws ConstraintViolationException {
        validating(requestedItemDTO);

        RequestedItem entity = new RequestedItem();
        entity.setQuantity(requestedItemDTO.quantity());
        entity.setPrice(requestedItemDTO.price());
        requestedItemRepository.persist(entity);

        return new RequestedItemResponseDTO(entity);

    }

    @Override
    @Transactional
    public RequestedItemResponseDTO updateRequestedItem(Long id, RequestedItemDTO requestedItemDTO) throws ConstraintViolationException {
        validating(requestedItemDTO);

        RequestedItem entity = requestedItemRepository.findById(id);

        entity.setQuantity(requestedItemDTO.quantity());
        entity.setPrice(requestedItemDTO.price());
        
        return new RequestedItemResponseDTO(entity);
    }

    private void validating(RequestedItemDTO requestedItemDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<RequestedItemDTO>> violations = validator.validate(requestedItemDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void deleteRequestedItem(Long id) {
        requestedItemRepository.deleteById(id);
    }

}
