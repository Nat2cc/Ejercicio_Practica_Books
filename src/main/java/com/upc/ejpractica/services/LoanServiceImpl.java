package com.upc.ejpractica.services;

import com.upc.ejpractica.entities.Book;
import com.upc.ejpractica.entities.Loan;
import com.upc.ejpractica.interfaceservice.LoanService;
import com.upc.ejpractica.repositories.BookRepository;
import com.upc.ejpractica.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BookRepository bookRepository;
    public Loan registerLoan(Loan loan, Book book) throws Exception{
        boolean flag=false;
        for (Loan a: loanRepository.findAll()) {
            if(a.getCodeStudent().equals(loan.getCodeStudent()) && a.getBook().getId().equals(loan.getBook().getId()))
                flag=true;
        }
        if(flag==true)
        {
            throw new Exception( "El préstamo del libro con código " + loan.getBook().getTitle() + " no es posible porque ya fue prestado por el alumno " +  loan.getCodeStudent());
        }
        else {
            Loan proof=loan;
            if(proof.getCodeStudent().isBlank())throw new Exception("El codigo del alumno debe ser obligatorio");
            else
                if(proof.getCodeStudent().length()<10)throw new Exception("El código del alumno debe tener 10 caracteres");
                else{
                    proof.setLoanDate(LocalDate.now());
                    proof.setBookLoan(true); //regla
                    proof.setBook(book);
                    return loanRepository.save(proof);
                }
        }
    }
    public Loan SetBookCode(Loan lo1, Long code){
        Book proof=bookRepository.findBookById(code);
        lo1.setBook(proof);
        return lo1;
    }
    public Loan registerLoanDevolution(Loan loan){
        Loan proof=loan;
        proof.setDevolutionDate(LocalDate.now());
        return loanRepository.save(proof);
    }
    public List<Loan> listBookByCodeStudent(String codeStudent) {
        return loanRepository.findAllByCodeStudent(codeStudent);
    }
}
