package service;


import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.Date;

@Path("/services")
public class Chese
{
	
	@POST
	@Path("/sign-in-secure-v2/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public static boolean signIn_ver2(@FormParam("username") String username, @FormParam("password") String password)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost:3307/chese";
			String connectionUser = "root";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM account");
			while (rs.next())
			{
				String u = rs.getString("username");
				String p = rs.getString("password");
				System.out.println(u+" "+p);
				if(username.equals(u) && password.equals(p))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}

		return false;
	}

}
