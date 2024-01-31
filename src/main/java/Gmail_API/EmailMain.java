package Gmail_API;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
				 	HashSet<String> list = new HashSet<>();
				 	String line;
				 	do {
				 	    line = scanner.nextLine();
				 	    if (!line.isEmpty()) {
				 	        list.add(line);
				 	    }
				 	} while (!line.isEmpty());
				 	HashSet<String> excludeList = new HashSet<>();
				 	System.out.println("Enter list of emails needs to be excluded if present:");
				 	String line1;
				 	do {
				 	    line1 = scanner.nextLine();
				 	    if (!line1.isEmpty()) {
				 	        excludeList.add(line1);
				 	    }
				 	} while (!line1.isEmpty());
				 		System.out.println("list : " +list.size());
				 		System.out.println("ExcludeList : " + excludeList.size());
				 		System.out.println("list : " +list.size());
				 		list.removeAll(excludeList);
					 	processing.startProcess();
					 	System.out.println("Sending Mails");
					 	//list.forEach(e -> System.out.println(e));
					 	coreMailSender(list);
					 	processing.endProcess();
					 	
						break;
				case 2:
					System.out.println("Enter path of file : \n");
					String path = scanner.nextLine();
					List<String> Lines = new ArrayList<>();
					 try {
						 Lines = Files.readAllLines(Paths.get(path));
				        } catch (IOException e) {
				            System.err.println("Error reading file: " + e.getMessage());
				        }
					 	HashSet<String> emailLines = new HashSet<>();
					 	emailLines.addAll(Lines);
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
	 public void coreMailSender(HashSet<String> list) {
		 String subject = "Application for Job Role";
		 	String body = "Enter email body";
		 	
		 	EmailSender sender = new EmailSender();
		 	try {
		 		for(String to: list)
		 			if(!to.isEmpty())
		 			sender.sendMail("email-id@gmail.com",to,subject,body);//Enter email on placeholder
		 	}catch (Exception e) {
				throw new RuntimeException(e);
			}
	 }
}
	    

	


