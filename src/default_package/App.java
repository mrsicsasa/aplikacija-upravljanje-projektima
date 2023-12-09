package default_package;

import java.util.ArrayList;
import java.util.List;

import utils.Reader;

public class App {

	public static void main(String[] args) {
		Reader r=new Reader();
		List<ArrayList<String>>rezultat=r.ucitaj("korisnik.txt");
		rezultat.forEach(System.out::println);

	}

}
