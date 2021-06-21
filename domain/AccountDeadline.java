package domain;

import java.sql.Date;

public class AccountDeadline {
    int id;
    Date startDay;
    Date endDay;
    boolean isNone;

    public AccountDeadline() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public boolean isNone() {
        return isNone;
    }

    public void setNone(boolean none) {
        isNone = none;
    }

    @Override
    public String toString() {
        return "AccountDeadline{" +
                "id=" + id +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                '}';
    }
}
