package pl.com.bottega.hrs.model;

import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.hrs.infrastructure.StandardTimeProvider;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "salaries")
public class Salary {

    @Embeddable
    public static class SalaryId implements Serializable {

        @Column(name = "emp_no")
        private Integer empNo;

        @Transient
        private TimeProvider timeProvider;

        @Column(name = "from_date")
        private LocalDate fromDate;

        protected SalaryId() {
        }

        public SalaryId(Integer empNo, TimeProvider timeProvider) {
            this.empNo = empNo;
            this.timeProvider = timeProvider;
            this.fromDate = timeProvider.today();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SalaryId salaryId = (SalaryId) o;

            if (!empNo.equals(salaryId.empNo)) return false;
            return fromDate.equals(salaryId.fromDate);
        }

        @Override
        public int hashCode() {
            int result = empNo.hashCode();
            result = 31 * result + fromDate.hashCode();
            return result;
        }

        public boolean startsToday() {
            return fromDate.isEqual(timeProvider.today());
        }

    }
    @EmbeddedId
    private SalaryId id;

    private Integer salary;

    @Transient
    private TimeProvider timeProvider;

    @Column(name = "to_date")
    private LocalDate toDate;

    protected Salary() {}

    public Salary(Integer empNo, Integer salary, TimeProvider timeProvider) {
        id = new SalaryId(empNo, timeProvider);
        this.salary = salary;
        this.timeProvider = timeProvider;
        toDate = TimeProvider.MAX_DATE;
    }

    public boolean isCurrent() {
        return toDate.isAfter(timeProvider.today());
    }

    public void terminate() {
        toDate = timeProvider.today();
    }

    public boolean startsToday() {
        return id.startsToday();
    }

    public int getValue() {
        return salary;
    }

    public LocalDate getFromDate() {
        return id.fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void update(Integer newSalary) {
        this.salary = newSalary;
    }

    @Autowired
    private void setTimeProvider(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
        id.timeProvider = timeProvider;
    }

}
