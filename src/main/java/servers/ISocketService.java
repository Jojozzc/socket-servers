package servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public interface ISocketService {
//    default public void doService(Socket socket){};
    default public void doService(DataInputStream inputStream, OutputStream outputStream){};
    default public void doService(SocketChannel channel){};
}
