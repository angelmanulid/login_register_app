package loginregisterapp_v1_00;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

public class LoginRegisterApp_Login {
	
	private String email;
	private char[] password;
	
	private Statement statement;
	
	public LoginRegisterApp_Login(Statement statement) {
		this.statement = statement;
	}
	
	boolean validateLogin(JTextField fieldEmail, JPasswordField fieldPassword) {
		email = fieldEmail.getText();
		password = fieldPassword.getPassword();
		
		boolean a = false; //validity of Email
		boolean b = false; //validity of Password
		String validateAccountLoginQuery = "select " + "Email" + " from Users" + " where Email = '" + fieldEmail.getText() + "'";
		System.out.println(validateAccountLoginQuery);
		try {
			// Check if email is already registered
			ResultSet result = statement.executeQuery(validateAccountLoginQuery);
				if(result.next() == false && !email.equals("Email")) {
					JOptionPane.showMessageDialog(null, "This email is not yet registered.\n Please create account first.", "Invalid Credentials", JOptionPane.ERROR_MESSAGE);
					System.out.println("This email is still not registered.\n Please create account first.");
					a = false;
				} else {
					a = true;
					// Compare password
					String validatePasswordLoginQuery = "select " + "PasswordHash" + " from Users" + " where Email = '" + fieldEmail.getText() + "'";
					result = statement.executeQuery(validatePasswordLoginQuery);
					result.next();
					if(result.getString("PasswordHash").equals(String.valueOf(password))) {
						System.out.println(fieldEmail.getText() + " has logged in.");
						JOptionPane.showMessageDialog(null, "Login Successful!");
						b = true;
					} else b = false;
					
				}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		if (a&&b) {
			return true;
		} else return false;
	}

}
