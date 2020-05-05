package server_side;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MySerialServer implements Server {

	private int port;
	private ClientHandler c;
	private volatile boolean stop;

	public MySerialServer(int port, ClientHandler c) {

		this.port = port;
		this.c = c;
	}

	@Override
	public void open(int port, ClientHandler c) {

		this.port = port;
		this.c = c;
	}

	@Override
	public void stop() {

		stop = true;
	}

	public void start() {

		new Thread(() -> {
			try {
				runServer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}

	private void runServer() throws Exception {
		ServerSocket server = new ServerSocket(port);
		server.setSoTimeout(60000000);
		while (!stop) {
			try {
				Socket aClient = server.accept(); // blocking call
				try {
					c.handleClient(aClient.getInputStream(), aClient.getOutputStream());
					// aClient.getInputStream().close();
					// aClient.getOutputStream().close();
					aClient.close();
				} catch (IOException e) {
					System.out.println("invalid input2-output");
					e.printStackTrace();
				}
			} catch (SocketTimeoutException e) {
				System.out.println("Time Out");
				e.printStackTrace();
			}
		}
		server.close();
	}
}