package test;

import servers.BlockingServer;
import servers.ISocketService;
import servers.SocketServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args){
        SocketServer socketServer = new BlockingServer(9090);
        try {
            ((BlockingServer) socketServer).startServer(new ISocketService() {
                public void doService(Socket socket) {
                    // 接收客户端的数据
                    try {
                        Thread.sleep(10000);
                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        String string = in.readUTF();
                        System.out.println("client:" + string);
                        // 发送给客户端数据
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        out.writeUTF("hi,i am socketserver!i say:" + string);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
