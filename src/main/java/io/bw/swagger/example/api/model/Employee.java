package io.bw.swagger.example.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Byungwook Lee on 2018-01-23
 * quddnr153@gmail.com
 * https://github.com/quddnr153
 */
@Getter
@NoArgsConstructor
public class Employee {
    private long employeeSeq;
    private String employeeId;
    private String employeeName;
    private String employeePassword;
    private LocalDateTime requestDate;
    private LocalDateTime modifyDate;
    private String status;

    private Employee(Builder builder) {
        employeeSeq = builder.employeeSeq;
        employeeId = builder.employeeId;
        employeeName = builder.employeeName;
        employeePassword = builder.employeePassword;
        requestDate = builder.requestDate;
        modifyDate = builder.modifyDate;
        status = builder.status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private long employeeSeq;
        private String employeeId;
        private String employeeName;
        private String employeePassword;
        private LocalDateTime requestDate;
        private LocalDateTime modifyDate;
        private String status;

        private Builder() {}

        public Builder withSeq(final long employeeSeq) {
            this.employeeSeq = employeeSeq;
            return this;
        }

        public Builder withId(final String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder withName(final String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public Builder withPassword(final String employeePassword) {
            this.employeePassword = employeePassword;
            return this;
        }

        public Builder withRequestDate(final LocalDateTime requestDate) {
            this.requestDate = requestDate;
            return this;
        }

        public Builder withModifyDate(final LocalDateTime modifyDate) {
            this.modifyDate = modifyDate;
            return this;
        }

        public Builder withStatus(final String status) {
            this.status = status;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
