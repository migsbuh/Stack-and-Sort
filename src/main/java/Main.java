import java.util.ArrayList;
import java.util.Stack;

class Book{
  String title;
  String author;
  String category;

  public Book(String title, String author, String category){
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

      

      private static final String[] categories = {"Fiction", "Non-Fiction", "Science", "History"};

      

      private ArrayList<Book> books = new ArrayList<>();



      

      private Stack<Book> removedBooksStack = new Stack<>();