package cl;

import java.io.IOException;

public class ma extends Thread{

	public static void main(String[] args) throws Exception
	{
		System.out.println("Connect to Login Server Port : 8585");
		cl a = new cl("127.0.0.1",8484,cb);
	}
	
	static cl gameServ;
	
	public static void connectGsv(int port) throws IOException
	{
		System.out.println("Connect to Game Server Port : "+port);
		gameServ = new cl("127.0.0.1",port,cb);
		gameServ.Send("Name:Hello");
	}
	
	private static String getRealResponse(String a)
	{
		return a.split(":")[1];
	}
	
	private static void raiseWaitP(String a)
	{
		System.out.println(getRealResponse(a));
	}
	
    private static CallBack cb = new CallBack() {
        @Override
        public void getResponse(Object obj) {
            String result = (String) obj;
            if (result.indexOf("85")!=-1)
            {
            	try {
					connectGsv( Integer.parseInt(result));
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
            }
            if (result.indexOf("WaitP:")!=-1) raiseWaitP(result);
        }
    };
}
