package servers;

import java.nio.channels.SocketChannel;

public interface INoBlockingService {
    default public void doService(SocketChannel socketChannel){}
}
