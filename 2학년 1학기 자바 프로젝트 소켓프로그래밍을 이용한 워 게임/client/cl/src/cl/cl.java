package cl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class cl extends Thread{

	Socket Client;
	
    private CallBack cb;

    //events
    public void itGets(String text) {
        cb.getResponse(text);
    }
    
	public cl(String ip,int sock, CallBack tCallBack) throws IOException{
		Client = new Socket(ip,sock);
		
		DataInputStream dis = new DataInputStream(Client.getInputStream());
		DataOutputStream dos = new DataOutputStream(Client.getOutputStream());
		
		new Thread(){
			public void run(){
				while(true){
					String msg = null;
					try {
						msg = dis.readUTF();
					} catch (IOException e) {
						break;
					}
					itGets(msg);
				}
			}
		}.start();
		this.cb = tCallBack;
	}
	
	public void close() throws IOException
	{
		Client.close();
	}
	
	public void Send(String text) throws IOException
	{
		DataInputStream dis = new DataInputStream(Client.getInputStream());
		DataOutputStream dos = new DataOutputStream(Client.getOutputStream());
		
		try {
			dos.writeUTF(text);
			dos.flush();
		} catch (IOException e) {
		}
	}
}
