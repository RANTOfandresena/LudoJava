
package common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import config.Config;
public class AccesBdd 
{
	private String 		driver 			= null;			//Ex : "oracle.jdbc.OracleDriver"
	private String 		url 			= null; 		//Ex : "jdbc:oracle:thin:@192.168.200.91:1521:DATA10G";
	private String 		user 			= null;
	private String 		password 		= null;
	private Connection 	conn 			= null;
	private ResultSet 	resultSet 		= null;
	
	public AccesBdd()
	{
		Config	config	=	new Config() ;
		this.driver 	= 	config.driver;
		this.url 		= 	config.url;
		this.user 		= 	config.user;
		this.password 	= 	config.password;
	}
	/**
	 * @param args
	 */
	public void loadDriver() 
	{
		try
		{
			//chargement Driver
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println("Driver non trouv�");// erreur du driver
		}
		catch(Exception e)
		{
			e.printStackTrace();//erreur du code 
		}
	}

	public Connection getConnection()
	{
		if (conn == null)
		{
			try
			{
				//Connexion
				conn = DriverManager.getConnection(url, user, password);
			}
			catch(SQLException e)
			{
				System.err.println("Probl�me de connexion  : "+ e.getMessage());
			}
		}
		else
		{
			try 
			{
				if (conn.isClosed())
					conn = DriverManager.getConnection(url, user, password);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	public ResultSet executeSelect(String sql)
	{
		Statement statement;
		try 
		{
			if (resultSet != null)
				resultSet.close();
			statement = getConnection().createStatement();
			resultSet = statement.executeQuery(sql);
			
			return resultSet ;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	public void executeUpdate(String sql)
	{
		Statement statement;
		try 
		{
			statement = getConnection().createStatement();
			statement.executeUpdate(sql);
		} 
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void closeConnection()
	{
		try 
		{
			if (resultSet != null)
				resultSet.close();
			if (conn != null)
				conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
