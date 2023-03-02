import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MathHelper {
    public double multiplyTwoNumbers(double x, double y) throws IOException{
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to server.");

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        output.println(x);
        output.println(y);
        double result = Double.parseDouble(input.readLine());
        socket.close();
        return result;
    }
}
