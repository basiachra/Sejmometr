package sejmometr;

import java.util.HashMap;
import java.util.Scanner;

public class ArgumentParser {

	private int wybor;
	private String nameOfEnvoy;
	private Envoy envoy;
	private HashMap<String, String> hmap;
	private Expenses expenses;
	private Trips trip;
	
	
	public ArgumentParser(Scanner scanner) {
		wybor = scanner.nextInt();
	}

	public void argumentCase() throws Exception {

		if (wybor == 1 || wybor == 2) {
			System.out.println("Podaj Imiê i Nazwisko którego wydatki chcesz sprawdziæ: ");
			Scanner scanner = new Scanner(System.in);
			nameOfEnvoy = scanner.nextLine();
			scanner.close();
		}

		switch (wybor) {
		case 1:
			if (Envoy.ifExist(nameOfEnvoy)) {
				this.expenses = new Expenses();
				envoy = new Envoy();
				this.hmap = envoy.getMap();
				String id = Envoy.getEnvoyId(hmap, nameOfEnvoy);
				expenses.envoyExpenses(id, 1);
			} else
				throw new IllegalArgumentException("Nie ma takiego pos³a...");
			break;
			
		case 2:
			if (Envoy.ifExist(nameOfEnvoy)) {
				this.expenses = new Expenses();
				envoy = new Envoy();
				this.hmap = envoy.getMap();
				String id = Envoy.getEnvoyId(hmap, nameOfEnvoy);
				expenses.envoyExpenses(id, 2);
			}
			else
				throw new IllegalArgumentException("Nie ma takiego pos³a...");
			break;
			
		case 3:
			this.expenses = new Expenses();
			expenses.envoysExpensesAvg();
			break;
			
		case 4:
			this.trip = new Trips();
			trip.theTrips(4);
			break;
			
		case 5:
			this.trip = new Trips();
			trip.theTrips(5);
			break;
			
		case 6:
			this.trip = new Trips();
			trip.theTrips(6);
			break;
			
		case 7:
			this.trip = new Trips();
			trip.theTrips(7);
			break;
			
		default:
			throw new IllegalArgumentException("Niestety nie ma takiej opcji...");
		}
	}
}
