package com.anurag.ExpenseTracker.service;

import com.anurag.ExpenseTracker.model.Expense;
import com.anurag.ExpenseTracker.model.User;
import com.anurag.ExpenseTracker.model.dto.EmailDetails;
import com.anurag.ExpenseTracker.model.dto.ExpenseMultiOutput;
import com.anurag.ExpenseTracker.model.dto.ExpenseRequest;
import com.anurag.ExpenseTracker.model.dto.ExpenseSingleOutput;
import com.anurag.ExpenseTracker.repository.IExpenseRepo;
import com.anurag.ExpenseTracker.repository.IUserRepo;
import com.anurag.ExpenseTracker.service.utility.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service

public class ExpenseService {
    @Autowired
    IExpenseRepo iExpenseRepo;

    @Autowired
    IUserRepo iUserRepo;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;


    public ExpenseSingleOutput createExpense(String email, ExpenseRequest expenseRequest) {

        User existingUser = iUserRepo.findFirstByUserEmail(email);

        Expense newExpense = Expense.builder()
                .expenseTitle(expenseRequest.getExpenseTitle())
                .expenseDescription(expenseRequest.getExpenseDescription())
                .expensePrice(expenseRequest.getExpensePrice())
                .user(existingUser)
                .build();

       Expense savedExpense = iExpenseRepo.save(newExpense);

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedExpense.getUser().getUserEmail())
                .subject("Expense Update")
                .messageBody("Congratulations! Your Expense Has been Successfully Created.\nYour Account Details: \n" +
                        "Account Name: " + savedExpense.getUser().getUserFirstName() + " "
                        + savedExpense.getUser().getUserLastName()
                        + "\n" + savedExpense )
                .build();

        emailService.sendEmailAlert(emailDetails);

        return ExpenseSingleOutput.builder()

                .expenseStatus(true)
                .expenseStatusMessage("Your Expense Added Successfully also sent over Mail")
                .expenseSingle(savedExpense)
                .build();
    }


    public ExpenseMultiOutput generateExpense(String email, String startDate, String endDate) {

        LocalDate start =LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end =LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        List<Expense> expenseList= iExpenseRepo.findAll().stream()
                .filter(expense ->expense.getUser().getUserEmail().equals(email))
                .filter(expense -> expense.getCreatedAtDate().isEqual(start))
                .filter(expense -> expense.getCreatedAtDate().isEqual(end)).toList();

        User existingUser = iUserRepo.findFirstByUserEmail(email);

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(existingUser.getUserEmail())
                .subject("Expense Report")
                .messageBody("Your Account Details: \n" +
                        "Account Name: " + existingUser.getUserFirstName() + " "
                        + existingUser.getUserLastName()
                        + "\n Your Expense Report from " +startDate + " to " +endDate  +"\n"+ expenseList )
                .build();
        emailService.sendEmailAlert(emailDetails);

        return ExpenseMultiOutput.builder()
                .expenseStatus(true)
                .expenseStatusMessage("Your Expense Report is sent Over Mail")
                .expenseReport(expenseList)
                .build();

    }


    public ExpenseMultiOutput generateMonthlyExpense(String email, String month) {


        List<Expense> expenseList = iExpenseRepo.findAll().stream()
                .filter(expense ->expense.getUser().getUserEmail().equals(email))
                .filter(expense -> expense.getCreatedAtDate().toString().substring(5,7).equals(month))
                .toList();

        User existingUser = iUserRepo.findFirstByUserEmail(email);

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(existingUser.getUserEmail())
                .subject("Expense Report")
                .messageBody("Your Account Details: \n" +
                        "Account Name: " + existingUser.getUserFirstName() + " "
                        + existingUser.getUserLastName()
                        + "\n Your Expense Report for month: " + month +"\n"+ expenseList )
                .build();
        emailService.sendEmailAlert(emailDetails);

        return ExpenseMultiOutput.builder()

                .expenseStatus(true)
                .expenseStatusMessage("Your Monthly Expense Report is sent Over Mail")
                .expenseReport(expenseList)

                .build();





    }


}
