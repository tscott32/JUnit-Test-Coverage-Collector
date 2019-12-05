Step 1:
	Download a project from Github with JUnit test
		Sample projects can be found in the project specification

Step 2:
	Insert the following into the pom.xml file of the downloaded project:
	
"<dependency>
	<groupId>com.mycompany.app</groupId>
	<artifactId>my-app</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>"

"<plugin>  
	<groupId>org.apache.maven.plugins</groupId>  
	<artifactId>maven-surefire-plugin</artifactId>  
	<configuration>  
		<argLine>-javaagent:"C:\Users\taylo\OneDrive - borejaksmgmt.com\Desktop\4367_Project\se4367project\my-app\target\my-app-1.0-SNAPSHOT.jar"</argLine>  
		<properties>  
			<property>  
				<name>listener</name>  
				<value>se4367project.JUnitListener</value>  
			</property>  
		</properties>  
		<excludes>  
			<exclude>**/BaseTestCase.java</exclude>  
		</excludes>  
		<testFailureIgnore>true</testFailureIgnore>
	</configuration>  
</plugin>"

Step 3:
	In CMD, go to the file path of the our coverage project, where the pom.xml file is located
	Type "mvn clean install"
	This will generate the necessary files 
	
Step 4:
	In CMD, go back to the directory of the project you want to test where pom.xml is located
	Type "mvn test"
	This will execute the tests in the downloaded project as well as your program.
	Inside the test project folder, a "stmt-cov.txt" file will be generated. This is where the results of your program will be located.

