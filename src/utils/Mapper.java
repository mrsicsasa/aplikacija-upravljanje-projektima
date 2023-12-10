package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import models.osobe.AngazovanaOsoba;
import models.osobe.Menadzer;
import models.osobe.Odmor;
import models.osobe.Odmori;
import models.osobe.Promoter;
import models.osobe.Uloga;

public class Mapper {
public Mapper() {
	super();
}
public ArrayList<Menadzer> konvertujUMenadzer(List<ArrayList<String>>podaci){
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	ArrayList<Menadzer>rezultat= new ArrayList<Menadzer>();
	for( ArrayList<String>podatak : podaci){
		LocalDateTime pocetakAngazovanja = LocalDateTime.parse(podatak.get(3), formatter);
		LocalDateTime krajAngazovanja= LocalDateTime.parse(podatak.get(4), formatter);
		rezultat.add(new Menadzer(podatak.get(0), podatak.get(1), Integer.parseInt(podatak.get(2)),pocetakAngazovanja,krajAngazovanja,new Uloga(podatak.get(5),podatak.get(6),podatak.get(7))))	;
	}
	return rezultat;
}
public ArrayList<Promoter> konvertujUPromotera(List<ArrayList<String>>podaci){
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    ArrayList<Promoter> rezultat = new ArrayList<Promoter>();

    for (ArrayList<String> podatak : podaci) {
        LocalDateTime pocetakAngazovanja = LocalDateTime.parse(podatak.get(3), formatter);
        LocalDateTime krajAngazovanja = LocalDateTime.parse(podatak.get(4), formatter);

        // Pretpostavljamo da se Menadzer nalazi na indeksu 8 u podacima
        Menadzer naredjeniMenadzer =new Menadzer();
        naredjeniMenadzer.setIme(podatak.get(5));
        int brojPodatakaOOdmorima=podatak.size()-6;
        ArrayList<Odmor>odmori=new ArrayList<Odmor>();
        if(brojPodatakaOOdmorima>2) {
        	for(int i=0;i<brojPodatakaOOdmorima;i=i+2) {
        		odmori.add(new Odmor(LocalDateTime.parse(podatak.get(i+6),formatter),LocalDateTime.parse(podatak.get(i+7),formatter)));
        	}
        }
        else {
        	odmori.add(new Odmor(LocalDateTime.parse(podatak.get(6),formatter),LocalDateTime.parse(podatak.get(7),formatter)));
        }
        // Dodajemo novu instancu Promoter u rezultat
        rezultat.add(new Promoter(
                podatak.get(0),
                podatak.get(1),
                Integer.parseInt(podatak.get(2)),
                pocetakAngazovanja,
                krajAngazovanja,
                naredjeniMenadzer,
                new Odmori(odmori)
        ));
    }
	return rezultat;
}
}
