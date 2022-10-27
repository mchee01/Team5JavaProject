package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.FarmDAO;
import model.WorkConnectionDAO;
import model.rec.WorkConnectionVO;
import model.rec.sidoVO;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;

public class WorkConnectionUserView extends JFrame implements ActionListener {
	WorkConnectionDAO dao = null;
	FarmDAO fdao = null;
	WorkConnectionTableModel tmWork;
	private JTextField tfProjectSearch;
	private JTable projectInfoTable;

	JButton searchButton, registBtn, reviewBtn;
	JComboBox proSearchComboBox;
	public JTextField tfUserName;
	private JTextField tfProjectName;
	private JTextField tfProQulify;
	private JTextField tfProLocation;
	private JTextField tfProContent;
	private JTextField tfParticipants;
	private JTextField tfProEndDate;
	private JTextField tfProStartDate;
	private JTextField tf_farmName;
	private JTextField tf_farmUse;
	private JComboBox cbx_sido;
	private JComboBox cbx_sigungu;
	private JComboBox cbx_dong;

	// ##############################################
	// constructor method
	public WorkConnectionUserView() {
		setBounds(100, 100, 920, 660);
		try {
			dao = new WorkConnectionDAO();
			fdao = new FarmDAO();
			newObject();
			addLayout();
			setStyle();
			eventProc();
			System.out.println("일손교류프로젝트 DB 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "일손교류프로젝트 DB 실패 : " + e.getMessage());
		}
	}

	// ##############################################
	// 멤버필드 객체 생성
	void newObject() {
		String janreText[] = { "에로", "코믹", "드라마", "공포", "애니메이션" };
		String searchText[] = { "제목", "감독", "배우" };
		tmWork = new WorkConnectionTableModel();
		searchButton = new JButton();
		registBtn = new JButton();
		reviewBtn = new JButton();
		proSearchComboBox = new JComboBox();
	}

	// ##############################################
	// GUI 구성하기 위해 Layout에 붙이기
	void addLayout() {
		// -----------------------------------------
		// west 영역 : 비디오 입력 및 수정
		JPanel pWest = new JPanel();
		pWest.setBackground(new Color(128, 128, 255));

		// -----------------------------------------
		// east 영역 : 비디오 검색부분
		JPanel pEast = new JPanel();

		JPanel pEastCenter = new JPanel();
		pEastCenter.setBackground(new Color(128, 128, 255));

		pEast.setLayout(new BorderLayout());
		pEast.add("Center", pEastCenter);
		pEastCenter.setLayout(null);

		tfUserName = new JTextField();
		tfUserName.setBounds(206, 8, 116, 21);
		pEastCenter.add(tfUserName);
		tfUserName.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\uB2D8 \uC548\uB155\uD558\uC138\uC694!");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(329, 9, 96, 23);
		pEastCenter.add(lblNewLabel_3);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 70, 428, 516);
		pEastCenter.add(tabbedPane);

		JPanel detailInfoPanel_1 = new JPanel();
		detailInfoPanel_1.setLayout(null);
		detailInfoPanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		detailInfoPanel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("프로젝트상세정보", null, detailInfoPanel_1, null);

		JLabel lblNewLabel_2_5 = new JLabel("프로젝트명");
		lblNewLabel_2_5.setBounds(12, 24, 63, 16);
		detailInfoPanel_1.add(lblNewLabel_2_5);

		JLabel lblNewLabel_2_1_2 = new JLabel("프로젝트 기간");
		lblNewLabel_2_1_2.setBounds(11, 53, 79, 16);
		detailInfoPanel_1.add(lblNewLabel_2_1_2);

		JLabel lblNewLabel_2_2_1 = new JLabel("참가인원");
		lblNewLabel_2_2_1.setBounds(12, 80, 52, 16);
		detailInfoPanel_1.add(lblNewLabel_2_2_1);

		JLabel lblNewLabel_2_3_2 = new JLabel("참가자격");
		lblNewLabel_2_3_2.setBounds(12, 109, 63, 16);
		detailInfoPanel_1.add(lblNewLabel_2_3_2);

		tfProjectName = new JTextField();
		tfProjectName.setColumns(10);
		tfProjectName.setBackground(Color.WHITE);
		tfProjectName.setBounds(102, 22, 303, 20);
		detailInfoPanel_1.add(tfProjectName);

		tfProQulify = new JTextField();
		tfProQulify.setColumns(10);
		tfProQulify.setBounds(102, 107, 303, 21);
		detailInfoPanel_1.add(tfProQulify);

		JLabel lblNewLabel_2_1_1_2 = new JLabel("~");
		lblNewLabel_2_1_1_2.setBounds(248, 54, 9, 16);
		detailInfoPanel_1.add(lblNewLabel_2_1_1_2);

		tfProLocation = new JTextField();
		tfProLocation.setColumns(10);
		tfProLocation.setBounds(103, 138, 302, 21);
		detailInfoPanel_1.add(tfProLocation);

		JLabel lblNewLabel_2_3_1_1 = new JLabel("농가위치");
		lblNewLabel_2_3_1_1.setBounds(13, 140, 62, 16);
		detailInfoPanel_1.add(lblNewLabel_2_3_1_1);

		JLabel lblNewLabel_2_4_1 = new JLabel("상세내용");
		lblNewLabel_2_4_1.setBounds(12, 166, 63, 16);
		detailInfoPanel_1.add(lblNewLabel_2_4_1);

		tfProContent = new JTextField();
		tfProContent.setColumns(10);
		tfProContent.setBounds(102, 169, 303, 282);
		detailInfoPanel_1.add(tfProContent);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("명");
		lblNewLabel_2_1_1_1_1.setBounds(255, 77, 13, 26);
		detailInfoPanel_1.add(lblNewLabel_2_1_1_1_1);

		tfParticipants = new JTextField();
		tfParticipants.setColumns(10);
		tfParticipants.setBounds(101, 79, 147, 21);
		detailInfoPanel_1.add(tfParticipants);

		tfProEndDate = new JTextField();
		tfProEndDate.setColumns(10);
		tfProEndDate.setBounds(269, 50, 136, 23);
		detailInfoPanel_1.add(tfProEndDate);

		tfProStartDate = new JTextField();
		tfProStartDate.setColumns(10);
		tfProStartDate.setBounds(102, 51, 136, 23);
		detailInfoPanel_1.add(tfProStartDate);

		JButton registBtn = new JButton("\uC2E0\uCCAD\uD558\uAE30");
		registBtn.setBounds(308, 458, 97, 23);
		detailInfoPanel_1.add(registBtn);
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = tfUserName.getText();
				try {
					int row = projectInfoTable.getSelectedRow();
					int col = 0;
					int projectNum = (Integer) projectInfoTable.getValueAt(row, col);
					System.out.println(projectNum);
					System.out.println(userName);
					dao.WorkConnectProjetAssign(userName, projectNum);
					System.out.println("신청완료");
					JOptionPane.showMessageDialog(null, "신청완료!");
					clearScreen();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "신청 실패 : " + e1.getMessage());
				}
			}
		});
		registBtn.setBackground(Color.YELLOW);
		
				JButton reviewBtn_1 = new JButton("\uD6C4\uAE30\uBCF4\uAE30");
				reviewBtn_1.setBounds(194, 458, 97, 23);
				detailInfoPanel_1.add(reviewBtn_1);
				reviewBtn_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id = tfUserName.getText();
						String prjtitle = tfProjectName.getText();
						ReviewView rv = new ReviewView();
						rv.setVisible(true);
						rv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						rv.tF_userID.setText(id);
						rv.tfPrjTitle.setText(prjtitle);
						JOptionPane.showMessageDialog(null, "후기보기!");
					}
				});
				reviewBtn_1.setBackground(Color.YELLOW);

		JPanel panel = new JPanel();
		tabbedPane.addTab("농업인일손신청", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("농가이름");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(22, 155, 73, 30);
		panel.add(lblNewLabel_2);

		tf_farmName = new JTextField();
		tf_farmName.setColumns(10);
		tf_farmName.setBackground(Color.WHITE);
		tf_farmName.setBounds(113, 150, 268, 44);
		panel.add(tf_farmName);

		JLabel lblNewLabel_2_1 = new JLabel("농가종류");
		lblNewLabel_2_1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(22, 215, 73, 30);
		panel.add(lblNewLabel_2_1);

		tf_farmUse = new JTextField();
		tf_farmUse.setColumns(10);
		tf_farmUse.setBackground(Color.WHITE);
		tf_farmUse.setBounds(113, 212, 268, 40);
		panel.add(tf_farmUse);

		cbx_sido = new JComboBox();
		cbx_sido.setToolTipText("");
		cbx_sido.setBounds(96, 59, 86, 30);
		cbx_sido.addItem("시.도");
		panel.add(cbx_sido);
		displaySido();

		cbx_sido.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					System.out.println("아이템!");
				selectSido(cbx_sido.getSelectedItem().toString());

			}

		});

		cbx_sigungu = new JComboBox();
		cbx_sigungu.setBounds(194, 59, 86, 30);
		panel.add(cbx_sigungu);

		cbx_sigungu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED)

					selectGugun(cbx_sido.getSelectedItem().toString(), cbx_sigungu.getSelectedItem().toString());

			}

		});

		JLabel lblNewLabel_1 = new JLabel("주소");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(30, 60, 54, 27);
		panel.add(lblNewLabel_1);

		cbx_dong = new JComboBox();
		cbx_dong.setBounds(295, 59, 86, 30);
		panel.add(cbx_dong);

		JButton registFarmBtn = new JButton("신청하기");
		registFarmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = 0;
				String name = tf_farmName.getText();
				String use = tf_farmUse.getText();
				String dong = (String) cbx_dong.getSelectedItem();
				num = fdao.dongNum(dong);
				try {
					fdao.insertFarm(name, use, num);
					farmclear();
					JOptionPane.showMessageDialog(null, "신청완료!");

				} catch (Exception e2) {
					// TODO: handle exception

					JOptionPane.showMessageDialog(null, e2.getMessage());
				}

			}
		});
		registFarmBtn.setBackground(Color.YELLOW);
		registFarmBtn.setBounds(308, 458, 97, 23);
		panel.add(registFarmBtn);

		// -----------------------------------------
		// 전체 붙이기
		getContentPane().setLayout(new GridLayout(1, 2));
		getContentPane().add(pWest);
		pWest.setLayout(null);

		JLabel lblNewLabel = new JLabel("BigFarm \uC77C\uC190\uAD50\uB958 \uD504\uB85C\uC81D\uD2B8");
		lblNewLabel.setFont(new Font("휴먼모음T", Font.PLAIN, 26));
		lblNewLabel.setBounds(12, 24, 300, 46);
		pWest.add(lblNewLabel);

		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		searchPanel.setBackground(Color.WHITE);
		searchPanel.setBounds(11, 68, 432, 42);
		pWest.add(searchPanel);
		searchPanel.setLayout(null);

		tfProjectSearch = new JTextField();
		tfProjectSearch.setBounds(87, 11, 265, 21);
		tfProjectSearch.setColumns(10);
		searchPanel.add(tfProjectSearch);

		JComboBox proSearchComboBox = new JComboBox();
		proSearchComboBox.setBounds(12, 10, 70, 23);
		proSearchComboBox.setToolTipText("");
		proSearchComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "\uC81C\uBAA9", "\uC790\uACA9\uC870\uAC74", "\uB0B4\uC6A9" }));
		proSearchComboBox.setSelectedIndex(0);
		searchPanel.add(proSearchComboBox);

		JButton searchButton = new JButton("\uAC80\uC0C9");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = proSearchComboBox.getSelectedIndex();
				System.out.println(sel);
				String text = tfProjectSearch.getText();
				System.out.println(text);
				try {
					System.out.println("검색성공");
					ArrayList list = dao.projectSearch(sel, text);
					tmWork.data = list;
					System.out.println(list);
					projectInfoTable.setModel(tmWork);
					tmWork.fireTableDataChanged();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "검색 실패 : " + e2.getMessage());
				}
			}
		});
		searchButton.setBounds(357, 11, 63, 21);
		searchPanel.add(searchButton);

		JPanel projectListPanel = new JPanel();
		projectListPanel.setLayout(null);
		projectListPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		projectListPanel.setBounds(13, 118, 431, 465);
		pWest.add(projectListPanel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 407, 445);
		projectListPanel.add(scrollPane);

		projectInfoTable = new JTable();
		scrollPane.setViewportView(projectInfoTable);
		getContentPane().add(pEast);
		// 일손교류 프로젝트 디비에서 가져온 정보 보는 곳
		try {
			System.out.println("일손교류 디비 연결 후 열람 성공");
			ArrayList list = dao.showProjectlist();
			System.out.println(list);
			projectInfoTable.setModel(tmWork);
			tmWork.data = list;
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "정보 열람 실패 : " + e2.getMessage());
		}
	}

	// ####################################################
	// 화면에 필요한 스타일 지정
	void setStyle() {

	}

	// ####################################################
	// 이벤트 등록 및 관련
	void eventProc() {
		searchButton.addActionListener(this);
		registBtn.addActionListener(this);
		reviewBtn.addActionListener(this);

		// JTable에 마우스 클릭 이벤트를 만들 것
		projectInfoTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 열 클릭이 발생하면
				int row = projectInfoTable.getSelectedRow();
				int col = 0;
				int projectNum = (Integer) projectInfoTable.getValueAt(row, col);
				System.out.println(projectNum);
				WorkConnectionVO vo = new WorkConnectionVO();
				try {
					vo = dao.findByNum(projectNum);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "프로젝트 검색 실패 : " + e2.getMessage());
				}
				// 검색된 비디오를 화면에 출력
				tfProjectName.setText(vo.getWs_Title());
				tfProStartDate.setText(vo.getWs_StartDate().substring(0, 10));
				tfProEndDate.setText(vo.getWs_EndDate().substring(0, 10));
				tfParticipants.setText(String.valueOf(vo.getWs_Participants()));
				tfProQulify.setText(vo.getWs_Qualify());
				tfProLocation.setText(vo.getWs_Location() + " " +vo.getWs_FarmName());
				tfProContent.setText(vo.getWs_Content());
			}
		});

	}

	public void actionPerformed(ActionEvent ev) {

	}

	public void clearScreen() {
		tfProjectName.setText("");
		tfProStartDate.setText("");
		tfProEndDate.setText("");
		tfParticipants.setText("");
		tfProQulify.setText("");
		tfProContent.setText("");
		tfProLocation.setText("");
		
	}

	public void displaySido() {
		System.out.println("시도 디스플레이!");

		// 선언

		FarmDAO dao = new FarmDAO();
		dao.connectDB();
		System.out.println("디비연결");
		ArrayList<sidoVO> sidoList = dao.searchSido();
		System.out.println("리스트확인");

		for (int i = 0; i < sidoList.size(); i++) {

//      		sidoVO sv = new sidoVO();

			// ArrayList arr = (ArrayList) sidoList.get(i);
//			sv = sidoList.get(i);
//            cbx_sido.addItem(sidoVO.getSidoName());
//			sidoVO sv = new sidoVO();
			sidoVO sv = sidoList.get(i);
			cbx_sido.addItem(sv.getSidoName());

		}

		// DB연결 해제

		dao.disconnectDB();

	}

	public void selectSido(String sido) {

		System.out.println(sido);

		FarmDAO dao = new FarmDAO();

		// DB연결

		dao.connectDB();

		//

		ArrayList<sidoVO> gugunList = dao.searchGugun(sido);

		cbx_sigungu.removeAllItems();

		cbx_sigungu.addItem("구.군");

		for (int i = 0; i < gugunList.size(); i++) {

			sidoVO sicode = gugunList.get(i);

			cbx_sigungu.insertItemAt(sicode.getSigunguName(), i);

		}

//        table.setModel(new sidoTable());

		// DB연결 해제

		dao.disconnectDB();

	}

	public void selectGugun(String sido, String gugun) {

		System.out.println(gugun);

		FarmDAO dao = new FarmDAO();

		// DB연결

		dao.connectDB();

		//

		ArrayList<sidoVO> dongList = dao.searchDong(sido, gugun);

		cbx_dong.removeAllItems();

		cbx_dong.addItem("동 선택");

		for (int i = 0; i < dongList.size(); i++) {

			sidoVO cicode = dongList.get(i);

			cbx_dong.insertItemAt(cicode.getDongName(), i);

		}

		// DB연결 해제

		dao.disconnectDB();

	}

	public void farmclear() {
		tf_farmName.setText("");
		tf_farmUse.setText("");
		cbx_sido.setSelectedItem("시.도");
		cbx_sigungu.setSelectedItem("구.군");
		cbx_dong.setSelectedItem("동 선택");
	}

};

class WorkConnectionTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "순번", "프로젝트명", "시작일", "마감일" };

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		ArrayList temp = (ArrayList) data.get(rowIndex);
		return temp.get(columnIndex);
	}

	// 2. 지정된 컬럼명으로 변환하기
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

}
