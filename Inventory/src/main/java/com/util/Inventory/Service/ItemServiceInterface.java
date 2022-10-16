package com.util.Inventory.Service;




import java.util.Map;

public interface ItemServiceInterface {
     Map<String,Long> getAllItems(String supplierName,Integer pageNumber,Integer pageSize);
     Map<String,Long> getNotExpiredItems(String supplierName,Integer pageNumber,Integer pageSize);
}
