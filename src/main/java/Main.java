    import java.util.ArrayList;

    import java.util.Scanner;

    import java.util.Stack;

    class Book {

        String title;

        String author;

        String category;

        public Book(String title, String author, String category) {

            this.title = title;

            this.author = author;

            this.category = category;

        }

        @Override

        public String toString() {

            return "Title: " + title + ", Author: " + author + ", Category: " + category;

        }

    }

    public class LibraryManagementSystem {

        // Fixed-size array of categories

        private static final String[] categories = {"Fiction", "Non-Fiction", "Science", "History"};

        // Dynamic list of books

        private ArrayList<Book> books = new ArrayList<>();



        // Stack for undo operations (for both remove and add operations)

        private Stack<Book> lastInsertedBooks = new Stack<>();