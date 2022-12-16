import enums.Gender;
import enums.Genre;
import enums.Language;
import exception.UniqueConstrainException;
import model.Book;
import model.User;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static BookServiceImpl bookService = new BookServiceImpl();
    public static UserServiceImpl userService = new UserServiceImpl();
    public static void main(String[] args) {
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();
        List<Book> books4 = new ArrayList<>();
        User user1 = new User(1L,"Iskhak","Abdukhamitov","Iskhak@gmail.com","+996123123123", Gender.FEMALE,new BigDecimal(2000),books1);
        User user2 = new User(2L,"Alibek","Altynbek Uulu","Alibek@gmail.com","+996123456789", Gender.MALE,new BigDecimal(2000),books2);
        User user3 = new User(3L,"Ilim","Shabdanov","Ilim@gmail.com","+996987654321", Gender.FEMALE,new BigDecimal(2000),books3);
        User user4 = new User(4L,"Rahim","Bazarbay Uulu","Rahim@gmail.com","+996112233445", Gender.MALE,new BigDecimal(2000),books4);
        List<User>users = new ArrayList<>(List.of(user1,user2,user3,user4));

        Book book1 = new Book(1L,"Harry Potter", Genre.FANTASY,new BigDecimal(500),"Iskhak", Language.ENGLISH, LocalDate.of(2002,2,6));
        Book book2 = new Book(2L,"Alchemical", Genre.DETECTIVE,new BigDecimal(200),"Alibek", Language.KYRGYZ, LocalDate.of(2000,11,10));
        Book book3 = new Book(3L,"Getsbi", Genre.ROMANCE,new BigDecimal(300),"Ilim", Language.RUSSIAN, LocalDate.of(1999,7,21));
        Book book4 = new Book(4L,"Book", Genre.HISTORICAL,new BigDecimal(100),"Rahim", Language.ENGLISH, LocalDate.of(2012,4,2));
        List<Book> books = new ArrayList<>(List.of(book1,book2,book3,book4));

        command(books,users);
    }
    public static void command(List<Book> books, List<User> users) {
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("""
                    1 -> Book,
                    2 -> User
                    """);
            int number = new Scanner(System.in).nextInt();
            boolean isTrue1 = true;
            while (isTrue1) {
                switch (number) {
                    case 1:
                        System.out.println("""
                                1 -> Create,
                                2 -> Show all books,
                                3 -> Find book by gener,
                                4 -> Remove book by Id,
                                5 -> Sort books by price,
                                6 -> Filter books by publisher year,
                                7 -> Find book by initial letter,
                                8 -> Show book with max price;
                                """);
                        int choice = new Scanner(System.in).nextInt();
                            switch (choice) {
                                case 1 -> System.out.println(bookService.createBooks(books));
                                case 2 -> System.out.println(bookService.getAllBooks());
                                case 3 -> System.out.println(bookService.getBooksByGenre(new Scanner(System.in).nextLine()));
                                case 4 -> System.out.println(bookService.removeBookById(new Scanner(System.in).nextLong()));
                                case 5 -> System.out.println(bookService.sortBooksByPriceInDescendingOrder());
                                case 6 -> System.out.println(bookService.filterBooksByPublishedYear());
                                case 7 -> System.out.println(bookService.getBookByInitialLetter());
                                case 8 -> System.out.println(bookService.maxPriceBook());
                                case 0 -> isTrue1 = false;
                        }
                        break;
                    case 2:
                        System.out.println("""
                                1 -> Create,
                                2 -> Show all user,
                                3 -> Find by user ID,
                                4 -> Remove user by name,
                                5 -> Update user,
                                6 -> Group user by gender,
                                7 -> Buy book,
                                """);
                        int choice1 = new Scanner(System.in).nextInt();
                            switch (choice1) {
                                case 1 -> System.out.println(userService.createUser(users));
                                case 2 -> System.out.println(userService.findAllUsers());
                                case 3 -> System.out.println(userService.findUserById(new Scanner(System.in).nextLong()));
                                case 4 -> System.out.println(userService.removeUserByName(new Scanner(System.in).next()));
                                case 5 -> userService.updateUser(new Scanner(System.in).nextLong());
                                case 6 -> userService.groupUsersByGender();
                                case 7 -> System.out.println(userService.buyBooks(null, bookService.getAllBooks()));
                                case 0 -> isTrue1 = false;
                        }
                        break;
                    case 0:
                        isTrue1 = false;
                        break;
                }
            }
        }
    }
}