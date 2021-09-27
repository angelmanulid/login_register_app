package loginregisterapp_v1_00;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

public class LoginRegisterApp_Register {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String country;
	private String phoneNumber;
	private String birthdate;
	private String email;
	private char[] password;
	
	private Statement statement;
	
	public LoginRegisterApp_Register(Statement statement) {
		this.statement = statement;
	}
	
	
	boolean validateRegister(JTextField fieldFirstName, JTextField fieldMiddleName, JTextField fieldLastName, JComboBox<String> boxCountry, 
			JTextField fieldPhoneNumber, DatePicker datePickerBirthdate, JTextField fieldEmail, JPasswordField fieldPassword, JPasswordField fieldRepPassword) {
		firstName = fieldFirstName.getText();
		middleName = fieldMiddleName.getText();
		lastName = fieldLastName.getText();
		country = boxCountry.getSelectedItem().toString();
		phoneNumber = fieldPhoneNumber.getText();
		birthdate = datePickerBirthdate.getDate().toString();
		email = fieldEmail.getText();
		password = fieldPassword.getPassword();
		
		
		
		// Setup Patterns
		Pattern alpha = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
		Pattern num = Pattern.compile("[0-9]");
		Pattern patternEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
		
		// Check if FirstName is valid
		Matcher m = alpha.matcher(firstName);
		boolean a = m.find();
		if(!a) {
			fieldFirstName.setBackground(Color.RED);
		} else fieldFirstName.setBackground(Color.WHITE);
		
		// Check if MiddleName is valid
		boolean b = true;
		if(middleName.isEmpty()) {
			b = true;
		} else {
			m = alpha.matcher(middleName);
			b = m.find();
			if(!b) {
				fieldMiddleName.setBackground(Color.RED);
			} else fieldMiddleName.setBackground(Color.WHITE);
		}

		
		// Check if LastName is valid
		m = alpha.matcher(lastName);
		boolean c = m.find();
		if(!c) {
			fieldLastName.setBackground(Color.RED);
		} else fieldLastName.setBackground(Color.WHITE);
		
		// Check if PhoneNumber is valid
		m = num.matcher(phoneNumber);
		boolean d = m.find();
		if(!d) {
			fieldPhoneNumber.setBackground(Color.RED);
		} else fieldPhoneNumber.setBackground(Color.WHITE);
		
		// Check if Email is valid
		m = patternEmail.matcher(email);
		boolean e = m.find();
		if(!e) {
			fieldEmail.setBackground(Color.RED);
		} else fieldEmail.setBackground(Color.WHITE);
		
		//! Implement validation if Email is duplicate
		boolean f = true;
		String emptyTableQuery = "Select COUNT(*) from Users";
		String validateRegisterQuery = "select " + "Email" + " from Users" + " where Email = '" + fieldEmail.getText() + "'";
		System.out.println(validateRegisterQuery);
		try {
			ResultSet result = statement.executeQuery(emptyTableQuery);
			result.next();
			if(result.getInt(1)==0) {
				System.out.println("Empty Table");
				f = true;
			} else {
				result = statement.executeQuery(validateRegisterQuery);
				if(result.next() == false) {
					System.out.println("This email is still not registered.");
					f = true;
				} else {
					f = false;
					JOptionPane.showMessageDialog(null, "This email is already registered.\nPlease log in instead.", "Email already registered", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// Check if Password is valid
		boolean g;
		System.out.println("Password Length: " + password.length);
	    if(password.length>=8)
	    {

	           Matcher hasLetter = alpha.matcher(String.valueOf(password));
	           Matcher hasDigit = num.matcher(String.valueOf(password));
	           Matcher hasSpecial = special.matcher(String.valueOf(password));

	           if(hasLetter.find() && hasDigit.find() && hasSpecial.find()) {
	        	   g = true;
	           } else g = false;
	    }
	    else g = false;

		if(!g) {
			fieldPassword.setBackground(Color.RED);
		} else fieldPassword.setBackground(Color.WHITE);
		
		// Check if Password and Repeat Password match
		boolean h = Arrays.equals(password, fieldRepPassword.getPassword());
		if(!h) {
			fieldPassword.setBackground(Color.RED);
			fieldRepPassword.setBackground(Color.RED);
			JOptionPane.showMessageDialog(null, "Passwords does not match.");
		} else {
			fieldPassword.setBackground(Color.WHITE);
			fieldRepPassword.setBackground(Color.WHITE);
		}
		
		
		if (a&&b&&c&&d&&e&&f&&g&&h) {
			return true;
		} else return false;
		
		
	}


	public void register() throws ParseException {
		// Convert Birthdate to SQL Date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
		java.util.Date birthdateStr = formatter.parse(birthdate);
		java.sql.Date birthdateDB = new java.sql.Date(birthdateStr.getTime());
		String registerQuery = "INSERT INTO users (First_Name,Middle_Name,Last_Name,Country,Phone_Number,Birthdate,Email,PasswordHash) VALUES(" 
				+ "'" + firstName + "'" + ","+ "'"  + middleName + "'"  + "," + "'"  + lastName + "'"  + "," + "'"  + country + "'"  + "," + "'" 
				+ phoneNumber + "'"  + "," + "'" + birthdateDB + "'" + "," + "'" + email + "'" + "," + "'" + String.valueOf(password) + "'" + ")";
		System.out.println("Register Query: " + registerQuery);
		try {
			int registerResult = statement.executeUpdate(registerQuery);
			System.out.println("Rows Affected:" + registerResult);
			if(registerResult != 0) {
				//Pop up message
				if(middleName.equals("Middle name")) {
					JOptionPane.showMessageDialog(null, "Registration Successful!\n" + " Account created for " + firstName +  " " + lastName + ".", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
				} else JOptionPane.showMessageDialog(null, "Registration Successful!\n" + " Account created for " + firstName + " " + middleName + " " + lastName + ".", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Account created for " + firstName + " " + lastName);
			}  else JOptionPane.showMessageDialog(null, "Registration is NOT Successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
