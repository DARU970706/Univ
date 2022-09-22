package svpa;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class serverClass extends Thread{

	Socket sock1;
	ServerSocket serv;
	
	DataOutputStream dos;
	DataInputStream dis;
	
	String nic;
	String Gets;
	String Svg="F";
	String Type="";
	int cgv;
	
	private CallBack cb;
	
	public serverClass(String n,CallBack tCallBack) throws IOException{
		Type = n;
		this.cb = tCallBack;
	}
	
	public void SetNic(String Text)
	{
		nic = Text;
	}
	
	public void Send(String text) throws IOException
	{
		dis = new DataInputStream(sock1.getInputStream());
		dos = new DataOutputStream(sock1.getOutputStream());

		dos.writeUTF(text);
		dos.flush(); // 메세지 전송
	}
	
	public String getDate()
	{
	    Date date = new Date();

	    Format formatter = new SimpleDateFormat("[hh:mm:ss]");
	    
	    return formatter.format(date);
	}
	
	public void isGets(String text)
	{
		cb.getResponse(text,cgv);
	}
	
	public void close() throws IOException
	{
		dis.close();
		dos.close();
		sock1.close();
		serv.close();
	}
	
	public void on(int sock,int n)throws IOException
	{
		serv = new ServerSocket(sock);
		
		sock1 = serv.accept();
		System.out.println(getDate()+sock1.getInetAddress() + " : "+Type+" Port : "+ sock);
		

		// 메세지 연속 송수신
		dos = new DataOutputStream(sock1.getOutputStream());
		dis = new DataInputStream(sock1.getInputStream());

		new Thread(){
			public void run(){
				while(true){
					String msg = null;
					try {
						msg = dis.readUTF();
					} catch (IOException e) {
						break;
					}
					isGets(msg);
				}
			}
		}.start();
	}


}

