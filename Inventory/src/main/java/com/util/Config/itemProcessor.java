package com.util.Config;

import com.util.Inventory.Model.Item;
import org.springframework.batch.item.ItemProcessor;

public class itemProcessor implements ItemProcessor<Item, Item> {

    @Override
    public Item process(Item item) throws Exception {
        return item;
    }
}
