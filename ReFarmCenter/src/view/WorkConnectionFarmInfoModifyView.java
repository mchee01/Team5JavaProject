package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.JoinDAO;
import model.WorkConnectionDAO;
import model.rec.LoginVO;
import model.rec.WorkConnectionVO;

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

public class WorkConnectionFarmInfoModifyView extends JFrame {
	WorkConnectionDAO dao = null;
	private JPanel modifyPane;
	private JPanel modifyPaneTop;
	private JLabel lblNewLabel;
	private JPanel modifyPaneBottom;
	private JButton modifyBtn;
	private JButton cancelBtn;
	private JPanel modifyPaneCenter;
	private JLabel JProjectNum;
	JTextField tfFarmNum;
	private JLabel JRegisterId;
	JTextField tfFarmId;
	private JLabel JRegisterName;
	JTextField tfFarmLocation;
	private JLabel JRegistPhoneNum;
	JTextField tfFarmUseage;
	WorkConnectionRegisterTableModel tmRegistWork;

	/**
	 * Create the frame.
	 */
	public WorkConnectionFarmInfoModifyView() {
		try {
			dao = new WorkConnectionDAO();
			System.out.println("일손교류 디비 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "일손교류 DB연결 실패 : " + e.getMessage());
		}
		setTitle("일손교류 요청 농가 정보 수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 653, 262);
		modifyPane = new JPanel();
		modifyPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(modifyPane);
		modifyPane.setLayout(new BorderLayout(0, 0));

		modifyPaneTop = new JPanel();
		modifyPane.add(modifyPaneTop, BorderLayout.NORTH);

		lblNewLabel = new JLabel("일손교류 요청 농가 정보 수정");
		lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		modifyPaneTop.add(lblNewLabel);

		modifyPaneBottom = new JPanel();
		modifyPane.add(modifyPaneBottom, BorderLayout.SOUTH);

		modifyBtn = new JButton("수정");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(tfFarmNum.getText());
					System.out.println(tfFarmId.getText());
					System.out.println(tfFarmLocation.getText());
					System.out.println(tfFarmUseage.getText());
					
					System.out.println("수정버튼이눌림");
					dao.farmInfoModify(Integer.parseInt(tfFarmNum.getText()), tfFarmId.getText(), tfFarmLocation.getText(), tfFarmUseage.getText()); //농가 번호, 이름, 주소, 용도
					System.out.println("관리자권한으로 수정성공!");
					JOptionPane.showMessageDialog(null, "관리자 권한으로 농장 정보를 수정했습니다.");
					clearScreen();
					dispose();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "수정 실패 : " + e2.getMessage());
				}
			}
		});
		modifyPaneBottom.add(modifyBtn);

		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		modifyPaneBottom.add(cancelBtn);

		modifyPaneCenter = new JPanel();
		modifyPane.add(modifyPaneCenter, BorderLayout.CENTER);
		modifyPaneCenter.setLayout(new GridLayout(4, 0, 0, 0));

		JProjectNum = new JLabel("농가번호");
		JProjectNum.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		JProjectNum.setHorizontalAlignment(SwingConstants.CENTER);
		modifyPaneCenter.add(JProjectNum);

		tfFarmNum = new JTextField();
		modifyPaneCenter.add(tfFarmNum);
		tfFarmNum.setColumns(10);

		JRegisterId = new JLabel("농가 명");
		JRegisterId.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		JRegisterId.setHorizontalAlignment(SwingConstants.CENTER);
		modifyPaneCenter.add(JRegisterId);

		tfFarmId = new JTextField();
		modifyPaneCenter.add(tfFarmId);
		tfFarmId.setColumns(10);

		JRegisterName = new JLabel("농가 주소");
		JRegisterName.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		JRegisterName.setHorizontalAlignment(SwingConstants.CENTER);
		modifyPaneCenter.add(JRegisterName);

		tfFarmLocation = new JTextField();
		modifyPaneCenter.add(tfFarmLocation);
		tfFarmLocation.setColumns(10);

		JRegistPhoneNum = new JLabel("농가 용도");
		JRegistPhoneNum.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		JRegistPhoneNum.setHorizontalAlignment(SwingConstants.CENTER);
		modifyPaneCenter.add(JRegistPhoneNum);

		tfFarmUseage = new JTextField();
		modifyPaneCenter.add(tfFarmUseage);
		tfFarmUseage.setColumns(10);

	}

	public void clearScreen() {
		tfFarmNum.setText("");
		tfFarmId.setText("");
		tfFarmLocation.setText("");
		tfFarmUseage.setText("");
	}

	void selectTable(int pro_num) {
		try {
			WorkConnectionManagerView wcmv = new WorkConnectionManagerView();
			tmRegistWork = new WorkConnectionRegisterTableModel();
			ArrayList list = dao.showRegisterList(pro_num);
			System.out.println("selectTable : " + pro_num);
			tmRegistWork.data = list;
			System.out.println("selectTable : " + list);
			wcmv.workerRegistTable.setModel(tmRegistWork);
			tmRegistWork.fireTableDataChanged(); // 내용 바뀐것을 화면에 알려주는것
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "반영 실패 : " + e.getMessage());

		}
	}
}
