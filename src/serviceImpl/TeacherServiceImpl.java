package serviceImpl;

import database.DatabaseConnection;

import entity.Teacher;
import service.CommonService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements CommonService<Teacher> {

    @Override
    public void update(Teacher user) {
        String query="Update Teacher set name = ?, salary = ? WHERE id = ?";
        try(
                Connection connection= DatabaseConnection.getConnection();
                PreparedStatement stmt=connection.prepareStatement(query)){
                stmt.setString(1, user.getName());
                stmt.setDouble(2,user.getSalary());
                stmt.setInt(1,user.getId());
                stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void create(Teacher user) {
        String query="Insert into Teacher(name,surname,age,salary,subject) values(?,?,?,?,?) ";
        try(
                Connection connection=DatabaseConnection.getConnection();
                PreparedStatement stmt=connection.prepareStatement(query))
        {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setInt(3, user.getAge());
            stmt.setDouble(4, user.getSalary());
            stmt.setString(5, user.getSubject());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(int id) {
        String query = "DELETE from Teacher where id=? ";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Teacher getById(int id) {
        Teacher teacher = null;
        String query = "Select *from Teacher Where id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                teacher = new Teacher(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getString("subject"),
                        resultSet.getDouble("salary")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teacher;
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "Select *from Teacher";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery(query);
            {
                while (resultSet.next()) {
                    Teacher teacher = new Teacher(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("age"),
                            resultSet.getString("subject"),
                            resultSet.getDouble("salary")

                    );
                    teachers.add(teacher);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teachers;
    }
}





