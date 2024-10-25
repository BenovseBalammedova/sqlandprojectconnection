package serviceImpl;

import database.DatabaseConnection;
import entity.Student;
import service.CommonService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements CommonService<Student> {
    @Override
    public void update(Student user) {
        String query = "Update Student set name = ?,surname=?,age=?, major = ?, gpa = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getMajor());
            stmt.setDouble(5, user.getGpa());
            stmt.setInt(6, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Student user) {
        String query = "INSERT INTO Student (name,surname,age,major, gpa,teacher_id)VALUES (?,?,?,?,?,?)";
        Connection connection = DatabaseConnection.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());      // 1-ci parametr: name
            stmt.setString(2, user.getSurname());   // 2-ci parametr: surname
            stmt.setInt(3, user.getAge());          // 3-cü parametr: age
            stmt.setString(4, user.getMajor());     // 4-cü parametr: major
            stmt.setDouble(5, user.getGpa());       // 5-ci parametr: gpa
            stmt.setInt(6, user.getTeacher_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xətaları çap et
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close(); // Bağlantını bağla
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Xətaları çap et
            }
        }

    }

    @Override
    public void delete(int id) {
        String query = "DELETE from Student where id=? ";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Student getById(int id) {
        Student student = null;
        String query = "Select *from Student Where id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getString("major"),
                        resultSet.getDouble("gpa"),
                        resultSet.getInt("teacher_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Student";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getString("major"),
                        resultSet.getDouble("gpa"),
                        resultSet.getInt("teacher_id")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
