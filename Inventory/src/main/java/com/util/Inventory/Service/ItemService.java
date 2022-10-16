package com.util.Inventory.Service;


import com.util.Inventory.ItemRepository;
import com.util.Inventory.Model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ItemService implements  ItemServiceInterface{
    @Autowired
    private ItemRepository itemRepository;


    @Override
    public Map<String,Long> getAllItems(String supplierName,Integer pageNumber,Integer pageSize) {
        Pageable p= PageRequest.of(pageNumber,pageSize);
        List<Item> itemsList= (List<Item>) itemRepository.findAll(p);
        Map<String,Long> result=new HashMap<>();
        for(Item item:itemsList){
            if(item.getSupplier().equals(supplierName)){
                if(!result.containsKey(item.getName()))
                    result.put(item.getName(),item.getStock());
                else
                    result.put(item.getName(),result.get(item.getName())+item.getStock());
            }
        }
        return result;
    }

    @Override
    public Map<String, Long> getNotExpiredItems(String supplierName,Integer pageNumber,Integer pageSize) {
        Pageable p= PageRequest.of(pageNumber,pageSize);
        List<Item> itemsList= (List<Item>) itemRepository.findAll(p);
        Map<String,Long> result=new HashMap<>();

        for(Item item:itemsList){
            if(item.getSupplier().equals(supplierName) &&
               item.getExpiry().after(new Date())){
                if(!result.containsKey(item.getName())){
                    result.put(item.getName(),item.getStock());
                }
                else{
                    result.put(item.getName(), result.get(item.getName())+item.getStock());
                }
            }
        }
        return result;
    }
}
