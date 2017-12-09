package pl.com.bottega.hrs.model;

import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.hrs.infrastructure.StandardTimeProvider;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "dept_emp")
public class DepartmentAssignment {


    @Embeddable
    public static class DepartmentAssignmentId implements Serializable {

        @Column(name = "emp_no")
        private Integer empNo;

        @ManyToOne
        @JoinColumn(name = "dept_no")
        private Department department;

        protected DepartmentAssignmentId() {}

        public DepartmentAssignmentId(Integer empNo, Department department) {
            this.empNo = empNo;
            this.department = department;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DepartmentAssignmentId that = (DepartmentAssignmentId) o;

            if (!empNo.equals(that.empNo)) return false;
            return department != null ? department.equals(that.department) : that.department == null;
        }

        @Override
        public int hashCode() {
            int result = empNo.hashCode();
            result = 31 * result + (department != null ? department.hashCode() : 0);
            return result;
        }
    }

    @Transient
    @Autowired
    private TimeProvider timeProvider;

    @EmbeddedId
    private DepartmentAssignmentId id;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    protected DepartmentAssignment() {}

    public DepartmentAssignment(Integer empNo, Department department, TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
        id = new DepartmentAssignmentId(empNo, department);
        fromDate = timeProvider.today();
        toDate = TimeProvider.MAX_DATE;
    }

    public Department getDepartment() {
        return id.department;
    }

    public boolean isAssigned(Department department) {
        return isCurrent() &&
                department.equals(id.department);
    }

    public boolean isCurrent() {
        return toDate.isAfter(timeProvider.today());
    }

    public void unassign() {
        toDate = timeProvider.today();
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

}
