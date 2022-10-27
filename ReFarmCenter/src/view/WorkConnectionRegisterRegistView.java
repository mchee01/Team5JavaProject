package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.JoinDAO;
import model.WorkConnectionDAO;
import model.rec.LoginVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class WorkConnectionRegisterRegistView extends JFrame {
	WorkConnectionDAO dao = null;
	private JPanel registPane;
	private JPanel registPaneTop;
	private JLabel lblNewLabel;
	private JPanel registPaneBottom;
	private JButton registeBtn;
	private JButton cancelBtn;
	private JPanel registPaneCenter;
	private JLabel JProjectNum;
	JTextField tfProjectNum;
	private JLabel JRegisterId;
	private JTextField tfRegisterId;
	WorkConnectionRegisterTableModel tmRegistWork;
	
	/**
	 * Create the frame.
	 */
	public WorkConnectionRegisterRegistView() {
		try {
			dao = new WorkConnectionDAO();
			System.out.println("일손교류 디비 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "일손교류 DB연결 실패 : " + e.getMessage());
		}
		setTitle("일손교류프로젝트 참여자 등록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 178);
		registPane = new JPanel();
		registPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(registPane);
		registPane.setLayout(new BorderLayout(0, 0));
		
		registPaneTop = new JPanel();
		registPane.add(registPaneTop, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("참여자 정보 등록");
		lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		registPaneTop.add(lblNewLabel);
		
		registPaneBottom = new JPanel();
		registPane.add(registPaneBottom, BorderLayout.SOUTH);
		
		registeBtn = new JButton("등록");
		registeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int projectNumber = Integer.parseInt(tfProjectNum.getText());
				String userID = tfRegisterId.getText();
				try {
					dao.registRegist(projectNumber, userID);
					System.out.println("관리자권한으로 등록성공!");
					//selectTable();
					
					//selectTable 하는 곳 근데 안됨;
					try {
						WorkConnectionManagerView wcmv = new WorkConnectionManagerView();
						//wcmv.tmRegistWork = new WorkConnectionRegisterTableModel();
						System.out.println("wcrrv selectTable : " + projectNumber);
						ArrayList list = dao.showRegisterList(projectNumber);
						wcmv.tmRegistWork.data = list;
						System.out.println("selectTable : " + list);
						wcmv.workerRegistTable.setModel(wcmv.tmRegistWork);
						System.out.println(wcmv.tmRegistWork);
						wcmv.tmRegistWork.fireTableDataChanged(); // 내용 바뀐것을 화면에 알려주는것
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "반영 실패 : " + e2.getMessage());
					}
					clearScreen();
					JOptionPane.showMessageDialog(null, "관리자 권한으로 참여자를 추가했습니다.");
					dispose();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "등록 실패 : " +e2.getMessage());
				}
			}
		});
		registPaneBottom.add(registeBtn);
		
		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		registPaneBottom.add(cancelBtn);
		
		registPaneCenter = new JPanel();
		registPane.add(registPaneCenter, BorderLayout.CENTER);
		registPaneCenter.setLayout(new GridLayout(2, 0, 0, 0));
		
		JProjectNum = new JLabel("프로젝트번호");
		JProjectNum.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		JProjectNum.setHorizontalAlignment(SwingConstants.CENTER);
		registPaneCenter.add(JProjectNum);
		
		tfProjectNum = new JTextField();
		registPaneCenter.add(tfProjectNum);
		tfProjectNum.setColumns(10);
		
		JRegisterId = new JLabel("참여자 아이디");
		JRegisterId.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		JRegisterId.setHorizontalAlignment(SwingConstants.CENTER);
		registPaneCenter.add(JRegisterId);
		
		tfRegisterId = new JTextField();
		registPaneCenter.add(tfRegisterId);
		tfRegisterId.setColumns(10);
		
	}
	public void clearScreen() {
		tfProjectNum.setText("");
		tfRegisterId.setText("");
	}
	
	void selectTable() {
		try {
			WorkConnectionManagerView wcmv = new WorkConnectionManagerView();
			//tmRegistWork = new WorkConnectionRegisterTableModel();
			int row = wcmv.projectListTable.getSelectedRow();
			int col = 0;
			int pro_num = (Integer) wcmv.projectListTable.getValueAt(row, col);
			System.out.println("wcrrv selectTable : " + pro_num);
			ArrayList list = dao.showRegisterList(pro_num);
			wcmv.tmRegistWork.data = list;
			System.out.println("selectTable : " + list);
			wcmv.workerRegistTable.setModel(tmRegistWork);
			wcmv.tmRegistWork.fireTableDataChanged(); // 내용 바뀐것을 화면에 알려주는것
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "반영 실패 : " + e.getMessage());

		}
	}
}
