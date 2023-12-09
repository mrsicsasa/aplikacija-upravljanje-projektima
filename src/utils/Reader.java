package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {
public Reader() {
	super();
}
public List<ArrayList<String>> ucitaj(String imeDatoteke){
	List<ArrayList<String>>podaci=new ArrayList<>();
	try {
		podaci=Files.lines(Paths.get("src","data",imeDatoteke)).map(line -> {
	        String[] atributi = line.split("\\|");
	        return new ArrayList<>(Arrays.asList(atributi));
	    })
	    .collect(Collectors.toList());
	}catch ( IOException e) {
		e.printStackTrace();
	}
	return podaci;
}
}
