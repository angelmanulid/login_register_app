package loginregisterapp_v1_00;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

public class LoginRegisterApp_Test {
	
	public LoginRegisterApp_Test(JTextField fieldFirstName, JTextField fieldMiddleName, JTextField fieldLastName, JComboBox<String> boxCountry, 
			JTextField fieldPhoneNumber, DatePicker datePickerBirthdate, JTextField fieldEmail, JPasswordField fieldPassword, JPasswordField fieldRepPassword) {
		fieldFirstName.setText("Jose");
		fieldMiddleName.setText("Marie");
		fieldLastName.setText("Chan");
		boxCountry.setSelectedItem("Philippines");
		fieldPhoneNumber.setText("09123456789");
		datePickerBirthdate.setText("January 1, 2000");
		fieldEmail.setText("josemariechan@yahoo.com");
		fieldPassword.setText("asdf1234!@#$");
		fieldRepPassword.setText("asdf1234!@#$");
		
	}
	
	public LoginRegisterApp_Test(JTextField fieldEmail, JPasswordField fieldPassword) {
		fieldEmail.setText("josemariechan@yahoo.com");
		fieldPassword.setText("asdf1234!@#$");
		
	}

}
