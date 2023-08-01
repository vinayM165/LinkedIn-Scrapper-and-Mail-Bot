package gmail;

import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessingDetails {

	public ProcessingDetails() {
		// TODO Auto-generated constructor stub
	}
	
	// Create a flag to signal when the code has finished executing
	AtomicBoolean isDone = new AtomicBoolean(false);
	Thread processingThread;
	// Create a new thread to display the processing message
	public void startProcess() {
		processingThread = new Thread(() -> {
	    while (!isDone.get()) {
	        System.out.print(".");
	        try {
	            Thread.sleep(500);
	        } catch (InterruptedException e) {
	            // Handle interruption
	        }
	    }
	});

	// Start the processing thread
	processingThread.start();
	}
	
	public void endProcess() throws InterruptedException {
		isDone.set(true);

		try {
	        processingThread.join();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

}
