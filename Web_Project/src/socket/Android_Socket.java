package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Android_Socket {
	private Socket socket;
	public Android_Socket(){
		try{
			System.out.println("正在连接服务器。。。");
			socket = new Socket("localhost",15640);
			System.out.println("已连接服务器。");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public synchronized void start(){
		try{
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
			BufferedWriter bw= new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(bw,true);
			String temp;
			pw.println("更新数据");
			pw.flush();			
			InputStream in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String str;
			while(true){
				if((str=br.readLine())==null){
					break;
				}
				System.out.println(str);
				System.out.println("xxx");
			}
			br.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
		}
	}
	public static void main(String[]args) throws IOException{
		Android_Socket as = new Android_Socket();
		as.start();
	}
//	public void update1(Socket socket){
//	try {
//		OutputStream os = socket.getOutputStream();
//		OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
//		PrintWriter pw = new PrintWriter(osw);
//		int index =1;
//		int sum =0;
//		for(String p:list.keySet()){
//			pw.write(list.get(p).toString()+"~");
//			if(index ==0){
//				pw.write("\n");
//				pw.flush();
//			}
//			index = (index+1)%1000;
//			sum++;
//		}
//		pw.flush();
//		pw.close();
//		File file = new File("C:/Users/zzd/Desktop/databases/lastId1.txt");
//		FileOutputStream fos = new FileOutputStream(file);
//		byte[] b = tmaxserial.getBytes();
//		fos.write(b);
//		fos.flush();
//		fos.close();
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
//public void update2(Socket socket){
//	try {
//		OutputStream os = socket.getOutputStream();
//		OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
//		PrintWriter pw = new PrintWriter(osw);
//		int index =1;
//		int sum =0;
//		for(String p:list.keySet()){
//			pw.write(list.get(p).toString()+"~");
//			if(index ==0){
//				pw.write("\n");
//				pw.flush();
//			}
//			index = (index+1)%1000;
//			sum++;
//		}
//		pw.flush();
//		pw.close();
//		File file = new File("C:/Users/zzd/Desktop/databases/lastId2.txt");
//		FileOutputStream fos = new FileOutputStream(file);
//		byte[] b = tmaxserial.getBytes();
//		fos.write(b);
//		fos.flush();
//		fos.close();
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//} 
}
