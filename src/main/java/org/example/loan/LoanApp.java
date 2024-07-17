package org.example.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoanApp {

    public static void main(String[] args) {
        System.out.println("Loan App");
        SpringApplication.run(LoanApp.class,args);
    }
}
