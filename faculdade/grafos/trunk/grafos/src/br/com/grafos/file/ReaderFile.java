package br.com.grafos.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderFile {

	private String file;

	public ReaderFile(String fileName) {
		file = fileName;
	}

	public List<String> readAll() throws IOException {
		String line;
		List<String> lines = new ArrayList<String>();

		BufferedReader bf = new BufferedReader(new FileReader(this.file));

		while ((line = bf.readLine()) != null) {
			lines.add(line);
		}		
		return lines;
	}
}
