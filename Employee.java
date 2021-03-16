package javaProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Employee 
{
	private int Id;
	private String name;
	private int salary;
	private String Department;	
	private boolean isActive;
	public Employee(int id, String name, int salary, String department, boolean Active) 
	{
		Id = id;
		this.name = name;
		this.salary = salary;
		Department = department;
		isActive = Active;
	}

	public int getId() 
	{
		return Id;
	}
	public void setId(int id) 
	{
		Id = id;
	}
	public String getName() 
	{
	
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public int getSalary() 
	{
		return salary;
	}
	public void setSalary(int salary) 
	{
		this.salary = salary;
	}
	public String getDepartment() 
	{
		return Department;
	}
	public void setDepartment(String department) 
	{
		Department = department;
	}
	public void delete() {
		isActive = false;
	}
	public void restore() {
		isActive = true;
	}
	public boolean getState() {
		return isActive;
	}
	/////////////////////////////////////////////////////////////


public static List<Employee> readEmployee(File file)
{
	List<Employee> empList = new ArrayList<Employee>();
	String strLine = "";
	String[] varFields;
	
	try (BufferedReader reader = new BufferedReader(new FileReader(file)))
	{
		while(strLine != null)
		{
			
			strLine = reader.readLine();
			if(strLine == null) break;
			varFields = strLine.split(",");
			int id = Integer.parseInt(varFields[2].trim().replace("[", "")
					.replaceAll("[a-zA-Z\\=]", ""));
			String name = (String) varFields[1].substring(6, varFields[1].length());
			int salary = Integer.parseInt(varFields[0].trim().replaceAll("[a-zA-Z\\=]", "")
					.replace("[", "").replace(" ", ""));
			String department = varFields[3].substring(12, varFields[3].length());
			String activeString = varFields[4].substring(8, varFields[4].length()-1);
			boolean active = Boolean.valueOf(activeString);
			empList.add(new Employee(salary, name, id, department, active));
			
		}
	}
	catch (FileNotFoundException e) 
	{
	e.printStackTrace();
	
	// This is the exception thrown if your UID doesn't match for the class you're
	// reading in
	} catch (InvalidClassException e) 
	{
		e.printStackTrace();
	} 
	catch (IOException e) 
	{
		e.printStackTrace();
		
	} 
	     
	return empList;
}

// this is for adding records to the end of a file
public static void appendEmp(File file, String input)
{
	FileWriter save;
	try 
	{
		// create writer to write to file
		save = new FileWriter(file, true);
		BufferedWriter writer = new BufferedWriter(save);
		
		// write input to file 
		writer.write("\n");
		writer.write(input);
		
		//close the stream
		writer.close();
		
	} catch (IOException e1) 
	{
		e1.printStackTrace();
	} 
}

// this overwrites an entire file
public static void saveNewEmp(File file, String input)
{
	FileWriter save;
	try 
	{
		// create writer to write to file
		save = new FileWriter(file);
		BufferedWriter writer = new BufferedWriter(save);
		
		// write input to file 
		writer.write(input);
		
		//close the stream
		writer.close();
		
	} catch (IOException e1) 
	{
		e1.printStackTrace();
	} 
}

@Override
public String toString() 
{
	return "Employee [Id=" + Id + ", name=" + name + ", salary=" + salary + ", Department=" + Department + ", Active=" + isActive  + "]";
}

public static String listToString(List<Employee> file)
{
	String contents = file.stream().map(Object::toString).collect(Collectors.joining("\n"));
	return contents;
}

}
