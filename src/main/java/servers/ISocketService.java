package servers;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public interface ISocketService {
    default public void doService(Socket socket){};
    default public void doService(DataInputStream inputStream){};
}
