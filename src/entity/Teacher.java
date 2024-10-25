package entity;

public class Teacher extends User{
    private String subject;
    private double salary;

    public Teacher() {
    }

    public Teacher(int id, String name, String surname, int age, String subject, double salary) {
        super(id, name, surname, age);
        this.subject = subject;
        this.salary = salary;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                super.toString()+
                "subject='" + subject + '\'' +
                ", salary=" + salary +
                "} " +"\n";
    }
}
