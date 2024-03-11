package application.student;

import application.common.Person;
import application.common.TimeUtil;
import application.department.DepartmentData;

public class Student extends Person {

    private long rollnumber;
    private String optionalCourse;
    private String firstname;
    private String lastname;
    private String fathername;
    private String fatheroccupation;
    private String mothername;
    private String motheroccupation;
    private String userid;
    private String admissiondate;

    public void setAdmissionDate(String admissiondate) {
        this.admissiondate = admissiondate;
    }

    public void setRollNumber(long rollnumber) {
        this.rollnumber = rollnumber;
    }

    public void setOptionalCourse(String optionalCourse) {
        this.optionalCourse = optionalCourse;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setFatherName(String fathername) {
        this.fathername = fathername;
    }

    public void setFatherOccupation(String fatheroccupation) {
        this.fatheroccupation = fatheroccupation;
    }

    public void setMotherName(String mothername) {
        this.mothername = mothername;
    }

    public void setMotherOccupation(String motheroccupation) {
        this.motheroccupation = motheroccupation;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

    public String getDeptName() {
        return new DepartmentData().getdeptname(this.getDeptCode());
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public long getRollNumber() {
        return rollnumber;
    }

    public String getOptionalCourse() {
        return optionalCourse;
    }

    public String getAdmissionDate() {
        return admissiondate;

    }

    public String generateAdmissionDate() {
        admissiondate = TimeUtil.getCurrentTime();
        return admissiondate;
    }

    public String getFatherName() {
        return fathername;
    }

    public String getMotherName() {
        return mothername;
    }

    public String getFatherOccupation() {
        return fatheroccupation;
    }

    public String getMotherOccupation() {
        return motheroccupation;
    }

    public String getUserId() {
        return userid;
    }

    public String generateUserId() {
        userid = getDeptCode() + "-" + getSemorYear() + "-" + rollnumber;
        return userid;
    }

}
