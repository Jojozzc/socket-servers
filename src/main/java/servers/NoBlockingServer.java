package servers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NoBlockingServer extends SocketServer {
    public NoBlockingServer(int port){
        this.port = port;
    }

    public void startServer(INoBlockingService service)throws Exception{
        System.out.println("NoBlockingServer starting...");
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(port));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("NoBlockingServer started.");
        System.out.println("port: " + port);
        while (selector.select() > 0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isAcceptable()){
                    SocketChannel channel = socketChannel.accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                }else if(key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    System.out.println(readFromSocketChannel(channel));
                    service.doService(channel);
                }
            }
            iterator.remove();
        }
    }
    private String readFromSocketChannel(SocketChannel channel) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        StringBuilder res = new StringBuilder();
        int len = 0;
        while ((len = channel.read(buf)) > 0) {
            buf.flip();
            byte[] bytes = new byte[1024];
            buf.get(bytes, 0, len);
            res.append(new String(bytes, 0, len));
        }
        return res.toString();
    }
}
