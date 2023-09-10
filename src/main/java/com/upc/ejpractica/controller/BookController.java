package com.upc.ejpractica.controller;

import com.upc.ejpractica.dtos.BookDTO;
import com.upc.ejpractica.entities.Book;
import com.upc.ejpractica.interfaceservice.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/library/v1")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<BookDTO> registerBook (@RequestBody BookDTO bookDTO){
        Book book;
        BookDTO dto;
        try{
            book = convertToEntity(bookDTO);
            book = bookService.registerBook(book);
            dto = convertToDTO(book);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido registrar");
        }
        return new ResponseEntity<BookDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> listBooks(){
        List<Book> list;
        List<BookDTO> listDTO=null;
        try {
            list = bookService.listBook();
            listDTO = convertToListDTO(list);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no disponible");
        }
        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    @GetMapping("/books/{filterByEditorial}")
    public ResponseEntity<List<BookDTO>> listBooksbyEditorial(@PathVariable(value="filterByEditorial") String filterByEditorial){
        List<Book> list;
        List<BookDTO> listDTO=null;
        try {
            list = bookService.listBookByEditorial(filterByEditorial);
            listDTO = convertToListDTO(list);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no disponible");
        }
        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    private Book convertToEntity(BookDTO bookDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookDTO, Book.class);
    }

    private BookDTO convertToDTO(Book book){
        ModelMapper modelMapper =new ModelMapper();
        return modelMapper.map(book, BookDTO.class);
    }

    private List<BookDTO> convertToListDTO(List<Book> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
