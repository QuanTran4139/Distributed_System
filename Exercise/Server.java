package exercise1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
	public static void main(String[] args) {
		ServerSocket ss;
		int port = 2222;
		try {
			ss = new ServerSocket(port);
			System.out.println("Server run on port " + port);
			Socket s = ss.accept();
			System.out.println("Client" + s.getLocalSocketAddress() + " connect");
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			List<Student> studentsFromServer = Db2.checkAllStudents();
			System.out.println(studentsFromServer);

			String xmlString = in.readUTF();
			System.out.println(xmlString);

			List<Student> students = Utils.getStudents(xmlString);
			System.out.println(students);
			
			// save data
			for (Student student : students) {
				Db2.insertStudent(student);
			}
			// send to client
			String xmlStringToClient = Utils.convertObject2XMLString(studentsFromServer);
			out.writeUTF(xmlStringToClient);

			in.close();
			out.close();
			ss.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
