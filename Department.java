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

public class Department 
{

		private int id;
		private String name;
		private String phone;
		private int budget;

		public Department(int id, String name, String phone, int budget) {
			super();
			this.id = id;
			this.name = name;
			this.phone = phone;
			this.budget = budget;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public int getBudget() {
			return budget;
		}

		public void setBudget(int budget) {
			this.budget = budget;
		}

		@Override
		public String toString() {
			return "Department [id=" + id + ", name=" + name + ", phone=" + phone + ", budget=" + budget + "]";
		}
		
		///////////////////////////////////////////////////////////
		
		public static String listToString(List<Department> file)
		{
			String contents = file.stream().map(Object::toString)
					.collect(Collectors.joining("\n"));
			return contents;
		}
		
		// this overwrites an entire file
		public static void saveNewDep(File file, String input)
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
		
		// this is for adding records to the end of a file
		public static void appendDep(File file, String input)
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
		
		public static List<Department> readDepartment(File file)
		{
			List<Department> empList = new ArrayList<Department>();
			String strLine = "";
			String[] varFields;
			
			try (BufferedReader reader = new BufferedReader(new FileReader(file)))
			{
				while(strLine != null)
				{
					
					strLine = reader.readLine();
					if(strLine == null) break;
					
					varFields = strLine.split(",");
					int id = Integer.parseInt(varFields[0].trim().replace("[", "")
							.replaceAll("[a-zA-Z\\=]", "").replace(" ", ""));
					String name = (String) varFields[1].substring(6, varFields[1].length());
					int budget = Integer.parseInt(varFields[3].trim().replaceAll("[a-zA-Z\\=]", "")
							.replace("[", "").replace("]", "").replace(" ", ""));
					String phone = varFields[2].substring(7, varFields[2].length());

					empList.add(new Department(id, name, phone, budget));
					
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
	}


