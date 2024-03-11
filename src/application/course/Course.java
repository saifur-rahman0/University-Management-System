package application.course;

import application.department.Department;

public class Course extends Department {

    private String coursename;
    private String coursecode;
    private int maxtheorymarks = 0;
    private String coursetype;

    public void setCourseType(String coursetype) {
        this.coursetype = coursetype;
    }

    public void setMaxTheoryMarks(int maxtheorymarks) {
        this.maxtheorymarks = maxtheorymarks;
    }


    public void setCourseName(String course) {
        this.coursename = course;
    }

    public void setCourseCode(String coursecode) {
        this.coursecode = coursecode;
    }

    public void setSemorYear(int semoryear) {
        super.setSemorYear(semoryear);
    }

    public String getCourseCode() {
        return coursecode;
    }

    public String getCourseName() {
        return coursename;
    }

    public int getMaxTheoryMarks() {
        return maxtheorymarks;
    }


    public String getCourseType() {
        return coursetype;
    }
}
