package Kundenverwaltung;

public class mainProg {

	public static void main(String[] args) {
		SQLiteDatabase db = new SQLiteDatabase("test.db");
		System.out.println("Datenbank geladen");
		
		System.out.println(db);
		
		db.getClientsFromDatabase();
		
		db.insertClientToDatabase("Nils", "Wegner", "Musterstra�e 1", "Musterstadt", "12345", "12345/6789");
		
		db.getClientsFromDatabase();

	}

}
