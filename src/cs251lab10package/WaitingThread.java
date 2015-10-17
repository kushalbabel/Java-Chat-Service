package cs251lab10package;


public class WaitingThread extends Thread {
    public void run() {
        int randomNum = 5000 + (int)(Math.random()*5000);
        try {
			Thread.sleep(randomNum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
