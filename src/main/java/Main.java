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

      // Helper function to get valid input from user

      public static int getValidIntegerInput(Scanner scanner, String prompt, int min, int max) {

          int choice;

          do {

              System.out.print(prompt);

              while (!scanner.hasNextInt()) {

                  System.out.print("Invalid input. Please enter a number: ");

                  scanner.next();

              }

              choice = scanner.nextInt();

          } while (choice < min || choice > max);

          return choice;

      }

      public static void main(String[] args) {

          LibraryManagementSystem library = new LibraryManagementSystem();

          Scanner scanner = new Scanner(System.in);

          boolean running = true;

          while (running) {

              System.out.println("\nLibrary Management System Menu:");

              System.out.println("1. Add a Book");

              System.out.println("2. Display Books");

              System.out.println("3. Sort Books by Title");

              System.out.println("4. Remove a Book");

              System.out.println("5. Undo Last Action");

              System.out.println("6. Exit");



              int choice = getValidIntegerInput(scanner, "Choose an option (1-6): ", 1, 6);

               switch (choice) {
                              case 1: // Add a book
                                  System.out.print("Enter book title: ");
                                  scanner.nextLine(); // Clear buffer
                                  String title = scanner.nextLine();

                                  System.out.print("Enter book author: ");
                                  String author = scanner.nextLine();

                                  System.out.println("Choose a category:");
                                  for (int i = 0; i < categories.length; i++) {
                                      System.out.println((i + 1) + ". " + categories[i]);
                                  }
                                  int categoryChoice = getValidIntegerInput(scanner, "Enter category number (1-4): ", 1, categories.length);
                                  String category = categories[categoryChoice - 1];

                                  library.addBook(title, author, category);
                                  System.out.println("Book added successfully!");
                                  break;

                              case 2: // Display books
                                  library.displayBooks();
                                  break;

                              case 3: // Sort books by title
                                  library.sortBooksByTitle();
                                  System.out.println("Books sorted by title.");
                                  break;

                              case 4: // Remove a book
                                  library.displayBooks();
                                  int removeIndex = getValidIntegerInput(scanner, "Enter index of the book to remove (1 to " + library.books.size() + "): ", 1, library.books.size()) - 1;
                                  library.removeBook(removeIndex);
                                  System.out.println("Book removed successfully!");
                                  break;

                              case 5: // Undo last action
                                  library.undoLastAction();
                                  System.out.println("Undo last action (add/remove) executed.");
                                  break;

                              case 6: // Exit
                                  running = false;
                                  System.out.println("Exiting the system. Goodbye!");
                                  break;

                              default:
                                  System.out.println("Invalid choice. Try again.");
                          }
                      }

                      scanner.close();
                  }
              }
