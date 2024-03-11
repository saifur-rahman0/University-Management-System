package application.department;

import application.common.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;

/*
 * Title : UserData.java
 * Purpose : Handling all the data related to dept
 */
public class DepartmentData {
    static Connection con = DataBaseConnection.getConnection();

    public static void closeConnection() throws SQLException {
        con.close();
    }

    public int addDept(String deptcode, String deptname, String semoryear, int totalyearorsem) {
        int result = 0;
        try {
            String query = "insert into departments values(?,?,?,?,?)";
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, 0);
            pr.setString(2, deptcode.toUpperCase());
            pr.setString(3, deptname);
            pr.setString(4, semoryear);
            pr.setInt(5, totalyearorsem);
            result = pr.executeUpdate();
            pr.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public ResultSet getDeptinfo() {
        ResultSet st = null;
        try {
            String query = "select c.sr_no as 'Index no.',c.Departmentcode as 'Dept Code' ,c.DepartmentName as 'Dept Name',(select count(*) from courses where courses.Departmentcode=c.Departmentcode) as 'Courses' ,(select count(*) from students where students.Departmentcode=c.Departmentcode) as 'Students',concat(c.totalsemoryear,' ',c.semoryear) as 'Total Sem/Year' from departments c;";
            PreparedStatement pr = con.prepareStatement(query);

            st = pr.executeQuery();
            return st;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return st;
    }

    public int getTotalDepartment() {
        int totalrow = 0;
        try {
            Statement pr = con.createStatement();
            ResultSet st = pr.executeQuery("select * from departments");
            while (st.next()) {
                totalrow++;
            }
            pr.close();

            return totalrow;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return totalrow;
    }

    public String[] getDeptName() {
        String[] deptname;
        int i = 0;
        deptname = new String[getTotalDepartment() + 1];
        deptname[i++] = "---Select Department---";
        try{
            Statement pr = con.createStatement();
            ResultSet st = pr.executeQuery("select DepartmentName from departments");
            while (st.next()) {
                deptname[i++] = st.getString(1);
            }
            pr.close();
            st.close();
            return deptname;
        }catch (Exception exp) {
            exp.printStackTrace();
        }
        return deptname;
    }

    public int getRollTotalDepartment() {
        int totalrow = 0;
        try {
            Statement pr = con.createStatement();
            ResultSet st = pr.executeQuery("select DepartmentName from departments where Departmentcode Not IN(select distinct Departmentcode from rollgenerator)");
            while (st.next()) {
                totalrow++;
            }
            pr.close();
            st.close();
            return totalrow;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return totalrow;
    }

    public String[] getRollDeptName() {
        String[] deptname;
        int i = 0;
        deptname = new String[getRollTotalDepartment() + 1];
        deptname[i++] = "---select---";
        try {
            Statement pr = con.createStatement();
            ResultSet st = pr.executeQuery("select DepartmentName from departments where Departmentcode NOT IN(select distinct Departmentcode from rollgenerator)");
            while (st.next()) {
                deptname[i++] = st.getString(1);
            }
            pr.close();
            st.close();
            return deptname;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return deptname;
    }

    public String[] getSemorYear(String Deptname) {
        String query = "select semoryear, totalsemoryear from departments where DepartmentName='" + Deptname + "'";
        String[] totalsem = new String[1];
        totalsem[0] = "---Select Sem/Year---";
        if (!Deptname.contains("--select--")) {
            try {
                Statement pr = con.createStatement();
                ResultSet st = pr.executeQuery(query);
                st.next();
                String semoryear = st.getString(1);
                int totalsemoryear = st.getInt(2);

                totalsem = new String[totalsemoryear + 1];
                if (semoryear.contains("sem")) {
                    semoryear = "Semester";
                } else {
                    semoryear = "Year";
                }
                totalsem[0] = "---Select " + semoryear + "---";
                for (int i = 1; i <= totalsemoryear; i++) {
                    totalsem[i] = semoryear + " " + i;
                }
                pr.close();
                st.close();
                return totalsem;
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        return totalsem;
    }

    public String[] getDeptcode() {
        String[] deptcode = new String[this.getTotalDepartment()];
        String query = "select Departmentcode from departments";
        try {
            Statement pr = con.createStatement();
            ResultSet st = pr.executeQuery(query);
            int i = 0;
            while (st.next()) {
                deptcode[i++] = st.getString(1);
            }
            pr.close();
            st.close();

            return deptcode;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return deptcode;
    }

    public String getDeptcode(String deptname) {
        String query = "select Departmentcode from departments where DepartmentName='" + deptname + "'";
        String deptcode = null;
        try {
            Statement pr = con.createStatement();
            ResultSet st = pr.executeQuery(query);
            st.next();
            deptcode = st.getString(1);
            pr.close();
            st.close();
            return deptcode;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return deptcode;
    }

    public String getsemoryear(String deptcode) {
        String query = "select semoryear from departments where Departmentcode='" + deptcode + "'";
        String semoryear = null;
        try {
            Statement pr = con.createStatement();
            ResultSet st = pr.executeQuery(query);

            st.next();
            semoryear = st.getString(1);
            pr.close();
            st.close();
            return semoryear;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return semoryear;
    }

    public String getdeptname(String deptcode) {
        String query = "select DepartmentName from departments where Departmentcode='" + deptcode + "'";
        String deptname = null;
        try {
            Statement pr = con.createStatement();
            ResultSet st = pr.executeQuery(query);

            st.next();
            deptname = st.getString(1);

            pr.close();
            st.close();
            return deptname;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return deptname;
    }

    public int getTotalsemoryear(String deptname) {
        String query = "select totalsemoryear from departments where DepartmentName='" + deptname + "'";
        int totalsemoryear = 0;
        try {
            Statement pr = con.createStatement();
            ResultSet st = pr.executeQuery(query);
            while (st.next()) {
                totalsemoryear = st.getInt(1);
            }
            pr.close();
            st.close();

            return totalsemoryear;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return totalsemoryear;
    }

    public boolean isDeptCodeExist(String deptcode) {
        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from departments where Departmentcode='" + deptcode + "'");
            rs.next();
            if (rs.getInt(1) > 0) {
                return true;
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isDepteNameExist(String deptname) {
        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from departments where DepartmentName='" + deptname + "'");
            rs.next();
            if (rs.getInt(1) > 0) {
                return true;
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isDeclared(String deptcode, int semoryear) {
        boolean isdeclared = false;
        try {
            String query = "select isdeclared from result where Departmentcode='" + deptcode + "' and semoryear=" + semoryear;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                isdeclared = rs.getBoolean(1);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return isdeclared;
    }

    public ArrayList<Department> getDeptsForDeclareResult(String deptname) {
        ArrayList<Department> list = new ArrayList<Department>();
        try {
            String query = "select DepartmentName,Departmentcode,totalsemoryear from departments where DepartmentName='" + deptname + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int totalsem = rs.getInt(3);
                for (int i = 0; i < totalsem; i++) {
                    Department department = new Department();
                    department.setDeptName(rs.getString(1));
                    department.setDeptCode(rs.getString(2));
                    department.setSemorYear((i + 1));
                    department.setIsDeclared(isDeclared(rs.getString(2), (i + 1)));
                    list.add(department);
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }

    public int updateResult(Department c) {
        int result = 0;
        try {
            String query = "update result set isdeclared=" + c.getIsDeclared() + " where Departmentcode='" + c.getDeptCode() + "' and semoryear=" + c.getSemorYear();
            PreparedStatement pr = con.prepareStatement(query);
            result = pr.executeUpdate();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public void declareResult(Department c) {
        try {
            if (updateResult(c) == 0) {
                String query = "insert into result values(?,?,?)";
                PreparedStatement pr = con.prepareStatement(query);
                pr.setString(1, c.getDeptCode());
                pr.setInt(2, c.getSemorYear());
                pr.setBoolean(3, c.getIsDeclared());
                pr.executeUpdate();
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
