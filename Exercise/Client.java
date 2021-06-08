package exercise1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;


public class Client {
	
	public static void main(String[] args) {
		Socket s = null;
		int port = 2222;
		try {
			s = new Socket("localhost", port);
			System.out.println("Connected to port" + port);
			DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
         // get data for this site
         			List<Student> students = Db1.checkAllStudents();
         			System.out.println(students);
         			String xmlString = Utils.convertObject2XMLString(students);
         			out.writeUTF(xmlString);
         			
         			// get data from other site
         			String xmlStringFromOtherSite = in.readUTF();
         			System.out.println(xmlStringFromOtherSite);
         			List<Student> studentsFromOtherSite = Utils.getStudents(xmlStringFromOtherSite);
         			System.out.println(studentsFromOtherSite);
         			
         			// save data
         			for (Student student : studentsFromOtherSite) {
         				System.out.println(student);
         				Db1.insertStudent(student);
         			}

         			/* close the socket connection */
         			in.close();
         			out.close();
         			s.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
