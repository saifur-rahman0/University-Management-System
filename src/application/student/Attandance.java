package application.student;

import application.course.Course;

/*
 * Title : UserData.java
 * Purpose : For binding all the data related to attandance
 */
public class Attandance extends Course {
    private String studentname;
    private String date;
    private int attandance;
    private int totalattandance;
    private long rollnumber;
    private boolean present;

    public void setAttandanceDate(String date) {
        this.date = date;
    }

    public void setStudentName(String studentname) {
        this.studentname = studentname;
    }

    public void setAttandance(int attandance) {
        this.attandance = attandance;
    }

    public void setTotalAttandance(int totalattandance) {
        this.totalattandance = totalattandance;
    }

    public void setPresentStatus(boolean presentstatus) {
        this.present = presentstatus;
    }

    public void setRollNumber(long rollnumber) {
        this.rollnumber = rollnumber;
    }

    public String getStudentName() {
        return studentname;
    }

    public long getRollNumber() {
        return rollnumber;
    }

    public int getAttandance() {
        return attandance;
    }

    public int getTotalAttandance() {
        return totalattandance;
    }

    public String getAttandanceDate() {
        return date;
    }

    public boolean getPresentStatus() {
        return present;
    }

}
