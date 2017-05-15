package com.package1;

import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapFunctions mp= new MapFunctions();
		int count=100;
		int x;
		int y;
		String check="";
		Scanner src = new Scanner(System.in);
		Outer:
	do{
			System.out.println("Press 1 to enter Employee Details ");
			System.out.println("Press 2 to display Employee Details ");
			x=src.nextInt();
			switch(x)
			{
				case 1:int eid=0;
							int deptid=0;
							
							System.out.println("Enter No of Employees ");
							y=src.nextInt();
							Inner:
							for(int i=1;i<=y;i++)
							{
								Employee emp =new Employee();
								//System.out.println(Runtime.getRuntime().freeMemory());
								try
								{
								System.out.println("Enter id for Employee");
								check = src.next();
									if(check.matches("[0-9]+")) 
									{
										eid = Integer.parseInt(check);
									}
									else
									{
										System.out.println("Enter numeric value");
										break Inner;
									}
										System.out.println("Enter name of Employee ");
										String name=src.next();
										System.out.println("Enter department of Employee");
										check=src.next();
											if(check.matches("[0-9]+")) 
											{
												deptid = Integer.parseInt(check);
											}
											else
											{
												System.out.println("Enter numeric value");
												break Inner;
											}
												emp.setId(eid);
												emp.setName(name);
												emp.setDept(deptid);
	
												mp.mapEntries(emp);
									}
											catch (HeapException e) 
											{
												System.out.println(e.getMessage());
												mp.writeToDisk(emp);                      // Calling method to Serialize
												//mp.writeToDiskBuffer();
											}
										
										}
										break;
							
				case 2:	System.out.println("Enter Employee id to search");   // calling method to display map
								 eid = src.nextInt();
								//mp.showDiskBuffer(id);
								mp.showEmployeeDetails(eid);
								break;		
							
				default:
				
							System.out.println("Not a Valid Choice");
			}// end of switch
		
						System.out.println("Do you want to continue ,Press 'y' for Yes and 'n' for No");
						//String check = src.next();
						 check=src.next();
						if(check.equals("n"))
						{
							break Outer;
						}
						
		}// end of do
			while(check.equals("y"));
	}

}
