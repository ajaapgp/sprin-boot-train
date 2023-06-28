package com.example.demo.web.ba01;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.example.demo.core.exception.AppException;
import com.example.demo.entity.Item;
import com.example.demo.web.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

/**
 * item登録サービス
 */
@Service
@RequiredArgsConstructor
public class ItemRegistService {
    
    /** itemMapper */
    private final ItemMapper mapper;

    /**
     * itemを登録する
     * @param item Item
     */
    public void registItem(Item item) {
        
        // 同一ItemNameの合計priceが3000円以上の場合、チェックエラー
        List<Item> list = mapper.findAllByItemName(item.getItemName());
        Integer totalPrice = item.getPrice();
        for (Item item2 : list) {
            totalPrice += item2.getPrice();
        }
        if (totalPrice >= 3000) {
            throw new AppException("ME001");
        }

        try {
            // 登録処理
            mapper.insertItem(item);
        } catch (DuplicateKeyException e) {
            // キー重複エラー
            throw new AppException("ME004", e);
        }
    }
}
