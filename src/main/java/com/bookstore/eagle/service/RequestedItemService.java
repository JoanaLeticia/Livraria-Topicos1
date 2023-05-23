package com.bookstore.eagle.service;

import java.util.List;

import com.bookstore.eagle.dto.RequestedItemDTO;
import com.bookstore.eagle.dto.RequestedItemResponseDTO;

public interface RequestedItemService {

    List<RequestedItemResponseDTO> listRequestedItems();

    RequestedItemResponseDTO searchRequestedItemById(Long id);

    RequestedItemResponseDTO addRequestedItem(RequestedItemDTO requestedItemDTO);

    RequestedItemResponseDTO updateRequestedItem(Long id, RequestedItemDTO requestedItemDTO);

    void deleteRequestedItem(Long id);
}
