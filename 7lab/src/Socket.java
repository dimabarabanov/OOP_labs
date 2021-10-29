import java.io.*;
import java.net.*;

public class Socket {
    java.net.Socket socket;
    // конструктор
    Socket (String host, int port) throws IOException {
        socket = new java.net.Socket(host,port);
        setSoTimeout(5000);
    }
    // устанавливаем время ожидания сокета в милс.
    void setSoTimeout(int timeout)throws IOException {
        socket.setSoTimeout(timeout);
    }
    // возвращает InputStream. позволяет сокету получить данные с другой стороны соединения
    InputStream getInputStream()throws IOException {
        return socket.getInputStream();
    }
    // возвращает OutputStream. позволяет сокету отправлять данные на другую сторону соединения
    OutputStream getOutputStream()throws IOException{
        return socket.getOutputStream();
    }
    // закрываем сокет
    void close()throws IOException{
        socket.close();
    }
}
