package domain;

import java.sql.Date;

public class Employees {
    int empNo;
    String departmentName;
    String name;
    String rank;
    Date birthday;
    String phone;
    String address;
    Date enteredDate;

    public Employees(int empNo, String departmentName, String name) {
        this.empNo = empNo;
        this.departmentName = departmentName;
        this.name = name;
    }

    public Employees(int empNo, String departmentName, String name, String rank, Date birthday, String phone, String address, Date enteredDate) {
        this.empNo = empNo;
        this.departmentName = departmentName;
        this.name = name;
        this.rank = rank;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.enteredDate = enteredDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }
}
