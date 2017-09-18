package demon;

public class DemonThread extends Thread{
	@Override
	  public void run() {
	      int i = 0;
	 
	      // Vòng lặp 10 lần. Luồng này sẽ kết thúc.
	      while (i < 10) {
	          System.out.println("  - Hello from None Deamon Thread " + i++);
	          try {
	              // Ngủ 1 giây.
	              Thread.sleep(1000);
	          } catch (InterruptedException e) {
	          }
	      }
	 
	      // Ghi ra thông báo luồng này kết thúc.
	      System.out.println("\n==> None Deamon Thread ending\n");
	  }

}
