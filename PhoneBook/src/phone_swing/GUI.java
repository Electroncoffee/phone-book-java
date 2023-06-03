package phone_swing;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class GUI {
	//메인프레임, 텍스트필드, 테이블모델, ArrayList 생성
	private JFrame frame;
	private JTextField add_name_field;
	private JTextField add_pnum_field;
	private JTextField search_name_field;
	private JTextField edit_name_field;
	private JTextField edit_pnum_field;
	private DefaultTableModel tablemodel;

	/*Launch the application.*/
	public static void main(String[] args) {
		//EDT를 통해 GUI작업을 처리
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*Create the application.*/
	public GUI() {
		//ArrayList, 테이블 모델 초기화
		tablemodel= new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 편집 불가능하게 설정
            }
        };
        //테이블모델 열 추가
		tablemodel.addColumn("이름");
        tablemodel.addColumn("전화번호");
        //파일 입출력 설정
        if(Data_IO.checkDataFormat()) Data_in(Data_IO.readData());
        //스윙 실행
		initialize();
	}

	/* Initialize the contents of the frame.*/
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 573, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("전화번호부 관리 프로그램");
		//--하단부--//
		//카드레이아웃 생성
		CardLayout cardLayout = new CardLayout();
		//하단부 패널 생성, 카드레이아웃으로 설정, 
		JPanel bottom = new JPanel();
		bottom.setBounds(10, 120, 540, 320);
		bottom.setLayout(cardLayout);
		//프레임에 하단부 패널 추가
		frame.getContentPane().add(bottom);
		//회원추가 하단부//
		GUI_add(bottom);
		//회원수정 하단부//
		GUI_edit(bottom);
		//회원삭제 하단부//
		GUI_del(bottom);
		//회원검색 하단부//
		GUI_search(bottom);
		//회원출력 하단부//
		GUI_prn(bottom);
		
		//--상단부--//
		//회원추가 버튼, 이벤트 설정, 프레임에 추가//
		JButton M_add_button = new JButton("회원 추가");
		//회원추가 버튼 이벤트//
		M_add_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(bottom, "M_add");
            }
        });
		M_add_button.setBounds(10, 10, 100, 45);
		frame.getContentPane().add(M_add_button);
		//회원수정 버튼, 이벤트 설정, 프레임에 추가//
		JButton M_edit_button = new JButton("회원 수정");
		M_edit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(bottom, "M_edit");
            }
        });
		M_edit_button.setBounds(120, 10, 100, 45);
		frame.getContentPane().add(M_edit_button);
		//회원삭제 버튼, 이벤트 설정, 프레임에 추가//
		JButton M_del_button = new JButton("회원 삭제");
		M_del_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(bottom, "M_del");
            }
        });
		M_del_button.setBounds(230, 10, 100, 45);
		frame.getContentPane().add(M_del_button);
		//회원검색 버튼, 이벤트 설정, 프레임에 추가//
		JButton M_search_button = new JButton("회원 검색");
		M_search_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(bottom, "M_search");
            }
        });
		M_search_button.setBounds(340, 10, 100, 45);
		frame.getContentPane().add(M_search_button);
		//회원출력 버튼, 이벤트 설정, 프레임에 추가//
		JButton M_prn_button = new JButton("회원 출력");
		M_prn_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(bottom, "M_prn");
            }
        });
		M_prn_button.setBounds(450, 10, 100, 45);
		frame.getContentPane().add(M_prn_button);
		//도움말 버튼//
		JButton help_button = new JButton("HELP");
		help_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame help = new JFrame();
				help.setBounds(100, 100, 500, 400);
				help.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				help.getContentPane().setLayout(null);
				help.setVisible(true);
				help.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 새 창의 닫기 동작 설정
				
				JButton Help_exit_button = new JButton("종료");
				Help_exit_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						help.dispose();
						}
					});
				Help_exit_button.setBounds(385, 332, 93, 23);
				help.getContentPane().add(Help_exit_button);
				
				JLabel Help_label = new JLabel("사용설명서");
				Help_label.setFont(new Font("굴림", Font.BOLD, 15));
				Help_label.setHorizontalAlignment(SwingConstants.CENTER);
				Help_label.setBounds(10, 10, 468, 42);
				help.getContentPane().add(Help_label);
				
				JScrollPane Help_scrollPane = new JScrollPane();
				Help_scrollPane.setBounds(10, 50, 468, 272);
				help.getContentPane().add(Help_scrollPane);
				
				JLabel Help_maintext_label = new JLabel("<html>[회원추가]<br/>&nbsp;"
						+ "1. 이름과 전화번호를 입력한 후 버튼을 누르세요.<br/>&nbsp;"
						+ "2. 이름은 필수입력입니다.<br/>&nbsp;"
						+ "3. 전화번호는 입력하지 않아도 되며 숫자만 입력가능합니다.<br/><br/>"
						+ "[회원수정]<br/>&nbsp;"
						+ "1. 수정하고 싶은 행을 클릭합니다.<br/>&nbsp;"
						+ "2. 이름과 전화번호를 입력합니다.<br/>&nbsp;"
						+ "3. 수정버튼을 누릅니다.<br/>&nbsp;"
						+ "4. 이름은 필수입력, 전화번호는 숫자만 가능합니다.<br/><br/>"
						+ "[회원삭제]<br/>&nbsp;"
						+ "1. 삭제하고 싶은 행을 클릭합니다.<br/>&nbsp;"
						+ "2. 삭제 실행버튼을 누릅니다.<br/><br/>"
						+ "[회원검색]<br/>&nbsp;"
						+ "1. 검색할 회원의 이름을 입력합니다.<br/>&nbsp;"
						+ "2. 검색버튼을 누릅니다.<br/><br/>"
						+ "[회원출력]<br/>&nbsp;"
						+ "1. 모든 회원의 정보를 출력합니다.<br/><br/>"
						+ "[HELP]<br/>&nbsp;"
						+ "1. 본 프로그램의 사용법을 알려줍니다.<br/><br/>"
						+ "[종료]<br/>&nbsp;"
						+ "1. 본 프로그램을 종료합니다.</html>");
				Help_scrollPane.setViewportView(Help_maintext_label);
			}
		});
		help_button.setBounds(340, 65, 100, 45);
		frame.getContentPane().add(help_button);
		//종료 버튼//
		JButton Exit = new JButton("종료");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data_out();
				System.exit(0); // 프로그램 종료
				}
			});
		Exit.setBounds(450, 65, 100, 45);
		frame.getContentPane().add(Exit);
	}
	//--각 기능별 함수--//
	//회원추가 기능//
	private void GUI_add(JPanel bottom) {
		//패널 생성 후 하단부에 추가//
		JPanel M_add_panel = new JPanel();
		M_add_panel.setBounds(10, 120, 540, 320);
		bottom.add(M_add_panel,"M_add");
		M_add_panel.setLayout(null);
		//회원추가 하단부 라벨//
		JLabel M_add_label = new JLabel("회원 추가");
		M_add_label.setHorizontalAlignment(SwingConstants.CENTER);
		M_add_label.setFont(new Font("굴림", Font.PLAIN, 16));
		M_add_label.setBounds(10, 10, 520, 40);
		M_add_panel.add(M_add_label);
		//회원추가 이름패널(라벨,텍스트필드)//
		JPanel name_panel = new JPanel();
		name_panel.setLayout(null);
		name_panel.setBorder(new LineBorder(Color.BLACK));
		name_panel.setBounds(10, 60, 520, 40);
		M_add_panel.add(name_panel);
		//이름패널 이름라벨//
		JLabel name_label = new JLabel("이름");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		name_label.setBounds(0, 0, 70, 40);
		name_panel.add(name_label);
		//이름패널 이름텍스트필드//
		add_name_field = new JTextField();
		add_name_field.setColumns(10);
		add_name_field.setBounds(80, 1, 430, 38);
		name_panel.add(add_name_field);
		//회원추가 전화번호패널//
		JPanel pnum_panel = new JPanel();
		pnum_panel.setLayout(null);
		pnum_panel.setBorder(new LineBorder(Color.BLACK));
		pnum_panel.setBounds(10, 130, 520, 40);
		M_add_panel.add(pnum_panel);
		//전화번호패널 전화번호라벨//
		JLabel pnum_label = new JLabel("전화번호");
		pnum_label.setHorizontalAlignment(SwingConstants.CENTER);
		pnum_label.setBounds(0, 0, 70, 40);
		pnum_panel.add(pnum_label);
		//전화번호패널 전화번호텍스트필드//
		add_pnum_field = new JTextField();
		add_pnum_field.setColumns(10);
		add_pnum_field.setBounds(80, 1, 430, 38);
		//전화번호패널 전화번호텍스트필드 이벤트(숫자외 무시)//
		add_pnum_field.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c)) { // 입력된 값이 숫자가 아니면 입력을 무시
		            e.consume();
		        }
		    }
		});
		pnum_panel.add(add_pnum_field);
		//회원추가 버튼//
		JButton add_button = new JButton("입력");
		//회원추가 버튼 이벤트(추가 실행)//
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=add_name_field.getText();
				String pnum=add_pnum_field.getText();
				if(name.isEmpty()) {//이름값이 비어있다면 종료
					JOptionPane.showMessageDialog(null, "이름은 필수입력입니다.");
					return;
				}
				Object[] rowData = {name, pnum};
		        tablemodel.addRow(rowData);
		        JOptionPane.showMessageDialog(null, "추가되었습니다.");
		        add_name_field.setText("");
		        add_pnum_field.setText("");
		        Data_out();
			}
		});
		add_button.setBounds(437, 287, 93, 23);
		M_add_panel.add(add_button);
	}
	//회원수정 기능//
	private void GUI_edit(JPanel bottom) {
		//패널 생성 후 하단부에 추가//
		JPanel M_edit_panel = new JPanel();
		M_edit_panel.setBounds(10, 120, 540, 320);
		bottom.add(M_edit_panel,"M_edit");
		M_edit_panel.setLayout(null);
		//회원수정 하단부 스크롤패널//
		JScrollPane M_edit_scrollPane = new JScrollPane();
		M_edit_scrollPane.setBounds(10, 158, 520, 152);
		M_edit_panel.add(M_edit_scrollPane);
		//회원수정 하단부 테이블//
		JTable M_edit_table = new JTable(tablemodel);
		M_edit_table.setBorder(new LineBorder(Color.BLACK));
		M_edit_table.getTableHeader().setReorderingAllowed(false);
		M_edit_table.getTableHeader().setResizingAllowed(false);
		M_edit_scrollPane.setViewportView(M_edit_table);
		//회원수정 하단부 라벨//
		JLabel M_edit_label = new JLabel("회원 수정");
		M_edit_label.setHorizontalAlignment(SwingConstants.CENTER);
		M_edit_label.setFont(new Font("굴림", Font.PLAIN, 16));
		M_edit_label.setBounds(10, 10, 520, 40);
		M_edit_panel.add(M_edit_label);
		//회원수정 이름 패널//
		JPanel edit_name_panel = new JPanel();
		edit_name_panel.setLayout(null);
		edit_name_panel.setBorder(new LineBorder(Color.BLACK));
		edit_name_panel.setBounds(10, 60, 452, 40);
		M_edit_panel.add(edit_name_panel);
		//회원수정 이름 라벨//
		JLabel edit_name_label = new JLabel("이름");
		edit_name_label.setHorizontalAlignment(SwingConstants.CENTER);
		edit_name_label.setBounds(0, 0, 70, 40);
		edit_name_panel.add(edit_name_label);
		//회원수정 이름 텍스트필드//
		edit_name_field = new JTextField();
		edit_name_field.setColumns(10);
		edit_name_field.setBounds(80, 1, 370, 38);
		edit_name_panel.add(edit_name_field);
		//회원수정 전화번호 패널//
		JPanel edit_pnum_panel = new JPanel();
		edit_pnum_panel.setLayout(null);
		edit_pnum_panel.setBorder(new LineBorder(Color.BLACK));
		edit_pnum_panel.setBounds(10, 110, 452, 40);
		M_edit_panel.add(edit_pnum_panel);
		//회원수정 전화번호 라벨//
		JLabel edit_pnum_label = new JLabel("전화번호");
		edit_pnum_label.setHorizontalAlignment(SwingConstants.CENTER);
		edit_pnum_label.setBounds(0, 0, 70, 40);
		edit_pnum_panel.add(edit_pnum_label);
		//회원수정 전화번호 필드//
		edit_pnum_field = new JTextField();
		edit_pnum_field.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c)) { // 입력된 값이 숫자가 아니면 입력을 무시
		            e.consume();
		        }
		    }
		});
		edit_pnum_field.setColumns(10);
		edit_pnum_field.setBounds(80, 1, 370, 38);
		edit_pnum_panel.add(edit_pnum_field);
		//회원수정 버튼//
		JButton edit_button = new JButton("수정");
		edit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 선택한 행의 데이터 수정
                int selectedRow = M_edit_table.getSelectedRow();
                if (selectedRow != -1) {
                    String name = edit_name_field.getText();
                    String phone = edit_pnum_field.getText();
                    if(name.isEmpty()) {
                    	JOptionPane.showMessageDialog(null, "이름은 필수입력입니다.");
                    	return;
                    }
                    M_edit_table.setValueAt(name, selectedRow, 0);
                    M_edit_table.setValueAt(phone, selectedRow, 1);
                    JOptionPane.showMessageDialog(null, "수정되었습니다.");
                    edit_name_field.setText("");
                    edit_pnum_field.setText("");
                    M_edit_table.clearSelection();
                    Data_out();
                }else {
                	JOptionPane.showMessageDialog(null, "테이블에서 수정을 원하는 행을 선택해주세요.");
                	return;
                }
            }
        });
		edit_button.setFont(new Font("굴림", Font.PLAIN, 11));
		edit_button.setBounds(472, 60, 58, 88);
		M_edit_panel.add(edit_button);
	}
	//회원삭제 기능//
	private void GUI_del(JPanel bottom) {
		//패널 생성 후 하단부에 추가//
		JPanel M_del_panel_bottom = new JPanel();
		M_del_panel_bottom.setBounds(10, 120, 540, 320);
		bottom.add(M_del_panel_bottom,"M_del");
		M_del_panel_bottom.setLayout(null);
		//회원삭제 하단부 스크롤패널//
		JScrollPane M_del_scrollPane = new JScrollPane();
		M_del_scrollPane.setBounds(10, 60, 520, 220);
		M_del_panel_bottom.add(M_del_scrollPane);
		//회원삭제 하단부 라벨//
		JLabel del_label = new JLabel("회원 삭제");
		del_label.setHorizontalAlignment(SwingConstants.CENTER);
		del_label.setFont(new Font("굴림", Font.PLAIN, 16));
		del_label.setBounds(10, 10, 520, 40);
		M_del_panel_bottom.add(del_label);
		//회원삭제 하단부 테이블//
		JTable M_del_table = new JTable(tablemodel);
		M_del_table.setBorder(new LineBorder(Color.BLACK));
		M_del_table.getTableHeader().setReorderingAllowed(false); //열재정렬 false
		M_del_table.getTableHeader().setResizingAllowed(false); //열조정 false
		M_del_scrollPane.setViewportView(M_del_table);
		//회원삭제 하단부 버튼//
		JButton del_button = new JButton("삭제 실행");
		//회원삭제 버튼 이벤트//
		del_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = M_del_table.getSelectedRow();
                if (selectedRow != -1) {
                	tablemodel.removeRow(selectedRow);
                	JOptionPane.showMessageDialog(null, "삭제되었습니다.");
                	M_del_table.clearSelection();
                	Data_out();
                }else {
                	JOptionPane.showMessageDialog(null, "테이블에서 삭제를 원하는 행을 선택해주세요.");
                }
            }
        });
		del_button.setBounds(437, 287, 93, 23);
		M_del_panel_bottom.add(del_button);
	}
	//회원 검색 기능//
	private void GUI_search(JPanel bottom) {
		//패널 생성 후 하단부에 추가//
		JPanel M_search_panel_bottom = new JPanel();
		M_search_panel_bottom.setBounds(10, 120, 540, 320);
		bottom.add(M_search_panel_bottom,"M_search");
		M_search_panel_bottom.setLayout(null);
		//회원검색 하단부 스크롤패널//
		JScrollPane M_search_scrollPane = new JScrollPane();
		M_search_scrollPane.setBounds(10, 110, 520, 200);
		M_search_panel_bottom.add(M_search_scrollPane);
		//회원검색 하단부 라벨//
		JLabel M_search_label = new JLabel("회원 검색");
		M_search_label.setHorizontalAlignment(SwingConstants.CENTER);
		M_search_label.setFont(new Font("굴림", Font.PLAIN, 16));
		M_search_label.setBounds(10, 10, 520, 40);
		M_search_panel_bottom.add(M_search_label);
		//회원검색 하단부 테이블//
		JTable M_search_table = new JTable(tablemodel);
		M_search_table.setEnabled(false);
		M_search_table.getTableHeader().setReorderingAllowed(false);
		M_search_table.getTableHeader().setResizingAllowed(false);
		M_search_table.setBorder(new LineBorder(Color.BLACK));
		M_search_scrollPane.setViewportView(M_search_table);
		//회원검색 하단부 이름 패널//
		JPanel search_name_panel = new JPanel();
		search_name_panel.setBorder(new LineBorder(Color.BLACK));
		search_name_panel.setBounds(10, 60, 452, 40);
		M_search_panel_bottom.add(search_name_panel);
		search_name_panel.setLayout(null);
		//회원검색 하단부 이름 라벨//
		JLabel search_name_label = new JLabel("이름");
		search_name_label.setHorizontalAlignment(SwingConstants.CENTER);
		search_name_label.setBounds(0, 0, 70, 40);
		search_name_panel.add(search_name_label);
		//회원검색 하단부 이름 필드//
		search_name_field = new JTextField();
		search_name_field.setBounds(80, 1, 370, 38);
		search_name_panel.add(search_name_field);
		search_name_field.setColumns(10);
		//회원검색 하단부 검색 버튼//
		JButton search_button = new JButton("검색");
		search_button.setFont(new Font("굴림", Font.PLAIN, 11));
		search_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String filterText = search_name_field.getText();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tablemodel);
                M_search_table.setRowSorter(sorter);
                sorter.setRowFilter(RowFilter.regexFilter(filterText, 0));
            }
        });
		search_button.setBounds(472, 60, 58, 40);
		M_search_panel_bottom.add(search_button);
	}
	//회원 출력 기능//
	private void GUI_prn(JPanel bottom) {
		//패널 생성 후 하단부에 추가//
		JPanel M_prn_panel_bottom = new JPanel();
		M_prn_panel_bottom.setBounds(10, 120, 540, 320);
		bottom.add(M_prn_panel_bottom,"M_prn");
		M_prn_panel_bottom.setLayout(null);
		//회원출력 하단부 스크롤패널//
		JScrollPane M_prn_scrollPane = new JScrollPane();
		M_prn_scrollPane.setBounds(10, 60, 520, 250);
		M_prn_panel_bottom.add(M_prn_scrollPane);
		//회원출력 하단부 라벨//
		JLabel prn_label = new JLabel("회원 출력");
		prn_label.setHorizontalAlignment(SwingConstants.CENTER);
		prn_label.setFont(new Font("굴림", Font.PLAIN, 16));
		prn_label.setBounds(10, 10, 520, 40);
		M_prn_panel_bottom.add(prn_label);
		//회원출력 하단부 테이블//
		JTable M_prn_table = new JTable(tablemodel);
		M_prn_table.setEnabled(false);
		M_prn_table.getTableHeader().setReorderingAllowed(false);
		M_prn_table.getTableHeader().setResizingAllowed(false);
		M_prn_table.setBorder(new LineBorder(Color.BLACK));
		M_prn_scrollPane.setViewportView(M_prn_table);
	}
	//데이터 형식 변환//
	private String [][] Data_change() {
		int rowcount = tablemodel.getRowCount();
		int colcount = tablemodel.getColumnCount();
		String[][] Data= new String[rowcount][colcount];
		for (int row = 0; row < rowcount; row++) {
		    for (int col = 0; col < colcount; col++) {
		    	Data[row][col] = tablemodel.getValueAt(row, col).toString();
		    }
		}
		return Data;
	}
	//파일 출력//
	private void Data_in(String[][] Data) {
		for (String[] row : Data) {
			tablemodel.addRow(row);
		}
	}
	//파일 입력//
	private void Data_out() {
		Data_IO.writeData(Data_change());
	}
}