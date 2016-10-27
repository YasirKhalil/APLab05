/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yasir
 */
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;

public class Lab05{

	static HashMap indexing;
	public static void main(String[] args) throws IOException {			//Throwing exception

		indexing = new HashMap();
	//Calling search function here for desktop to populate hashmap function	
		populatingHashMap("C:\\Users\\Yasir\\Downloads");	
		
		int inputagain = 1;
		
		while (inputagain==1){
		
			System.out.println("Please enter the string/folder name you want to search. ");
			Scanner userinput = new Scanner(System.in);
			String searchString = userinput.nextLine();
			inputagain = 0;
			
			String s = searchthroughMap(indexing,searchString);
			
			System.out.println("Enter 1 if you want to search again? ");
			int selectagain = userinput.nextInt();
			if(selectagain ==1){
				inputagain = 1;
			}
		}
	

	}
	public static String searchthroughMap(HashMap indexing,String searchString) {
	    Iterator it = indexing.entrySet().iterator();
	    String result="";
	    System.out.println("Following are the records found for your matched string\n\n");
        
	    while (it.hasNext()) {
	    	
	        Map.Entry pair = (Map.Entry)it.next();
	        if(pair.getKey().toString().toLowerCase().contains(searchString.toLowerCase())){
	        	System.out.println(pair.getKey() + " :: "+ pair.getValue()+"\n");
	        }
	    }
	    it.remove(); // avoids a ConcurrentModificationException
	    return result;
	}
	public static void populatingHashMap(String filepath) {
		File base = new File(filepath);
		File[] listoffiles = base.listFiles();				//Getting all sub directories from the desired directory
		if (listoffiles == null) {
			return;
		}
		for (File f : listoffiles) {					//Traversing through all sub directories of the entered directory
			if (f.isDirectory()) {
				indexing.put(f.getName(),f.getAbsolutePath());
				populatingHashMap(f.getAbsolutePath());			//Recalling populatingHashmap function
			} else {
				indexing.put(f.getName(),f.getAbsolutePath()); //this is a string
				if(f.getName().endsWith(".txt")){
					BufferedReader br;
					try {
						br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
						String lineread = null;
						while( (lineread = br.readLine())!= null ){
							
						    String [] tokens = lineread.split("\\s+");
						    for(int i=0; i < tokens.length ; i++){
						    	indexing.put(tokens[i],f.getAbsolutePath());
       
						    }
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				System.out.println("Indexing file:  " + f.getName());
			}
		}
	}
}																	//YourPanel ends
