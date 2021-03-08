package Kundenverwaltung;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.TextArea;
import javax.swing.JLabel;

public class MainMenu {

	private JFrame frmKundenverwaltung;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frmKundenverwaltung.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		SQLiteDatabase db = new SQLiteDatabase("test.db");
		System.out.println("Datenbank geladen");
		
		String data = SQLiteDatabase.getClientsFromDatabase();
		
		frmKundenverwaltung = new JFrame();
		frmKundenverwaltung.setTitle("Kundenverwaltung");
		frmKundenverwaltung.setBounds(100, 100, 450, 300);
		frmKundenverwaltung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmKundenverwaltung.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Close Program");
		mntmNewMenuItem.addActionListener(new exitApp());
		
		mnNewMenu.add(mntmNewMenuItem);
		
		JLabel lblNewLabel = new JLabel(data);
		frmKundenverwaltung.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
	}
	
	//exit app
	static class exitApp implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

}
