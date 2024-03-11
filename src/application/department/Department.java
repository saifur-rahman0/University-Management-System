package application.department;

public class Department {

    private String deptcode;
    private int semoryear;
    private boolean isdeclared;
    private String deptname;


    public void setDeptName(String deptname) {
        this.deptname = deptname;
    }

    public void setDeptCode(String deptcode) {
        this.deptcode = deptcode;
    }

    public void setSemorYear(int semoryear) {
        this.semoryear = semoryear;
    }

    public void setIsDeclared(boolean isdeclared) {
        this.isdeclared = isdeclared;
    }

    public String getDeptName() {
        return deptname != null ? deptname : new DepartmentData().getdeptname(deptcode);
    }

    public String getDeptCode() {
        return deptcode;
    }

    public int getSemorYear() {
        return semoryear;
    }

    public boolean getIsDeclared() {
        return isdeclared;
    }
}
