package ua.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.sun.javafx.font.freetype.FTFactory;

public class QueryModel {

	private File file;

	public QueryModel() throws URISyntaxException {
		file = new File(getClass().getClassLoader().getResource("NewTextFile.txt").toURI());
	}

	public JsonObject getInfo(String str, int limit, int length) {

		try {
			JsonObjectBuilder value = Json.createObjectBuilder();

			JsonArray arrayBuilder;

			if (length != -1)
				arrayBuilder = getTextWithLength(str, length, limit);
			else
				arrayBuilder = getTextWithoutLength(str, limit);

			value.add("text", arrayBuilder);
			

			return value.build();

		} catch (IOException e) {
			return Json.createObjectBuilder().add("error", e.getMessage()).build();
		}

	}

	private JsonArray getTextWithLength(String str, int length, int limit) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String line;
		int count = 0;
		int countFind = 0;
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		while ((line = bf.readLine()) != null) {
			if (str.isEmpty() || !line.contains(str)) {
				if (line.contains(str)){
					count++;
				}
				if(count<1 || str.isEmpty()) {
					arrayBuilder.add(line);
				}
			}

			if (line.contains(str) && !str.isEmpty()) {
				str = str.substring(0, Math.min(str.length(), length));
				if (str.length() <= length) {
					String[] sentence = line.split(" ");
					for (String word : sentence) {
						count += word.length();
						if (word.contains(str) && count < limit) {
							arrayBuilder.add(str);

						}

					}

				}

			}
		}
		return arrayBuilder.build();

	}

	private JsonArray getTextWithoutLength(String str, int limit) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String line;
		int count = 0;

		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		while ((line = bf.readLine()) != null && line.length() < limit) {
			if (str.isEmpty() || !line.contains(str)) {
				if (line.contains(str)){
					count++;
				}
				if(count<1 || str.isEmpty()) {
					arrayBuilder.add(line);
				}
			}
			if (line.contains(str) && !str.isEmpty()) {
				String[] sentence = line.split(" ");
				for (String word : sentence) {
					count += word.length();
					if (word.contains(str) && count < limit) {
						arrayBuilder.add(str);
					}
				}

			}

		}
		return arrayBuilder.build();

	}

	


	public List<String> getAllLines() throws IOException {
		List<String> text = new ArrayList<>();
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String line;
		while ((line = bf.readLine()) != null) {
			text.add(line);
		}

		return text;
	}

	

}
