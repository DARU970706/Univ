package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;

public class Main extends Application {

	static int maxPlayer;
	
	//labelNic.setTextFill(Color.WHITE);
	static client gameServ;
	static TextField txtip = new TextField();
	static TextField msgbox = new TextField();
	
	static TextField x = new TextField();
	static TextField y = new TextField();
	
	static Label SumPower = new Label();
	
	static Label lx = new Label();
	static Label ly = new Label();
	
	static int px = 0;
	static int py = 0;
	
	static TextField txtid = new TextField();
	static final Label state = new Label();
	static Thread thread;
	static ListView chat = new ListView();
	static ListView name = new ListView();
	
	static int isMyTurn = 0;
	static int money = 0;
	static int hp = 0;
	static int umoney=0;
	
	static int[] wep = new int[3];
	static int[] wpp = {40,30,20};
	
	static Label lmoney = new Label();
	static Label lhp = new Label();
	static Label llaser = new Label();
	static Label lgun = new Label();
	static Label lbomb = new Label();
	static Rectangle ani = new Rectangle(230+400, 20+400, 20, 20) ;
	
	public static final ObservableList data = 
	        FXCollections.observableArrayList();

	private static String getRealResponse(String a)
	{        
		return a.split(":")[1];
	}
	
	private static void raiseWaitP(String a)
	{
		String n = "Wargame Waiting Player : "+getRealResponse(a)+"/"+maxPlayer;
		
		if( Integer.parseInt(getRealResponse(a))>=maxPlayer)
		{
			thread.start();
		}
		Platform.runLater(()->state.setText(n));
	}
	
	private static void joinTeam(String n)
	{
		Platform.runLater(()->name.getItems().add(getRealResponse(n)));
	}
	
	public static void connectGsv(int port) throws IOException
	{
		System.out.println("Connect to Game Server Port : "+port);
		gameServ = new client(txtip.getText(),port,cb);
		gameServ.Send("Name:"+txtid.getText());
	}
	
	private static void GetMsg(String n)
	{
		Platform.runLater(()->chat.getItems().add(getRealResponse(n)));
	}
	
	private static void setting(String n)
	{
		hp = Integer.parseInt(n.split(":")[1]);
		money = Integer.parseInt(n.split(":")[2]);
		wep = new int[3];
		
		Platform.runLater(()->lhp.setText("hp : "+hp));
		Platform.runLater(()->lmoney.setText("money : "+money));
		Platform.runLater(()->llaser.setText("laser : 0"));
		Platform.runLater(()->lbomb.setText("bomb : 0"));
		Platform.runLater(()->lgun.setText("gun : 0"));
		Platform.runLater(()->ani.setX(230+Integer.parseInt(n.split(":")[3])*4));
		Platform.runLater(()->ani.setY(20+Integer.parseInt(n.split(":")[4])*4));
		Platform.runLater(()->SumPower.setText("sum : 0"));
	}
	
    private static CallBack cb = new CallBack()
    {
        @Override
        public void getResponse(Object obj) {
            String result = (String) obj;
            System.out.println(result);
            
            if (result.indexOf("msg:")!=-1) GetMsg(result);
            else if (result.indexOf("85")!=-1)
            {
            	try {
					connectGsv( Integer.parseInt(result));
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
            }
            else if (result.indexOf("WaitP:")!=-1) raiseWaitP(result);
            else if (result.indexOf("name:")!=-1) joinTeam(result);
            else if (result.indexOf("data:")!=-1) setting(result);
            else if (result.indexOf("Turn:")!=-1) 
            {
            	Platform.runLater(()->chat.getItems().add("당신의 차례"));
            	isMyTurn=1;
            }
            else if (result.indexOf("win:")!=-1)Platform.runLater(()->chat.getItems().add("당신의 팀이 승리하였습니다."));
            else if (result.indexOf("lose:")!=-1)Platform.runLater(()->chat.getItems().add("당신의 팀이 패배하였습니다."));
            else if (result.indexOf("draw:")!=-1)Platform.runLater(()->chat.getItems().add("비겼습니다."));
            else if (result.indexOf("FL:")!=-1)Platform.runLater(()->chat.getItems().add("Your team lose the game."));
            else if (result.indexOf("FW:")!=-1)Platform.runLater(()->chat.getItems().add("Your team win the game."));
            else if (result.indexOf("MAX:")!=-1)maxPlayer=Integer.parseInt(getRealResponse(result));
        }
    };   
    
    public static void GetSum()
    {
		int power=0;
		for(int i=0;i<3;i++)power=power+(wep[i]*wpp[i]);
		SumPower.setText("sum : "+power);
    }
    
    public static void gameForm()
	{		
		//thread.start();
		Pane root = new Pane();
		Scene scene = new Scene(root,880,585);
		
		Rectangle GameBord = new Rectangle(230, 20, 425, 425) ;
		GameBord.setFill(Color.WHITE);
		GameBord.setStroke(Color.GRAY);
		
		Rectangle Teambord = new Rectangle(20, 455, 200, 115) ;
		Teambord.setFill(Color.WHITE);
		Teambord.setStroke(Color.GRAY);
		
		Rectangle buybord = new Rectangle(230, 455, 425, 115) ;
		buybord.setFill(Color.WHITE);
		buybord.setStroke(Color.GRAY);
		
		Rectangle my = new Rectangle(230, 20, 20, 20) ;
		my.setFill(Color.BLACK);
		
		ani.setFill(Color.RED);
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        // Update UI here.
		    }
		});
		
		Label labelNic = new Label();
		labelNic.setText("id : "+txtid.getText());
		labelNic.setFont(new Font(13));
		labelNic.setLayoutX(35);
		labelNic.setLayoutY(465);
		
		lx.setText("x : 0");
		lx.setFont(new Font(13));
		lx.setLayoutX(150);
		lx.setLayoutY(465);
		
		lmoney.setText("money : "+money);
		lmoney.setFont(new Font(13));
		lmoney.setLayoutX(35);
		lmoney.setLayoutY(480);
		
		ly.setText("y : 0");
		ly.setFont(new Font(13));
		ly.setLayoutX(150);
		ly.setLayoutY(480);
		
		lhp.setText("hp : "+hp);
		lhp.setFont(new Font(13));
		lhp.setLayoutX(35);
		lhp.setLayoutY(495);
		
		SumPower.setText("sum : 0");
		SumPower.setFont(new Font(13));
		SumPower.setLayoutX(150);
		SumPower.setLayoutY(495);
		
		llaser.setText("laser : 0");
		llaser.setFont(new Font(13));
		llaser.setLayoutX(35);
		llaser.setLayoutY(510);
		
		lbomb.setText("bomb : 0");
		lbomb.setFont(new Font(13));
		lbomb.setLayoutX(35);
		lbomb.setLayoutY(525);
		
		lgun.setText("gun : 0");
		lgun.setFont(new Font(13));
		lgun.setLayoutX(35);
		lgun.setLayoutY(540);
				
        name.setPrefSize(200, 425);
        name.setLayoutX(20);
        name.setLayoutY(20);
        name.setEditable(true);
        
		msgbox.setText(null);
		msgbox.setPrefWidth(120);
		msgbox.setLayoutX(665);
		msgbox.setLayoutY(545);
		
		x.setText(null);
		x.setPrefWidth(50);
		x.setLayoutX(520);
		x.setLayoutY(445);
		
		y.setText(null);
		y.setPrefWidth(50);
		y.setLayoutX(560);
		y.setLayoutY(445);
		
        Button mov = new Button("move");
        mov.setLayoutX(600);
        mov.setLayoutY(445);
        mov.setPrefWidth(50);
        mov.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if(isMyTurn==1)
				{
					if(px+Integer.parseInt(x.getText())<=100&&py+Integer.parseInt(y.getText())<=100)
					{
						if(px+Integer.parseInt(x.getText())>=0&&py+Integer.parseInt(y.getText())>=0)
						{
							px=px+Integer.parseInt(x.getText());
							py=py+Integer.parseInt(y.getText());
							
							lx.setText("x : "+px);
							ly.setText("y : "+py);
							
							my.setX(230+(px*4));
							my.setY(20+(py*4));
						}
					}
					//230,20
				}
			}
		});
		
        
        chat.setPrefSize(200, 520);
        chat.setLayoutX(665);
        chat.setLayoutY(20);
        chat.setEditable(true);

        Button sendb = new Button("Send");
        sendb.setLayoutX(795);
        sendb.setLayoutY(545);
        sendb.setPrefWidth(70);
        sendb.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				try {
					gameServ.Send("msg:"+msgbox.getText());
					msgbox.setText(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
        Button buyLaser = new Button("Laser");
        buyLaser.setLayoutX(250);
        buyLaser.setLayoutY(475);
        buyLaser.setPrefWidth(75);
        buyLaser.setPrefHeight(75);
        
        buyLaser.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if(isMyTurn==1)
				{				
					if(money>=30)
					{
						wep[0]++;
						money=money-30;
						llaser.setText("laser : "+wep[0]);
						lmoney.setText("money : "+money);
						GetSum();
					}
					else JOptionPane.showMessageDialog(null, "기범 : 돈부족해요.");
				}
				else JOptionPane.showMessageDialog(null, "당신의 차례가 아닙니다.");
			}
		});
		
        Button buybomb = new Button("bomb");
        buybomb.setLayoutX(345);
        buybomb.setLayoutY(475);
        buybomb.setPrefWidth(75);
        buybomb.setPrefHeight(75);
        
        buybomb.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if(isMyTurn==1)
				{
					if(money>=20)
					{
						wep[1]++;
						lbomb.setText("bomb : "+wep[1]);
						money=money-20;
						lmoney.setText("money : "+money);
						GetSum();
					}
					else JOptionPane.showMessageDialog(null, "기범 : 돈부족해요.");
				}
				else JOptionPane.showMessageDialog(null, "당신의 차례가 아닙니다.");
			}
		});
        
        Button buygun = new Button("gun");
        buygun.setLayoutX(440);
        buygun.setLayoutY(475);
        buygun.setPrefWidth(75);
        buygun.setPrefHeight(75);
        
        buygun.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if(isMyTurn==1)
				{
					if(money>=10)
					{
						wep[2]++;
						money=money-10;
						lgun.setText("gun : "+wep[2]);
						lmoney.setText("money : "+money);
						GetSum();
					}
					else JOptionPane.showMessageDialog(null, "기범 : 돈부족해요.");
				}
				else JOptionPane.showMessageDialog(null, "당신의 차례가 아닙니다.");
			}
		});
        
        Button ready = new Button("ready");
        ready.setLayoutX(535);
        ready.setLayoutY(475);
        ready.setPrefWidth(75);
        ready.setPrefHeight(75);
        
        ready.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if(isMyTurn==1)
				{
					int power=0;
					for(int i=0;i<3;i++)power=power+(wep[i]*wpp[i]);
					try {
						gameServ.Send("ready:"+power+":"+wep[0]+":"+hp+":"+money+":"+px+":"+py+":"+wep[2]+":"+wep[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					isMyTurn=0;
				}
				else JOptionPane.showMessageDialog(null, "당신의 차례가 아닙니다.");
			}
		});
        
        
        root.getChildren().addAll(GameBord,chat,name,sendb,Teambord,msgbox,labelNic,lmoney,lhp,buybord,buyLaser);
        root.getChildren().addAll(buybomb,buygun,llaser,lgun,lbomb,ready,x,y,my,mov,ani,lx,ly,SumPower);
        
		Stage a=new Stage();
		a.setScene(scene);
		
		a.show();
	}
	
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		Scene scene = new Scene(root,725,465);
		
		Button login = new Button("서버 연결");
		login.setLayoutX(390);
		login.setLayoutY(200);
		login.setPrefWidth(70);
		login.setPrefHeight(50);
		login.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				state.setText("Wargame Waiting Player : ");
				System.out.println(txtid.getText());
				if(txtid.getText()!=null)
				{
					System.out.println("Connect to Login Server Port : 8585");
					try {
						client a = new client(txtip.getText(),8484,cb);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Server is full or closed");
					}
				}
				else JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			}
		});
		
		//id area
		Label labelNic = new Label();
		labelNic.setText("ID : ");
		labelNic.setFont(new Font(13));
		labelNic.setLayoutX(270);
		labelNic.setLayoutY(203);
		
		txtid.setText(null);
		txtid.setPrefWidth(80);
		txtid.setLayoutX(300);
		txtid.setLayoutY(200);
		//id area
		
		//ip area
		Label labelip = new Label();
		labelip.setText("IP : ");
		
		labelip.setFont(new Font(13));
		labelip.setLayoutX(270);
		labelip.setLayoutY(233);
		
		txtip.setPrefWidth(80);
		txtip.setLayoutX(300);
		txtip.setLayoutY(230);
		txtip.setText("127.0.0.1");
		//ip area
		
		//state area
		state.setText("Server is not connected");
		state.setFont(new Font(13));
		state.setLayoutX(270);
		state.setLayoutY(175);
		//state area
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        // Update UI here.
		    }
		});
		
		root.getChildren().addAll(state,labelip,txtid,labelNic,login,txtip);
		
		Task<Integer> task = new Task<Integer>() {
		    public Integer call() throws Exception {
		    	Platform.runLater( () -> { 
	                gameForm();
	                primaryStage.hide();
	            });
				return null;
		    }
		};
		
		thread = new Thread(task);
		
		primaryStage.setScene(scene);      
		primaryStage.setTitle("JAVA Project");
		primaryStage.setOnCloseRequest(event->System.out.println("w"));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
