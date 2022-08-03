package Manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductManagerTest {

    Book book1 = new Book(001, "Тестирование ПО", 1000, "Святослав Куликов");
    Book book2 = new Book(002, "Tестирование dot com", 1000, "Роман Савин");
    Book book3 = new Book(003, "Тестирование черного ящика", 1000, "Борис Бейзер");
    Book book4 = new Book(003, "Тестирование черного ящика", 1000, "Борис Бейзер");
    Book book5 = new Book(005, "Автоматизированное тестирование программного обеспечения", 1000, "Элфрид Дастин, Джефф Рэшка, Джон Пол");

    Smartphone smartphone1 =new Smartphone(006, "Samsung", 5000, "Южная Корея");
    Smartphone smartphone2 = new Smartphone(7, "Xiaomi", 5000, "Китай");
    Smartphone smartphone3 = new Smartphone(8, "Huawei", 5000, "Китай");
    Smartphone smartphone4 = new Smartphone(9, "Nokia", 5000, "Финляндия");
    Smartphone smartphone5 = new Smartphone(010, "Sony", 5000, "Япония");

    Product product1 = new Product(011, "Шорты", 500);

    @Test
    public void checkForDeletingId () {

        ProductRepository repository = new ProductRepository();
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.save(book5);
        repository.removeById(04);

        ProductManager manager = new ProductManager(repository);

        Product[] expected = {book1, book2, book3, book5};
        Product[] actual = repository.findAll();

        assertArrayEquals (expected, actual);
    }

    @Test
    public void deletingNonexistentElement () {

        ProductRepository repository = new ProductRepository();
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.save(book5);
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.save(smartphone3);
        repository.save(smartphone4);
        repository.save(smartphone5);
        repository.save(product1);

        assertThrows(NotFoundException.class, () -> {repository.removeById(020);});
    }
}