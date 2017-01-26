package sejmometr;

import java.util.Scanner;
import java.io.IOException;

public class SejmSystem {

	public static void main(String[] args) throws Exception, IOException {

		try{
			System.out.println("Wybierz jakie informacje chcesz uzyska�: \n\n"
					+ "1.Sum� wydatk�w pos�a/pos�anki o okre�lonym imieniu i nazwisku \n"
					+ "2.Wysoko�� wydatk�w na drobne naprawy i remonty biura poselskiego, okre�lonego pos�a/pos�anki \n"
					+ "3.�redni� warto�ci sumy wydatk�w wszystkich pos��w \n"
					+ "4.Pos�a/pos�ank�, kt�ry wykona� najwi�cej podr�y zagranicznych \n"
					+ "5.Pos�a/pos�ank�, kt�ry najd�u�ej przebywa� za granic� \n"
					+ "6.Pos�a/pos�ank�, kt�ry odby� najdro�sz� podr� zagraniczn� \n"
					+ "7.List� wszystkich pos��w, kt�rzy odwiedzili W�ochy ");

			Scanner scanner = new Scanner(System.in);
			ArgumentParser argParser = new ArgumentParser(scanner) ;
			argParser.argumentCase();
			scanner.close();
			
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}
}