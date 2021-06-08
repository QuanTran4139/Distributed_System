package exercise1;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Utils {
	public static String convertObject2XMLString(List<Student> students) {
        String xmlString = null;

        try {
            DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // create root element
            Element root = doc.createElement("students");
            for(Student stud : students) {
            // create customer
            Element student = doc.createElement("student");
            
            // id
            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(String.valueOf(stud.getId())));
            student.appendChild(id);

            // name
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(stud.getName()));
            student.appendChild(name);

            // age
            Element grade = doc.createElement("grade");
            grade.appendChild(doc.createTextNode(String.valueOf(stud.getGrade())));
            student.appendChild(grade);

            // append to root
            root.appendChild(student);
            }
            // append to doc
            doc.appendChild(root);

            // convert doc to xml string
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			xmlString = writer.getBuffer().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return xmlString;
    }
	
	public static List<Student> xml2Object(String xml) throws Exception {
		List<Student> students = new ArrayList<>();
        System.out.println(xml);
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(new InputSource(new StringReader(xml)));

             // get root element
            Element rootElement = (Element) doc.getDocumentElement();
            NodeList stud = rootElement.getChildNodes();

            // loop through children
            for (int i = 0; i < stud.getLength(); i++) {
                Element student = (Element) stud.item(i);

                // create customer
                students.add(new Student(Integer.parseInt(student.getElementsByTagName("id").item(0).getTextContent()),
                						student.getElementsByTagName("name").item(0).getTextContent(),
                						Float.parseFloat(student.getElementsByTagName("grade").item(0).getTextContent()))
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return students;
    }
	
	private static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Student getStudent(String xmlString) {
		Student student = null;

		// get <staff>
		Document doc = convertStringToDocument(xmlString);
		String id = doc.getElementsByTagName("id").item(0).getTextContent();
		String name = doc.getElementsByTagName("name").item(0).getTextContent();
		String grade = doc.getElementsByTagName("grade").item(0).getTextContent();

		student = new Student(Integer.parseInt(id), name, Float.parseFloat(grade));

		return student;
	}
	
	public static List<Student> getStudents(String xmlString) {
		List<Student> students = new ArrayList<Student>();
		
		// get <staff>
		Document doc = convertStringToDocument(xmlString);	
		NodeList studentsNL = doc.getElementsByTagName("student");
		for (int i=0; i<studentsNL.getLength(); i++) {
			Element studentElement = (Element) studentsNL.item(i);
			String id = studentElement.getElementsByTagName("id").item(0).getTextContent();
			String name = studentElement.getElementsByTagName("name").item(0).getTextContent();
			String grade = studentElement.getElementsByTagName("grade").item(0).getTextContent();
			
			Student student = new Student(Integer.parseInt(id), name, Float.parseFloat(grade));
			System.out.println(student);
			students.add(student);
		}

		return students;
	}
}
