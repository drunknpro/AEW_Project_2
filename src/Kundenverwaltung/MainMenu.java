package Kundenverwaltung;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Button;
import java.awt.Dialog.ModalityType;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;

public class MainMenu {

	private JFrame frmKundenverwaltung;
	private JDialog createClient;
	private ArrayList<Client> data;

	private SQLiteDatabase db = new SQLiteDatabase("test.db");
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
	public void initialize() {
		SQLiteDatabase db = new SQLiteDatabase("test.db");
		System.out.println("Datenbank geladen");

//		db.getClientsFromDatabase();

//		db.insertClientToDatabase("Nils", "Wegner", "Musterstraﬂe 1", "Musterstadt", "12345", "12345/6789");
//
//		db.getClientsFromDatabase();

		data = db.getClientsFromDatabase();

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

		JPanel panel = new JPanel();
		frmKundenverwaltung.getContentPane().add(panel, BorderLayout.NORTH);

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
		
		table = new JTable(dataV, titles);
		panel.add(table);

		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.addActionListener(new addClient());
		panel.add(btnAddClient);

		

	}

	public void renewClientTable() {

		data = db.getClientsFromDatabase();

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

		table.invalidate();
		table.revalidate();
		table.setModel(new DefaultTableModel(dataV, titles));
		table.repaint();
		this.frmKundenverwaltung.repaint();
	}

	// exit app
	static class exitApp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	class addClient implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			createClient = new JDialog(frmKundenverwaltung, "Kunden Anlegen", ModalityType.APPLICATION_MODAL);
			createClient.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			createClient.setBounds(120, 120, 500, 300);
			createClient.setLayout(null);
			
			//////////////////////////////////
			// VornamenLabel und -Texxtfeld //
			//////////////////////////////////
			// Vornamen-Label //
			JLabel labelVorname = new JLabel("Vorname:");
			labelVorname.setBounds(10, 10, 100, 20);
			labelVorname.setHorizontalAlignment(SwingConstants.RIGHT);
			createClient.add(labelVorname);
			// Vornamen-Textfeld //
			JTextField textVorname = new JTextField();
			textVorname.setBounds(115, 10, 300, 20);
			createClient.add(textVorname);
			
			///////////////////////////////////
			// NachnamenLabel und -Texxtfeld //
			///////////////////////////////////
			// Nachnamen-Label //
			JLabel labelNachname = new JLabel("Nachname:");
			labelNachname.setBounds(10, 30, 100, 20);
			labelNachname.setHorizontalAlignment(SwingConstants.RIGHT);
			createClient.add(labelNachname);
			
			// Vornamen-Textfeld //
			JTextField textNachname = new JTextField();
			textNachname.setBounds(115, 30, 300, 20);
			createClient.add(textNachname);
			
			createClient.setVisible(true);
			// db.insertClientToDatabase("Nils", "Wegner", "MusterStraﬂe 1", "Musterstadt", "12345", "12345/6789");
			// renewClientTable();
		}
	}

}
