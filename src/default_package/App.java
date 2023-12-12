package default_package;

import java.util.ArrayList;
import java.util.List;

import controller.MainController;
import models.osobe.Menadzer;
import models.osobe.Promoter;
import utils.Mapper;
import utils.Reader;
import utils.Writer;
import view.MainView;

public class App {

	public static void main(String[] args) {
		Reader r = new Reader();
		List<ArrayList<String>> rezultat = r.ucitaj("korisnik.txt");
		// rezultat.forEach(System.out::println);
		Mapper m = new Mapper();
		ArrayList<Menadzer> instance = m.konvertujUMenadzer(rezultat);
		// ArrayList<Promoter> instance=m.konvertujUPromotera(rezultat);
		instance.forEach(System.out::println);
		Writer w = new Writer();
		for (ArrayList<String> primer : rezultat) {
			if (primer.get(0) == instance.get(0).getIme()) {
				primer.set(0, "Marko");
			}
		}
		System.out.println(rezultat);
		w.upis("proba.txt", rezultat);
			
	        MainView view = new MainView();
	        MainController controller = new MainController(view);

	}

}
