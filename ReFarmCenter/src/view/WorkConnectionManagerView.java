package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.ReviewDAO;
import model.WorkConnectionDAO;
import model.rec.WorkConnectionVO;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;

/**
 * 파일 명 : WorkConnectionManagerView
 * 
 * @author : 주민지
 * @date : 2022.09.08
 * @description : 로그인한 관리자의 등록, 수정, 삭제 완료
 */

public class WorkConnectionManagerView extends JFrame implements ActionListener {
	WorkConnectionDAO dao = null;
	WorkConnectionManageTableModel tmManageWork;
	WorkConnectionRegisterTableModel tmRegistWork;
	WorkConnectionFarmTableModel tmFarmWork;
	public JTextField tfManageId;
	private JTextField tfTitle;
	private JTextField tfBeginDate;
	private JTextField tfParticipants;
	private JTextField tfQualify;
	private JTextField tfLocation;
	private JTextField tfContent;
	private JTextField tfEndDate;
	private JTextField tfSearch;
	private JTextField tfFarmSearch;
	private JTextField tfprojectSearch;
	JTable projectListTable;
	private JTable farmListTable;
	JTable workerRegistTable;

	// ##############################################
	// constructor method
	public WorkConnectionManagerView() {
		setBounds(100, 100, 1033, 700);
		try {
			dao = new WorkConnectionDAO();
			newObject();
			addLayout();
			setStyle();
			eventProc();
			System.out.println("일손교류 디비 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "일손교류 DB연결 실패 : " + e.getMessage());
		}
	}

	// ##############################################
	// 멤버필드 객체 생성
	void newObject() {
		String janreText[] = { "에로", "코믹", "드라마", "공포", "애니메이션" };

		String searchText[] = { "제목", "감독", "배우" };

		tmManageWork = new WorkConnectionManageTableModel();
		tmRegistWork = new WorkConnectionRegisterTableModel();
		tmFarmWork = new WorkConnectionFarmTableModel();
	}

	// ##############################################
	// GUI 구성하기 위해 Layout에 붙이기
	void addLayout() {
		// -----------------------------------------
		// west 영역 : 비디오 입력 및 수정
		JPanel pWest = new JPanel();
		try {
			System.out.println("일손교류 디비 연결 후 열람 성공");
			ArrayList list = dao.showProjectlist();
			System.out.println(list);
			tmManageWork.data = list;
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "정보 열람 실패 : " + e2.getMessage());
		}
		// -----------------------------------------
		// east 영역 : 비디오 검색부분
		JPanel pEast = new JPanel();

		JPanel pEastCenter = new JPanel();

		pEast.setLayout(new BorderLayout());
		pEast.add("Center", pEastCenter);
		pEastCenter.setLayout(new BorderLayout(0, 0));

		JPanel pEastNorth = new JPanel();
		pEastCenter.add(pEastNorth, BorderLayout.CENTER);
		pEastNorth.setLayout(new BorderLayout(0, 0));

		JPanel pEastNorthRight = new JPanel();
		FlowLayout fl_pEastNorthRight = (FlowLayout) pEastNorthRight.getLayout();
		fl_pEastNorthRight.setAlignment(FlowLayout.RIGHT);
		pEastNorth.add(pEastNorthRight, BorderLayout.NORTH);

		tfManageId = new JTextField();
		pEastNorthRight.add(tfManageId);
		tfManageId.setColumns(10);

		JLabel lblNewLabel = new JLabel("\uAD00\uB9AC\uC790\uB2D8 \uC548\uB155\uD558\uC138\uC694!");
		pEastNorthRight.add(lblNewLabel);

		JPanel pEastBottom = new JPanel();
		pEastNorth.add(pEastBottom, BorderLayout.CENTER);
		pEastBottom.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		pEastBottom.add(tabbedPane, BorderLayout.CENTER);

		JPanel projectRegistPanel = new JPanel();
		projectRegistPanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("\uD504\uB85C\uC81D\uD2B8\uB4F1\uB85D", null, projectRegistPanel, null);
		projectRegistPanel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("\uD504\uB85C\uC81D\uD2B8\uBA85");
		lblNewLabel_2.setBounds(24, 28, 73, 16);
		projectRegistPanel.add(lblNewLabel_2);

		tfTitle = new JTextField();
		tfTitle.setColumns(10);
		tfTitle.setBackground(Color.WHITE);
		tfTitle.setBounds(125, 26, 348, 21);
		projectRegistPanel.add(tfTitle);

		JLabel lblNewLabel_2_1 = new JLabel("\uD504\uB85C\uC81D\uD2B8 \uAE30\uAC04");
		lblNewLabel_2_1.setBounds(24, 55, 90, 16);
		projectRegistPanel.add(lblNewLabel_2_1);

		tfBeginDate = new JTextField();
		tfBeginDate.setColumns(10);
		tfBeginDate.setBounds(126, 54, 150, 21);
		projectRegistPanel.add(tfBeginDate);

		JLabel lblNewLabel_2_1_1 = new JLabel("~");
		lblNewLabel_2_1_1.setBounds(294, 51, 9, 32);
		projectRegistPanel.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_2 = new JLabel("\uCC38\uAC00\uC778\uC6D0");
		lblNewLabel_2_2.setBounds(25, 82, 76, 16);
		projectRegistPanel.add(lblNewLabel_2_2);

		tfParticipants = new JTextField();
		tfParticipants.setColumns(10);
		tfParticipants.setBounds(126, 82, 150, 21);
		projectRegistPanel.add(tfParticipants);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("\uBA85");
		lblNewLabel_2_1_1_1.setBounds(292, 76, 21, 32);
		projectRegistPanel.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel_2_3 = new JLabel("\uCC38\uAC00\uC790\uACA9");
		lblNewLabel_2_3.setBounds(25, 115, 70, 16);
		projectRegistPanel.add(lblNewLabel_2_3);

		tfQualify = new JTextField();
		tfQualify.setColumns(10);
		tfQualify.setBounds(125, 112, 269, 21);
		projectRegistPanel.add(tfQualify);

		JLabel lblNewLabel_2_3_1 = new JLabel("농가번호");
		lblNewLabel_2_3_1.setBounds(25, 151, 73, 16);
		projectRegistPanel.add(lblNewLabel_2_3_1);

		tfLocation = new JTextField();
		tfLocation.setColumns(10);
		tfLocation.setBounds(126, 149, 347, 21);
		projectRegistPanel.add(tfLocation);

		JLabel lblNewLabel_2_4 = new JLabel("\uC0C1\uC138\uB0B4\uC6A9");
		lblNewLabel_2_4.setBounds(24, 191, 73, 16);
		projectRegistPanel.add(lblNewLabel_2_4);

		tfContent = new JTextField();
		tfContent.setColumns(10);
		tfContent.setBounds(125, 192, 348, 354);
		projectRegistPanel.add(tfContent);

		tfEndDate = new JTextField();
		tfEndDate.setColumns(10);
		tfEndDate.setBounds(323, 55, 150, 21);
		projectRegistPanel.add(tfEndDate);

		JPanel pEastBottomUnder = new JPanel();
		pEastBottomUnder.setBounds(-2, 570, 508, 33);
		projectRegistPanel.add(pEastBottomUnder);

		JButton projectRegistBtn = new JButton("\uB4F1\uB85D\uD558\uAE30");
		projectRegistBtn.setBounds(127, 5, 81, 23);
		projectRegistBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				int row = projectListTable.getSelectedRow();
//				int col = 0;
//				int projectNum = (Integer) projectListTable.getValueAt(row, col);
				try {
					String managerName = tfManageId.getText();
					System.out.println(managerName);
					String title = tfTitle.getText();
					System.out.println(title);
					String startDate = tfBeginDate.getText();
					String endDate = tfEndDate.getText();
					String content = tfContent.getText();
					String qualify = tfQualify.getText();
					int participants = Integer.parseInt(tfParticipants.getText());
					int famNum = Integer.parseInt(tfLocation.getText());

					dao.projectRegist(
							new WorkConnectionVO(title, startDate, endDate, content, qualify, participants, famNum),
							managerName, title);
					// dao.insert_HOST_ORGANIZATION_WC(managerName, title);
					selectTable(managerName);
					clearScreen();
					System.out.println("프로젝트 등록 성공!");
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "프로젝트 등록 실패 : " + e2.getMessage());
				}
			}
		});
		pEastBottomUnder.setLayout(null);
		pEastBottomUnder.add(projectRegistBtn);

		JButton projectModifyBtn = new JButton("수정하기");
		projectModifyBtn.setBounds(213, 5, 81, 23);
		projectModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = projectListTable.getSelectedRow();
					int col = 0;
					int pro_num = (Integer) projectListTable.getValueAt(row, col);
					System.out.println(pro_num);
					String managerName = tfManageId.getText();
					System.out.println("projectModifyBtn : " + managerName);
					String title = tfTitle.getText();
					System.out.println(title);
					String startDate = tfBeginDate.getText();
					String endDate = tfEndDate.getText();
					String content = tfContent.getText();
					String qualify = tfQualify.getText();
					int participants = Integer.parseInt(tfParticipants.getText());
					int famNum = Integer.parseInt(tfLocation.getText());
					dao.projectModify(
							new WorkConnectionVO(title, startDate, endDate, content, qualify, participants, famNum),
							pro_num);
					selectTable(managerName);
					System.out.println("프로젝트 수정완료!");
					clearScreen();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "프로젝트 수정 실패 : " + e2.getMessage());
				}
			}
		});
		pEastBottomUnder.add(projectModifyBtn);

		JButton projectDeleteBtn = new JButton("\uC0AD\uC81C\uD558\uAE30");
		projectDeleteBtn.setBounds(299, 5, 81, 23);
		projectDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = projectListTable.getSelectedRow();
					int col = 0;
					int pro_num = (Integer) projectListTable.getValueAt(row, col);
					System.out.println("delete : " + pro_num);
					String managerName = tfManageId.getText();
					System.out.println("projectDeleteBtn : " + managerName);
					dao.projectDelete(pro_num);
					selectTable(managerName);
					clearScreen();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		pEastBottomUnder.add(projectDeleteBtn);

		JPanel registerManagePanel = new JPanel();
		tabbedPane.addTab("\uC2E0\uCCAD\uC790\uAD00\uB9AC", null, registerManagePanel, null);
		registerManagePanel.setLayout(new BorderLayout(0, 0));

		JPanel searchPanel = new JPanel();
		registerManagePanel.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BorderLayout(10, 10));

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "전체 조회", "이름", "아이디", "전화번호" }));
		comboBox_1.setToolTipText("");
		searchPanel.add(comboBox_1, BorderLayout.WEST);

		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		searchPanel.add(tfSearch, BorderLayout.CENTER);

		// 사용자관리 검색
		JButton searchBtm = new JButton("검색");
		searchBtm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = comboBox_1.getSelectedIndex();
				System.out.println(sel);
				String text = tfSearch.getText();
				System.out.println(text);
				String managerName = tfManageId.getText();
				if (sel == 0) {
					// 관리자가 자신이 가진 프로젝트 목록을 볼 수있게 함
					try {
						System.out.println("일손교류 디비 연결 후 열람 성공");
						int row = projectListTable.getSelectedRow();
						int col = 0;
						int pro_num = (Integer) projectListTable.getValueAt(row, col);
						System.out.println("searchBtm : " + pro_num);
						ArrayList list = dao.showRegisterList(pro_num);
						System.out.println(list);
						tmRegistWork.data = list;
						workerRegistTable.setModel(tmRegistWork);
						tmRegistWork.fireTableDataChanged();
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "프로젝트 번호를 선택해주세요");
						// JOptionPane.showMessageDialog(null, "정보 열람 실패 : " + e2.getMessage());
					}

				} else {
					try {
						System.out.println("검색성공");
						int row = projectListTable.getSelectedRow();
						int col = 0;
						int pro_num = (Integer) projectListTable.getValueAt(row, col);
						ArrayList list = dao.registInfoSearch(pro_num, sel, text);
						System.out.println(list);
						tmRegistWork.data = list;
						workerRegistTable.setModel(tmRegistWork);
						tmRegistWork.fireTableDataChanged();
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "정보 열람 실패 : " + e2.getMessage());
					}
				}
			}
		});
		searchPanel.add(searchBtm, BorderLayout.EAST);

		JPanel pRMCenter = new JPanel();
		registerManagePanel.add(pRMCenter, BorderLayout.CENTER);
		pRMCenter.setLayout(new BorderLayout(0, 0));

		JPanel pEastBottomUnder2 = new JPanel();
		pRMCenter.add(pEastBottomUnder2, BorderLayout.SOUTH);

		JButton workerRegistBtn = new JButton("등록하기");
		workerRegistBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("일손교류 디비 연결 후 열람 성공");
					int row = projectListTable.getSelectedRow();
					int col = 0;
					int pro_num = (Integer) projectListTable.getValueAt(row, col);
					System.out.println("workerRegistBtn : " + pro_num);
					WorkConnectionRegisterRegistView wcrrv = new WorkConnectionRegisterRegistView();
					wcrrv.setVisible(true);
					wcrrv.tfProjectNum.setText(Integer.toString(pro_num));
					// wcrrv.tfProjectNum.setText(pro_num);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "참여자가 등록될 프로젝트를 선택하세요");
				}
			}
		});
		pEastBottomUnder2.add(workerRegistBtn);

		// 사용자관리 수정하기 버튼
		JButton workerModifyBtn = new JButton("수정하기");
		workerModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("일손교류 디비 연결 후 열람 성공");

					int workerRow = workerRegistTable.getSelectedRow();
					int row = projectListTable.getSelectedRow();
					int col = 0;
					int pro_num = (Integer) projectListTable.getValueAt(row, col);
					System.out.println("workerRegistBtn : " + pro_num);

					ArrayList list = dao.showRegisterList(pro_num);
					System.out.println("일손로우 : " + workerRow);
					System.out.println("내가 선택한 사용자 로우 : " + ((ArrayList) list.get(0)).get(1)); // 이중 arraylist는 이렇게 값을
																								// 들고온다

					WorkConnectionRegisterModifyView wcrmv = new WorkConnectionRegisterModifyView();
					wcrmv.setVisible(true);
					wcrmv.tfProjectNum.setText(Integer.toString(pro_num));
					wcrmv.tfRegisterId.setText((String) ((ArrayList) list.get(workerRow)).get(1));
					wcrmv.tfRegisterName.setText((String) ((ArrayList) list.get(workerRow)).get(2));
					wcrmv.tfRegisterPhoneNum.setText((String) ((ArrayList) list.get(workerRow)).get(3));

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "참여자가 수정될 프로젝트를 선택하세요");
				}
			}
		});
		pEastBottomUnder2.add(workerModifyBtn);

		JButton workerDeleteBtn = new JButton("삭제하기");
		workerDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("일손교류 디비 연결 후 열람 성공");
					int workerRow = workerRegistTable.getSelectedRow();
					int row = projectListTable.getSelectedRow();
					int col = 0;
					int pro_num = (Integer) projectListTable.getValueAt(row, col);
					System.out.println("workerDeleteBtn : " + pro_num);

					ArrayList list = dao.showRegisterList(pro_num);
					System.out.println("일손로우 : " + workerRow);
					System.out.println("내가 선택한 사용자 로우 : " + ((ArrayList) list.get(0)).get(1)); // 이중 arraylist는 이렇게 값을
																								// 들고온다

					String user_id = (String) ((ArrayList) list.get(workerRow)).get(1);
					System.out.println("workerDeleteBtn : " + user_id);
					dao.registDelete(pro_num, user_id);
					selectTable2();
					JOptionPane.showMessageDialog(null, "해당 참여자가 삭제되었습니다.");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		pEastBottomUnder2.add(workerDeleteBtn);

		JPanel pRMCenter2 = new JPanel();
		pRMCenter.add(pRMCenter2, BorderLayout.CENTER);
		pRMCenter2.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		pRMCenter2.add(scrollPane_1);

		workerRegistTable = new JTable();
		scrollPane_1.setViewportView(workerRegistTable);

		JPanel farmListPanel = new JPanel();
		tabbedPane.addTab("\uB18D\uAC00\uB9AC\uC2A4\uD2B8", null, farmListPanel, null);
		farmListPanel.setLayout(new BorderLayout(0, 0));

		JPanel farmSeacrhPanel = new JPanel();
		farmListPanel.add(farmSeacrhPanel, BorderLayout.NORTH);
		farmSeacrhPanel.setLayout(new BorderLayout(10, 10));

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "농가명", "상세내용" }));
		comboBox_2.setToolTipText("");
		farmSeacrhPanel.add(comboBox_2, BorderLayout.WEST);

		tfFarmSearch = new JTextField();
		tfFarmSearch.setColumns(10);
		farmSeacrhPanel.add(tfFarmSearch, BorderLayout.CENTER);

		JButton farmSearchBtn = new JButton("검색");
		farmSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = comboBox_2.getSelectedIndex();
				System.out.println(sel);
				String text = tfFarmSearch.getText();
				System.out.println(text);
				try {
					System.out.println("검색성공");
					ArrayList list = dao.farmInfoSearch(sel, text);
					System.out.println(list);
					tmFarmWork.data = list;
					workerRegistTable.setModel(tmFarmWork);
					tmFarmWork.fireTableDataChanged();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "정보 열람 실패 : " + e2.getMessage());
				}
			}
		});
		farmSeacrhPanel.add(farmSearchBtn, BorderLayout.EAST);

		JPanel pFLCenter = new JPanel();
		farmListPanel.add(pFLCenter, BorderLayout.CENTER);
		pFLCenter.setLayout(new BorderLayout(0, 0));

		JPanel pEastBottomUnder3 = new JPanel();
		pFLCenter.add(pEastBottomUnder3, BorderLayout.SOUTH);

		JButton farmRegistBtn = new JButton("수락하기");
		farmRegistBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("일손교류 디비 연결 후 열람 성공");
					int row = farmListTable.getSelectedRow();
					int col = 0;
					int farm_num = (Integer) farmListTable.getValueAt(row, col);
					System.out.println("farmRegistBtn : " + farm_num);
					dao.farmAssign(farm_num);
					JOptionPane.showMessageDialog(null, "일손교류 요청을 수락하였습니다.");
					selectTable3();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "수락 실패 : " + e2.getMessage());
				}
			}
		});
		pEastBottomUnder3.add(farmRegistBtn);

		JButton farmModifyBtn = new JButton("정보수정하기");
		farmModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("일손교류 디비 연결 후 열람 성공");
					int row = farmListTable.getSelectedRow();
					int col = 0;
					int farm_num = (Integer) farmListTable.getValueAt(row, col);
					System.out.println("farmModifyBtn : " + farm_num);
					WorkConnectionFarmInfoModifyView wcfm = new WorkConnectionFarmInfoModifyView();
					wcfm.setVisible(true);
					wcfm.tfFarmNum.setText(Integer.toString(farm_num));
					wcfm.tfFarmId.setText((String) farmListTable.getValueAt(row, 1));
					wcfm.tfFarmLocation.setText((String) farmListTable.getValueAt(row, 2));
					wcfm.tfFarmUseage.setText((String) farmListTable.getValueAt(row, 3));
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "정보가 수정될 농가를 선택하세요");
					// JOptionPane.showMessageDialog(null, "수정 실패 : " + e2.getMessage());
				}

			}
		});
		pEastBottomUnder3.add(farmModifyBtn);

		JButton farmDeleteBtn = new JButton("반려하기");
		farmDeleteBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("일손교류 디비 연결 후 열람 성공");
					int row = farmListTable.getSelectedRow();
					int col = 0;
					int farm_num = (Integer) farmListTable.getValueAt(row, col);
					System.out.println("farmDeleteBtn : " + farm_num);
					dao.farmDisagree(farm_num);
					JOptionPane.showMessageDialog(null, "일손교류 요청을 반려하였습니다.");
					selectTable3();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "반려 실패 : " + e2.getMessage());
				}
			}
		});
		pEastBottomUnder3.add(farmDeleteBtn);

		JPanel pFLCenter2 = new JPanel();
		pFLCenter.add(pFLCenter2, BorderLayout.CENTER);
		pFLCenter2.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		pFLCenter2.add(scrollPane_2);

		// 농가리스트 보여주는 곳
		farmListTable = new JTable();
		scrollPane_2.setViewportView(farmListTable);
		try {
			System.out.println("일손교류 디비 연결 후 열람 성공");
			ArrayList list = dao.showFarmlist(); // 수정해야함
			System.out.println(list);
			farmListTable.setModel(tmFarmWork);
			tmFarmWork.data = list;
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "정보 열람 실패 : " + e2.getMessage());
		}

		// -----------------------------------------
		// 전체 붙이기
		getContentPane().setLayout(new GridLayout(1, 2));
		getContentPane().add(pWest);
		pWest.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		pWest.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("BigFarm \uC77C\uC190\uAD50\uB958 \uD504\uB85C\uC81D\uD2B8 \uBAA9\uB85D");
		lblNewLabel_1.setFont(new Font("휴먼모음T", Font.PLAIN, 26));
		panel.add(lblNewLabel_1);

		JPanel pWestTop = new JPanel();
		pWest.add(pWestTop, BorderLayout.CENTER);
		pWestTop.setLayout(new BorderLayout(0, 0));

		JPanel projectSearchPanel = new JPanel();
		projectSearchPanel.setBackground(new Color(192, 192, 192));
		pWestTop.add(projectSearchPanel, BorderLayout.NORTH);
		projectSearchPanel.setLayout(new BorderLayout(10, 10));

		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "조회하기", "제목", "자격요건", "내용" }));
		comboBox.setToolTipText("");
		projectSearchPanel.add(comboBox, BorderLayout.WEST);

		tfprojectSearch = new JTextField();
		tfprojectSearch.setBackground(new Color(255, 255, 255));
		tfprojectSearch.setColumns(10);
		projectSearchPanel.add(tfprojectSearch, BorderLayout.CENTER);

		JButton projectSearchBtn = new JButton("검색");
		projectSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String managerName = tfManageId.getText();
				int sel = comboBox.getSelectedIndex();
				System.out.println(sel);
				String text = tfprojectSearch.getText();
				System.out.println(text);
				String managerName = tfManageId.getText();
				if (sel == 0) {
					// 관리자가 자신이 가진 프로젝트 목록을 볼 수있게 함
					try {
						System.out.println("일손교류 디비 연결 후 열람 성공");
						System.out.println(managerName);
						ArrayList list = dao.showManagerProjectlist(managerName);
						System.out.println(list);
						tmManageWork.data = list;
						projectListTable.setModel(tmManageWork);
						tmManageWork.fireTableDataChanged();
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "정보 열람 실패 : " + e2.getMessage());
					}

				} else {
					try {
						System.out.println("검색성공");
						ArrayList list = dao.managerProjectSearch(managerName, sel, text);
						tmManageWork.data = list;
						System.out.println(list);
						projectListTable.setModel(tmManageWork);
						tmManageWork.fireTableDataChanged();
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "검색 실패 : " + e2.getMessage());
					}
				}
			}
		});
		projectSearchPanel.add(projectSearchBtn, BorderLayout.EAST);

		JPanel pWestCenter = new JPanel();
		pWestCenter.setBackground(new Color(255, 128, 192));
		pWestTop.add(pWestCenter, BorderLayout.CENTER);
		pWestCenter.setLayout(new BorderLayout(20, 20));

		JScrollPane scrollPane = new JScrollPane();
		pWestCenter.add(scrollPane);

		projectListTable = new JTable();
		scrollPane.setViewportView(projectListTable);
		getContentPane().add(pEast);
		// projectListTable.setModel(tmManageWork);
	}

	// ####################################################
	// 화면에 필요한 스타일 지정
	void setStyle() {

	}

	// ####################################################
	// 이벤트 등록 및 관련
	void eventProc() {
		// JTable에 마우스 클릭 이벤트를 만들 것
		projectListTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 열 클릭이 발생하면
				int row = projectListTable.getSelectedRow();
				int col = 0;
				int projectNum = (Integer) projectListTable.getValueAt(row, col);
				System.out.println("eventProc : " + projectNum);
				WorkConnectionVO vo = new WorkConnectionVO();
				try {
					vo = dao.findByNum(projectNum);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "프로젝트 검색 실패 : " + e2.getMessage());
				}
				// 검색된 비디오를 화면에 출력
				tfTitle.setText(vo.getWs_Title());
				tfBeginDate.setText(vo.getWs_StartDate().substring(0, 10));
				tfEndDate.setText(vo.getWs_EndDate().substring(0, 10));
				tfParticipants.setText(String.valueOf(vo.getWs_Participants()));
				tfQualify.setText(vo.getWs_Qualify());
				tfLocation.setText(String.valueOf(vo.getWs_FarmNum()));
				tfContent.setText(vo.getWs_Content());

			}
		});
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
	}

	public void clearScreen() {
		tfTitle.setText("");
		tfBeginDate.setText("");
		tfEndDate.setText("");
		tfContent.setText("");
		tfLocation.setText("");
		tfQualify.setText("");
		tfParticipants.setText("");
	}

	void selectTable(String manager_Name) {
		try {
			ArrayList list = dao.showManagerProjectlist(manager_Name);
			System.out.println("selectTable" + manager_Name);
			tmManageWork.data = list;
			System.out.println("selectTable" + list);
			projectListTable.setModel(tmManageWork);
			tmManageWork.fireTableDataChanged(); // 내용 바뀐것을 화면에 알려주는것

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "검색 실패 : " + e.getMessage());

		}
	}

	void selectTable2() {
		try {
			System.out.println("일손교류 디비 연결 후 열람 성공");
			int row = projectListTable.getSelectedRow();
			int col = 0;
			int pro_num = (Integer) projectListTable.getValueAt(row, col);
			System.out.println("selectTable2 : " + pro_num);
			ArrayList list = dao.showRegisterList(pro_num);
			System.out.println(list);
			tmRegistWork.data = list;
			workerRegistTable.setModel(tmRegistWork);
			tmRegistWork.fireTableDataChanged();
		} catch (Exception e2) {
			// TODO: handle exception
			// JOptionPane.showMessageDialog(null, "프로젝트 번호를 선택해주세요");
			JOptionPane.showMessageDialog(null, "정보 열람 실패 : " + e2.getMessage());
		}
	}

	void selectTable3() {
		try {
			System.out.println("일손교류 디비 연결 후 열람 성공");
			int row = farmListTable.getSelectedRow();
			int col = 0;
			int farm_num = (Integer) farmListTable.getValueAt(row, col);
			System.out.println("selectTable3 : " + farm_num);
			ArrayList list = dao.showFarmlist();
			System.out.println(list);
			tmFarmWork.data = list;
			farmListTable.setModel(tmFarmWork);
			tmFarmWork.fireTableDataChanged();
		} catch (Exception e2) {
			// TODO: handle exception
			// JOptionPane.showMessageDialog(null, "프로젝트 번호를 선택해주세요");
			JOptionPane.showMessageDialog(null, "정보 열람 실패 : " + e2.getMessage());
		}
	}
};

class WorkConnectionManageTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "순번", "프로젝트명", "시작일", "마감일" };

//=============================================================
//1. 기본적인 TabelModel  만들기
//아래 세 함수는 TabelModel 인터페이스의 추상함수인데
//AbstractTabelModel에서 구현되지 않았기에...
//반드시 사용자 구현 필수!!!!

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

//===============================================================
//2. 지정된 컬럼명으로 변환하기
//
//     기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
	public String getColumnName(int col) {
		return columnNames[col];
	}

}

//신청자 정보 열람하는 테이블
class WorkConnectionRegisterTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "순번", "아이디", "이름", "전화번호" };

//=============================================================
//1. 기본적인 TabelModel  만들기
//아래 세 함수는 TabelModel 인터페이스의 추상함수인데
//AbstractTabelModel에서 구현되지 않았기에...
//반드시 사용자 구현 필수!!!!

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

//===============================================================
//2. 지정된 컬럼명으로 변환하기
//
//     기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
	public String getColumnName(int col) {
		return columnNames[col];
	}

}

//농가 정보 열람하는 테이블
class WorkConnectionFarmTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "순번", "농가명", "농가 위치", "농가 종류", "신청상태" };

//=============================================================
//1. 기본적인 TabelModel  만들기
//아래 세 함수는 TabelModel 인터페이스의 추상함수인데
//AbstractTabelModel에서 구현되지 않았기에...
//반드시 사용자 구현 필수!!!!

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

//===============================================================
//2. 지정된 컬럼명으로 변환하기
//
//   기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
	public String getColumnName(int col) {
		return columnNames[col];
	}

}