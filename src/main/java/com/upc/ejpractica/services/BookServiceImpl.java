package com.upc.ejpractica.services;

import com.upc.ejpractica.entities.Book;
import com.upc.ejpractica.interfaceservice.BookService;
import com.upc.ejpractica.repositories.BookRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book registerBook(Book book) throws Exception {
        boolean flag=false;
        for (Book b1:bookRepository.findAll()) {
            if(b1.getEditorial().equals(book.getEditorial()) && b1.getTitle().equals(book.getTitle()))flag=true;
        }
        if(flag==false) {
            if (book.getTitle().isBlank()) throw new Exception("El título del libro debe ser obligatorio");
            else if (book.getEditorial().isBlank()) throw new Exception("La editorial del libro debe ser obligatorio");
            else return bookRepository.save(book);
        }
        else
        {
            throw new Exception("No se puede registrar el libro porque existe uno con el mismo título y editorial");
        }
    }
    public Book ReturnBookByID(Long id)
    {
        return bookRepository.findBookById(id);
    }
    @Override
    public List<Book> listBook() {
        return bookRepository.findAll();
    }
    public List<Book> listBookByEditorial(String editorial){
        return bookRepository.findBookByEditorial(editorial);
    }
}
