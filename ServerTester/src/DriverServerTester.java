
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;


public class DriverServerTester {
	
	public static void main(String args[]) throws IOException, InterruptedException {
		 Gson gson = new Gson();
		int port = 12345;
        DataModel<String> data = new DataModel<String>("yuval");
       
        
        	Socket socket = new Socket("localhost", port);
           
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            Map<String,String> headers = new HashMap<String,String>();
            headers.put("action", "POST");
            Request<DataModel<String>> req = new Request<DataModel<String>>(headers, data);
            String x = gson.toJson(req);
            writer.println(x);
            socket.close();
            TimeUnit.SECONDS.sleep(5);
            Socket socket2 = new Socket("localhost", port);
            output = socket2.getOutputStream();
            writer = new PrintWriter(output, true);
            headers.remove("action");
            headers.put("action", "GET");
            x = gson.toJson(req);
            writer.println(x);
            socket2.close();
            TimeUnit.SECONDS.sleep(5);
            Socket socket3 = new Socket("localhost", port);
            output = socket3.getOutputStream();
            writer = new PrintWriter(output, true);
            headers.remove("action");
            headers.put("action", "DELETE");
            x = gson.toJson(req);
            writer.println(x);
            socket3.close();
           //System.out.println(x);
           
           //InputStream input = socket.getInputStream();
           //BufferedReader reader = new BufferedReader(new InputStreamReader(input));
           //String s = reader.readLine();
           socket.close();
	}
}

