package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.JoinDAO;
import model.rec.LoginVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JoinView extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_Password;
	private JTextField textField_Name;
	private JTextField textField_Jumin;
	private JTextField textField_Phone;
	private JButton btn_join,btn_cancel;
	public JComboBox position;
	
	/**
	 * Create the frame.
	 */
	public JoinView() {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 290, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_join = new JLabel("회원정보 입력");
		lbl_join.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_join.setBounds(12, 10, 108, 29);
		contentPane.add(lbl_join);
		
		JLabel lbl_id = new JLabel("ID :");
		lbl_id.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_id.setBounds(89, 83, 33, 15);
		contentPane.add(lbl_id);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(123, 83, 116, 21);
		contentPane.add(textField_ID);
		textField_ID.setColumns(10);
		
		JLabel lbl_pwd = new JLabel("Password :");
		lbl_pwd.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_pwd.setBounds(31, 118, 81, 15);
		contentPane.add(lbl_pwd);
		
		textField_Password = new JTextField();
		textField_Password.setColumns(10);
		textField_Password.setBounds(123, 116, 116, 21);
		contentPane.add(textField_Password);
		
		JLabel lbl_name = new JLabel("이름 :");
		lbl_name.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_name.setBounds(71, 160, 42, 19);
		contentPane.add(lbl_name);
		
		textField_Name = new JTextField();
		textField_Name.setColumns(10);
		textField_Name.setBounds(123, 160, 116, 21);
		contentPane.add(textField_Name);
		
		JLabel lbl_jumin = new JLabel("주민번호 :");
		lbl_jumin.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_jumin.setBounds(41, 195, 81, 27);
		contentPane.add(lbl_jumin);
		
		textField_Jumin = new JTextField();
		textField_Jumin.setColumns(10);
		textField_Jumin.setBounds(123, 199, 116, 21);
		contentPane.add(textField_Jumin);
		
		JLabel lbl_phone = new JLabel("Phone :");
		lbl_phone.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_phone.setBounds(57, 243, 65, 15);
		contentPane.add(lbl_phone);
		
		textField_Phone = new JTextField();
		textField_Phone.setColumns(10);
		textField_Phone.setBounds(123, 241, 116, 21);
		contentPane.add(textField_Phone);
		
		btn_join = new JButton("회원가입");
		btn_join.setBounds(71, 316, 100, 23);
		contentPane.add(btn_join);
		
		
		
		btn_cancel = new JButton("취소");
		btn_cancel.setBounds(188, 316, 74, 23);
		contentPane.add(btn_cancel);
		
		String [] admin  ={"사용자", "관리자"};
		position = new JComboBox(admin);
		position.setModel(new DefaultComboBoxModel(new String[] {"사용자", "산업자"}));
		position.setFont(new Font("굴림", Font.PLAIN, 17));
		
		position.setBounds(182, 13, 80, 23);
		contentPane.add(position);
		
		
		// 회원가입
		btn_join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object ob = e.getSource();
				try {
					 int num =0;
					 String combo = (String) position.getSelectedItem();
					 JoinDAO jdao = new JoinDAO();
					num = jdao.selectNum(combo);
					
//					for(int i=0;i<admin.length;i++) { 
//					if(admin[i].equals("사용자")) {
//						num = i;
//						break;
//					}
//					else if(admin[i].equals("관리자")) {
//						num = i;
//						break;
//					}
//				}
					
					String id = textField_ID.getText();
					String pwd = textField_Password.getText();
					String name  = textField_Name.getText();
					String jumin  = textField_Jumin.getText();
					String phone = textField_Phone.getText();
					
					LoginVO vo = new LoginVO(id, pwd, name, jumin, phone, num);
					jdao.regist(vo);
					JOptionPane.showMessageDialog(null, "회원가입완료");
					clearScreen();
					dispose();
				
					
					
				
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "입고실패입니다 : " + e2.getMessage());
				}
				
				
			}
		});
		
		//취소버튼 누르면 꺼지는 이벤트
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
		
	}
	public void clearScreen() {
		textField_ID.setText("");
		textField_Password.setText("");
		textField_Name.setText("");
		textField_Jumin.setText("");
		textField_Phone.setText("");
		position.setSelectedItem("사용자");
		
	}
}
