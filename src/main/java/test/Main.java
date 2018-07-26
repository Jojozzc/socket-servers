package test;

import servers.*;
import service.SharebaseConfirmService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class Main {
    public static void main(String[] args){
        blockingServer();
    }

    private static void noBlockingServerTest(){
        SocketServer socketServer = new NoBlockingServer(9090);
        try{
            ((NoBlockingServer) socketServer).startServer(new INoBlockingService() {
                @Override
                public void doService(SocketChannel socketChannel) {
                    return;
                }
            });
        }catch (Exception e){

        }
    }

    private static void blockingServer(){
        // Server example 实例化SocketServer后，实现ISocketService即可，ISocketService的doService在socket链接后执行
        SocketServer socketServer = new BlockingServer(9090);
        try {
            ((BlockingServer) socketServer).startServer(new ISocketService() {
                public void doService(DataInputStream inputStream, OutputStream outputStream) {
                    // 接收客户端的数据
                    try {
                        String token = inputStream.readUTF();

                        System.out.println("client:" + token);
                        // 发送给客户端数据
                        DataOutputStream out = new DataOutputStream(outputStream);
                        if(token == null || token.length() != 32){
                            out.writeUTF("");
                        }else{
                            SharebaseConfirmService confirmService = new SharebaseConfirmService();
                            String userId = confirmService.confirmAndGetUserId(token);
                            if(userId == null || userId.length() == 0)out.writeUTF("");
                            else out.writeUTF(userId);
                            out.close();
                        }

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
