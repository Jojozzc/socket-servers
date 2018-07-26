package servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingServer extends SocketServer {
    public BlockingServer(int port){
        this.port = port;
    }
    public void startServer(ISocketService socketService)throws Exception{
        while (true){
            ServerSocket server = new ServerSocket(port);
            while (true) {
                //等待client的请求
                System.out.println("waiting...");
                Socket socket = server.accept();
                socketService.doService(socket);
                socket.close();
            }

        }
    }

}
