package service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.json.JSONException;
import org.json.*;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/services")
public class ChineseChess
{
	//final static String url = "jdbc:mysql://localhost:3306/ChineseChess";
	//String connectionUser = "root";
	//String connectionPassword = "12345678m@M";
	
	
	final static String url = "jdbc:mysql://db4free.net:3306/"+"chinesechess"+"?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
	
           
	String connectionUser = "vuongthai97";
	String connectionPassword = "12345678m@M";
	
	@PersistenceContext
	public EntityManager em;
	
	
	
	@POST
	@Path("/login/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public Response login2(User u) throws JSONException
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;		
		JSONObject jo3 = new JSONObject();
		jo3.put("mess", "Incorrect password or username entered. Please try again.");
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, connectionUser, connectionPassword);
				JSONObject jo = new JSONObject();		
				PreparedStatement pst = conn.prepareStatement("Select * from user where name=? and pass=?");
			       pst.setString(1, u.getName()); 
			       pst.setString(2,u.getPass());
			       rs = pst.executeQuery();        
			       if(rs.next())   {  			    	   
				       jo.put("name", rs.getString("name") );
				       jo.put("mess","successful");
			           System.out.println(rs.getString("name") + " - logged in !");
			           conn.close();
			           return Response.status(200).entity(jo.toString()).build();
			       }
			       else{
			    	   System.out.println(u.getName() + " - is trying login but not success !");
			    	   conn.close();
			    	   return Response.status(200).entity(jo3.toString()).build();
			       }          
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }	
			}			
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		return Response.status(200).entity(jo3.toString()).build();
	}
	
	@POST
	@Path("/registry/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser( User u) throws JSONException
    {	
		JSONObject jo3 = new JSONObject();
        Connection con=null;
        String s = " created ! ";
        try {   
        	Class.forName("com.mysql.jdbc.Driver");
        	con = DriverManager.getConnection(url, connectionUser, connectionPassword);
        String sql="insert into user values(null,?,0,0,0,?)";
        PreparedStatement pstm= con.prepareStatement(sql);
        pstm.setString(1, u.getName());
        pstm.setString(2, u.getPass());       
        pstm.executeUpdate();
        if(pstm != null) {
        		System.out.println(u.getName() + " created !");
        		con.close();
        	 return Response.status(201).entity(jo3.put("mess",u.getName() + s).toString()).build();
        }
       
         } catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
        return Response.status(201).entity(jo3.put("mess", "This user is not created !").toString()).build();
    }
	
	@POST
	@Path("/history/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateHis( History h) throws JSONException, ClassNotFoundException
    {	
		JSONObject jo3 = new JSONObject();
		Connection con=null;
		 ResultSet rs1 = null;
		 ResultSet rs2 = null;
        try {      
        	Class.forName("com.mysql.jdbc.Driver");
        	con = DriverManager.getConnection(url, connectionUser, connectionPassword);
        String sql="insert into history values(null,?,?,?,now())";
        PreparedStatement pstm= con.prepareStatement(sql); 
       
        PreparedStatement pstm1= con.prepareStatement("Select * from user where name=?");
        pstm1.setString(1, h.getId1().getName());
        rs1 = pstm1.executeQuery();
        if(rs1.next()) System.out.println("rs1 - "+rs1.getString("name"));
        
        PreparedStatement pstm2= con.prepareStatement("Select * from user where name=?");
        pstm2.setString(1, h.getId2().getName());
        rs2 = pstm2.executeQuery();
        if(rs2.next()) System.out.println("rs2 - "+rs2.getString("name"));
        
        pstm.setInt(1,rs1.getInt("id"));
        pstm.setInt(3, rs2.getInt("id"));
        pstm.setString(2, h.getStatus());
        pstm.executeUpdate();
        con.close();
        
        return Response.status(200).entity(jo3.put("mess", "Done !").toString()).build();
        
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return Response.status(201).entity(jo3.put("mess", "Failed !").toString()).build();
    }
	
	
	@POST
	@Path("/info/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public Response getInfo( User u) throws JSONException
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;		
		JSONObject jo3 = new JSONObject();
		jo3.put("mess", "User not exist");
		JSONObject info = new JSONObject();
		
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, connectionUser, connectionPassword);			
				PreparedStatement pst = conn.prepareStatement("Select * from user where name = ?");
			      // pst.setInt(1, u.getId().intValue()); 
				pst.setString(1, u.getName());
			       rs = pst.executeQuery();
			       
			       if(rs.next())   {  
			    	   info.put("id", rs.getInt("id"));
			    	   info.put("name", rs.getString("name"));
			    	   info.put("win", rs.getInt("win"));
			    	   info.put("lose",rs.getInt("lose"));
			    	   info.put("draw", rs.getInt("draw"));	   
			    	   conn.close();
			           return Response.status(200).entity(info.toString()).build();
			       }
			       else{
			    	   conn.close();
			    	   return Response.status(404).entity(jo3.toString()).build();
			       }          
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }	
			}	
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}	
		return Response.status(404).entity(jo3.toString()).build();		
	}
	
}
