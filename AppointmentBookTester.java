import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AppointmentBookTester {
	private AppointmentBook book = new AppointmentBook();
	private JFrame frmAppointmentMaker;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JSpinner spinner_1;
	private JSpinner spinner_2;
	private JComboBox<String> comboBox_4;
	/**
	 * Create the application.
	 */
	public AppointmentBookTester() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentBookTester window = new AppointmentBookTester();
					window.frmAppointmentMaker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		new FileDownloader();
		FileDownloader.download("http://g-ecx.images-amazon.com/images/G/02/acs/ms/01/calendar.png", "icon.png");
		ImageIcon imgicon = new ImageIcon("icon.png");
		frmAppointmentMaker = new JFrame();
		frmAppointmentMaker.setTitle("Appointment Maker");
		frmAppointmentMaker.setIconImage(imgicon.getImage());
		frmAppointmentMaker.setSize(1150, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmAppointmentMaker.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAppointmentMaker.setLocation(dim.width/2-frmAppointmentMaker.getSize().width/2, dim.height/2-frmAppointmentMaker.getSize().height/2 - 20);
		frmAppointmentMaker.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we)
			  { 
			    String ObjButtons[] = {"Yes","No"};
			    int PromptResult = JOptionPane.showOptionDialog(null, 
			        "Are you sure you want to exit?", "Appointment Maker", 
			        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
			        ObjButtons,ObjButtons[1]);
			    if(PromptResult==0)
			    {
			    	int PromptResult2 = JOptionPane.showOptionDialog(null, 
			        "Do you want to save your Appointment Book? \nIf yes, then it will be saved into a readable format in AppointmentBook.txt and a copy of the AppointmentBook for future use will be stored in AppointmentBook.ser \n(Overwriting May Occur)", "Appointment Maker", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
					ObjButtons,ObjButtons[1]);
			    	if(PromptResult2==0)
			    	{
					    try {
							PrintWriter out = new PrintWriter("AppointmentBook.txt");
							String z = book.toString();
						    out.print(z);
						    out.close();
						    ObjectOutputStream a = new ObjectOutputStream(new FileOutputStream("AppointmentBook.ser"));
						    a.writeObject(book);
						    a.flush();
						    a.close();
					    }
					    catch (IOException x)
				        {
				        	JOptionPane.showMessageDialog(null,"The Appointment Book couldn't be saved. All data has been lost.");
				        }
					}
				    System.exit(0);
			    }
			  }
			});
		frmAppointmentMaker.addWindowListener(new WindowAdapter() {
			  public void windowOpened(WindowEvent we)
			  {
				  try 
				  {
						ObjectInputStream a = new ObjectInputStream(new FileInputStream("AppointmentBook.ser"));
						AppointmentBook x = (AppointmentBook) a.readObject();
				    	a.close();
					    String ObjButtons[] = {"Yes","No"};
					    int PromptResult = JOptionPane.showOptionDialog(null, 
					    "Continue From Previous Book?", "Appointment Maker", 
					    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
					        ObjButtons,ObjButtons[1]);
					    if(PromptResult==0)
					    {
					    	book = x;
					    }
					    else
					    {
					    	JOptionPane.showMessageDialog(null, "A new book has been created");
					    }
			        }
				    catch(IOException x) 
				    {
				    	JOptionPane.showMessageDialog(null, "Welcome to Appointment Maker");
				    
				    }
				    catch(ClassNotFoundException x)
				    {
				    	JOptionPane.showMessageDialog(null, "Welcome to Appointment Maker");;
				    }
			   }
			  });
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		frmAppointmentMaker.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblAppointmentMaker = new JLabel("Appointment Maker");
		lblAppointmentMaker.setBackground(new Color(255, 255, 255));
		lblAppointmentMaker.setForeground(new Color(255, 255, 255));
		lblAppointmentMaker.setFont(new Font("Times New Roman", Font.BOLD, 28));
		panel.add(lblAppointmentMaker);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBackground(new Color(0, 0, 128));
		frmAppointmentMaker.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(148, 0, 211));
		panel_2.setBackground(new Color(0, 0, 128));
		
		JLabel lblToPrintAppointment = new JLabel("To Print Appointment Book, Click Below:");
		lblToPrintAppointment.setForeground(new Color(255, 255, 255));
		lblToPrintAppointment.setHorizontalAlignment(SwingConstants.CENTER);
		lblToPrintAppointment.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JButton btnPrintBook = new JButton("Print Book");
		btnPrintBook.setForeground(new Color(255, 255, 255));
		btnPrintBook.setBackground(new Color(65, 105, 225));
		btnPrintBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, book.toString());
			}
		});
		btnPrintBook.setFont(new Font("Stencil", Font.PLAIN, 20));
		
		JLabel lblToPrintAppointment_1 = new JLabel("For Appointments On A Specific Day:");
		lblToPrintAppointment_1.setForeground(new Color(255, 255, 255));
		lblToPrintAppointment_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblToPrintAppointment_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblSelectDay = new JLabel("Select Date (MM/DD/YY):");
		lblSelectDay.setForeground(new Color(255, 255, 255));
		lblSelectDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDay.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JSpinner spinner = new JSpinner();
		spinner.setBackground(new Color(65, 105, 225));
		spinner.setForeground(new Color(255, 255, 255));
		spinner.setFont(new Font("Times New Roman", Font.BOLD, 20));
		spinner.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
		
		JButton btnPrintDayBook = new JButton("Print Day Book");
		btnPrintDayBook.setForeground(new Color(255, 255, 255));
		btnPrintDayBook.setBackground(new Color(65, 105, 225));
		btnPrintDayBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = (Date) spinner.getValue();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				JOptionPane.showMessageDialog(null, new AppointmentBook(book.getAppointmentsOnDay(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH))));

			}
		});
		spinner.setEditor(new JSpinner.DateEditor(spinner,"M/d/yyyy"));
		btnPrintDayBook.setFont(new Font("Stencil", Font.PLAIN, 20));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(25)
									.addComponent(lblSelectDay, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(120)
									.addComponent(btnPrintBook))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblToPrintAppointment, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblToPrintAppointment_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addGap(122))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnPrintDayBook, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addGap(93)))
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblToPrintAppointment)
					.addGap(18)
					.addComponent(btnPrintBook)
					.addGap(20)
					.addComponent(lblToPrintAppointment_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblSelectDay, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPrintDayBook, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
		);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setForeground(new Color(255, 255, 255));
		lblFirstName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField.setBackground(new Color(65, 105, 225));
		textField.setForeground(new Color(255, 255, 255));
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setForeground(new Color(255, 255, 255));
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_1.setBackground(new Color(65, 105, 225));
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setColumns(10);
		
		JLabel lblEnterTheDate = new JLabel("Enter The Date (MM/DD/YY): ");
		lblEnterTheDate.setForeground(new Color(255, 255, 255));
		lblEnterTheDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblDayOfThe = new JLabel("Day of the Week (e.g. Friday)");
		lblDayOfThe.setForeground(new Color(255, 255, 255));
		lblDayOfThe.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblDescriptionOfEvent = new JLabel("Description Of Event:");
		lblDescriptionOfEvent.setForeground(new Color(255, 255, 255));
		lblDescriptionOfEvent.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_4.setBackground(new Color(65, 105, 225));
		textField_4.setForeground(new Color(255, 255, 255));
		textField_4.setColumns(10);
		
		JLabel lblStartTimeOf = new JLabel("Start Time of Event: ");
		lblStartTimeOf.setForeground(new Color(255, 255, 255));
		lblStartTimeOf.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblEndTimeOf = new JLabel("End Time of Event: ");
		lblEndTimeOf.setForeground(new Color(255, 255, 255));
		lblEndTimeOf.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JButton btnAddAppointment = new JButton("Add Appointment");
		btnAddAppointment.setForeground(new Color(255, 255, 255));
		btnAddAppointment.setBackground(new Color(65, 105, 225));
		btnAddAppointment.setFont(new Font("Stencil", Font.PLAIN, 20));
		
		
		
		spinner_1 = new JSpinner();
		spinner_1.setBackground(new Color(65, 105, 225));
		spinner_1.setForeground(new Color(255, 255, 255));
		spinner_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		spinner_1.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
		spinner_1.setEditor(new JSpinner.DateEditor(spinner_1,"h:mm aa"));
		
		
		spinner_2 = new JSpinner();
		spinner_2.setBackground(new Color(65, 105, 225));
		spinner_2.setForeground(new Color(255, 255, 255));
		spinner_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		spinner_2.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
		spinner_2.setEditor(new JSpinner.DateEditor(spinner_2,"h:mm aa"));
		
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
		spinner_3.setForeground(Color.WHITE);
		spinner_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		spinner_3.setBackground(new Color(65, 105, 225));
		spinner_3.setEditor(new JSpinner.DateEditor(spinner_3,"M/d/yy"));
		btnAddAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = textField.getText();
				String lname = textField_1.getText();
				String description = textField_4.getText();
				Date date = (Date) spinner_1.getValue();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				Date date2 = (Date) spinner_2.getValue();
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(date2);
				Date date3 = (Date) spinner_3.getValue();
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(date3);
				try {
					Appointment app = new Appointment(fname,lname,description,cal3.get(Calendar.YEAR),cal3.get(Calendar.MONTH),cal3.get(Calendar.DAY_OF_MONTH),(String) comboBox_4.getSelectedItem(),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal2.get(Calendar.HOUR_OF_DAY),cal2.get(Calendar.MINUTE));
					try {
						book.addAppointment(app);
						JOptionPane.showMessageDialog(null, "Appointment Added");
						reset();
						}
					catch(IllegalArgumentException z)
					{
						JOptionPane.showMessageDialog(null,  "Appointment Not Added: " + z.getMessage());
					}
				}
				catch(IllegalArgumentException a)
				{
				   JOptionPane.showMessageDialog(null, "Appointment Not Added: " + a.getMessage());
				}
			}
		});
		comboBox_4 = new JComboBox<String>();
		comboBox_4.setForeground(new Color(255, 255, 255));
		comboBox_4.setBackground(new Color(65, 105, 225));
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}));
		comboBox_4.setSelectedIndex(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1);
		comboBox_4.setFont(new Font("Stencil", Font.PLAIN, 20));
		
		JLabel lblToAddAn = new JLabel("To Add An Appointment To The Appointment Book, Enter The Following:");
		lblToAddAn.setForeground(new Color(255, 255, 255));
		lblToAddAn.setHorizontalAlignment(SwingConstants.CENTER);
		lblToAddAn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblToAddAn, GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEnterTheDate)
								.addComponent(lblFirstName)
								.addComponent(lblLastName))
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
										.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
										.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(spinner_3, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)))))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescriptionOfEvent)
								.addComponent(lblEndTimeOf)
								.addComponent(lblStartTimeOf)
								.addComponent(lblDayOfThe))
							.addGap(30)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(spinner_2, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
								.addComponent(spinner_1, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
								.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(230)
							.addComponent(btnAddAppointment, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblToAddAn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFirstName))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLastName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterTheDate)
						.addComponent(spinner_3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDayOfThe)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblStartTimeOf)
						.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndTimeOf, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescriptionOfEvent)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAddAppointment)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		panel_1.setLayout(gl_panel_1);}
	
	private void reset() {
		textField_1.setText("");
		textField.setText("");
		textField_4.setText("");
		comboBox_4.setSelectedIndex(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1);
		spinner_1.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
		spinner_1.setEditor(new JSpinner.DateEditor(spinner_1,"h:mm aa"));
		spinner_2.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
		spinner_2.setEditor(new JSpinner.DateEditor(spinner_2,"h:mm aa"));
	}
}