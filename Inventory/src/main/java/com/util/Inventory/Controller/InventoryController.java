package com.util.Inventory.Controller;


import com.util.Inventory.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {


    @Autowired
    private ItemService itemService;
    @GetMapping("/products/{supplierName}")
    public ResponseEntity<Map<String,Long>> getAllItem(@PathVariable String supplierName,
                                                       @RequestParam (value="pageNumber",defaultValue="10",required = false ) Integer pageNumber,
                                                       @RequestParam (value="pageSize", defaultValue="1",required = false) Integer pageSize){
        return new ResponseEntity<>(itemService.getAllItems(supplierName,pageNumber,pageSize), HttpStatus.OK);
    }
    @GetMapping("/products/fresh/{supplierName}")
    public ResponseEntity<Map<String,Long>> getNotExpiredItems(@PathVariable String supplierName,
                                                               @RequestParam (value="pageNumber",defaultValue="10",required = false ) Integer pageNumber,
                                                               @RequestParam (value="pageSize", defaultValue="1",required = false) Integer pageSize){
        return new ResponseEntity<>(itemService.getNotExpiredItems(supplierName,pageNumber,pageSize),HttpStatus.OK);
    }
}
