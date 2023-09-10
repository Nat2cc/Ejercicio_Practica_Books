package com.upc.ejpractica.controller;

import com.upc.ejpractica.dtos.BookDTO;
import com.upc.ejpractica.dtos.LoanDTO;
import com.upc.ejpractica.entities.Book;
import com.upc.ejpractica.entities.Loan;
import com.upc.ejpractica.interfaceservice.BookService;
import com.upc.ejpractica.interfaceservice.LoanService;
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
public class LoanController {
    @Autowired
    private LoanService loanService;
    @Autowired
    private BookService bookService;
    @PostMapping("/books/{id}/loans")
    public ResponseEntity<LoanDTO> registerLoan (@RequestBody LoanDTO loanDTO, @PathVariable(value="id") Long id){
        Loan loan;
        LoanDTO dto;
        Book book;
        BookDTO bookdto;
        try{
            loan = convertToEntity(loanDTO);
            book= bookService.ReturnBookByID(id);
            loan = loanService.registerLoan(loan, book);
            dto = convertToDTO(loan);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido registrar");
        }
        return new ResponseEntity<LoanDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/books/getLoans/{filterByCodeStudent}")
    public ResponseEntity<List<LoanDTO>> listLoansByCodeStudent(@PathVariable(value="filterByCodeStudent") String filterByCodeStudent){
        List<Loan> list;
        List<LoanDTO> listDTO = null;
        try {
            list = loanService.listBookByCodeStudent(filterByCodeStudent);
            listDTO = convertToListDTO(list);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no disponible");
        }
        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    private Loan convertToEntity(LoanDTO loanDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(loanDTO, Loan.class);
    }

    private LoanDTO convertToDTO(Loan loan){
        ModelMapper modelMapper =new ModelMapper();
        return modelMapper.map(loan, LoanDTO.class);
    }

    private List<LoanDTO> convertToListDTO(List<Loan> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
