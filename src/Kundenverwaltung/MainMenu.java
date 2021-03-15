package Kundenverwaltung;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.Button;

public class MainMenu {

	private JFrame frmKundenverwaltung;
	private static JTable table_clients;
	private static ArrayList<Client> data;
	
	static SQLiteDatabase db = new SQLiteDatabase("test.db");
	

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
//		SQLiteDatabase db = new SQLiteDatabase("test.db");
		System.out.println("Datenbank geladen");

		db.getClientsFromDatabase();

//		db.insertClientToDatabase("Nils", "Wegner", "Musterstraﬂe 1", "Musterstadt", "12345", "12345/6789");
//
//		db.getClientsFromDatabase();

		data = SQLiteDatabase.getClientsFromDatabase();

		frmKundenverwaltung = new JFrame();
		frmKundenverwaltung.setTitle("Kundenverwaltung");
		frmKundenverwaltung.setBounds(100, 100, 943, 573);
		frmKundenverwaltung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmKundenverwaltung.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Close Program");
		mntmNewMenuItem.addActionListener(new exitApp());

		mnNewMenu.add(mntmNewMenuItem);
		Vector dataV = new Vector();
		for (int i = 0; i < data.size(); i++) {
			Vector row = new Vector();
			row.add(data.get(i).getId());
			row.add(data.get(i).getName());
			row.add(data.get(i).getNachname());
			row.add(data.get(i).getAdresse());
			row.add(data.get(i).getWohnort());
			row.add(data.get(i).getPostleitzahl());
			row.add(data.get(i).getTelefonnummer());
			dataV.add(row);
		}

		Vector titles = new Vector();
		titles.add("id");
		titles.add("Vorname");
		titles.add("Nachname");
		titles.add("Adresse");
		titles.add("Wohnort");
		titles.add("Postleitzahl");
		titles.add("Telefonnummer");

		table_clients = new JTable(dataV, titles);

		frmKundenverwaltung.getContentPane().add(table_clients.getTableHeader(), BorderLayout.NORTH);
		frmKundenverwaltung.getContentPane().add(table_clients, BorderLayout.CENTER);
		
		Button addNewClientButton = new Button("Add Client");
		addNewClientButton.addActionListener(new addNewClient());
		frmKundenverwaltung.getContentPane().add(addNewClientButton, BorderLayout.SOUTH);

	}

	// exit app
	static class exitApp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	// Add new Client to SQL
	static class addNewClient implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			db.insertClientToDatabase("Nils", "Wegner", "Musterstraﬂe 1", "Musterstadt", "12345", "12345/6789");
			data = SQLiteDatabase.getClientsFromDatabase();
			System.out.println(data);
			table_clients.invalidate();
			table_clients.revalidate();
			table_clients.repaint();
		}
	}

}
