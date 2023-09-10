package com.upc.ejpractica.interfaceservice;

import com.upc.ejpractica.entities.Book;
import com.upc.ejpractica.entities.Loan;

import java.util.List;

public interface LoanService {
    public Loan registerLoan(Loan loan, Book book) throws Exception;
    public Loan registerLoanDevolution(Loan loan);
    public List<Loan> listBookByCodeStudent(String codeStudent);
    public Loan SetBookCode(Loan lo1, Long code);
}
