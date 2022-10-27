package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import buttonLib.ButtonRound;
import model.FarmDAO;
import model.rec.sidoVO;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class UserView extends JFrame {

	private JPanel contentPane;
	public JTextField tF_id;
    private FarmDAO dao;
	
	private JTable table;
	
	private JPanel panel_2;
	
	private JComboBox cbx_sido;
	private JComboBox cbx_sigungu;
	
	private JButton btn_search;
	private JButton btn_workconn;
	private JButton btn_ed;
	private JButton btn_sell;
	
	private ScrollPane scrollPane;
	EducationUserView edub;
	RealeStateView rsv;
	private sidoModel sidoModel;
	
	private JPanel panel_1_1;
	
	private JComboBox comboBox;
	private Connection con = null;

    private PreparedStatement ps = null;      

    private ResultSet rs = null;
    private JLabel lblNewLabel;
    


	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public UserView() {
		sidoModel = new sidoModel();
		
		setTitle("유저페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tF_id = new JTextField();
		tF_id.setBorder(null);
		tF_id.setForeground(new Color(255, 255, 255));
		tF_id.setBackground(new Color(255, 255, 255));
		tF_id.setBounds(789, 36, 105, 30);
		contentPane.add(tF_id);
		tF_id.setColumns(10);
		tF_id.setEnabled(false);
		
		JLabel lbl_id = new JLabel("사용자 ID");
		lbl_id.setForeground(new Color(255, 255, 255));
		lbl_id.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lbl_id.setBounds(712, 39, 105, 27);
		contentPane.add(lbl_id);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 90, 385, 367);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 388, 347);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(sidoModel);
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(22, 25, 385, 52);
		contentPane.add(panel_1_1);
		
		cbx_sido = new JComboBox();
		cbx_sido.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		cbx_sido.setModel(new DefaultComboBoxModel(new String[] {"시.도 선택"}));
		cbx_sido.setToolTipText("");
		cbx_sido.setBounds(13, 10, 86, 30);
		panel_1_1.add(cbx_sido);
		
		displaySido();
		cbx_sido.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	if(e.getStateChange()==ItemEvent.SELECTED)
            		System.out.println("아이템!");
					selectSido(cbx_sido.getSelectedItem().toString());
                  

            }

		});
		cbx_sido.setToolTipText("");
		
		
		
		
		btn_search = new JButton("");
		btn_search.setIcon(new ImageIcon(UserView.class.getResource("/img/돋보기.PNG")));
		btn_search.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("검색");
				dao = new FarmDAO();
				String cb_sido = cbx_sido.getSelectedItem().toString();
				System.out.println("시.도"+cb_sido);
				String cb_sigungu = cbx_sigungu.getSelectedItem().toString();
				System.out.println("시군구"+cb_sigungu);
				int sel = comboBox.getSelectedIndex();
				System.out.println("index"+sel);
				if (sel == 1) {
					try {
						ArrayList list = dao.projSearch(cb_sido, cb_sigungu);
						sidoModel.data = list;
						table.setModel(sidoModel);
						sidoModel.fireTableDataChanged();
						} catch (Exception e2) {
						// TODO: handle exception
			                  JOptionPane.showMessageDialog(null, "이름 검색 실패:" + e2.getMessage());
					}
				}
				else if(sel == 0) {
					try {
						System.out.println("검색성공");
						
						ArrayList list = dao.edubSearch(cb_sido, cb_sigungu);				
						sidoModel.data = list;
						table.setModel(sidoModel);
						sidoModel.fireTableDataChanged();
					} catch (Exception e2) {
					// TODO: handle exception
		                  JOptionPane.showMessageDialog(null, " 실패:" + e2.getMessage());
						}
				}
			}
		});
		btn_search.setBounds(331, 10, 42, 30);
		panel_1_1.add(btn_search);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"귀농/귀촌", "일손교류"}));
		comboBox.setBounds(203, 10, 86, 30);
		panel_1_1.add(comboBox);
		
		cbx_sigungu = new JComboBox();
		cbx_sigungu.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		cbx_sigungu.setBounds(111, 10, 80, 30);
		panel_1_1.add(cbx_sigungu);
		
		btn_workconn = new ButtonRound("일손교류");
		btn_workconn.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		btn_workconn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tF_id.getText();
				WorkConnectionUserView WCUV = new WorkConnectionUserView();
			    WCUV.setVisible(true);
			    WCUV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				WCUV.tfUserName.setText(id);
				
			}
		});
		btn_workconn.setBounds(629, 434, 97, 23);
		contentPane.add(btn_workconn);
		
		ButtonRound btn_ed = new ButtonRound("귀농/귀촌");
		btn_ed.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		btn_ed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tF_id.getText();
				edub = new EducationUserView();
				edub.frame.setVisible(true);
				edub.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				edub.tfUserName.setText(id);
				
			}
		});
		
		btn_ed.setBounds(481, 434, 97, 23);
		contentPane.add(btn_ed);
		
		ButtonRound btn_sell = new ButtonRound("부동산");
		btn_sell.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		btn_sell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tF_id.getText();
				System.out.println(id);
				rsv = new RealeStateView();
				rsv.setVisible(true);
				rsv.tf_id.setText(id);
			}
		});
		btn_sell.setBounds(774, 434, 97, 23);
		contentPane.add(btn_sell);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(UserView.class.getResource("/img/[크기변환]유저뷰.gif")));
		lblNewLabel.setBounds(-49, -39, 1001, 522);
		contentPane.add(lblNewLabel);
//		String id = tF_id.getText();
//		edub = new EducationBusinessView();
//		edub.frame.setVisible(true);
//		edub.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		edub.tf_id.setText(id);
		
	}
	
	
	
	
	
	
	
	public void displaySido(){
		System.out.println("시도 디스플레이!");

		//선언
		
		FarmDAO dao = new FarmDAO();
		dao.connectDB();
		System.out.println("디비연결");
		ArrayList<sidoVO> sidoList = dao.searchSido();
		System.out.println("리스트확인");

		for(int i = 0 ; i < sidoList.size() ; i++){
 
//      		sidoVO sv = new sidoVO();
    	    
			//ArrayList arr = (ArrayList) sidoList.get(i);
//			sv = sidoList.get(i);
//            cbx_sido.addItem(sidoVO.getSidoName());
//			sidoVO sv = new sidoVO();
			sidoVO sv = sidoList.get(i); 
			cbx_sido.addItem(sv.getSidoName());

			}             

    //DB연결 해제

		dao.disconnectDB();
		

	}
	
	public void selectSido(String sido){

        System.out.println(sido);

        FarmDAO dao = new FarmDAO();

        //DB연결

        dao.connectDB();             

        //

        ArrayList<sidoVO> gugunList = dao.searchGugun(sido);

        cbx_sigungu.removeAllItems();


        cbx_sigungu.addItem("구.군 선택");
        
        for(int i = 0 ; i < gugunList.size() ; i++){

                sidoVO sicode = gugunList.get(i);

                cbx_sigungu.insertItemAt(sicode.getSigunguName(), i);

        }

//        table.setModel(new sidoTable());

        //DB연결 해제

        dao.disconnectDB();

	}
//	void selectTable() {
//	      try {
//	         ArrayList list = dao.sidosearch();
//	         rsidoTable.data = list;
//	         table.setModel(rsidoTable);
//	         rsidoTable.fireTableDataChanged();
//	         
//	      } catch (Exception e) {
//	         // TODO: handle exception
//	         JOptionPane.showMessageDialog(null, "테이블 출력 실패 : " + e.getMessage());
//	      }
//	      
//	   }
	
	class sidoModel extends AbstractTableModel{
		ArrayList data = new ArrayList();
		 String[] columNames = {"동네","프로그램","시작날짜","끝나는날짜","인 원"};
		
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			ArrayList temp = (ArrayList) data.get(rowIndex);
			return temp.get(columnIndex);
		}
		public String getColumnName(int rowIndex) {
			return columNames[rowIndex];
		}
		 
	}
}
