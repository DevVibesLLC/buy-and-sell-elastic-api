package am.devvibes.buyandsellelastic.service;

import am.devvibes.buyandsellelastic.dto.FilterDto;
import am.devvibes.buyandsellelastic.entity.ItemEntity;

import java.util.List;

public interface ItemService {

	ItemEntity findItemById(Long id);

	List<ItemEntity> findAllItems();

	List<ItemEntity> filterItems(FilterDto filterDto);

}
