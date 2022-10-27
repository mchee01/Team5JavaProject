package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Button;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.Label;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;

import model.EducationManagerDAO;
import model.rec.EducationManagerVO;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.ComponentOrientation;
import javax.swing.border.LineBorder;

public class EducationManagerView implements ActionListener {

   public JFrame frame;
   private JTextField tfsearch;
   private JTextField tfebName;
   private JTextField tfebStartDate;
   private JTextField tfebEndDate;
   private JTextField tfebContent;
   private JTextField tfebParticipants;
   private JTextField tfebLocation;
   private JTextField tfebTarget;
   public  JTextField tfUserName;
   private JButton btnNewButton_1;

   JButton btnSearch;
   EducationManagerDAO dao;
   EducationBusinessModel ebModel;
   JTable EducationList;
   JComboBox comboBox;
   
  

   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               EducationManagerView window = new EducationManagerView();
//               window.frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   /**
    * Create the application.
    */
   public EducationManagerView() {
      try {
         dao = new EducationManagerDAO();
         btnSearch = new JButton();
         ebModel = new EducationBusinessModel();
         // EducationList = new JTable(ebModel);
         
         
         initialize();
         eventProc();
         // tmWork = new WorkConnectionTableModel();
         System.out.println("교육관리자 디비 연결 성공");
      } catch (Exception e) {
         // TODO: handle exception
         JOptionPane.showMessageDialog(null, "교육관리자 DB연결 실패 : " + e.getMessage());
      }
   }

   /**
    * Initialize the contents of the frame.
    */
   public void initialize() {
      frame = new JFrame();
      frame.getContentPane().setBackground(new Color(0, 128, 0));
      frame.setBounds(100, 100, 1205, 898);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().setLayout(null);

      JLabel lblNewLabel = new JLabel("관리자가 등록한 귀농 귀촌 목록");
      lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
      lblNewLabel.setForeground(new Color(255, 255, 255));
      lblNewLabel.setBackground(new Color(230, 230, 250));
      lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(266, 31, 597, 57);
      frame.getContentPane().add(lblNewLabel);

      JPanel panel = new JPanel();
      panel.setBounds(26, 98, 551, 68);
      frame.getContentPane().add(panel);
      panel.setLayout(null);

      comboBox = new JComboBox();
      comboBox.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
      comboBox.setBorder(null);
      comboBox.setModel(new DefaultComboBoxModel(new String[] { "교육이름", "교육대상", "교육내용" }));
      comboBox.setBounds(28, 17, 93, 28);
      panel.add(comboBox);

      tfsearch = new JTextField();
      tfsearch.setBounds(133, 18, 319, 28);
      panel.add(tfsearch);
      tfsearch.setColumns(10);

      JButton searchBtn = new JButton("");
      searchBtn.setBorder(null);
      searchBtn.setHorizontalTextPosition(SwingConstants.CENTER);
      searchBtn.setIcon(new ImageIcon(EducationManagerView.class.getResource("/img/돋보기.PNG")));
      searchBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            Object oj = e.getSource();
            
            if (oj == searchBtn) {
               int sel = comboBox.getSelectedIndex();
               String text = tfsearch.getText();
               try {
                  System.out.println("검색성공");
                  ArrayList list = dao.educationSearch(sel, text);
                  ebModel.data = list;
                  EducationList.setModel(ebModel);
                  ebModel.fireTableDataChanged();
               } catch (Exception e2) {
                  JOptionPane.showMessageDialog(null, "이름 검색 실패:" + e2.getMessage());
               }
            }
         }
      });
      searchBtn.setBounds(464, 17, 38, 28);
      panel.add(searchBtn);

      JPanel panel_2 = new JPanel();
      panel_2.setBorder(null);
      panel_2.setBounds(619, 98, 557, 319);
      frame.getContentPane().add(panel_2);
      panel_2.setLayout(null);

      JLabel lblNewLabel_1 = new JLabel("교육 이름");
      lblNewLabel_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setBounds(23, 10, 71, 15);
      panel_2.add(lblNewLabel_1);

      JLabel lblNewLabel_2 = new JLabel("교육 기간");
      lblNewLabel_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setBounds(23, 46, 71, 15);
      panel_2.add(lblNewLabel_2);

      JLabel lblNewLabel_3 = new JLabel("교육 내용");
      lblNewLabel_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
      lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3.setBounds(23, 85, 71, 15);
      panel_2.add(lblNewLabel_3);

      JLabel lblNewLabel_4 = new JLabel("교육 대상");
      lblNewLabel_4.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
      lblNewLabel_4.setBounds(32, 189, 71, 15);
      panel_2.add(lblNewLabel_4);

      JLabel lblNewLabel_5 = new JLabel("교육 지역");
      lblNewLabel_5.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
      lblNewLabel_5.setBounds(32, 226, 62, 15);
      panel_2.add(lblNewLabel_5);

      tfebName = new JTextField();
      tfebName.setBounds(105, 7, 417, 21);
      panel_2.add(tfebName);
      tfebName.setColumns(10);

      tfebStartDate = new JTextField();
      tfebStartDate.setBounds(104, 43, 138, 21);
      panel_2.add(tfebStartDate);
      tfebStartDate.setColumns(10);

      tfebEndDate = new JTextField();
      tfebEndDate.setBounds(362, 43, 160, 21);
      panel_2.add(tfebEndDate);
      tfebEndDate.setColumns(10);

      JLabel lblNewLabel_6 = new JLabel("~");
      lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_6.setBounds(276, 46, 52, 15);
      panel_2.add(lblNewLabel_6);

      tfebContent = new JTextField();
      tfebContent.setBounds(106, 85, 421, 84);
      panel_2.add(tfebContent);
      tfebContent.setColumns(10);

      tfebParticipants = new JTextField();
      tfebParticipants.setBounds(105, 270, 422, 21);
      panel_2.add(tfebParticipants);
      tfebParticipants.setColumns(10);

      tfebLocation = new JTextField();
      tfebLocation.setBounds(104, 223, 423, 21);
      panel_2.add(tfebLocation);
      tfebLocation.setColumns(10);

      JLabel lblNewLabel_7 = new JLabel("참가 인원");
      lblNewLabel_7.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
      lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_7.setBounds(32, 273, 62, 15);
      panel_2.add(lblNewLabel_7);

      tfebTarget = new JTextField();
      tfebTarget.setBounds(105, 181, 422, 32);
      panel_2.add(tfebTarget);
      tfebTarget.setColumns(10);

      btnNewButton_1 = new JButton("");
      btnNewButton_1.setIcon(new ImageIcon(EducationManagerView.class.getResource("/img/등록.jpg")));
      btnNewButton_1.setHorizontalTextPosition(SwingConstants.CENTER);
      btnNewButton_1.setBorder(null);
      btnNewButton_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
      btnNewButton_1.setBounds(629, 435, 140, 62);
      frame.getContentPane().add(btnNewButton_1);

      btnNewButton_1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            Object oj = e.getSource();
            if (oj == btnNewButton_1) {
               try {
            	  String userName = tfUserName.getText();
                  String name = tfebName.getText();
                  String startdate = tfebStartDate.getText();
                  String enddate = tfebEndDate.getText();
                  String content = tfebContent.getText();
                  String location = tfebLocation.getText();
                  String target = tfebTarget.getText();
                  int participants = Integer.parseInt(tfebParticipants.getText());
                  EducationManagerVO vo = new EducationManagerVO(name, startdate, enddate, content, location,
                        target, participants);

                  EducationManagerDAO dao = new EducationManagerDAO();
                  dao.regist(vo);

                  clearScreen();
                  System.out.println("등록성공");
                  JOptionPane.showMessageDialog(null, "등록완료!");

               } catch (Exception e2) {
                  JOptionPane.showMessageDialog(null, "등록 실패" + e2.getMessage());
               }
            }
         }

         private void clearScreen() {
            tfebName.setText("");
            tfebStartDate.setText("");
            tfebEndDate.setText("");
            tfebContent.setText("");
            tfebLocation.setText("");
            tfebTarget.setText("");
            tfebParticipants.setText("");

         }
      });

      
      
      JButton btnNewButton_2 = new JButton("");
      btnNewButton_2.setIcon(new ImageIcon(EducationManagerView.class.getResource("/img/수정.jpg")));
      btnNewButton_2.setHorizontalTextPosition(SwingConstants.CENTER);
      btnNewButton_2.setBorder(null);
      btnNewButton_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
      btnNewButton_2.setBounds(834, 435, 140, 62);
      frame.getContentPane().add(btnNewButton_2);
      
      btnNewButton_2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
            if (o == btnNewButton_2) {
               try {
                  int row = EducationList.getSelectedRow();
                  int col = 0;
                  int eb_num = (Integer) EducationList.getValueAt(row, col);
                  System.out.println(eb_num);
                     
                  String name = tfebName.getText();
                  String startDate = tfebStartDate.getText();
                  String endDate = tfebEndDate.getText();
                  String content = tfebContent.getText();
                  String location = tfebLocation.getText();
                  String target = tfebTarget.getText();
                  int participants = Integer.parseInt(tfebParticipants.getText());
                        
                        
                       // VideoVO vo = new VideoVO(title, janre, director, actor, content);
                        EducationManagerVO vo = new EducationManagerVO(name, startDate, endDate, content, location, target, participants);
                        
                        EducationManagerDAO dao = new EducationManagerDAO();
                        dao.modify(vo, eb_num);
                        
                        clearScreen();
                     System.out.println("수정성공");
                     JOptionPane.showMessageDialog(null, "수정완료!");
                  } catch (Exception e2) {
                     // TODO: handle exception
                     JOptionPane.showMessageDialog(null, "수정실패 : " + e2.getMessage());
                  }
            }
      }
         
      
      
   });
   
      


      JButton btnNewButton_3 = new JButton("");
      btnNewButton_3.setIcon(new ImageIcon(EducationManagerView.class.getResource("/img/삭제.png")));
      btnNewButton_3.setHorizontalTextPosition(SwingConstants.CENTER);
      btnNewButton_3.setBorder(null);
      btnNewButton_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
      btnNewButton_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
            if (o == btnNewButton_3) {
               int row = EducationList.getSelectedRow();
               int col = 0;
               int eb_num = (Integer) EducationList.getValueAt(row, col);
               //int num = Integer.parseInt(tfebName.getText()); //여기 문제잇음
               try {
                  dao.ebDelete(eb_num);
                  clearScreen();
                  System.out.println("삭제 성공");
                  JOptionPane.showMessageDialog(null, "삭제완료!");
               } catch (Exception e1) {
                  // TODO: handle exception
                  System.out.println("삭제 실패" + e1.getMessage());
               }
            }
         }
      });
      btnNewButton_3.setBounds(1036, 434, 140, 63);
      frame.getContentPane().add(btnNewButton_3);

      JPanel panel_3 = new JPanel();
      panel_3.setBorder(null);
      panel_3.setBounds(619, 519, 557, 272);
      frame.getContentPane().add(panel_3);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
      scrollPane.setBounds(26, 207, 551, 632);
      frame.getContentPane().add(scrollPane);

      // 교육정보테이블
      EducationList = new JTable();
      scrollPane.setViewportView(EducationList);
      

      
      
      
      
      

      // db에서 교육정보를 가져오기 위함
      try {
         System.out.println("디비연결후 열람 성공");
         ArrayList list = dao.showEducationList();
         EducationList.setModel(ebModel);
         
         JLabel lblNewLabel_8 = new JLabel("");
         lblNewLabel_8.setHorizontalTextPosition(SwingConstants.CENTER);
         lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_8.setIcon(new ImageIcon(EducationManagerView.class.getResource("/img/[크기변환]초로롱.gif")));
         lblNewLabel_8.setBounds(-161, -36, 1519, 955);
         frame.getContentPane().add(lblNewLabel_8);
         
         tfUserName = new JTextField();
         tfUserName.setBorder(null);
         tfUserName.setForeground(new Color(255, 255, 255));
         tfUserName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
         tfUserName.setBackground(new Color(0, 0, 0));
         tfUserName.setBounds(1057, 54, 119, 21);
         frame.getContentPane().add(tfUserName);
         tfUserName.setColumns(10);
         tfUserName.setEnabled(false);
         
         JButton btn_wcp = new JButton("일손 교류 관리로 이동");
         btn_wcp.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		String id = tfUserName.getText();
         		WorkConnectionManagerView WCMV = new WorkConnectionManagerView();
			    WCMV.setVisible(true);
			    WCMV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				WCMV.tfManageId.setText(id);
				 JOptionPane.showMessageDialog(null, "일손관리프로그램접속");
         		
         		
         	}
         });
         btn_wcp.setForeground(new Color(255, 255, 255));
         btn_wcp.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
         btn_wcp.setIcon(new ImageIcon(EducationManagerView.class.getResource("/img/버튼배경.PNG")));
         btn_wcp.setBounds(619, 813, 557, 21);
         frame.getContentPane().add(btn_wcp);
         btn_wcp.setBorder(null);
         btn_wcp.setHorizontalTextPosition(SwingConstants.CENTER);
         ebModel.data = list;
      } catch (Exception e) {
         // TODO: handle exception
         JOptionPane.showMessageDialog(null, "정보 열람 실패 : " + e.getMessage());
      }

   }
   

   protected void selectTable() {
      // TODO Auto-generated method stub

   }

   void eventProc() {
      // TODO Auto-generated method stub
      btnSearch.addActionListener(this);

      EducationList.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            int row = EducationList.getSelectedRow();
            int col = 0;
            int eb_num = (Integer) EducationList.getValueAt(row, col);
            System.out.println(eb_num);
            EducationManagerVO vo = new EducationManagerVO();

            try {
               System.out.println("검색성공");
               vo = dao.findByNum(eb_num);
            } catch (Exception e2) {
               JOptionPane.showMessageDialog(null, "검색 실패" + e2.getMessage());
            }
            tfebName.setText(vo.getName());
            tfebStartDate.setText(vo.getStartDate());
            tfebEndDate.setText(vo.getEndDate());
            tfebContent.setText(vo.getContent());
            tfebTarget.setText(vo.getTarget());
            tfebLocation.setText(String.valueOf(vo.getLocation()));
            tfebParticipants.setText(String.valueOf(vo.getParticipants()));
         }

      });
   }

   private void clearScreen() {
      tfebName.setText("");
      tfebStartDate.setText("");
      tfebEndDate.setText("");
      tfebContent.setText("");
      tfebLocation.setText("");
      tfebTarget.setText("");
      tfebParticipants.setText("");

   }

//   public void EducationManagerView() {
//      newObject();
//      addLayout();
//      setStyle();
//      eventProc();
//      try {
//         dao = new EducationManagerDAO();
//         System.out.println("비디오 디비 연결 성공");
//      } catch (Exception e) {
//         // TODO: handle exception
//         JOptionPane.showMessageDialog(null, "비디오 DB연결 실패 : " + e.getMessage());
//      }
//
//      JButton ebDelete = new JButton("삭제하기");
//      ebDelete.addActionListener(new ActionListener() {
//
//         public void actionPerformed(ActionEvent e) {
//            Object o = e.getSource();
//            if (o == ebDelete) {
//               int num = Integer.parseInt(tfebName.getText());
//
//               try {
//
//                  dao.ebDelete(num);
//                  System.out.println("삭제 성공");
//
//               } catch (Exception e1) {
//                  // TODO: handle exception
//                  System.out.println("삭제 실패" + e1.getMessage());
//               }
//            }
//
//         }
//      });
//
//      ebDelete.setBounds(1291, 428, 140, 54);
//      frame.getContentPane().add(ebDelete);
//
//      JPanel panel_3 = new JPanel();
//      panel_3.setBounds(785, 536, 650, 338);
//      frame.getContentPane().add(panel_3);
//   }

   private void newObject() {
      // TODO Auto-generated method stub

   }

   private void addLayout() {
      // TODO Auto-generated method stub

   }

   private void setStyle() {
      // TODO Auto-generated method stub

   }

   public JTextField getTfebTarget() {
      return tfebTarget;
   }

   public void setTfebTarget(JTextField tfebTarget) {
      this.tfebTarget = tfebTarget;
   }

   class EducationBusinessModel extends AbstractTableModel {

      ArrayList data = new ArrayList();
      String[] columnNames = { "순번", "교육 이름", "교육시작날짜", "교육종료날짜", "교육내용", "지역번호", "교육대상", "참가인원" };

      // =============================================================
      // 1. 기본적인 TabelModel 만들기
      // 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
      // AbstractTabelModel에서 구현되지 않았기에...
      // 반드시 사용자 구현 필수!!!!

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

      public String getColumnName(int col) {
         return columnNames[col];
      }

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub

   }
}