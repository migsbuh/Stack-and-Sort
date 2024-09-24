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

        public void addBook(String title, String author, String category) {
            Book newBook = new Book(title, author, category);
            books.add(newBook);
            lastInsertedBooks.push(newBook); // Store for undo
        }

        // Remove a book and push it to the undo stack
        public void removeBook(int index) {
            if (index >= 0 && index < books.size()) {
                Book removedBook = books.remove(index);
                lastInsertedBooks.push(removedBook); // Store the removed book for undo
            }
        }

        // Undo the last insertion operation (or last removal)
        public void undoLastAction() {
            if (!lastInsertedBooks.isEmpty()) {
                Book lastBook = lastInsertedBooks.pop();
                books.remove(lastBook); // Remove the book from the list if added or restored
            } else {
                System.out.println("No action to undo.");
            }
        }

        // Selection Sort implementation to sort books by title
        public void sortBooksByTitle() {
            int n = books.size();
            for (int i = 0; i < n - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < n; j++) {
                    if (books.get(j).title.compareTo(books.get(minIndex).title) < 0) {
                        minIndex = j;
                    }
                }
                // Swap the found minimum element with the first element
                Book temp = books.get(minIndex);
                books.set(minIndex, books.get(i));
                books.set(i, temp);
            }
        }

        // Display all books
        public void displayBooks() {
            if (books.isEmpty()) {
                System.out.println("No books in the library.");
            } else {
                for (Book book : books) {
                    System.out.println(book);
                }
            }
        }

