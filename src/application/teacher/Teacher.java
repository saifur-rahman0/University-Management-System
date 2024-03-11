package application.teacher;

import application.common.Person;
import application.common.TimeUtil;
import application.department.DepartmentData;

/*
 * Title : Teacher.java
 * Purpose : Binding all the of teacher
 */
public class Teacher extends Person {
    private int teacherId;
    private String teachername;
    private String qualification;
    private String experience;
    private String course;
    private String position;
    private String joineddate;

    public void setJoinedDate(String joineddate) {
        this.joineddate = joineddate;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeacherName(String teachername) {
        this.teachername = teachername;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDeptName() {
        return new DepartmentData().getdeptname(this.getDeptCode());
    }

    public String getTeacherName() {
        return teachername;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getQualification() {
        return qualification;
    }

    public String getPosition() {
        return position;
    }

    public String getExperience() {
        return experience;
    }

    public String getCourse() {
        return course;
    }

    public String getJoinedDate() {
        return joineddate;
    }

    public String generateJoinedDate() {
        joineddate = TimeUtil.getCurrentTime();
        return joineddate;
    }

}
