package loginregisterapp_v1_00;

import java.awt.FontFormatException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class LoginRegisterApp_Main {

	public static void main(String[] args) throws SQLException {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
			System.err.print(e);
		}
		
		String url = "jdbc:mysql://localhost:3306/LoginRegisterAppDatabase";
		String uName = "root";
		String password = "My5QL2021!";
		

		String query = "select" + " * " + "from Users";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
		try {
			// Implementation of GUI
			LoginRegisterApp_GUI gui = new LoginRegisterApp_GUI();
			gui.launch();
			String userPassword = gui.getPassword();
			
			// SQL Query
			Connection con = DriverManager.getConnection(url, uName, password);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				String UserData = "";
				for (int i = 1; i <=6; i++) {
					UserData += result.getString(i) + ":";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
