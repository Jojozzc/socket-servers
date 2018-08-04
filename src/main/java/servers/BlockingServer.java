package servers;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BlockingServer extends SocketServer {
    // 阻塞服务器,通过开启新线程的方式
    public BlockingServer(int port){
        this.port = port;
    }
    public void startServer(ISocketService socketService)throws Exception{
        Executor exe = Executors.newFixedThreadPool(20);
        while (true){
            ServerSocket server = new ServerSocket(port);
            while (true) {
                //等待client的请求
                System.out.println("waiting...");
                Socket socket = server.accept();
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//                Runnable task = new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            socketService.doService(inputStream, socket.getOutputStream());
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//
//                    }
//                };
                exe.execute(()->{try {
                    socketService.doService(inputStream, socket.getOutputStream());
                }catch (Exception e){
                    e.printStackTrace();
                }});
//                socket.close();
            }
        }
    }

}
