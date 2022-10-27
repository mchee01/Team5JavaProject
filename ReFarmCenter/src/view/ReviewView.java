package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import model.ReviewDAO;
import model.rec.ReviewVO;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.TextArea;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class ReviewView extends JFrame implements ActionListener {

	private JPanel contentPane;

	private JTable reviewList;
	private JTextField tfReviewTitle;
	private JTextField tfReviewContents;
	
	private JComboBox comInsertScore;

	private ReviewDAO dao = null;

	ReviewTableModel tmReview;
	private JTextField tfReviewNum;
	public JTextField tF_userID, tfPrjTitle;



	public ReviewView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 577);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ---별점 검색------------------------------
		String[] search = { "", "1점", "2점", "3점", "4점", "5점" };
		JComboBox cbSearchGrade = new JComboBox(search);
		cbSearchGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();

				if (o == cbSearchGrade) {
					String grade = (String) cbSearchGrade.getSelectedItem();
					
					
					String prjName = tfPrjTitle.getText();
					
					System.out.println("prjName : " + prjName);
					try {

						dao = new ReviewDAO();
						tmReview = new ReviewTableModel();

						ArrayList list = dao.searchReview(grade, prjName);
						System.out.println(list);

						tmReview.data = list;
						reviewList.setModel(tmReview);
						tmReview.fireTableDataChanged();
						
						clearScreen();

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "검색 실패 : " + e2.getMessage());
					}
					
				}
			}
			private void clearScreen() {
				tfReviewNum.setText("");
				tfReviewTitle.setText("");
				tfReviewContents.setText("");
				comInsertScore.setSelectedItem("");
			}

		});
		cbSearchGrade.setBounds(311, 69, 62, 24);

		contentPane.add(cbSearchGrade);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 160, 344, 351);
		contentPane.add(scrollPane);

		reviewList = new JTable();
		scrollPane.setViewportView(reviewList);
		
//		try {
//			
//			ReviewDAO dao = new ReviewDAO();
//			ReviewTableModel tmReview = new ReviewTableModel();
//			
//			String prjName = tfPrjTitle.getText();
//			System.out.println("prjName : " + prjName);
//			
//			ArrayList List = dao.showReviewlist(prjName);
//			
//			reviewList.setModel(tmReview);
//			tmReview.data = List;
//			tmReview.fireTableDataChanged();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(null, "목록 실패 : " + e1.getMessage());
//		}

		reviewList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				System.out.println("click");

				int row = reviewList.getSelectedRow();
				int col = 0;
				int reviewNum = (Integer) reviewList.getValueAt(row, col);
				ReviewVO vo = new ReviewVO();

				try {
					ReviewDAO dao = new ReviewDAO();
					vo = dao.findByNum(reviewNum);

					System.out.println(vo.getGrade());

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "리뷰 검색 실패 : " + e2.getMessage());
				}

				System.out.println(row + " " + col);

				tfReviewNum.setText(String.valueOf(vo.getReviewnum()));
				tfReviewContents.setText(vo.getReviewcontent());
				tfReviewTitle.setText(vo.getReviewtitle());
				comInsertScore.setSelectedItem(vo.getGrade());

			}
		});

		JLabel lblNewLabel = new JLabel("제목");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(385, 69, 56, 24);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("내용");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(365, 128, 105, 35);
		contentPane.add(lblNewLabel_1);

		tfReviewTitle = new JTextField();
		tfReviewTitle.setBounds(435, 70, 347, 24);
		contentPane.add(tfReviewTitle);
		tfReviewTitle.setColumns(10);

		tfReviewContents = new JTextField();
		tfReviewContents.setBounds(388, 160, 394, 306);
		contentPane.add(tfReviewContents);
		tfReviewContents.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("별점 :");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(247, 64, 62, 35);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("별점");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(385, 103, 56, 24);
		contentPane.add(lblNewLabel_5);

		ButtonGroup group = new ButtonGroup();

		JLabel lblNewLabel_6 = new JLabel(" 후기목록");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(20, 69, 65, 20);
		contentPane.add(lblNewLabel_6);

		String star[] = { "1점", "2점", "3점", "4점", "5점" };
		comInsertScore = new JComboBox(star);
		comInsertScore.setForeground(new Color(0, 0, 0));
		comInsertScore.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		comInsertScore.setBounds(435, 104, 74, 26);
		contentPane.add(comInsertScore);

		// --등록--------------------------------------------------------------
		JButton bReviewInsert = new JButton("후기등록");
		bReviewInsert.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();

				if (o == bReviewInsert) {
					
					String prjName = tfPrjTitle.getText();
					String userName = tF_userID.getText();
					System.out.println("prjName : " + prjName);
					
					try {
						dao = new ReviewDAO();
						String reviewtitle = tfReviewTitle.getText();
						String reviewcontent = tfReviewContents.getText();
						String grade = (String) comInsertScore.getSelectedItem();
						System.out.println("title :" + reviewtitle + "  content : " + reviewcontent + "  grade " + grade);

						ReviewVO vo = new ReviewVO(grade, reviewcontent, reviewtitle, prjName, userName);
						System.out.println("vo : " + vo);

						dao.reviewInsert(grade, reviewcontent, reviewtitle, prjName, userName);
						selectTable();
						clearScreen();
						JOptionPane.showMessageDialog(null, "후기등록완료!");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "후기등록실패 : " + e2.getMessage());
					}
				}

			}
			
		

			private void clearScreen() {
				tfReviewNum.setText("");
				tfReviewTitle.setText("");
				tfReviewContents.setText("");
				comInsertScore.setSelectedItem("");
			}
			void selectTable()
			   {
				String prjName = tfPrjTitle.getText();
			     
			      try {
			    	 dao = new ReviewDAO();
			    	 tmReview = new ReviewTableModel();
			    	 
			         ArrayList list = dao.searchReview(prjName);
			         tmReview.data = list;
			         reviewList.setModel(tmReview);
			         tmReview.fireTableDataChanged(); //내용 바뀐것을 화면에 알려주는것
			         
			         
			      } catch (Exception e) {
			         // TODO: handle exception
			         JOptionPane.showMessageDialog(null, "검색 실패 : " + e.getMessage());

			      }
			   }

		});

		bReviewInsert.setBackground(Color.WHITE);
		bReviewInsert.setForeground(Color.BLACK);
		bReviewInsert.setBounds(677, 476, 105, 35);
		contentPane.add(bReviewInsert);

		// --등록--------------------------------------------------------------

		// --수정--------------------------------------------------------------

		JButton reviewUpdBTN = new JButton("후기수정");
		reviewUpdBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object o = e.getSource();

				if (o == reviewUpdBTN) {

					try {

						dao = new ReviewDAO();

						String reviewtitle = tfReviewTitle.getText();
						String reviewcontent = tfReviewContents.getText();
						String grade = (String) comInsertScore.getSelectedItem();
						System.out
								.println("title :" + reviewtitle + "  content : " + reviewcontent + "  grade " + grade);

						int num = Integer.parseInt(tfReviewNum.getText());
						System.out.println(num);

						ReviewVO vo = new ReviewVO(grade, reviewcontent, reviewtitle);

						dao.reviewUpdate(vo, num);
						selectTable();
						clearScreen();
						JOptionPane.showMessageDialog(null, "후기수정완료!");

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "후기수정실패 : " + e2.getMessage());
					}

				}

			}

			private void clearScreen() {
				tfReviewNum.setText("");
				tfReviewTitle.setText("");
				tfReviewContents.setText("");
				comInsertScore.setSelectedItem("");

			}
			void selectTable()
			   {
				String prjName = tfPrjTitle.getText();
			     
			      try {
			    	 dao = new ReviewDAO();
			    	 tmReview = new ReviewTableModel();
			    	 
			         ArrayList list = dao.searchReview(prjName);
			         tmReview.data = list;
			         reviewList.setModel(tmReview);
			         tmReview.fireTableDataChanged(); //내용 바뀐것을 화면에 알려주는것
			         
			         
			      } catch (Exception e) {
			         // TODO: handle exception
			         JOptionPane.showMessageDialog(null, "검색 실패 : " + e.getMessage());

			      }
			   }
		});
		reviewUpdBTN.setForeground(Color.BLACK);
		reviewUpdBTN.setBackground(Color.WHITE);
		reviewUpdBTN.setBounds(398, 476, 105, 35);
		contentPane.add(reviewUpdBTN);
		// --수정--------------------------------------------------------------

		// --삭제--------------------------------------------------------------
		JButton reviewDelBTN = new JButton("후기삭제");
		reviewDelBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object o = e.getSource();

				if (o == reviewDelBTN) {

					try {

						dao = new ReviewDAO();

						int num = Integer.parseInt(tfReviewNum.getText());

						dao.reviewDelete(num);
						
						selectTable();
						clearScreen();
						JOptionPane.showMessageDialog(null, "후기실패완료!");

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "후기삭제실패 : " + e2.getMessage());
					}
				}
			}
			
			private void clearScreen() {
				tfReviewNum.setText("");
				tfReviewTitle.setText("");
				tfReviewContents.setText("");
				comInsertScore.setSelectedItem("");

			}
			
			void selectTable()
			   {
				String prjName = tfPrjTitle.getText();
			      try {
			    	 dao = new ReviewDAO();
			    	 tmReview = new ReviewTableModel();
			    	 
			         ArrayList list = dao.searchReview(prjName);
			         tmReview.data = list;
			         reviewList.setModel(tmReview);
			         tmReview.fireTableDataChanged(); //내용 바뀐것을 화면에 알려주는것
			         
			         
			      } catch (Exception e) {
			         // TODO: handle exception
			         JOptionPane.showMessageDialog(null, "검색 실패 : " + e.getMessage());

			      }
			   }

		});
		reviewDelBTN.setForeground(Color.BLACK);
		reviewDelBTN.setBackground(Color.WHITE);
		reviewDelBTN.setBounds(539, 476, 105, 35);
		contentPane.add(reviewDelBTN);

		tfReviewNum = new JTextField();
		tfReviewNum.setEditable(false);
		tfReviewNum.setBackground(new Color(255, 255, 255));
		tfReviewNum.setBounds(435, 30, 119, 24);
		contentPane.add(tfReviewNum);
		tfReviewNum.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("글번호");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(385, 29, 56, 24);
		contentPane.add(lblNewLabel_7);
		
		tF_userID = new JTextField();
		tF_userID.setBounds(663, 30, 119, 24);
		contentPane.add(tF_userID);
		tF_userID.setColumns(10);
		
		tfPrjTitle = new JTextField();
		tfPrjTitle.setEditable(false);
		tfPrjTitle.setBounds(87, 30, 286, 24);
		contentPane.add(tfPrjTitle);
		tfPrjTitle.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("프로젝트");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(23, 30, 62, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("사용자 ID");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(567, 32, 96, 19);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(ReviewView.class.getResource("/img/[크기변환]초원이다.jpg")));
		lblNewLabel_8.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(0, 0, 819, 540);
		contentPane.add(lblNewLabel_8);
		// --삭제--------------------------------------------------------------

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}

class ReviewTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "순번", "유저명", "별점", "제목" };

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
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	// 2. 지정된 컬럼명으로 변환하기
	public String getColumnName(int col) {
		return columnNames[col];
	}

}
