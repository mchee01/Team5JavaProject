package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.JoinDAO;
import model.rec.LoginVO;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PasswordSearchView extends JFrame {

	private JPanel contentPane;
	private JTextField tF_Jumin;
	private JTextField tF_Name;
	private JTextField tF_id;
	private JButton btn_PWSearch, btn_Cancel;
	
	/**
	 * Create the frame.
	 */
	public PasswordSearchView() {
		setTitle("PW 찾기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 290, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 274, 285);
		contentPane.add(panel);
		
		tF_Jumin = new JTextField();
		tF_Jumin.setColumns(10);
		tF_Jumin.setBounds(134, 146, 116, 21);
		panel.add(tF_Jumin);
		
		JLabel lbl_Name = new JLabel("이름 :");
		lbl_Name.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_Name.setBounds(71, 113, 42, 19);
		panel.add(lbl_Name);
		
		tF_Name = new JTextField();
		tF_Name.setColumns(10);
		tF_Name.setBounds(134, 113, 116, 21);
		panel.add(tF_Name);
		
		JLabel lbl_Jumin = new JLabel("주민번호 :");
		lbl_Jumin.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_Jumin.setBounds(41, 142, 81, 27);
		panel.add(lbl_Jumin);
		
		JLabel lbl_NameSearch = new JLabel("PW 찾기");
		lbl_NameSearch.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_NameSearch.setBounds(22, 22, 116, 27);
		panel.add(lbl_NameSearch);
		
		btn_PWSearch = new JButton("PW 찾기");
		btn_PWSearch.setBounds(105, 221, 90, 23);
		panel.add(btn_PWSearch);
		
		btn_Cancel = new JButton("취소");
		btn_Cancel.setBounds(194, 221, 68, 23);
		panel.add(btn_Cancel);
		
		
		
		
		JLabel lbl_id = new JLabel("ID :");
		lbl_id.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_id.setBounds(89, 76, 33, 15);
		panel.add(lbl_id);
		
		tF_id = new JTextField();
		tF_id.setColumns(10);
		tF_id.setBounds(134, 74, 116, 21);
		panel.add(tF_id);
		
		btn_PWSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object ob = e.getSource();
				try {
					JoinDAO dao = new JoinDAO();
					String id = tF_id.getText();
					String jumin = tF_Jumin.getText();
					String name = tF_Name.getText();
					
					LoginVO vo = dao.pwdsearch(name,jumin,id);
					
					JOptionPane.showMessageDialog(null,"비밀번호 :"+vo.getPwd());
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				} 
				
					
			}
		});
		
		btn_Cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		 
	}

}
