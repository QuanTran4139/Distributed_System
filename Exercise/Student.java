package exercise1;

public class Student {
	private int id;
	private String name;
	private float grade;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public Student(int id, String name, float grade) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "ID: " + id +" - Name: " + name +" - grade: " + grade;
	}
}