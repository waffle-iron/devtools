package com.gh.funthomas424242.eclipseutils;

public class StringEscaper {

	protected String rawText;

	public StringEscaper(final String textToEscape) {
		this.rawText = textToEscape;
	}

	public String getEscapedContent() {

		final String value = this.rawText;

		// HINT replace \ to \\
		final StringBuffer buf = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			final char character = value.charAt(i);
			if (character == '\\') {
				buf.append('\\');
				buf.append('\\');
			} else {
				buf.append(character);
			}
		}
		String escapedString = buf.toString();
		escapedString = escapedString.replaceAll("\\:", "\\\\:");
		escapedString = escapedString.replaceAll("=", "\\\\=");
		escapedString = escapedString.replaceAll("\t", "\\\\t");
		escapedString = escapedString.replaceAll("\r", "\\\\r");
		escapedString = escapedString.replaceAll("\n", "\\\\n");
		return escapedString;

	}
}
