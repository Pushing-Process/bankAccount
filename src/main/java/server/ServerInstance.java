package server;

import controller.ServerController;
import javafx.scene.control.Label;
import model.LoggedUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInstance {

    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream objectInputStream;

    public ServerInstance(ServerSocket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
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

    public void receiveMessage(Label userNameTxt, Label passwordNameTxt) {
        new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    LoggedUser loggedUser = (LoggedUser) objectInputStream.readObject();
                    ServerController.addLabel(loggedUser, userNameTxt, passwordNameTxt);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    closeEverything(socket, objectInputStream);
                    break;
                }
            }
        }).start();
    }
}
