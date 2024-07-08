package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ProductTest {


    private Product[] products;
    private ShopRepository repo;

    Product product1 = new Product(1, "Voter", 100);
    Product product2 = new Product(2, "Peper", 80);
    Product product3 = new Product(3, "lemon", 50);
    Product product4 = new Product(4, "cat", 200);
    Product product5 = new Product(4, "dog", 500);

    @BeforeEach
    public void setUp() {
        repo = new ShopRepository();
    }

    @Test
    public void deletingNonExistingObject() {
        repo.add(product1);
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(100);
        });
    }

    @Test
    public void successOfDeletingAnExistingElement() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.removeById(2);
        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void successfulAdditionOfElement() {
        repo.add(product4);
        Product[] expected = {product4};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addingAnElementWithADuplicateID() {
        repo.add(product4);

        Assertions.assertThrows(AlreadyExistsException.class, () ->
                repo.add(product5)
        );
    }
}
