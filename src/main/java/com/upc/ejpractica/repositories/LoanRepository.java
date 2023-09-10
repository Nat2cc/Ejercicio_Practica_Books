package com.upc.ejpractica.repositories;

import com.upc.ejpractica.entities.Book;
import com.upc.ejpractica.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
   public List<Loan> findAllByCodeStudent(String wa);
}
