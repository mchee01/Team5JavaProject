package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import model.JoinDAO;
import model.rec.LoginVO;
import view.EducationManagerView;
import view.EducationUserView;
import view.IDSearchView;
import view.JoinManageView;
import view.JoinView;

import view.PasswordSearchView;
import view.UserView;
import view.WorkConnectionManagerView;
import view.WorkConnectionUserView;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Panel;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

public class LoginFarm extends JFrame {
   private JTextField textField_ID;
   private JButton btnPwSearch, btnJoin, btnId, btn_login;
   public JFrame frame;
   
   LoginVO vo;
   JoinDAO dao;
   private JComboBox cb_position;
   private JLabel lblNewLabel;
   private JPasswordField textField_PW;
    
   /**
    * Launch the application.
    */
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               LoginFarm frame = new LoginFarm();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public LoginFarm() {
      
      
      textField_ID = new JTextField(20);
      btn_login = new JButton("");
      btn_login.setBorder(null);
      btn_login.setIcon(new ImageIcon(LoginFarm.class.getResource("/img/[크기변환]로그인이미지.PNG")));
      btn_login.setHorizontalTextPosition(SwingConstants.CENTER);
      
      textField_ID = new JTextField(20);
      textField_ID.setHorizontalAlignment(SwingConstants.CENTER);
      
      
      
      try {
           dao = new JoinDAO();
           System.out.println("고객디비 연결 성공");
         
      } catch (Exception e) {
         // TODO: handle exception
         JOptionPane.showConfirmDialog(null, "고객 DB 연결 실패 : "+ e.getMessage());
      }
      
      
      getContentPane().setBackground(new Color(194, 224, 200));
      setTitle("Back Farm");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 641, 1027);
      getContentPane().setLayout(null);
      
      textField_PW = new JPasswordField();
      textField_PW.setHorizontalAlignment(SwingConstants.CENTER);
      textField_PW.setBorder(null);
      textField_PW.setBounds(135, 680, 287, 30);
      getContentPane().add(textField_PW);
      
      
      textField_ID.setBounds(135, 616, 287, 30);
      getContentPane().add(textField_ID);
      
      
      btn_login.setFont(new Font("궁서체", Font.BOLD | Font.ITALIC, 17));
      btn_login.setBackground(new Color(255, 250, 240));
      btn_login.setForeground(new Color(0, 0, 0));
      btn_login.setBounds(434, 616, 124, 94);
      getContentPane().add(btn_login);
      
      ButtonGroup btgroup = new ButtonGroup();
      
      String [] admin = {"사용자", "관리자"};
      cb_position = new JComboBox(admin);
      cb_position.setBorder(new LineBorder(new Color(0, 0, 0)));
      cb_position.setForeground(new Color(82, 81, 97));
      cb_position.setBackground(new Color(82, 81, 97));
      cb_position.setModel(new DefaultComboBoxModel(new String[] {"사용자", "산업자", "관리자"}));
      cb_position.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
      cb_position.setBounds(223, 762, 145, 66);
      getContentPane().add(cb_position);
      btnId = new JButton("ID 찾기");
      btnId.setIcon(new ImageIcon(LoginFarm.class.getResource("/img/ip회.PNG")));
      btnId.setHorizontalTextPosition(SwingConstants.CENTER);
      btnId.setForeground(new Color(255, 255, 255));
      btnId.setBorder(null);
      btnId.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      btnId.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
      
      
      btnId.setBackground(new Color(255, 255, 255));
      btnId.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob == btnId) {
            IDSearchView id = new IDSearchView();
            id.setVisible(true);
            }
         }
         
      });
      btnId.setBounds(76, 867, 124, 49);
      getContentPane().add(btnId);
      btnPwSearch = new JButton("PW 찾기");
      btnPwSearch.setBorder(null);
      btnPwSearch.setHorizontalTextPosition(SwingConstants.CENTER);
      btnPwSearch.setForeground(new Color(255, 250, 250));
      btnPwSearch.setIcon(new ImageIcon(LoginFarm.class.getResource("/img/ip회.PNG")));
      btnPwSearch.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
      
      
      btnPwSearch.setBackground(new Color(255, 250, 240));
      btnPwSearch.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob == btnPwSearch) {
            PasswordSearchView pw = new PasswordSearchView();
            pw.setVisible(true);
            }
         }
      });
      btnPwSearch.setBounds(244, 869, 124, 44);
      getContentPane().add(btnPwSearch);
      btnJoin = new JButton("회원가입");
      btnJoin.setHorizontalTextPosition(SwingConstants.CENTER);
      btnJoin.setIcon(new ImageIcon(LoginFarm.class.getResource("/img/ip회.PNG")));
      btnJoin.setForeground(new Color(255, 250, 250));
      btnJoin.setBorder(null);
      btnJoin.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
      btnJoin.setBackground(new Color(255, 250, 240));
      
      
      btnJoin.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            int num = 0;
            LoginVO vo = new LoginVO();
            try {
            JoinDAO jdao = new JoinDAO();
            String combo = (String) cb_position.getSelectedItem();
            num = jdao.selectNum(combo);
            System.out.println(combo);
            if(ob == btnJoin && num == 0) {
            JoinView join = new JoinView();
            join.setVisible(true);
            }
            else if(ob == btnJoin && num == 1) {
               JoinManageView jmv = new JoinManageView();
               jmv.setVisible(true);
            }
            else if(ob == btnJoin && num ==2) {
               JoinView join = new JoinView();
               join.setVisible(true);
            }
            }
          catch (Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      });
      btnJoin.setBounds(408, 869, 133, 44);
      getContentPane().add(btnJoin);
      
      lblNewLabel = new JLabel("");
      lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
      lblNewLabel.setIcon(new ImageIcon(LoginFarm.class.getResource("/img/최종 로그인.jpg")));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(-51, -61, 743, 1104);
      getContentPane().add(lblNewLabel);
      
      btn_login.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
//             Object ob = e.getSource();
            LoginVO vo = new LoginVO();
            try {
               int num;
            JoinDAO jdao = new JoinDAO();
            String id = textField_ID.getText();
            String pwd = textField_PW.getText();
            String combo = (String) cb_position.getSelectedItem();
            System.out.println(combo);
            num = jdao.selectNum(combo);
            System.out.println(num);
         
              if(id != null && pwd != null){
                 if (num == 0) {
                 if(dao.login(id,pwd,num)) {
//                    WorkConnectionUserView WCUV = new WorkConnectionUserView();
//                    WCUV.setVisible(true);
//                    WCUV.tfUserName.setText(id);
                    
                  UserView userPage = new UserView();
                  userPage.setVisible(true);
                  userPage.tF_id.setText(id);
//                    EducationUserView ebv = new EducationUserView();
//                  ebv.frame.setVisible(true);
//                     ebv.tfUserName.setText(id);
                  dispose();
                     }
                  }else if(num == 1) {
                     if(dao.managelogin(id, pwd)) {
                       //민지 관리자 뷰
//                        WorkConnectionManagerView WCMV = new WorkConnectionManagerView();
//                        WCMV.setVisible(true);
//                        WCMV.tfManageId.setText(id);
//                     ManagerView mv = new ManagerView();
//                     mv.setVisible(true);
//                     mv.tF_manager.setText(id);
                        
                        EducationManagerView emv = new EducationManagerView();
                        emv.frame.setVisible(true);
                        emv.tfUserName.setText(id);
                        
                     dispose();
                     }
                  
                  }else if(num == 2) {
                     if(dao.login(id, pwd, num)) {
                        UserView userPage = new UserView();
                        userPage.setVisible(true);
                        userPage.tF_id.setText(id);
                        dispose();
                     }
                  }
               }if(textField_ID.getText().trim().length()==0) {
                   JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
                   
               }
               else if(textField_PW.getText().trim().length()==0) {
                 JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
               }
               
//               
               
//              pwd!=vo.getPwd()
//              if(pwd!=vo.getPwd()) {
//                 JOptionPane.showMessageDialog(null, "잘못입력하였습니다.");
//              }
//              if(vo.getPosition()==0) {
//                 
//              }else {
//                 
//              }
              
               
            } catch (Exception e2) {
               // TODO: handle exception
               
               JOptionPane.showMessageDialog(null, e2.getMessage());
            }
            
            }
            
         
      });
   }
   public void clearScreen() {
      textField_ID.setText("");
      textField_PW.setText("");
      cb_position.setSelectedItem("사용자");
   }
}