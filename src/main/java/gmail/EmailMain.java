package gmail;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.security.auth.Subject;
public class EmailMain {
		
	 public void startEmailProcess() throws Exception { 	
		 System.out.println("\n=============================================================");
		 	System.out.println("Starting execution for sending mail");
		 	System.out.println("=============================================================");
		 	Scanner scanner = new Scanner(System.in);
		 	ProcessingDetails processing = new ProcessingDetails();
		 	System.out.println("Options of input:\n");
		 	System.out.println("1 : Paste the list of Email Id's\n2 : Get the list from saved Email list file\n3 : Exit");
		 	System.out.println("Enter your choice: ");
		 	int choice = Integer.parseInt(scanner.nextLine());
		 	switch (choice) {
				case 1:
					System.out.println("Enter the list of emails:");
				 	List<String> list = new ArrayList<>();
				 	String line;
				 	do {
				 	    line = scanner.nextLine();
				 	    if (!line.isEmpty()) {
				 	        list.add(line);
				 	    }
				 	} while (!line.isEmpty());
					 	processing.startProcess();
					 	System.out.println("Sending Mails");
					 	coreMailSender(list);
					 	processing.endProcess();
					 	System.out.println("\nMail sent");
					 	//list.forEach(e -> System.out.println(e));
					 	
						break;
				case 2:
					System.out.println("Enter path of file : \n");
					String path = scanner.nextLine();
					List<String> emailLines = new ArrayList<>();
					 try {
						 emailLines = Files.readAllLines(Paths.get(path));
				        } catch (IOException e) {
				            System.err.println("Error reading file: " + e.getMessage());
				        }
					 	processing.startProcess();
					 	System.out.println("Sending Mails");
					 	coreMailSender(emailLines);
					 	processing.endProcess();
					 	System.out.println("\nMail sent");
					 	//emailLines.forEach(e -> System.out.println(e));
					
					 	break;
				case 3:
						System.out.println("=============System Exit===============");
						System.exit(0);
						break;
				default :
					System.out.println("Please enter correct input.");
					startEmailProcess();
					break;
			}
	
		 	
	    }
	 public void coreMailSender(List<String> list) {
		 String subject = "Application for Java Developer Role";
		 	String body = "\nDear Hiring Manager,\r\n"
		 			+ "\r\n"
		 			+ "I am writing to express my interest in the Java Developer role at your company. I have nearly 2 years of experience in the field and possess hands-on knowledge of technologies such as SpringBoot, Hibernate, JUnit, SQL Server, JSP, Servlet, Core Java, Advance Java, Spring MVC and Maven. Additionally, I am familiar with Agile methodologies and have experience using tools like JIRA and Scrum board.\r\n"
		 			+ "\r\n"
		 			+ "In my current position, I have been responsible for developing and maintaining several web applications using the aforementioned technologies. I am fluent in English and Hindi and thrive in fast-paced, agile environments.\r\n"
		 			+ "\r\n"
		 			+ "Please find attached my resume for your review. I am currently serving my notice period and would be available to join your team within 15-30 days. I believe that my skills and experience make me a strong candidate for this role and I am excited at the prospect of joining your team.\r\n"
		 			+ "\r\n"
		 			+ "Thank you for considering my application. I look forward to the opportunity to discuss my qualifications further.\r\n"
		 			+ "\r\n"
		 			+ "Sincerely,\r\n"
		 			+ "Vinay Mandge\r\n"
		 			+ "+91 9096103432";
		 	
		 	EmailSender sender = new EmailSender();
		 	try {
		 		for(String to: list)
		 			if(!to.isBlank())
		 			sender.sendMail("mandgevinay16@gmail.com",to,subject,body);
		 	}catch (Exception e) {
				throw new RuntimeException(e);
			}
	 }
}
	    

	


