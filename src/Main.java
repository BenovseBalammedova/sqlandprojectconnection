import entity.Student;
import entity.Teacher;
import serviceImpl.StudentServiceImpl;
import serviceImpl.TeacherServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();





     //   studentService.delete(20);
        studentService.update(new Student(11,"Benovse", "Aydinli",22,"Kimya",91.0,1));
        System.out.println(studentService.getAll());
    }}