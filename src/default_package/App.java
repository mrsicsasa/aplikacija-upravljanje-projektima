package default_package;

import java.util.ArrayList;
import java.util.List;

import models.osobe.Menadzer;
import models.osobe.Promoter;
import utils.Mapper;
import utils.Reader;
import utils.Writer;

public class App {

	public static void main(String[] args) {
		Reader r=new Reader();
		List<ArrayList<String>>rezultat=r.ucitaj("kopija.txt");
	//	rezultat.forEach(System.out::println);
		Mapper m=new Mapper();
	//	ArrayList<Menadzer>instance=m.konvertujUMenadzer(rezultat);
		ArrayList<Promoter> instance=m.konvertujUPromotera(rezultat);
		instance.forEach(System.out::println);
	//	Writer w=new Writer();
	//	w.upis("kopija.txt", rezultat);

	}

}
