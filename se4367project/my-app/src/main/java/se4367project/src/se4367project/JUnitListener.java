package se4367project;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import se4367project.CollectCoverage;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class JUnitListener extends RunListener {
	private static FileWriter fw;
	private static StringBuffer sb;	

    public void testRunStarted(Description description) throws Exception {
        CollectCoverage.suiteInfo = new HashMap<String,HashMap<String,HashSet<Integer>>>();
    }
    
    public void testRunFinished(Result result) throws Exception {
		writeIntoFile(fw);
		System.out.println("Test run finished!");
    }

    public void testStarted(Description description) throws Exception {
        CollectCoverage.testName = "[TEST] " + description.getClassName() + ":" + description.getMethodName() + "\n" ;
        CollectCoverage.cases = new HashMap<String,HashSet<Integer>>();
    }

    public void testFinished(Description description) throws Exception {
        CollectCoverage.suiteInfo.put(CollectCoverage.testName, CollectCoverage.cases);
    }

	 public static void writeIntoFile(FileWriter writer)throws Exception {
        try {
			fw = new FileWriter("stmt-cov.txt",true);
			sb = new StringBuffer();
            for (String caseNames : CollectCoverage.suiteInfo.keySet())
            {
                sb.append(caseNames);
                HashMap<String, HashSet<Integer>> coverage = CollectCoverage.suiteInfo.get(caseNames);
                for (String cName : coverage.keySet())
                {
                	HashSet<Integer> values = coverage.get(cName);
                	for (Integer i : values) {
                		sb.append(cName + ":" + i + "\n");
                	}
                }
            }
		fw.write(sb.toString());
		fw.close();
        } catch (IOException ex) {
			System.out.println("error");
        }
	 }
}
