package eicoma.com.github.net.programming.TCPDemo5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(12000);

        Socket s = ss.accept();
        //创建一个缓冲字符输入流对象（通过Socket对象来获取输入流）
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        //创建一个缓冲字符输出流对象，输出到本模块的file.txt中
        BufferedWriter bw = new BufferedWriter(new FileWriter("net-programming\\ClientDemoCopy.java", true));

        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        //向流中写入反馈数据
        BufferedWriter bws = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bws.write("文件已成功上传至服务器！");
        bws.newLine();
        bws.flush();
        //释放资源时，因为BufferedWriter对象不受ServerSocket对象控制，所以也得关闭
        bw.close();
        ss.close();
    }
}
