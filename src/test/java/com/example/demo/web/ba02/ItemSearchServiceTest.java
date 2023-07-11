package com.example.demo.web.ba02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.core.exception.AppException;
import com.example.demo.entity.Item;
import com.example.demo.web.mapper.ItemMapper;

@SpringBootTest
public class ItemSearchServiceTest {
    
    /** テスト対象、モックをInjectする対象 */
    @InjectMocks
    private ItemSearchService target;

    /** モックマッパー */
    @Mock
    private ItemMapper itemMapper;

    /**
     * 検索結果が無く、ゼロ件エラーが発生すること
     */
    @Test
    public void testCountZero() {

        when(itemMapper.countAll(any())).thenReturn(0L);
        ItemSearchCriteria criteria = new ItemSearchCriteria("aaa", null, PageRequest.of(0, 3));
        AppException e = assertThrows(AppException.class, () -> target.findAll(criteria));
        assertEquals("ME003", e.getMessageId());
    }

    /**
     * 1件ヒットすること
     */
    @Test
    public void testCountOne() {

        when(itemMapper.countAll(any())).thenReturn(1L);
        List<Item> list = new ArrayList<>();
        list.add(new Item(1, "ペン", 100, "CD-A01", LocalDate.now()));
        when(itemMapper.findAll(any())).thenReturn(list);

        ItemSearchCriteria criteria = new ItemSearchCriteria(null, null, PageRequest.of(0, 5));
        Page<Item> pages = target.findAll(criteria);
        assertEquals(0,pages.getPageable().getPageNumber());
        assertEquals(1,pages.getTotalPages());
        assertEquals(1, pages.getContent().get(0).getId());
        assertEquals("ペン", pages.getContent().get(0).getItemName());
        assertEquals(100, pages.getContent().get(0).getPrice());
        assertEquals("CD-A01", pages.getContent().get(0).getGroupid());
    }

    /**
     * 検索結果が1001件以上ヒットで、1000件超過エラーが発生すること
     */
    @Test
    public void testCountThousand() {

        when(itemMapper.countAll(any())).thenReturn(1001L);
        ItemSearchCriteria criteria = new ItemSearchCriteria("aaa", null, PageRequest.of(0, 3));
        AppException e = assertThrows(AppException.class, () -> target.findAll(criteria));
        assertEquals("ME002", e.getMessageId());
    }
}
