package org.example;

import org.example.Amazon.Amazon;
import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Database;
import org.example.Amazon.Item;
import org.example.Amazon.ShoppingCartAdaptor;
import org.example.Barnes.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmazonIntegrationTest {

//    @Override
//    public void add(Item item) {
//        connection.withSql(() -> {  // Executes SQL operations within the database connection
//            try (var ps = connection.getConnection().prepareStatement("insert into shoppingcart (name, type, quantity, priceperunit) values (?,?,?,?)")) {  // Prepares the SQL query to insert a new invoice
//                ps.setString(1, item.getName());  // Sets the customer name in the query
//                ps.setString(2, item.getType().name());  // Sets the invoice value in the query
//                ps.setInt(3, item.getQuantity());  // Sets the invoice value in the query
//                ps.setDouble(4, item.getPricePerUnit());  // Sets the invoice value in the query
//                ps.execute();  // Executes the insert query
//
//                connection.getConnection().commit();  // Commits the transaction to make the changes permanent
//            }
//            return null;  // Returns null as this operation does not need to return any value
//        });
//    }

    private Database db;
    private ShoppingCartAdaptor sca;
    private Amazon amazon;

    @BeforeEach
    void reset () {
        db = new Database();
        db.resetDatabase();

        sca = new ShoppingCartAdaptor(db);
        amazon = new Amazon(sca, new ArrayList<>());
    }

    //Specification-Based Testing




    //Structural-Based Testing

    @DisplayName("structural-based")
    @Test
    void cartAdd () {
        Item item = new Item(ItemType.ELECTRONIC, "Phone", 1, 799.99);
        sca.add(item);

        assertEquals(1, sca.getItems().size());
    }

    @DisplayName("structural-based")
    @Test
    void returnNumItems () {
        Item item = new Item(ItemType.ELECTRONIC, "Phone", 1, 799.99);
        Item item1 = new Item(ItemType.ELECTRONIC, "Phone", 1, 799.99);
        sca.add(item);
        sca.add(item1);
        sca.numberOfItems();

        assertEquals(0, sca.numberOfItems());
        db.close();
    }

    @DisplayName("structural-based")
    @Test
    void closeTest () {
        db.close();

        //db null now
        db.close();
    }

    @DisplayName("structural-based")
    @Test
    void addToCartTest () {
        Item item = new Item(ItemType.ELECTRONIC, "Phone", 1, 799.99);
        amazon.addToCart(item);
    }
}
