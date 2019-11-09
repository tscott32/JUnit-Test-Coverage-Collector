package se4367project;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class JUnitListener extends RunListener {
	static FileWriter writer;

	public void testRunStarted(Description description) throws java.lang.Exception {
		try {
			File fileGenerated = new File("stmt-cov.txt");
			if (fileGenerated.exists()) {
				fileGenerated.delete();
			} else {
				fileGenerated.createNewFile();
			}
			writer = new FileWriter("stmt-cov.txt", false);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public void testRunFinished(Result result) throws java.lang.Exception {
		writer.close();
	}

	public void testFinished(Description description) throws java.lang.Exception {
		CollectCoverage.writeIntoFile(writer);
	}

	public void testStarted(Description description) throws java.lang.Exception {
		writer.write("[TEST] " + description.getClassName() + ": " + description.getMethodName() + " " + System.lineSeparator());
	}
}
