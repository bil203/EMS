package javaProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class menu 
{
	
	static List<Employee> employeeList = new ArrayList<>();
	static List<Department> departmentList = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		employeeList = Employee.readEmployee(createFile("Employee"));
		departmentList = Department.readDepartment(createFile("Department"));

		titleMenu();
		
	}
	public static void mainMenu() 
	{
		System.out.println(
				"Hello, press the appropriate option \n1: Add Employee \n"
				+ "2: Update Employee \n3: Remove Employee \n"
				+ "4: Deactivate Employee \n5: List Employee\n6: Title Menu\n7: Exit");
		
		int x = 0;
		x = scanInt();
		
		switch (x) 
		{
		case 1:
			addemployee();
			System.out.println("\nAdded Employee\n");
			mainMenu();
			break;
		case 2:
			updateMenu();
			System.out.println("\nUpdated Employee\n");
			mainMenu();
			break;
		case 3:
			System.out.println("\nWhat is the emplyee you wish to remove?");
			int empNum = scanInt();
			Crud.deleteEmployee(employeeList, empNum);
			System.out.println("Removed Employee\n");
			mainMenu();
			break;
		case 4:
			deactivateMenu();
			mainMenu();
			break;
		case 5:
			listEmployee(employeeList);
			System.out.println("\n");
			mainMenu();
			break;
		case 6:
			titleMenu();
		case 7:
			System.out.println("Bye");
			scan.close();
			Employee.saveNewEmp(createFile("Employee"), Employee.listToString(employeeList));
			Department.saveNewDep(createFile("Department"), Department
					.listToString(departmentList));
			System.exit(1);
		default:
			System.out.println("\nWrong input\n");
			mainMenu();
		}
	}
	
	public static void deleteMenu() {
		System.out.println("\nWhat is the emplyee you wish to remove?");
		int empNum = scanInt();
		try {
			Crud.deleteEmployee(employeeList, empNum);
			System.out.println("Removed Employee\n");
		} catch(Exception e) {
			System.out.println("Wrong input");
			deleteMenu();
		}
	}
	
	public static void deactivateMenu() {
		System.out.println("\nWhat is the emplyee you wish to deactivate?");
		int empNum = scanInt();
		try {
			Crud.deactivateEmployee(employeeList, empNum);
			System.out.println("Deactivated Employee" + employeeList.get(empNum).getName() + "\n");
		} catch(Exception e) {
			System.out.println("Wrong input");
			deactivateMenu();
		}
	}
	
	public static void updateMenu() 
	{
		System.out.println("Please enter employee ID");
		
		int id = 0;
		id = scanInt();
		
		System.out.println("Enter the Employee Name");
		String name = scanString();
		
		System.out.println("Enter the Employee Salary");
		int salary = scanInt();
		System.out.println("Enter the Employee Department");
		String department = scanString();
		
		employeeList = Crud.updateEmployee(employeeList, id, name, salary, department);
		System.out.println("Successfully updated: " + employeeList.get(id));
	}
	
	public static void updatedepartment()
	{
		System.out.println("Please enter department ID.");
		
		int id = 0;
		id = scanInt();
		
		System.out.println("Enter the Department Name.");
		String name = scanString();
		
		System.out.println("Enter the Employee budget.");
		int budget = scanInt();
		
		System.out.println("Enter the Department phone.");
		String phone = scanString();
		
		departmentList = Crud.updateDepartment(departmentList, id, name, budget, phone);
		System.out.println("Successfully created: " + departmentList.get(departmentList.size()-1));
	}
	
	public static void addemployee() {		
		System.out.println("Enter the Employee Name");
		String name = scanString();
		
		System.out.println("Enter the Employee Salary");
		int salary = scanInt();
		
		System.out.println("Enter the Employee Department");
		String department = scanString();
		
		employeeList = Crud.createEmployee(employeeList, 0, name, salary, department);
		System.out.println("Successfully created: " + employeeList.get(employeeList.size()-1));
	}
	
	public static void addDepartment()
	{
		System.out.println("Enter the Department name.");
		String name = scanString();
		
		System.out.println("Enter the Department budget.");
		int budget = scanInt();
		
		System.out.println("Enter the department phone number.");
		String phone = scanString();
		
		departmentList = Crud.createDepartment(departmentList, 0, name, phone, budget);
		System.out.println("Successfully created: " + departmentList.get(departmentList.size()-1));
	}
	
	public static File createFile(String name)
	{
			File file = new File("resources/" + name + ".txt");
			if(!file.exists())
			{
				try {
					file.createNewFile();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			return file;
	}
	
	// simple method to take int input.
	@SuppressWarnings("resource")
	public static int scanInt()
	{
		
		while(true)
		{
			try {
				int scanInt = scan.nextInt();
				scan.nextLine();
				return scanInt;
			}
			catch(InputMismatchException e)
			{
				System.out.println("That was not an int");
				scan.nextLine();
			}
			
		}
	}
	
	//simple method to take string input.
	@SuppressWarnings("resource")
	public static String scanString()
	{
		
		String scanStr = scan.nextLine();
		
		return scanStr;
	}
	// uses stream to list employee objects.
	public static void listEmployee(List<Employee> emps)
	{
		List<Employee> emplist = emps.stream().filter(e -> e.getState() == true)
				.collect(Collectors.toList());
		emplist.forEach(e -> System.out.println(e));
	}
	
	// uses stream to list department objects.
	public static void listDepartments(List<Department> deps)
	{
		List<Department> deplist = deps.stream().collect(Collectors.toList());
		deplist.forEach(e -> System.out.println(e));
	}
	
	
	public static void titleMenu()
	{
		System.out.println("Select Employee or Department.\n0   Employee Menu"
				+ "\n1   Department Menu");
		int menOP = scanInt();
		switch(menOP)
		{
		case 0:
			mainMenu();
			break;
			
		case 1:
			departmentMenu();
			break;
		
		default:
			System.out.println("Please enter 0 or 1");
			titleMenu();
		}
	}
	
	public static void departmentMenu()
	{
		System.out.println(
				"Hello, press the appropriate option \n1: Add Department \n"
				+ "2: Update Department \n3: Remove Department \n"
				+ "4: List Department \n5: Title Menu\n6: End Program");
		
		int x = 0;
		x = scanInt();
		
		switch (x) 
		{
		case 1:
			addDepartment();  //done
			System.out.println("\nAdded Department\n");
			departmentMenu();
			break;
		case 2:
			updatedepartment();   // done
			System.out.println("\nUpdated Department\n");
			departmentMenu();
			break;
		case 3:
			System.out.println("\nWhat is the department you wish to remove?"
					+ "\n use the id.");
			
			int depNum = scanInt();   //done
			try {
				
				Crud.deleteDepartment(employeeList, departmentList, Crud.getNameByDepartmentId(
						departmentList, depNum));
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			System.out.println("Removed Department and Employees within it.\n");
			departmentMenu();
			break;
			
		case 4:
			
			listDepartments(departmentList);  
			System.out.println("\n");
			departmentMenu();
			break;
		case 5: 
			
			titleMenu();
			break;
		case 6:    
			
			System.out.println("Bye");
			scan.close();
			Department.saveNewDep(createFile("Department"), Department
					.listToString(departmentList));
			Employee.saveNewEmp(createFile("Employee"), Employee.listToString(employeeList));  // done
			System.exit(1);
			
		default:
			System.out.println("\nWrong input\n");
			departmentMenu();
		}
	}
	}



