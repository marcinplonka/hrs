package pl.com.bottega.hrs.ui.listeners;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import pl.com.bottega.hrs.model.Employee;
import pl.com.bottega.hrs.model.events.SalaryChangedEvent;
import pl.com.bottega.hrs.model.repositories.EmployeeRepository;


@Component
public class SalaryChangedNotifier {

    private EmployeeRepository employeeRepository;

    public SalaryChangedNotifier(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @TransactionalEventListener
    @Async
    public void salaryChanged(SalaryChangedEvent event) {
        Logger.getLogger(SalaryChangedNotifier.class).info("Starting email sending");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Employee employee = employeeRepository.get(event.getEmpNo());
        Logger.getLogger(SalaryChangedNotifier.class).info("Sending email to " + employee.getLastName());
    }

}
