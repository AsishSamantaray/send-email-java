

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ParseEmployee {
	public static List<Employee> parseEmp(String fileName) {
		List<Employee> empData = new ArrayList<Employee>();

		try(BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
			reader.readLine();
			String line = "";
			while((line=reader.readLine()) != null) {
				String arr[] = line.split(",");
				Employee e = new Employee();
				e.setfName(arr[0]);
				e.setlName(arr[1]);
				e.seteId(arr[2]);
				e.setEmail(arr[3]);
				e.setPhone(arr[4]);
				empData.add(e);
			}
		}
		catch (Exception e) {
		}

		return empData;
	}
}
