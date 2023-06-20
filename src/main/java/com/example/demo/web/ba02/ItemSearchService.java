package com.example.demo.web.ba02;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Item;

@Service
public class ItemSearchService {
    
    public Page<Item> findAll(ItemSearchCriteria criteria){

        System.out.println("PageSize:" + criteria.getPageable().getPageSize() + ", Offset:" + criteria.getPageable().getOffset() + ", ItemName:" + criteria.getItemName() + ", Price:" + criteria.getPrice());

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, "ペン", 100));
        itemList.add(new Item(2, "ノート", 200));
        itemList.add(new Item(3, "コンパス", 300));
        itemList.add(new Item(4, "消しゴム", 100));
        itemList.add(new Item(5, "えんぴつ", 100));

        return new PageImpl<Item>(itemList, criteria.getPageable(), 15);
    }
}
