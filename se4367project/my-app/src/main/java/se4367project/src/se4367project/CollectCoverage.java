package se4367project;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class CollectCoverage {
	static HashSet<String> set = new HashSet<String>();
	public static String testClassName;
	public static HashMap<String, HashSet> cases;
    public static HashMap<String, HashMap<String, HashSet>> suite;

    public static void lineinfo(String testClassName, Integer coverage) {
        HashSet covered = cases.get(testClassName);
        
        if ( covered == null) {
            int[] temp = { coverage };
            
            cases.put(testClassName, new HashSet(temp.length));
        } 
        else { 
        	covered.add(coverage); 
        }
        
        if (cases == null) {
            return;
        }
 
    }
    
    public static void lineExecuted(String str) 
	{
		  set.add(str);
	}
	
	public static void writeIntoFile(FileWriter writer)
	{
		try 
		{		  
		    Iterator iterator = set.iterator();  
            String temp = "";			
            while (iterator.hasNext()) {
                temp += iterator.next() + System.lineSeparator();              	
            }
			writer.write(temp);	
			set.clear();
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();	
		}
	}		
}
