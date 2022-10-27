package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import buttonLib.ButtonRound;
import model.ReservationDAO;

import java.awt.Button;
import javax.swing.SwingConstants;

public class UserSelectView extends JFrame implements ActionListener{

	private JFrame jframe;
	public JTextField tf_id;
	private JTextField textField_1;
	private JTextArea textArea;
	private ButtonRound btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private ButtonRound btnNewButton_2;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public UserSelectView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 465, 415);
		setDefaultCloseOperation(jframe.DISPOSE_ON_CLOSE);
		setVisible(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{31, 36, 127, 65, 87, 69, 0};
		gridBagLayout.rowHeights = new int[]{60, 21, 23, 156, 49, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel = new JLabel("아이디");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		tf_id = new JTextField();
		tf_id.setEnabled(false);
		GridBagConstraints gbc_tf_id = new GridBagConstraints();
		gbc_tf_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_id.insets = new Insets(0, 0, 5, 5);
		gbc_tf_id.gridwidth = 3;
		gbc_tf_id.gridx = 2;
		gbc_tf_id.gridy = 1;
		getContentPane().add(tf_id, gbc_tf_id);
		tf_id.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("예약일을 입력해 주세요.");
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LocalDate now = LocalDate.now();
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
				 String format = now.format(formatter);
				 System.out.println(format);
				 textField_1.setText("");
				 textField_1.setText(format);
				 textField_1.setEditable(false);
			}
		});
		
		lblNewLabel_1 = new JLabel("날짜");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		btnNewButton_2 = new ButtonRound("예약일");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 5;
		gbc_btnNewButton_2.gridy = 2;
		getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridwidth = 3;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 3;
		getContentPane().add(textArea, gbc_textArea);
		
		btnNewButton = new ButtonRound("상담예약");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = tf_id.getText();
				String date = textField_1.getText();
				String text = textArea.getText();
				int num = 0;
				 ReservationDAO dao = new ReservationDAO();
				 RealeStateView view = new RealeStateView();
				 num = dao.selectNum(id);
				 dao.Inserted(num,view.number,date, text);
				 System.out.println("insert완료.");
				 clear();
				 JOptionPane.showMessageDialog(null, "상담예약완료!");
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 5;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void clear() {
		textField_1.setText("");
		textArea.setText("");
		
	}

}
