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

public class JoinManageView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tF_id;
	private JTextField tF_gi;
	private JTextField tF_pwd;
	private JButton btn_join, btn_cancel;
	private JLabel lbl_name_1;
	private JTextField tF_gibun;
	private JoinDAO jdao;

	/**
	 * Create the frame.
	 */
	public JoinManageView() {
		 try {
        	 jdao = new JoinDAO();
        	 System.out.println("고객디비 연결 성공");
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showConfirmDialog(null, "고객 DB 연결 실패 : "+ e.getMessage());
		}

		setTitle("관리자회원가입");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 298, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tF_id = new JTextField();
		tF_id.setBounds(128, 96, 116, 21);
		contentPane.add(tF_id);
		tF_id.setColumns(10);
		
		JLabel lbl_id = new JLabel("ID :");
		lbl_id.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_id.setBounds(87, 92, 29, 27);
		contentPane.add(lbl_id);
		
		JLabel lbl_join = new JLabel("관리자 정보 입력");
		lbl_join.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_join.setBounds(12, 10, 122, 29);
		contentPane.add(lbl_join);
		
		JLabel lbl_pwd = new JLabel("Password :");
		lbl_pwd.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_pwd.setBounds(37, 143, 81, 15);
		contentPane.add(lbl_pwd);
		
		tF_gi = new JTextField();
		tF_gi.setColumns(10);
		tF_gi.setBounds(128, 185, 116, 21);
		contentPane.add(tF_gi);
		
		tF_pwd = new JTextField();
		tF_pwd.setColumns(10);
		tF_pwd.setBounds(128, 141, 116, 21);
		contentPane.add(tF_pwd);
		
		JLabel lbl_name = new JLabel("기관명 :");
		lbl_name.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_name.setBounds(54, 185, 58, 19);
		contentPane.add(lbl_name);
		
		btn_join = new JButton("회원가입");
		btn_join.setBounds(76, 279, 100, 23);
		contentPane.add(btn_join);
		
		btn_cancel = new JButton("취소");
		btn_cancel.setBounds(196, 279, 74, 23);
		contentPane.add(btn_cancel);
		
		lbl_name_1 = new JLabel("기관번호 :");
		lbl_name_1.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_name_1.setBounds(37, 225, 79, 19);
		contentPane.add(lbl_name_1);
		
		tF_gibun = new JTextField();
		tF_gibun.setBounds(128, 225, 116, 21);
		contentPane.add(tF_gibun);
		tF_gibun.setColumns(10);
		
		btn_join.addActionListener(this);
		btn_cancel.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		if(ob == btn_join) {
			try {
		
			String id = tF_id.getText();
			String pwd = tF_pwd.getText();
			String gi = tF_gi.getText();
			String gibun = tF_gibun.getText();
			
			LoginVO vo = new LoginVO(id,pwd,gi,gibun);
			jdao.manageRegist(vo);
			JOptionPane.showMessageDialog(null, "회원가입완료");
			clearScreen();
			dispose();

			}catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "매니저 회원가입실패 : " + e2.getMessage());
			}
			
			
			
			
			
		}
		else if(ob == btn_cancel) {
			
			dispose();
			
		}
	}
	public void clearScreen() {
		tF_id.setText("");
		tF_pwd.setText("");
		tF_gi.setText("");
		tF_gibun.setText("");
	}

}
