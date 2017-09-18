package demon;

public class Demon_test {
	public static void main(String[] args) {
	       System.out.println("==> Main Thread running..\n");
	       // Tạo một Thread
	       Thread deamon = new Demon();
	       // Sét nó là Deamon Thread.
	       deamon.setDaemon(true);
	       deamon.start();
	 
	       // Tạo một Thread khác
	       new DemonThread().start();
	 
	       try {
	           // Ngủ 5 giây.
	           Thread.sleep(5000);
	       } catch (InterruptedException e) {
	       }
	        
	       // Ghi ra thông báo luồng main này kết thúc.
	       System.out.println("\n==> Main Thread ending\n");
	   }

}
