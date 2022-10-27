package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import buttonLib.ButtonRound;
import model.RealeStateDAO;
import model.rec.RealeStateVO;

import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class RealeStateView extends JFrame implements ActionListener{


	private JTextField textField;
	private JTable table;
	private RealeStateTableModel rsTableModel;
	private RealeStateDAO dao=null;
	private ReservationView view;
	private ButtonRound btnNewButton;
	public JTextField tf_id;
	public static int number;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	


	/**
	 * Create the application.
	 */
	public RealeStateView() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBackground(new Color(255, 255, 255));
		setBounds(500, 500, 700, 600);
		getContentPane().setLayout(null);
		setVisible(true);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setBounds(93, 70, 95, 25);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"매물번호", "지역"}));
		comboBox.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		comboBox.setBackground(new Color(255,255,255));
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(193, 72, 305, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new ButtonRound("검색");
		btnNewButton.setBounds(503, 70, 81, 25);
		btnNewButton.setBackground(new Color(59, 89, 182));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		getContentPane().add(btnNewButton);
		
		comboBox.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			String toolTip = (String)comboBox.getSelectedItem();
			textField.setText(toolTip+"");
		}
		});
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String comboText = (String) comboBox.getSelectedItem();
				String findText = textField.getText();
				if(comboText.equals("매물번호")&&!findText.equals("")) {
					String pattern = "^[0-9]*$";
					boolean result = Pattern.matches(pattern, findText);
					if(result) {
						int num = Integer.parseInt(findText);
						dao = new RealeStateDAO();
						ArrayList list = new ArrayList();
						list = dao.findByNum(num);
						rsTableModel = new RealeStateTableModel();
						rsTableModel.data = list;
						table.setModel(rsTableModel);
						rsTableModel.fireTableDataChanged();
						System.out.println(list);
						System.out.println(findText);
						textField.setText("");
					}else{
						JOptionPane.showMessageDialog(null, "형식에 맞지 않습니다.");
					}
				}
				if(comboText.equals("지역")&&!findText.equals("")) {
					String pattern = "^[ㄱ-ㅎㅏ-ㅣ가-힣]*[0-9]*[\t\n\f\r]*[ㄱ-ㅎㅏ-ㅣ가-힣]*$";
					boolean result = Pattern.matches(pattern, findText);
					if(result) {
						System.out.println(findText);
						System.out.println("참");
						dao = new RealeStateDAO();
						ArrayList list = new ArrayList<>();
						rsTableModel = new RealeStateTableModel();
						list=dao.findBydong(findText);
						rsTableModel.data = list;
						table.setModel(rsTableModel);
						rsTableModel.fireTableDataChanged();
						System.out.println(list);
						textField.setText("");
					}else{
						System.out.println(result);
						JOptionPane.showMessageDialog(null, "형식에 맞지 않습니다.");
						textField.setText("");
					}
				}
				
			}
			});


//String pattern = "^[0-9]*$"; // 숫자만 등장하는지
//String str = "123321"; 
//value.matches("^[ㄱ-ㅎㅏ-ㅣ가-힣]*[*0-9*]*[\t\n\f\r]*[ㄱ-ㅎㅏ-ㅣ가-힣]*$")//문자 판단
//boolean result = Pattern.matches(pattern, str);
//System.out.println(result); // true
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(93, 111, 491, 360);
		getContentPane().add(scrollPane);
		
		
		
		rsTableModel = new RealeStateTableModel();
		table = new JTable(rsTableModel);
		scrollPane.setViewportView(table);
		
		tf_id = new JTextField();
		tf_id.setBounds(503, 22, 87, 21);
		getContentPane().add(tf_id);
		tf_id.setColumns(10);
		tf_id.setEnabled(false);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RealeStateView.class.getResource("/img/[크기변환]여어어름.gif")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 695, 572);
		getContentPane().add(lblNewLabel);
		dao = new RealeStateDAO();
		ArrayList list = new ArrayList<>();
		list = dao.obSelect();
		rsTableModel.data = list;
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = tf_id.getText();
				int row = table.getSelectedRow();
				int col = 0;
				int data = (int)table.getValueAt(row, col);
				RealeStateVO vo = dao.selected(data);
				System.out.println(data);
//				컬럼 갖고오기.
				number = data;
				System.out.println("Table Clicked");
				
				ReservationView rv=new ReservationView(vo);
				
				rv.tf_id.setText(id);
				
				
					
			}
		});
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
	}

	}

	class RealeStateTableModel extends AbstractTableModel {
		
		ArrayList data = new ArrayList();
		String[] columnNames = {"매물번호","읍면동","매물분야명","부동산이름","매물가격","매물면적"};
		
	

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

