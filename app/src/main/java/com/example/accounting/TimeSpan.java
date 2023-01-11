package com.example.accounting;

import java.sql.Time;
import java.time.LocalDate;

public class TimeSpan {
  LocalDate startDate;
  LocalDate endDate;

    public TimeSpan(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


}
