package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import obj.thing;

public class Android_Server{
	private Map<String,thing> list;
	private ServerSocket server;
	private Date date;
	private Integer index;
	private String maxserial,tmaxserial;
	Set<String> container;
	public Android_Server(){
		try{
			System.out.println("正在启动服务器。。。");
			server = new ServerSocket(12345);
			System.out.println("服务器启动完毕！");
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public synchronized void  start(){
		try{
			while(true){
				System.out.println("等待客户连接");
				Socket socket = server.accept();
				System.out.println("一个客户连接成功");		
				InputStream in = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(in,"UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String temp;
				while(true){
					temp=br.readLine();
					if(temp.equals("更新排柜数据")){
						System.out.println("准备更新");				
						init1();
						update(socket,"C:/Users/Administrator/Desktop/database/lastId1.txt");
						System.out.println("更新结束,客户断开\n。。。。。");
						break;
					}
					if(temp.equals("更新验货数据")){
						System.out.println("准备更新");
						init2();
						update(socket,"C:/Users/Administrator/Desktop/database/lastId2.txt");
						System.out.println("更新结束,客户断开\n。。。。。");
						break;
					}
					if(temp.equals("上传数据")) {
						System.out.println("准备接收数据\n......");
						String detail=null;
						if((temp=br.readLine())!=null && (detail=br.readLine())!=null) {
							toDatabase(temp,detail);
							reveive(socket);
							System.out.println("接收完成");
						}
						break;
					}
				}
				try{
					br.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}		
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public void reveive(Socket socket) {
		try {
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw);
			pw.println("OK");
			pw.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	public void toDatabase(String db,String detail) {
		Connection ct = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ct = DriverManager
					.getConnection(
							"jdbc:sqlserver://192.168.43.147; DatabaseName=android_database",
							"sa","*1admin");
			ps = ct.prepareStatement("update comContainer set isCheck='1' where containerNum='"+db+"'");
			ps.executeUpdate();
			String[] lcn = detail.split(",");
			for(String lc:lcn) {
				ps = ct.prepareStatement("update comPick_list set state='1' where labelCode='"+lc+"'");
				ps.executeUpdate();
			}
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void init1(){
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		index = 0;
		tmaxserial ="";
		try { 
			container = new HashSet<>();
			list = new HashMap<>();
			File file = new File("C:/Users/Administrator/Desktop/database/lastId1.txt");
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			if((maxserial=br.readLine())==null || maxserial.trim()==""){
				maxserial="0";
			}
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ct = DriverManager
					.getConnection(
							"jdbc:sqlserver://192.168.43.147; DatabaseName=android_database",
							"sa","*1admin");
			ps = ct.prepareStatement("select t.*,t2.isCheck from comPick_list t join comContainer t2 on t.containerNum = t2.containerNum order by serial desc");
			re = ps.executeQuery();
			if(re.next()){
				tmaxserial = re.getString("serial");
			}else{
				System.out.println("未查询到相关数据");
				return;
			}
			if(Integer.parseInt(tmaxserial)>Integer.parseInt(maxserial) && re.getString("isCheck").equals("0")){
				thing t = new thing(re.getString("id"),re.getString("codeId"),re.getString("containerNum"),re.getString("containerType"),re.getString("invoiceNum"),re.getString("boxId"),
						re.getString("loadingTime"),re.getString("supplier"),re.getString("labelCode"),re.getString("boxOrder"),re.getString("boxNum"),re.getString("boxType"),
						re.getString("component"),re.getString("color"),re.getString("volume"),re.getString("count"),re.getString("prepareNum"),re.getString("grossWeight"),
						re.getString("netWeight"),re.getString("units"),re.getString("labelNum"),re.getString("state"),re.getString("scanTime"),re.getString("lock"),re.getString("serial"));
				list.put(re.getString("id"),t);
				container.add(re.getString("containerNum"));
				index++;
				while(re.next()){
					while(Integer.parseInt(maxserial)>=Integer.parseInt(re.getString("serial")) && re.getString("isCheck").equals("0")){
						break;
					}
					thing tt = new thing(re.getString("id"),re.getString("codeId"),re.getString("containerNum"),re.getString("containerType"),re.getString("invoiceNum"),re.getString("boxId"),
							re.getString("loadingTime"),re.getString("supplier"),re.getString("labelCode"),re.getString("boxOrder"),re.getString("boxNum"),re.getString("boxType"),
							re.getString("component"),re.getString("color"),re.getString("volume"),re.getString("count"),re.getString("prepareNum"),re.getString("grossWeight"),
							re.getString("netWeight"),re.getString("units"),re.getString("labelNum"),re.getString("state"),re.getString("scanTime"),re.getString("lock"),re.getString("serial"));
					list.put(re.getString("id"),tt);
					container.add(re.getString("containerNum"));
					index++;
				}
			}
			System.out.println("数据库中更新"+index+"条数据");
			re.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void init2(){
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		index = 0;
		maxserial="";
		try {
			container = new HashSet<>();
			list = new HashMap<>();
			File file = new File("C:/Users/Administrator/Desktop/database/lastId2.txt");
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			if((maxserial=br.readLine())==null || maxserial.trim()==""){
				maxserial="0";
			}
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ct = DriverManager
					.getConnection(
							"jdbc:sqlserver://192.168.43.147; DatabaseName=android_database",
							"sa","*1admin");
			
			ps = ct.prepareStatement("select t.*,t2.isCheck from comReceive_list t join comContainer t2 on t.containerNum = t2.containerNum order by serial desc");
			re = ps.executeQuery();	
			if(re.next()){
				tmaxserial = re.getString("serial");
			}else{
				System.out.println("未查询到相关数据");
				return;
			}
			if(Integer.parseInt(tmaxserial)>Integer.parseInt(maxserial) && re.getString("isCheck").equals("0")){
				thing t = new thing(re.getString("id"),re.getString("codeId"),re.getString("containerNum"),re.getString("containerType"),re.getString("invoiceNum"),re.getString("boxId"),
						re.getString("loadingTime"),re.getString("supplier"),re.getString("labelCode"),re.getString("boxOrder"),re.getString("boxNum"),re.getString("boxType"),
						re.getString("component"),re.getString("color"),re.getString("volume"),re.getString("count"),re.getString("prepareNum"),re.getString("grossWeight"),
						re.getString("netWeight"),re.getString("units"),re.getString("labelNum"),re.getString("state"),re.getString("scanTime"),re.getString("lock"),re.getString("serial"));
				list.put(re.getString("id"),t);
				container.add(re.getString("containerNum"));
				index++;
				while(re.next()){
					while(Integer.parseInt(maxserial)>=Integer.parseInt(re.getString("serial")) && re.getString("isCheck").equals("0")){
						break;
					}
					thing tt = new thing(re.getString("id"),re.getString("codeId"),re.getString("containerNum"),re.getString("containerType"),re.getString("invoiceNum"),re.getString("boxId"),
							re.getString("loadingTime"),re.getString("supplier"),re.getString("labelCode"),re.getString("boxOrder"),re.getString("boxNum"),re.getString("boxType"),
							re.getString("component"),re.getString("color"),re.getString("volume"),re.getString("count"),re.getString("prepareNum"),re.getString("grossWeight"),
							re.getString("netWeight"),re.getString("units"),re.getString("labelNum"),re.getString("state"),re.getString("scanTime"),re.getString("lock"),re.getString("serial"));
					list.put(re.getString("id"),tt);
					container.add(re.getString("containerNum"));
					index++;
				}
			}
			System.out.println("数据库中更新"+index+"条数据");
			re.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update(Socket socket,String path){
		try {
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
			PrintWriter pw = new PrintWriter(osw);
			pw.write(index.toString()+"\n");
			if(index==0){
				pw.write("0\n");
				return;
			}
			for(String cn:container) {
				pw.write(cn+",");
			}
			pw.write("\n");
			pw.flush();
			index =1;
			System.out.println(list.size());
			for(String p:list.keySet()){
				pw.write(list.get(p).toString()+"~");
				if(index ==0){
					pw.write("\n");
					pw.flush();
				}
				index = (index+1)%500;
			}
			pw.flush();
			pw.close();
			File file = new File(path);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] b = tmaxserial.getBytes();
			fos.write(b);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void main(String[]args){
		Android_Server server = new Android_Server();
		server.start();
	}
}
