package javaProject;

import java.util.List;
import java.util.stream.Collectors;
/*
 * To use in another function write: Crud.nameOfFunction
 * All methods take a list and return a modified version of it. Make sure to assign it.
 */

public class Crud {

	public static List<Employee> createEmployee(List<Employee> employeeList, int id, String name, int salary,
			String department) 
	{
			id = employeeList.size();
		employeeList.add(new Employee(id, name, salary, department, true));
		return employeeList;
	}

	public static List<Employee> updateEmployee(List<Employee> employeeList, int id, String name, int salary,
			String department) {
		
		Employee employee = employeeList.get(id);
		employee.setName(name);
		employee.setDepartment(department);
		employee.setSalary(salary);
		employeeList.set(id, employee);
		return employeeList;
	}

	public static List<Employee> deactivateEmployee(List<Employee> employeeList, int id) 
	{
	
		Employee emp = employeeList.get(id);
		emp.delete();
		employeeList.set(id,emp);
		return employeeList;
	}
	
	public static List<Employee> deleteEmployee(List<Employee> employeeList, int id) 
	{
		Integer Id = (Integer) id;


				Employee removeBob = employeeList.stream()
					.filter(emp -> Id.equals(emp.getId()))
					.findAny().orElse(null);
	
				employeeList.remove(removeBob);
				return employeeList;
	}

	public static List<Employee> searchByDepartment(List<Employee> employeeList, String dep1) {

		List<Employee> search = employeeList.stream().filter(e -> e.getDepartment().equals(dep1))
				.collect(Collectors.toList());
		return search;
	}
	
	public static List<Department> searchDepartmentByName(List<Department> departmentList, String dep1) {

		List<Department> search = departmentList.stream().filter(e -> e.getName().equals(dep1))
				.collect(Collectors.toList());
		return search;
	}
	
	public static String getNameByDepartmentId(List<Department> departmentList, int depId) throws Exception {

		List<Department> search = departmentList.stream().filter(e -> e.getId() == depId)
				.collect(Collectors.toList());
		if(search.isEmpty()){
			throw new Exception("Department id " + depId + " Not Found");
		} else {
			return search.get(0).getName();
		}
	}
	
	public static void deleteDepartment(List<Employee> employeeList, String department) throws Exception {
		if (!employeeList.removeAll(searchByDepartment(employeeList, department))) {
			throw new Exception("Department " + department + " Not Found in Employees");
		}
	}
	
	public static void deleteDepartment(List<Employee> employeeList, List<Department> departmentList, String department) throws Exception {
		if (!departmentList.removeAll(searchDepartmentByName(departmentList, department))) {
			throw new Exception("Department " + department + " Not Found in Departments");
		}
		employeeList.removeAll(searchByDepartment(employeeList, department));
	}

	public static List<Department> createDepartment(List<Department> departmentList, int id, String name, String phone,
			int budget) 
	{
			id = departmentList.size();
		departmentList.add(new Department(id, name, phone, budget));
		return departmentList;
	}

	public static List<Department> updateDepartment(List<Department> departmentList, int id, String name, int budget,
			String phone) {
		
		for (int i=0; i<departmentList.size(); i++) 
		{ 
		    Department department = departmentList.get(i);
		    if(department.getId() == id) {
		    	department.setName(name);
				department.setPhone(phone);
				department.setBudget(budget);
		    	departmentList.set(i, department);
		    }
		    
		}
		return departmentList;
	}
}
