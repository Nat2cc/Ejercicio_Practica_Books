package com.upc.ejpractica.interfaceservice;

import com.upc.ejpractica.entities.Book;

import java.util.List;

public interface BookService {
    public Book registerBook(Book book) throws Exception;
    public List<Book> listBook();
    public List<Book> listBookByEditorial(String editorial);
    public Book ReturnBookByID(Long id);
}
