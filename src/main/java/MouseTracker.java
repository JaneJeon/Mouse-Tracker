import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class MouseTracker {
	private static final int FRAMERATE = 30;
	private FileWriter log;
	// database schema?
	// run #, system information, run information
	
	private MouseTracker() throws IOException {
		// open file
		log = new FileWriter("mouse-movement-"+System.currentTimeMillis()+".txt", true);
		
		// write information about screen on startup
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		log.write("width:"+size.getWidth()+", height:"+size.getHeight()+"\n");
		
		recordMousePosition();
	}
	
	private String printMousePosition() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		return "("+p.x+","+p.y+")";
	}
	
	private void recordMousePosition() {
		new Thread(() -> {
			try {
				while (true) {
					log.append(printMousePosition());
					Thread.sleep(1000 / FRAMERATE);
				}
			} catch (Exception ignored) {}
		}).start();
	}
	
	// TODO: clicks?
	public static void main(String[] args) throws IOException {
		new MouseTracker();
	}
}