package com.upc.ejpractica.repositories;
import com.upc.ejpractica.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    public List<Book> findBookByEditorial(String editorial);
    public Book findBookById(Long id);
}
