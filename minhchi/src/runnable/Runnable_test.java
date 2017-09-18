package runnable;

public class Runnable_test {
	public static void main(String[] args) throws InterruptedException {
		 
	       System.out.println("Main thread running..");
	 
	       // Tạo một thread từ Runnable.
	       Thread thread = new Thread(new DemoRunnable());
	 
	       thread.start();
	 
	       // Ngủ 5 giây.
	       Thread.sleep(5000);
	       System.out.println("Main thread stopped");
	   }

}
