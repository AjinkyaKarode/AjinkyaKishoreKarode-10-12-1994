package com.package1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapFunctions {
	
	Map<Integer,Employee> map = Collections.synchronizedMap(new TreeMap<Integer,Employee>());

	public void mapEntries(Employee emp) throws HeapException
	{
			if(map.size()<2)
			{
				map.put(emp.getId(), emp);
			}
			else
			{
				throw new HeapException("Heap memory almost running out");
			}
	}
//-----------------------------------------------------------------------------------------------------------------------	
	public void writeToDisk(Employee emp)  //Serialization of object
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream("demo.ser");
			ObjectOutputStream objstr= new ObjectOutputStream(fos);
			objstr.writeObject(map);
			fos.close();
			objstr.close();
			System.out.println("written to disk succesfully");
			map.clear();
			map.put(emp.getId(), emp);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//-------------------------------------------------------------------------------------------------------------------------	
	public void writeToDiskBuffer()     //Serialization of object
	{
		try 
		{
			FileWriter writer = new FileWriter("test.ser");
			BufferedWriter bwrite = new BufferedWriter(writer);
			Set<Map.Entry<Integer, Employee>> set = map.entrySet();
			
				for(Map.Entry<Integer, Employee> mp:set)
				{
					bwrite.write(mp.getKey()+","+mp.getValue().getName()+","+mp.getValue().getDept());
					bwrite.newLine();
					bwrite.flush();
				}
				map.clear();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//-----------------------------------------------------------------------------------------------------------------------	
	public void showEmployeeDetails(int id)      //Deserialization of object in unreadable format
	{
		if(map.containsKey(id))
		{
			System.out.println("Hello");
			System.out.println(map.get(id));
		}
		else
		{
			try 
			{
				System.out.println("Retrieving files from Disk");
				FileInputStream fis = new FileInputStream("demo.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Map<Integer,Employee> mapInFile = Collections.synchronizedMap(new TreeMap<Integer,Employee>());
				mapInFile =(Map<Integer, Employee>) ois.readObject();
					if(!mapInFile.isEmpty())
					{
						System.out.println(mapInFile.get(id));
					}
					else
					{
						System.out.println("No such Employee");
					}
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//-----------------------------------------------------------------------------------------------------------------------	
	public void showDiskBuffer(int id)  //Deserialization but in readable format
	{
		try 
		{
			String text=" ";
			String[] sarr=null;
			FileReader fr = new FileReader("test.ser");
			BufferedReader br = new BufferedReader(fr);
		Outer:
			while((text=br.readLine()) != null)
			{
				
				String line=text.substring(0, 3);
				String empid=Integer.toString(id);
				
				if(text.contains(empid))
				{
					 sarr = text.split(",");
					 for(int i=0;i<sarr.length;i++)
						{
							System.out.print(sarr[i]+",");
						}
					 System.out.println();
					 break Outer;
				}
				else
				{
					System.out.println("No such Employee");
				}

			} 
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
