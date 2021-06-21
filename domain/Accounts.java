package domain;

public class Accounts {
    int id;
    String departmentName;
    int empNo;
    String name;
    String bankName;
    String accNum;

    public Accounts(int id, String departmentName, int empNo, String name, String bankName, String accNum) {
        this.id = id;
        this.departmentName = departmentName;
        this.empNo = empNo;
        this.name = name;
        this.bankName = bankName;
        this.accNum = accNum;
    }

    public Accounts(String departmentName, int empNo, String name, String bankName, String accNum, int id) {
        this.departmentName = departmentName;
        this.empNo = empNo;
        this.name = name;
        this.bankName = bankName;
        this.accNum = accNum;
        this.id = id;
    }

    public Accounts(int id, int empNo, String bankName, String accNum) {
        this.id = id;
        this.empNo = empNo;
        this.bankName = bankName;
        this.accNum = accNum;
    }

    public Accounts() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }
}
