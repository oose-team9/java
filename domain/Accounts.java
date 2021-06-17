package domain;

public class Accounts {
    int id;
    int empNo;
    String backName;
    String accNum;

    public Accounts(int id, int empNo, String backName, String accNum) {
        this.id = id;
        this.empNo = empNo;
        this.backName = backName;
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

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getBackName() {
        return backName;
    }

    public void setBackName(String backName) {
        this.backName = backName;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }
}
