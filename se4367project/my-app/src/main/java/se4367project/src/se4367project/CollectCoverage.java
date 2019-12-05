package se4367project;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.*;
import java.util.*;

public class CollectCoverage {
	static HashSet<String> set = new HashSet<String>();
	public static HashMap<String, HashMap<String, HashSet<Integer>>> suiteInfo;
	public static HashMap<String, HashSet<Integer>> cases;
	public static String testName;

	 public static void visitLine(String className, Integer line){
			 if(cases == null) return;
			 HashSet<Integer> lines = cases.get(className);
			 if(lines==null){
				 lines = new HashSet<Integer>();
				 cases.put(className, lines);
			 } else {
				 lines.add(line);
			 }
	 }		
}