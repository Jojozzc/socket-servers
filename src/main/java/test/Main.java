package test;

import servers.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class Main {
    public static void main(String[] args){
        blockingServerTest();

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

    private static void blockingServerTest(){
        // Server example 实例化SocketServer后，实现ISocketService即可，ISocketService的doService在socket链接后执行
        SocketServer socketServer = new BlockingServer(9090);
        try {
            ((BlockingServer) socketServer).startServer(new ISocketService() {
                public void doService(DataInputStream inputStream, OutputStream outputStream) {
                    // 接收客户端的数据
                    try {
                        String string = inputStream.readUTF();
                        if(string != null && string.length() > 0 && string.charAt(string.length() - 1) == 'b'){
                            // 模拟操作 延迟
                            Thread.sleep(3000);
                        }

                        System.out.println("client:" + string);
                        // 发送给客户端数据
                        DataOutputStream out = new DataOutputStream(outputStream);
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
