package mysql;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Connection connection =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab","root","1907");
			Statement stat = (Statement) connection.createStatement();
			JFrame frame = new JFrame();
			frame.setSize(1440,768);
			frame.setLayout(null);
			Arayuz panel = new Arayuz(frame,connection,stat);
			frame.add(panel);
			panel.AnaEkran();
			
	
			
//			b1.addActionListener(new ActionListener() {
//				// UPDATE tabloadı set ogrno WHERE okul=kocaeli;
//				@Override
//				public void actionPerformed(ActionEvent e) {
//				
//					// TODO Auto-generated method stub
//					String x = jf1.getText();
//					String y = jf2.getText();
//					int xson = Integer.parseInt(x);
//					int yson = Integer.parseInt(y);
//					String sqlsorgu = "UPDATE oyun SET OyunAlanix = " + xson + "," + "OyunAlaniy = " + yson + ";";
//					Db.insert(sqlsorgu);
//					
//					
////					anapanel.setLayout(new GridLayout(xson,yson));
//					
////					System.out.println(sqlsorgu);
//					
//				}
//			});
		
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			String a = "select * from kisi WHERE ad = \"Yonetici\"";
//			ResultSet rs = stat.executeQuery(a);
//			
//			System.out.println(rs.getString("Sifre"));
//			ResultSet rs = stat.executeQuery("select * from kisi");
//			while(rs.next())
//			{
//				System.out.println(rs.getString("Sifre") );
//			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
