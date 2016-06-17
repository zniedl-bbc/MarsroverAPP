package bbcag.ch.myapplication.rovercontroll;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import bbcag.ch.myapplication.HUDActivity;
import bbcag.ch.myapplication.R;

/**
 *This is the class that draws the live-image of a Camera 
 */
public class IPCamera implements Runnable {

	private static final long serialVersionUID = 1285588686202021396L;

	// mjpg stream url
	private final String MJPG_URL;
	private PropertiesManager properties = new PropertiesManager();
	private String ip;
	private String port;
	private HttpURLConnection huc = null;
	private DataInputStream dis;
	private Bitmap image = null;
	private HUDActivity hudActivity;

	private boolean connected = false;
	// private boolean initialized = false;

	public IPCamera(HUDActivity hudActivity) {
		try {
			ip = properties.getIp();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			port = properties.getPort();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MJPG_URL = "http://" + ip + ":" + port;

		this.hudActivity = hudActivity;
	}


	@Override
	public void run() {
		try {
			while (!connected) {
				connect();
			}
			readStream();
			ImageView cameraImage = (ImageView) hudActivity.findViewById(R.id.imageView);
			cameraImage.setImageBitmap(image);
		} catch (RuntimeException e) {
			System.err.println("Sorry, an error has occurred");
			System.err.println(e.getMessage());
			System.err.println("Trying to disconnect live stream ...");
			disconnect();
			System.exit(1);
		}
	}

	private void connect() {
		try {
			URL url = new URL(MJPG_URL);
			huc = (HttpURLConnection) url.openConnection();
			InputStream is = huc.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			dis = new DataInputStream(bis);
			connected = true;
			System.err.println("Camera connected");
		}

		/*
		 * in case no connection exists wait and try again, instead of printing
		 * the error
		 */
		catch (IOException e) {
			try {
				Thread.sleep(60);
			} catch (InterruptedException ie) {
				throw new RuntimeException(ie);
			}
			connected = false;
		}
	}

	// the basic method to continuously read the stream
	private void readStream() {
		while (true) {
			readMJPGStream();
		}
	}

	// pre process the mjpg stream to remove the mjpg encapsulation
	private void readMJPGStream() {
		readLine(4, dis); // discard the first 3 lines
		readJPG();
		readLine(1, dis); // discard the last two lines
	}

	// used to strip out the header lines
	private void readLine(int n, DataInputStream dis) {
		for (int i = 0; i < n; i++) {
			readLine(dis);
		}
	}

	private void readLine(DataInputStream dis) {
		try {
			boolean end = false;
			String lineEnd = "\n";
			byte[] lineEndBytes = lineEnd.getBytes();
			byte[] byteBuf = new byte[lineEndBytes.length];

			while (!end) {
				dis.read(byteBuf, 0, lineEndBytes.length);
				String t = new String(byteBuf);

				if (t.equals(lineEnd))
					end = true;
			}
		} catch (IOException ie) {
			throw new RuntimeException(ie);
		}
	}

	// read the embedded jpg image
	private void readJPG() {
		image = BitmapFactory.decodeStream(dis);
	}

	private void disconnect() {
		try {
			if (connected) {
				dis.close();
				huc.disconnect();
				connected = false;
				System.err.println("Camera disconnected");
			}
		} catch (IOException ie) {
			System.err.println("Disconnecting camera failed");
		}
	}

	public Bitmap getImage(){
		return image;
	}
}
