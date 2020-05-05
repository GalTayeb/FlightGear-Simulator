package server_client;

import java.net.Socket;

public interface Client {

	void connect(String ip, int port);

	void setPathValue(String path, double value);

	Socket getSocket();

	void disconnect();

	void close();
}