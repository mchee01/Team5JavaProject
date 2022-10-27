package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import buttonLib.ButtonRound;
import model.RealeStateDAO;
import model.rec.RealeStateVO;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.ImageIcon;

public class ReservationView extends JFrame implements ActionListener{


	private JTable table;
	private JTextField textField;
	private RealeStateDAO dao;
	private RealeStateVO vo;
	public JTextField tf_id;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public ReservationView() {
		getContentPane().setBackground(new Color(255, 255, 255));
		initialize();
	}

	public ReservationView(RealeStateVO vo) {
		this.vo = vo;
		getContentPane().setBackground(new Color(255, 255, 255));
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		setBackground(new Color(255, 255, 255));
		
		setBounds(500, 500, 700, 500);
		setVisible(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {50, 250, 30, 30, 64, 180, 0};
		gridBagLayout.rowHeights = new int[] {30, 57, 216, 89, 15, 0, 35, 8};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		tf_id = new JTextField();
		tf_id.setEnabled(false);
		GridBagConstraints gbc_tf_id = new GridBagConstraints();
		gbc_tf_id.insets = new Insets(0, 0, 5, 5);
		gbc_tf_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_id.gridx = 1;
		gbc_tf_id.gridy = 1;
		getContentPane().add(tf_id, gbc_tf_id);
		tf_id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(ReservationView.class.getResource("/img/부동산처분 허가.jpg")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		dao= new RealeStateDAO();
		String name;
		System.out.println(vo.getName());
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setForeground(new Color(0, 0, 0));
		textPane.setBackground(new Color(255, 255, 255));
		textPane.setFont(new Font("굴림", Font.PLAIN, 23));
		dao = new RealeStateDAO();
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridwidth = 2;
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 4;
		gbc_textPane.gridy = 2;
		getContentPane().add(textPane, gbc_textPane);
		
		ArrayList list = new ArrayList();
		System.out.println(vo.getName());
		textPane.setText("위치 : "+vo.getName()+"\n"+"매물금액 : "+vo.getSel_price()+"\n"+"매물 면적 : "+vo.getArea());
		
		JLabel lblNewLabel_2 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridwidth = 5;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		ButtonRound btnNewButton = new ButtonRound("상담");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("selected");
				String id = tf_id.getText();
				UserSelectView usv =new UserSelectView();
				usv.tf_id.setText(id);
			}
		});
		btnNewButton.setBackground(new Color(59, 89, 182));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 5;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
