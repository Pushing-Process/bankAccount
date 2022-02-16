package server;

import model.LoggedUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInstance {

    private ServerSocket serverSocket;
    private SendMessage listener;
    private Socket socket;
    private ObjectInputStream objectInputStream;

    public ServerInstance(ServerSocket serverSocket, SendMessage listener) {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.listener = listener;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeEverything(Socket socket, ObjectInputStream objectInputStream) {
        try {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() {
        while (socket.isConnected()) {
            try {
                LoggedUser loggedUser = (LoggedUser) objectInputStream.readObject();
                listener.sendMessage(loggedUser);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                closeEverything(socket, objectInputStream);
                break;
            }
        }
    }
}
