package bbcag.ch.myapplication.rovercontroll;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * This class is the interface between the model and the view of the Apollo 18
 * application. It contains all logic called by view. It is the controller layer
 * in the MVC pattern.
 */

public class ApolloController {
	private JSch jsch = new JSch();
	private Session session;
	private boolean connected;
	private boolean enabled = false;

	public static PropertiesManager prop = new PropertiesManager();

	public ApolloController() {
	}

	/**
	 * sends a console command to the connected device (in our case a raspberry
	 * pi)
	 * 
	 * @param command
	 *            this is the command that the device should execute
	 */
	public void command(String command) {
		String cmd = command;

		Channel channel;
		try {
			channel = getSession().openChannel("exec");
			((ChannelExec) channel).setCommand(cmd);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);

			channel.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * calls the command function with an e-speak command which is a program on
	 * our raspberry pi
	 * 
	 * @param text
	 *            the sentence/word that should be read out by the Computer
	 */
	public void speakText(String text) {
		command("espeak -ven+f3 -k5 -s150 -a 200 '" + text + "'");
	}

	/**
	 * calls the command function with a command to execute a python file in the
	 * folder "Apollo18" on the Desktop of the user pi
	 * 
	 * @param motionFile
	 *            this is the name of the file that should be executed
	 */
	public void motionCommand(String motionFile) {
		command("sudo python /home/pi/Desktop/Apollo18/" + motionFile + ".py");
	}

	/**
	 * creats a session with the given username, IP & port (in our case with a
	 * Raspberry Pi)
	 */
	public void connect() {
		try {

			String host = prop.getIp();
			String user = prop.getUser();
			setSession(getJsch().getSession(user, host, 22));
			String xhost = prop.getIp();
			int xport = Integer.parseInt(prop.getPort());
			getSession().setX11Host(xhost);
			getSession().setX11Port(xport + 6000);
			getSession().connect();
			setConnected(true);
		} catch (Exception e) {
			System.err.println("Connecting failed");
			System.err.println("Trying to disconnect ...");
			disconnect();
		}
	}

	/**
	 * disconnects the session
	 */
	public void disconnect() {
		try {
			getSession().disconnect();
			setConnected(false);
		} catch (Exception e) {
			System.err.println("Disonnecting failed");
		}
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public JSch getJsch() {
		return jsch;
	}

	public Session getSession() {
		return session;
	}

	public static PropertiesManager getProp() {
		return prop;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}