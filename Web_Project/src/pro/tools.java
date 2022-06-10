package pro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import obj.thing;

public class tools {
	static DateFormat format;
	public tools(){
		 
	}
	public ArrayList<Collection> readCsv(File file) throws Exception{
		int x=0;
		ArrayList<Collection> res = new ArrayList<>();
		ArrayList<thing> list = new ArrayList<>();
		Set<String> container = new HashSet<>();
		FileInputStream fis;
		Scanner in;
		try{
			fis = new FileInputStream(file);
			in = new Scanner(fis,"UTF-8");
			in.nextLine();
			while(in.hasNextLine()){
				String[] lines = in.nextLine().split(",");
				thing t = new thing(lines[0],lines[1],lines[2],lines[3],lines[4],lines[5],lines[6],lines[7],lines[8],lines[9],lines[10],lines[11],lines[12],lines[13],lines[14],lines[15],lines[16],lines[17],lines[18],lines[19],lines[20],lines[21],lines[22],lines[23]);
				list.add(t);
				container.add(lines[2]);
				x++;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		res.add(list);
		res.add(container);
		System.out.println(x);
		return res; 
	}
	public void toDatabase(ArrayList<thing> list,Set<String> container){
		int x=0 ;
		PreparedStatement ps=null;
        Connection ct=null;
        ResultSet rs=null;
        ArrayList<String> data = getData();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ct = DriverManager
					.getConnection(
							"jdbc:sqlserver://192.168.200.37; DatabaseName=android_database",
							"sa","1");
            ps=ct.prepareStatement("insert into comPick_list(id,codeId,containerNum,containerType,invoiceNum,boxId,loadingTime,supplier,labelCode,boxOrder,"
            												+"boxNum,boxType,component,color,volume,count,prepareNum,grossWeight,netWeight,units,labelNum,"
            												+"state,scanTime,lock) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            ps=ct.prepareStatement("insert into comReveive_list values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            for(thing t:list){
            	if(data.contains(t.getId())){
            		continue;
            	}
            	ps.setString(1, t.getId());
                ps.setString(2, t.getCodeId());
                ps.setString(3, t.getContainerNum());
                ps.setString(4, t.getContainerType());
                ps.setString(5, t.getInvoiceNum());
                ps.setString(6, t.getBoxId());
                ps.setString(7, t.getLoadingTime());
                ps.setString(8, t.getSupplier());                
                ps.setString(9, t.getLabelCode());
                ps.setString(10, t.getBoxOrder());
                ps.setString(11, t.getBoxNum());
                ps.setString(12, t.getBoxType());
                ps.setString(13, t.getComponent());
                ps.setString(14, t.getColor());
                ps.setString(15,t.getVolume());
                ps.setString(16,t.getCount());
                ps.setString(17,t.getPrepareNum());
                ps.setString(18,t.getGrossWeight());
                ps.setString(19,t.getNetWeight());
                ps.setString(20,t.getUnits());
                ps.setString(21,t.getLabelNum());
                ps.setString(22,t.getState());
                ps.setString(23,t.getScanTime());
                ps.setString(24,t.getLock());
                ps.executeUpdate();
                x++;
            }
            System.out.println(x);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null)rs.close();
                if(ps!=null)ps.close();
                if(ct!=null)ct.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ct = DriverManager
					.getConnection(
							"jdbc:sqlserver://192.168.200.37; DatabaseName=android_database",
							"sa","1");
            ps=ct.prepareStatement("insert into comContainer values(?,?,?)");
            for(String s:container){
            	ps.setString(1, s);
                ps.setString(2, "0");
                ps.setString(3, "P");              
                ps.executeUpdate();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null)rs.close();
                if(ps!=null)ps.close();
                if(ct!=null)ct.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
	}
	public ArrayList<String> getData(){
		ArrayList<String> list = new ArrayList<>();
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ct = DriverManager
					.getConnection(
							"jdbc:sqlserver://192.168.200.37; DatabaseName=android_database",
							"sa","1");
			ps = ct.prepareStatement("select id from comPick_list");
//			ps = ct.prepareStatement("select id from comReceive_list");
			rs = ps.executeQuery();
			while(rs.next()){
					list.add(rs.getString("id"));
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[]args){
//		tools t = new tools();
//		try {
//			File file = new File("C:/Users/zzd/Desktop/databases/compick.csv");
////			File file = new File("C:/Users/zzd/Desktop/databases/comreceive.csv");
//			ArrayList<Collection> res= t.readCsv(file);
//			ArrayList<thing> list = (ArrayList<thing>)res.get(0);
//			Set<String> container = (Set<String>)res.get(1);
//			t.toDatabase(list,container);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		int c = 12;
		System.out.println(-c);
		System.out.println(-c+40);
		
	}
}
