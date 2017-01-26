package sejmometr;

import java.util.Scanner;
import java.io.IOException;

public class SejmSystem {

	public static void main(String[] args) throws Exception, IOException {

		try{
			System.out.println("Wybierz jakie informacje chcesz uzyskaæ: \n\n"
					+ "1.Sumê wydatków pos³a/pos³anki o okreœlonym imieniu i nazwisku \n"
					+ "2.Wysokoœæ wydatków na drobne naprawy i remonty biura poselskiego, okreœlonego pos³a/pos³anki \n"
					+ "3.Œredni¹ wartoœci sumy wydatków wszystkich pos³ów \n"
					+ "4.Pos³a/pos³ankê, który wykona³ najwiêcej podró¿y zagranicznych \n"
					+ "5.Pos³a/pos³ankê, który najd³u¿ej przebywa³ za granic¹ \n"
					+ "6.Pos³a/pos³ankê, który odby³ najdro¿sz¹ podró¿ zagraniczn¹ \n"
					+ "7.Listê wszystkich pos³ów, którzy odwiedzili W³ochy ");

			Scanner scanner = new Scanner(System.in);
			ArgumentParser argParser = new ArgumentParser(scanner) ;
			argParser.argumentCase();
			scanner.close();
			
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}
}