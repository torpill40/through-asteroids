package com.torpill.throughasteroids.engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.torpill.throughasteroids.App;

public class FileUtils {

	public static String loadAsString(final String path) {

		final StringBuilder result = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.class.getResourceAsStream(path)))) {

			String line = "";
			while ((line = reader.readLine()) != null) {
				result.append(line).append("\n");
			}

		} catch (final IOException e) {

			App.LOGGER.error("Couldn't find the file at {}", path);
		}

		return result.toString();
	}
}
