package entity;

public class Student extends User{
    private String major;
    private double gpa;
    private int teacher_id;

    public Student() {
    }

    public Student(int id, String name, String surname, int age, String major, double gpa, int teacher_id) {
        super(id, name, surname, age);
        this.major = major;
        this.gpa = gpa;
        this.teacher_id = teacher_id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString()+
                "major='" + major + '\'' +
                ", gpa=" + gpa +
                ", teacher_id=" + teacher_id +
                "} " +"\n";
    }
}
