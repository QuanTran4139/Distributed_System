package exercise1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Db1 {
	
	public static void insertStudent(Student student) {
		try {
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root","Abc@12345");

			PreparedStatement ps = conn.prepareStatement("INSERT INTO studenttable values(" + student.getId() + ",'" + student.getName()+ "',"
															+ student.getGrade() + ")");
			int signal = ps.executeUpdate();
			System.out.println(signal);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Student> checkAllStudents(){
		List<Student> students = new ArrayList<Student>();
		try{
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "Abc@12345");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM studenttable");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float grade = rs.getFloat("grade");
				students.add(new Student(id,name,grade));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return students;
	}
	
	public static Student getStudentById(int id) {
		Student result = null;
		try {
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "Abc@12345");

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM studenttable WHERE id=" + id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new Student(rs.getInt(1), rs.getString(2), rs.getFloat(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
