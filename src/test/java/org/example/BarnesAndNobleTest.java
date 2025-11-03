package org.example;

import org.example.Barnes.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class BarnesAndNobleTest {

    //Specification-Based Tests

    @DisplayName("specification-based")
    @Test
    void nullOrder () {
        BookDatabase mockDb = mock(BookDatabase.class);
        BuyBookProcess mockBb = mock(BuyBookProcess.class);

        BarnesAndNoble barn = new BarnesAndNoble(mockDb, mockBb);
        assertNull(barn.getPriceForCart(null));
    }

    @DisplayName("specification-based")
    @Test
    void emptyOrder () {
        BookDatabase mockDb = mock(BookDatabase.class);
        BuyBookProcess mockBb = mock(BuyBookProcess.class);

        HashMap<String, Integer> order = new HashMap<String, Integer>();
        BarnesAndNoble barn = new BarnesAndNoble(mockDb, mockBb);

        assertEquals(0.0, barn.getPriceForCart(order).getTotalPrice());
    }

    @DisplayName("specification-based")
    @Test
    void correctPrice () {
        BookDatabase mockDb = mock(BookDatabase.class);
        BuyBookProcess mockBb = mock(BuyBookProcess.class);

        HashMap<String, Integer> order = new HashMap<String, Integer>();
        BarnesAndNoble barn = new BarnesAndNoble(mockDb, mockBb);

        assertEquals(0.0, barn.getPriceForCart(order).getTotalPrice());
    }

    //Structural-Based Tests

    @DisplayName("structural-based")
    @Test
    void bookQuantityLess () {
        //create mocks
        BookDatabase mockDb = mock(BookDatabase.class);
        BuyBookProcess mockBb = mock(BuyBookProcess.class);


        Book book = new Book("2121", 10,2);

        when(mockDb.findByISBN("2121")).thenReturn(book);

        HashMap<String, Integer> order = new HashMap<String, Integer>();
        order.put("2121", 5);

        BarnesAndNoble barn = new BarnesAndNoble(mockDb, mockBb);
        PurchaseSummary summary = barn.getPriceForCart(order);

        verify(mockBb).buyBook(book,2);
    }

    @DisplayName("structural-based")
    @Test
    void bookQuantityMore () {
        //create mocks
        BookDatabase mockDb = mock(BookDatabase.class);
        BuyBookProcess mockBb = mock(BuyBookProcess.class);

        Book book = new Book("2121", 10,7);

        when(mockDb.findByISBN("2121")).thenReturn(book);

        HashMap<String, Integer> order = new HashMap<String, Integer>();
        order.put("2121", 5);

        BarnesAndNoble barn = new BarnesAndNoble(mockDb, mockBb);
        PurchaseSummary summary = barn.getPriceForCart(order);

        verify(mockBb).buyBook(book,5);
    }

    @DisplayName("structural-based")
    @Test
    void bookEquals () {
        Book book1 = new Book("2121", 10,7);
        Book book2 = new Book("2121", 10,7);
        int test = 0;
        assertEquals(true, book1.equals(book1));
        assertEquals(true, book1.equals(book2));
        assertEquals(false, book1.equals(test));
        assertEquals(false, book1.equals(null));
    }

}
