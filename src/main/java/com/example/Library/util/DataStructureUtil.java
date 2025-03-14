package com.example.Library.util;

import com.example.Library.model.Book;

import java.util.*;

public class DataStructureUtil {
    private Map<Long, Book> bookMap = new HashMap<>();
    private Queue<String> bookQueue = new LinkedList<>();
    private Set<String> authorSet = new HashSet<>();
    private String[] genres = {"Fiction", "Non-fiction", "Science", "History"};
    public void processGenre(String genre) {
        boolean found = false;
        for(String g : genres) {
            if(g.equalsIgnoreCase(genre)) {
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Genre '" + genre + "' is not among the predefined genres.");
        }
    }

    public void processBookList(List<Book> books) {
        for(Book book : books) {
            authorSet.add(book.getAuthor());
        }
        System.out.println("Unique Authors: " + authorSet);
    }

    public void addBookToMap(Book book) {
        bookMap.put(book.getId(), book);
        bookQueue.offer(book.getTitle());
    }

    public String getNextBookTitleInQueue() {
        return bookQueue.poll();
    }

}
