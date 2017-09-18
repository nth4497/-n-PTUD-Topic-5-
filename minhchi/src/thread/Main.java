package thread;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		 
	       int idx = 1;
	 
	       for (int i = 0; i < 2; i++) {
	 
	           System.out.println("Main thread running " + idx++);
	           // Ngủ 2101 milli giây.
	           Thread.sleep(2101);
	       }
	 
	       Thread_test helloThread = new Thread_test();
	 
	       // Chạy thread
	       helloThread.start();
	 
	       for (int i = 0; i < 3; i++) {
	           System.out.println("Main thread running " + idx++);
	           // Ngủ 2101 milli giây.
	           Thread.sleep(2101);
	       }
	 
	       System.out.println("==> Main thread stopped");
	   }

}
