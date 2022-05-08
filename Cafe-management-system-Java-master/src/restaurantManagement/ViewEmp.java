package restaurantManagement;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewEmp{
	int cnt,r,c;
	JScrollPane jsp;
	String columns[];
	String data[][];
	JTable table;
	int count=0;
	String compare;

	final JDialog dialog=new JDialog();
	String nameoftable;
	private JButton btnClose;
	
	

	public ViewEmp(String query) {
		dialog.getContentPane().setPreferredSize(new Dimension(1000, 600));
		dialog.getContentPane().setSize(new Dimension(1000, 600));
		dialog.setModal(true);
		dialog.setSize(new Dimension(1000, 632));
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		Connection con=DBConnection.connect();
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			columns=new String[]{"ID","Name","Gender","Age","Contact","Post","Salary","Joining Date"};
			ResultSet rs=ps.executeQuery();
			if(!(rs.next())){
				JOptionPane.showMessageDialog(null, "Data Not Found!");
				dialog.dispose();
			}
			else
			{
			rs.last();
			cnt=rs.getRow();
			rs.beforeFirst();
			data=new String[cnt][8];
			while(rs.next())
			{
				data[r][c]=String.valueOf(rs.getInt("eid"));
				++c;
				data[r][c]=String.valueOf(rs.getString("ename"));
				++c;
				data[r][c]=String.valueOf(rs.getString("gender"));
				++c;
				data[r][c]=String.valueOf(rs.getInt("age"));
				compare=data[r][c];
				++c;
				data[r][c]=String.valueOf(rs.getString("ecntct"));
				++c;
				data[r][c]=String.valueOf(rs.getString("epost"));
				++c;
				data[r][c]=String.valueOf(rs.getString("salary"));
				++c;
				data[r][c]=String.valueOf(rs.getString("joindt"));
				c++;
				++r;
				c=0;
			}
			if(compare.isEmpty())
			{
				dialog.dispose();
			}
			else
			{
			table=new JTable(data,columns);
			table.setFont(new Font("Century Gothic", Font.PLAIN, 12));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jsp=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp.setSize(new Dimension(1000, 600));
			jsp.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			dialog.getContentPane().setLayout(null);
			jsp.setBounds(0, 0, 994, 571);
			dialog.getContentPane().add(jsp);
			
			
			
			btnClose = new JButton("CLOSE");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
				}
			});
			btnClose.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			btnClose.setBounds(437, 576, 89, 23);
			dialog.getContentPane().add(btnClose);
			dialog.setModal(true);
			dialog.setVisible(true);
			}
			}
			
		}
		catch (SQLException se)
		{
			JOptionPane.showMessageDialog(null, "Data Not Found!");
			se.printStackTrace();
		}
		
	}
	public static void main(String args[])
	{
	}
}
