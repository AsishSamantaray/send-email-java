

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class Main {
	public static void main(String[] args) {
		String fromEmail = null;
		String password = null;
		Properties properties = new Properties();
		try(FileReader reader = new FileReader("E:\\java_demo\\EmployeeDemo\\config.properties");) {
			properties.load(reader);
			fromEmail = properties.getProperty("fromEmail");
			password = properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Employee> parseData = ParseEmployee.parseEmp("E:\\java_demo\\EmployeeDemo\\data.csv");
		for(Employee e: parseData) {
			String fname = e.getfName();
			String lname = e.getlName();
			String empid = e.geteId();
			String email = e.getEmail();
			String phone = e.getPhone();
			String message = "Hi "+fname.charAt(0)+" "+lname+",\n\nPlease find attached to this email data of all employee.\n\nBest regards.\n\nAsish Samantaray";
			EmailUtil.sendAttachmentEmail(fromEmail, password, email, "Employee Data", message);
		}




	}
}
