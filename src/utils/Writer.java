package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Writer {
	public Writer() {
		super();
	}

	public void upis(String imeDatoteke, List<ArrayList<String>> podaci) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/" + imeDatoteke))) {
			podaci.forEach(instanca -> {
				String linija = instanca.stream().collect(Collectors.joining("|"));
				try {
					writer.write(linija);
					writer.newLine(); // Pisanje nove linije nakon svake instance
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
