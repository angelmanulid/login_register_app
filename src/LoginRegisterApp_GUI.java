package loginregisterapp_v1_00;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.demo.FullDemo;

public class LoginRegisterApp_GUI implements ActionListener{
	
//---------------------------------------------------REGISTER FRAME----------------------------------------------------------------//
    JFrame frame_Register = new JFrame("Register");
    
	JTextField fieldFirstName_Register = new JTextField();
	JTextField fieldMiddleName_Register = new JTextField();
	JTextField fieldLastName_Register = new JTextField();
	JTextField fieldCountry_Register = new JTextField();
	JTextField fieldPhoneNumber_Register = new JTextField();
	JTextField fieldBirthdate_Register = new JTextField();
	JTextField fieldEmail_Register = new JTextField();
	JPasswordField fieldPassword_Register = new JPasswordField();
	JPasswordField fieldRepPassword_Register = new JPasswordField();
	
	String[] listCountry_Register = {"Afghanistan","Albania","Algeria","Andorra","Angola","Antigua and Barbuda","Argentina","Armenia",
			"Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin",
			"Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Brazil","Brunei","Bulgaria","Burkina Faso","Burundi",
			"Côte d'Ivoire","Cabo Verde","Cambodia","Cameroon","Canada","Central African Republic","Chad","Chile","China",
			"Colombia","Comoros","Congo (Congo-Brazzaville)","Costa Rica","Croatia","Cuba","Cyprus","Czechia (Czech Republic)",
			"Democratic Republic of the Congo","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador",
			"Equatorial Guinea","Eritrea","Estonia","Eswatini (formerly Swaziland)","Ethiopia","Fiji","Finland","France","Gabon",
			"Gambia","Georgia","Germany","Ghana","Greece","Grenada","Guatemala","Guinea","Guinea-Bissau","Guyana","Haiti",
			"Holy See","Honduras","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Israel","Italy","Jamaica",
			"Japan","Jordan","Kazakhstan","Kenya","Kiribati","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia",
			"Libya","Liechtenstein","Lithuania","Luxembourg","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta",
			"Marshall Islands","Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro",
			"Morocco","Mozambique","Myanmar (formerly Burma)","Namibia","Nauru","Nepal","Netherlands","New Zealand","Nicaragua",
			"Niger","Nigeria","North Korea","North Macedonia","Norway","Oman","Pakistan","Palau","Palestine State","Panama",
			"Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Qatar","Romania","Russia","Rwanda",
			"Saint Kitts and Nevis","Saint Lucia","Saint Vincent and the Grenadines","Samoa","San Marino","Sao Tome and Principe",
			"Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands",
			"Somalia","South Africa","South Korea","South Sudan","Spain","Sri Lanka","Sudan","Suriname","Sweden","Switzerland",
			"Syria","Tajikistan","Tanzania","Thailand","Timor-Leste","Togo","Tonga","Trinidad and Tobago","Tunisia","Turkey",
			"Turkmenistan","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States of America",
			"Uruguay","Uzbekistan","Vanuatu","Venezuela","Vietnam","Yemen","Zambia","Zimbabwe"};
	JComboBox<String> boxCountry_Register = new JComboBox<String>(listCountry_Register);
	
	JButton buttonRegister_Register = new JButton("Register");
	
	// Create and set up the date picker.
    DatePicker datePickerBirthdate_Register = new DatePicker();
    
  //---------------------------------------------------LOGIN FRAME----------------------------------------------------------------//
    JFrame frame_Login = new JFrame("Login");
    
	JTextField fieldEmail_Login = new JTextField();
	JPasswordField fieldPassword_Login = new JPasswordField();
	
	JButton buttonLogin_Login = new JButton("Login");
	
	
	// Create color
    Color LIGHTGRAY = new Color(240, 240, 240);
    Color DARKGRAY = new Color(105, 105, 105);
	
	public void launch() throws FontFormatException, IOException {
		
//------------------------------------REGISTER FRAME-------------------------------------------------//
		
		// Set Frame Close Operation
	    frame_Register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create Main Panel
//        JPanel cardRegister = new JPanel();
	    
  	    //**********************************************//
  	    Color PINK = new Color(231, 84, 128);
  	    Color VIOLET = new Color(51, 0, 102);
  	    GradientPanel cardRegister = new GradientPanel(VIOLET, PINK);
  	    //**********************************************//
  	    
        cardRegister.setPreferredSize(new Dimension(600, 900));
        
		// Top Panel Layout
		JPanel cardTop_Register = new JPanel();
		cardTop_Register.setPreferredSize(new Dimension(700, 100));
		
		// Top panel elements
		JLabel labelRegister_Register = new JLabel("Register", JLabel.CENTER);
		labelRegister_Register.setPreferredSize(new Dimension(700, 70));
		labelRegister_Register.setFont(new Font("serif", Font.PLAIN, 50)); 
 		JLabel labelSide_Register = new JLabel("Already have an account?");
 		JLabel linkLogin_Register = new JLabel("<html><u>Sign in here.</u><html>");
 		linkLogin_Register.setForeground(Color.BLUE.darker());
 		linkLogin_Register.setCursor(new Cursor(Cursor.HAND_CURSOR));
 		linkLogin_Register.addMouseListener(new MouseAdapter() {
        	 
            @Override
            public void mouseClicked(MouseEvent e) {
		        frame_Register.dispose();
		        frame_Register.setVisible(false);
		        frame_Login.setVisible(true);
		        buttonLogin_Login.requestFocusInWindow();
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
            	linkLogin_Register.setText("<html><u>Sign in here.</u><html>");
            }
 
            @Override
            public void mouseEntered(MouseEvent e) {
            	linkLogin_Register.setText("<html><u>Sign in here.</u><html>");
            }
 
        });
	
 		cardTop_Register.add(labelRegister_Register, BorderLayout.PAGE_START);
 		cardTop_Register.add(labelSide_Register, BorderLayout.CENTER);
 		cardTop_Register.add(linkLogin_Register, BorderLayout.LINE_END);
		
        // Mid Panel Layout
        JPanel cardMid_Register = new JPanel();
		cardMid_Register.setLayout(new BoxLayout(cardMid_Register, BoxLayout.PAGE_AXIS));
		cardMid_Register.setPreferredSize(new Dimension(350, 700));
		
	
        // Mid panel elements
		JLabel labelFirstName_Register = new JLabel("First name");
		JLabel labelMiddleName_Register = new JLabel("Middle name (optional)");
		JLabel labelLastName_Register = new JLabel("Last name");
		JLabel labelCountry_Register = new JLabel("Country");
		JLabel labelPhoneNumber_Register = new JLabel("Phone number");
		JLabel labelBirthdate_Register = new JLabel("Birthdate");
		JLabel labelEmail_Register = new JLabel("<html>&#9993; Email<html>");
		JLabel labelPassword_Register = new JLabel("<html>&#128274; Password<html>");
		JLabel labelPasswordCriteria_Register = new JLabel("<html>Password should contain:"
				+ "<br>&bull; at least 8 characters"
				+ "<br>&bull; at least 1 letter"
				+ "<br>&bull; at least 1 symbol"
				+ "<br>&bull; at least 1 number<html>");
		labelPasswordCriteria_Register.setPreferredSize(new Dimension(350, 100));
		JLabel labelRepPassword_Register = new JLabel("<html>&#128274; Repeat Password<html>");
						
  
	    // Adjust any needed date picker settings.
	    DatePickerSettings settings = datePickerBirthdate_Register.getSettings();
	    settings.setGapBeforeButtonPixels(0);
	    settings.setSizeTextFieldMinimumWidthDefaultOverride(false);
	    settings.setSizeTextFieldMinimumWidth(20);
	    settings.setSizeDatePanelMinimumHeight(200);
	    settings.setSizeDatePanelMinimumWidth(300);
	    settings.setFontValidDate(new Font("serif", Font.PLAIN, 20));
	    settings.setFontCalendarDateLabels(new Font("serif", Font.PLAIN, 18));
	    settings.setFontMonthAndYearMenuLabels(new Font("serif", Font.PLAIN, 18));
	    settings.setAllowKeyboardEditing(false);
	    settings.setAllowEmptyDates(true);
	    
	    // Set LocalDates
        final LocalDate today = LocalDate.now();
        LocalDate defDate = today.minusYears(18);
        defDate = defDate.withMonth(1);
        defDate = defDate.withDayOfMonth(1);
        YearMonth defYearMonth = YearMonth.from(defDate);
        LocalDate dateY2K = LocalDate.of(2000, Month.JANUARY, 1);
        datePickerBirthdate_Register.setDate(dateY2K);

        settings.setDefaultYearMonth(defYearMonth);
	    settings.setDateRangeLimits(today.minusYears(150), today.minusYears(18));

	    // Set icon of Date Picker toggle button
	    URL dateImageURL = FullDemo.class.getResource("/images/datepickerbutton1.png");
        Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
        ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
        
        // Create the date picker, and apply the image icon.
        JButton datePickerBirthdate_RegisterButton = datePickerBirthdate_Register.getComponentToggleCalendarButton();
        datePickerBirthdate_RegisterButton.setText("");
        datePickerBirthdate_RegisterButton.setIcon(dateExampleIcon);  
	    
	    // Set alignment and layout of components
		labelFirstName_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelLastName_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelMiddleName_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelCountry_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelPhoneNumber_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelBirthdate_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelEmail_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelPassword_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelRepPassword_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldFirstName_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldLastName_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldMiddleName_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		boxCountry_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		boxCountry_Register.setBackground(Color.WHITE);
		fieldPhoneNumber_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		datePickerBirthdate_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldEmail_Register.setAlignmentX(Component.LEFT_ALIGNMENT);		
		fieldPassword_Register.setAlignmentX(Component.LEFT_ALIGNMENT);	
		fieldRepPassword_Register.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Set initial text
		setInitialText(fieldFirstName_Register, "First name");
		setInitialText(fieldLastName_Register, "Last name");
		setInitialText(fieldMiddleName_Register, "Middle name");
		setInitialText(fieldPhoneNumber_Register, "Phone number");
		setInitialText(fieldEmail_Register, "Email");
		setInitialText(fieldPassword_Register, "Password");
		setInitialText(fieldRepPassword_Register, "Repeat password");
		

		// Add all components to Middle
		cardMid_Register.add(labelFirstName_Register);
		cardMid_Register.add(fieldFirstName_Register);
		cardMid_Register.add(labelLastName_Register);
		cardMid_Register.add(fieldLastName_Register);
		cardMid_Register.add(labelMiddleName_Register);
		cardMid_Register.add(fieldMiddleName_Register);
		cardMid_Register.add(labelCountry_Register);
		cardMid_Register.add(boxCountry_Register);
		cardMid_Register.add(labelPhoneNumber_Register);
		cardMid_Register.add(fieldPhoneNumber_Register);
		cardMid_Register.add(labelBirthdate_Register);
	    cardMid_Register.add(datePickerBirthdate_Register);
		cardMid_Register.add(labelEmail_Register);
		cardMid_Register.add(fieldEmail_Register);
		cardMid_Register.add(labelPassword_Register);
		cardMid_Register.add(fieldPassword_Register);
		cardMid_Register.add(labelPasswordCriteria_Register);
		cardMid_Register.add(labelRepPassword_Register);
		cardMid_Register.add(fieldRepPassword_Register);
			
		// Bottom Panel Layout
		JPanel cardBottom_Register = new JPanel();
		cardBottom_Register.setPreferredSize(new Dimension(600, 150));
		cardBottom_Register.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			
		// Bottom panel elements
		buttonRegister_Register.setPreferredSize(new Dimension(200, 50));
		buttonRegister_Register.addActionListener(this);
		cardBottom_Register.add(buttonRegister_Register);
		
		// Add sub panels to main panel
        cardRegister.add(cardTop_Register, BorderLayout.PAGE_START);
        cardRegister.add(cardMid_Register, BorderLayout.CENTER);
        cardRegister.add(cardBottom_Register, BorderLayout.PAGE_END);
              
             
        // Add panel to frame_Register
        frame_Register.add(cardRegister);
        
        //***********************************************************//
        // Create Font and set Font of all elements
        InputStream is = LoginRegisterApp_GUI.class.getResourceAsStream("nunito-sans.bold.ttf");
        Font NunitoSansBold = Font.createFont(Font.TRUETYPE_FONT, is);
        Font sizedNunitoSansBold = NunitoSansBold.deriveFont(40f);
        
        is = LoginRegisterApp_GUI.class.getResourceAsStream("nunito-sans.semibold.ttf");
        Font NunitoSansSemiBold = Font.createFont(Font.TRUETYPE_FONT, is);
        Font sizedNunitoSansSemiBold = NunitoSansSemiBold.deriveFont(18f);
        Font sizedSmallNunitoSansSemiBold = NunitoSansSemiBold.deriveFont(14f);
        
        setFontAllJTextField(frame_Register, sizedNunitoSansSemiBold);
        setFontAllJLabel(frame_Register, sizedNunitoSansSemiBold, Color.WHITE);
        labelRegister_Register.setFont(sizedNunitoSansBold);
        labelSide_Register.setFont(sizedSmallNunitoSansSemiBold);
        linkLogin_Register.setFont(sizedSmallNunitoSansSemiBold);
        boxCountry_Register.setFont(sizedNunitoSansSemiBold);
        labelPasswordCriteria_Register.setFont(sizedSmallNunitoSansSemiBold);
        buttonRegister_Register.setFont(sizedNunitoSansSemiBold);
        
        cardTop_Register.setOpaque(false);
        cardMid_Register.setOpaque(false);
        cardBottom_Register.setOpaque(false);
        //***********************************************************//
        
        // Set background color
        setBackgroundAll(frame_Register, LIGHTGRAY);
          
        //Display the window.
        frame_Register.pack();
        buttonRegister_Register.requestFocusInWindow(); 
        frame_Register.setVisible(false);
         
            
        
//-----------------------------------------------------LOGIN FRAME-----------------------------------------------------------//
  		       
         
         // Set Frame Close Operation
  	    frame_Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create Main Panel
//        JPanel cardLogin = new JPanel();
  	    
  	    
  	    //**********************************************//
  	    PINK = new Color(231, 84, 128);
  	    VIOLET = new Color(51, 0, 102);
  	    GradientPanel cardLogin = new GradientPanel(VIOLET, PINK);
  	    //**********************************************//
 
        cardLogin.setPreferredSize(new Dimension(600, 400));
         
 		// Top Panel Layout
 		JPanel cardTop_Login = new JPanel();
 		cardTop_Login.setPreferredSize(new Dimension(350, 120));
 		
 		// Top panel elements
 		JLabel labelLogin_Login = new JLabel("Login", JLabel.CENTER);
 		labelLogin_Login.setPreferredSize(new Dimension(350, 80));
 		labelLogin_Login.setFont(new Font("serif", Font.PLAIN, 50)); 
 		JLabel labelSide_Login = new JLabel("Don't have an account?");
 		JLabel linkRegister_Login = new JLabel("<html><u>Create an account here.</u><html>");
 		linkRegister_Login.setForeground(Color.BLUE.darker());
 		linkRegister_Login.setCursor(new Cursor(Cursor.HAND_CURSOR));
 		linkRegister_Login.addMouseListener(new MouseAdapter() {
        	 
            @Override
            public void mouseClicked(MouseEvent e) {
				frame_Login.dispose();
		        frame_Login.setVisible(false);
		        frame_Register.setVisible(true);
		        buttonRegister_Register.requestFocusInWindow(); 
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
            	linkRegister_Login.setText("<html><u>Create an account here.</u><html>");
            }
 
            @Override
            public void mouseEntered(MouseEvent e) {
            	linkRegister_Login.setText("<html><u>Create an account here.</u><html>");
            }
 
        });
	
 		cardTop_Login.add(labelLogin_Login, BorderLayout.PAGE_START);
 		cardTop_Login.add(labelSide_Login, BorderLayout.CENTER);
 		cardTop_Login.add(linkRegister_Login, BorderLayout.LINE_END);
 		
        // Mid Panel Layout
        JPanel cardMid_Login = new JPanel();
 		cardMid_Login.setLayout(new BoxLayout(cardMid_Login, BoxLayout.PAGE_AXIS));
 		cardMid_Login.setPreferredSize(new Dimension(350, 150)); 
 		
 		//Mid panel elements
		JLabel labelEmail_Login = new JLabel("<html>&#9993; Email<html>");
		JLabel labelPassword_Login = new JLabel("<html>&#128274; Password<html>");
 	    
 	    // Set alignment and layout of components
 		labelEmail_Login.setAlignmentX(Component.LEFT_ALIGNMENT);
 		labelPassword_Login.setAlignmentX(Component.LEFT_ALIGNMENT);
 		fieldEmail_Login.setAlignmentX(Component.LEFT_ALIGNMENT);
 		fieldPassword_Login.setAlignmentX(Component.LEFT_ALIGNMENT);
	
 		// Add all components to Middle
 		cardMid_Login.add(labelEmail_Login);
 		cardMid_Login.add(fieldEmail_Login);
 		cardMid_Login.add(labelPassword_Login);
 		cardMid_Login.add(fieldPassword_Login);
 		
 		// Set initial text
 		setInitialText(fieldEmail_Login, "Email");
		setInitialText(fieldPassword_Login, "Password");		
 			
 		// Bottom Panel Layout
 		JPanel cardBottom_Login = new JPanel();
 		cardBottom_Login.setPreferredSize(new Dimension(350, 150));
 		cardBottom_Login.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
 			
 		// Bottom panel elements
 		buttonLogin_Login.setPreferredSize(new Dimension(250, 50));
 		buttonLogin_Login.addActionListener(this);
 		cardBottom_Login.add(buttonLogin_Login);
 		
 		// Add sub panels to main panel
        cardLogin.add(cardTop_Login);
        cardLogin.add(cardMid_Login);
        cardLogin.add(cardBottom_Login);
        

        
//        labelLogin_Login.setFont(new Font("serif", Font.PLAIN, 30));
//        labelEmail_Login.setFont(f);
//        labelPassword_Login.setFont(f);
//        fieldEmail_Login.setFont(f);
//        fieldPassword_Login.setFont(f);
//        buttonLogin_Login.setFont(f);
//        labelLogin_Login.setForeground(Color.WHITE);
//        labelSide_Login.setForeground(Color.WHITE);
//        linkRegister_Login.setForeground(Color.WHITE);
//        labelEmail_Login.setForeground(Color.WHITE);
//        labelPassword_Login.setForeground(Color.WHITE);
       
        // Add panel to frame_Login
        frame_Login.add(cardLogin);
        
        //***********************************************************//
        cardTop_Login.setOpaque(false);
        cardMid_Login.setOpaque(false);
        cardBottom_Login.setOpaque(false);
        //***********************************************************//
        
        // Set Font
        //***********************************************************//        
        
        setFontAllJTextField(frame_Login, sizedNunitoSansSemiBold);
        setFontAllJLabel(frame_Login, sizedNunitoSansSemiBold, Color.WHITE);
        labelLogin_Login.setFont(sizedNunitoSansBold);
        labelLogin_Login.setForeground(Color.WHITE);
        labelSide_Login.setFont(sizedSmallNunitoSansSemiBold);
        labelSide_Login.setForeground(Color.WHITE);
        linkRegister_Login.setFont(sizedSmallNunitoSansSemiBold);
        linkRegister_Login.setForeground(Color.WHITE);
        buttonLogin_Login.setFont(sizedNunitoSansSemiBold);

        
        //***********************************************************//
        
        // Set background color
        setBackgroundAll(frame_Login, LIGHTGRAY);
           
        //Display the window.
        frame_Login.pack();
        buttonLogin_Login.requestFocusInWindow(); 
        frame_Login.setVisible(true);
        

        
 
        
        

         
//------------------------------------------------------------TEST CASE---------------------------------------------------------------//       
//         LoginRegisterApp_Test testRegister = new LoginRegisterApp_Test(fieldFirstName_Register, fieldMiddleName_Register, fieldLastName_Register, boxCountry_Register, 
//        		 fieldPhoneNumber_Register, datePickerBirthdate_Register, fieldEmail_Register, fieldPassword_Register, fieldRepPassword_Register);
//         
//         LoginRegisterApp_Test testLogin = new LoginRegisterApp_Test(fieldEmail_Login, fieldPassword_Login);
//------------------------------------------------------------TEST CASE---------------------------------------------------------------// 
		
		
		
	}


	public String getPassword() {
		return fieldPassword_Register.toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		try {
			String url = "jdbc:mysql://localhost:3306/LoginRegisterAppDatabase";
			String uName = "root";
			String password = "My5QL2021!";
			
			// SQL Query
			Connection con = DriverManager.getConnection(url, uName, password);
			Statement statement = con.createStatement();
			
			// Register Frame Functions
			if(e.getSource() == buttonRegister_Register) {

				LoginRegisterApp_Register newuser = new LoginRegisterApp_Register(statement);
				boolean validity = newuser.validateRegister(fieldFirstName_Register, fieldMiddleName_Register, fieldLastName_Register, boxCountry_Register, 
						fieldPhoneNumber_Register, datePickerBirthdate_Register, fieldEmail_Register, fieldPassword_Register, fieldRepPassword_Register);
				if(validity == true) {
					try {
						newuser.register();
						frame_Register.setVisible(false); 
						frame_Register.dispose();
						frame_Login.setVisible(true);
						buttonLogin_Login.requestFocusInWindow();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			
			// Login Frame Functions
			if(e.getSource() == buttonLogin_Login) {

				LoginRegisterApp_Login user = new LoginRegisterApp_Login(statement);
				boolean validity = user.validateLogin(fieldEmail_Login, fieldPassword_Login);
				if(validity == true) {
					frame_Login.setVisible(false); 
					frame_Login.dispose();
				}
			}
				
				
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		
	}

	public void setBackgroundAll (Container parent, Color color)
	{
	    for (Component c : parent.getComponents()) {
	    	if (c instanceof JTextField) {
	    		continue;
	    	}
	    	parent.setBackground(color);
	    	c.setBackground(color);
	    	if (c instanceof Container) {
	    		setBackgroundAll((Container)c, color);
	        }

	    }
	}
	
	public void setFontAllJLabel (Container parent, Font font, Color color)
	{
	    for (Component c : parent.getComponents()) {
	    	if (c instanceof Container) {
	    		setFontAllJLabel((Container)c, font, color);
	        }
	    	if (c instanceof JLabel) {
	    		c.setFont(font);
	    		c.setForeground(color);
	    	}


	    }
	}
	
	public void setFontAllJTextField (Container parent, Font font)
	{
	    for (Component c : parent.getComponents()) {
	    	if (c instanceof Container) {
	    		setFontAllJTextField((Container)c, font);
	        }
	    	if (c instanceof JTextField) {
	    		c.setFont(font);
	    	}


	    }
	}
	
	
	private void setInitialText(JTextField field, String text) {
		field.setText(text);
		field.setForeground(DARKGRAY);
		field.addMouseListener(new MouseAdapter() {
       	 
            @Override
            public void mouseClicked(MouseEvent e) {
        		if(field.getText().equals(text)) {
	            	field.setText("");
	            	field.setForeground(Color.BLACK);
        		}
            }
 
        });
		field.addFocusListener(new FocusListener() {
 			@Override
 			public void focusGained(FocusEvent e) {
 				if(field.getText().equals(text)) {
	 				field.setText("");
	 				field.setForeground(Color.BLACK);
 				}
 				
 			}


 			@Override
 			public void focusLost(FocusEvent e) {
 				if(field.getText().isEmpty()) {
 					field.setText(text);
 					field.setForeground(DARKGRAY);
 				}
 			}

 		});
	}
	
	private void setInitialText(JPasswordField field, String text) {
		field.setEchoChar((char) 0);
		field.setText(text);
		field.setForeground(DARKGRAY);
		field.addMouseListener(new MouseAdapter() {
       	 
            @Override
            public void mouseClicked(MouseEvent e) {
            	if(field.getEchoChar() == (char) 0) {
	            	field.setText("");
	            	field.setEchoChar('*');
	            	field.setForeground(Color.BLACK);
            	}
            }
 
        });
		
		field.addFocusListener(new FocusListener() {
 			@Override
 			public void focusGained(FocusEvent e) {
 				if(field.getEchoChar() == (char) 0) {
	 				field.setText("");
	            	field.setEchoChar('*');
	 				field.setForeground(Color.BLACK);
 				}

 				
 			}


 			@Override
 			public void focusLost(FocusEvent e) {	
 				if(String.valueOf(field.getPassword()).isEmpty()) {
 					field.setEchoChar((char) 0);
 					field.setText(text);
 					field.setForeground(DARKGRAY);
 				}
 			}

 		});
		
	}





}
