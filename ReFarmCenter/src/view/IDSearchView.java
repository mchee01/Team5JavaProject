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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class IDSearchView extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField tF_Jumin;
	private JLabel lbl_Name;
	private JTextField tF_Name;
	private JLabel lbl_Jumin;
	private JLabel lbl_Phone;
	private JTextField tF_Phone;
	private JLabel lbl_IDSearch;
	private JButton btn_IDSearch;
	private JButton btn_Cancel;


	/**
	 * Create the frame.
	 */
	public IDSearchView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 290, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 274, 293);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tF_Jumin = new JTextField();
		tF_Jumin.setColumns(10);
		tF_Jumin.setBounds(134, 112, 116, 21);
		panel.add(tF_Jumin);
		
		lbl_Name = new JLabel("이름 :");
		lbl_Name.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_Name.setBounds(71, 79, 42, 19);
		panel.add(lbl_Name);
		
		tF_Name = new JTextField();
		tF_Name.setColumns(10);
		tF_Name.setBounds(134, 79, 116, 21);
		panel.add(tF_Name);
		
		lbl_Jumin = new JLabel("주민번호 :");
		lbl_Jumin.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_Jumin.setBounds(41, 108, 81, 27);
		panel.add(lbl_Jumin);
		
		lbl_Phone = new JLabel("Phone :");
		lbl_Phone.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_Phone.setBounds(60, 147, 62, 15);
		panel.add(lbl_Phone);
		
		tF_Phone = new JTextField();
		tF_Phone.setColumns(10);
		tF_Phone.setBounds(134, 145, 116, 21);
		panel.add(tF_Phone);
		
		lbl_IDSearch = new JLabel("ID 찾기");
		lbl_IDSearch.setFont(new Font("굴림", Font.PLAIN, 17));
		lbl_IDSearch.setBounds(22, 22, 74, 27);
		panel.add(lbl_IDSearch);
		
		btn_IDSearch = new JButton("ID 찾기");
		btn_IDSearch.setBounds(121, 221, 74, 23);
		panel.add(btn_IDSearch);
		
		btn_Cancel = new JButton("취소");
		btn_Cancel.setBounds(194, 221, 68, 23);
		panel.add(btn_Cancel);
		
		btn_IDSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object ob = e.getSource();
				if(ob == btn_IDSearch) {
					try {
						JoinDAO dao = new JoinDAO();
			    		String jumin  = tF_Jumin.getText();
			    		String name = tF_Name.getText();
			    		String phone = tF_Phone.getText();
						LoginVO vo = dao.idsearch(name,jumin,phone);
						
					
						JOptionPane.showMessageDialog(null,"아이디 :"+vo.getId());
//						vo.getId()
						
						
						
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null,"아이디 검색 실패:"+e2.getMessage());
					}
				
				}
			}
		});
		
		
		btn_Cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object ob = e.getSource();
				if(ob == btn_Cancel) {
					dispose();
				}
			}
		});
		
		
	}
}
