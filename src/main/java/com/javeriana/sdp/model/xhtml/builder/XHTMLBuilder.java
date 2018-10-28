package com.javeriana.sdp.model.xhtml.builder;

/**
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class XHTMLBuilder {

    /**
     * Represents the content of this xhtml
     */
    private final StringBuilder content = new StringBuilder();

    /**
     * Appends a plain piece of feature to the content
     * @param feature   the feature
     * @return  the same instance of the xhtml
     * @note: use with caution
     */
    public XHTMLBuilder add(final String feature) {
        content.append(feature);
        return this;
    }

    /**
     * Appends a paragraph with a given feature and left padding
     * @param feature   the feature
     * @param padding   the padding
     * @return  the same instance of the xhtml
     */
    public XHTMLBuilder addParagraph(final String feature, final int padding) {
        content.append("<p style=\"padding-left: ").append(padding).append("em\">").
                append(feature).append("</p>");
        return this;
    }

    /**
     * Appends a paragraph with a given feature and left padding
     * @param feature   the feature
     * @return  the same instance of the xhtml
     */
    public XHTMLBuilder addParagraph(final String feature) {
        return addParagraph(feature, 0);
    }

    /**
     * Appends an url to a feature with left padding value
     * @param url   the url to redirect
     * @param feature   the feature
     * @param padding   represents the padding distance in 'em'
     * @return  the same instance of the xhtml
     */
    public XHTMLBuilder addLink(final String url, final String feature, final int padding) {
        content.append("<a style=\"padding-left: ").append(padding).append("em\" href=\"").
                append(url).append("\">").append(feature).append("</a>");
        return this;
    }

    /**
     * Appends an url to a feature without padding
     * @param url   the url to redirect
     * @param feature   the feature
     * @return  the same instance of the xhtml
     */
    public XHTMLBuilder addLink(final String url, final String feature) {
        return addLink(url, feature, 0);
    }

    /**
     * Adds a new line
     * @return  the same instance of the xhtml
     */
    public XHTMLBuilder addNewLine() {
        content.append("<br/>");
        return this;
    }

    /**
     * Adds a heading to a feature
     * @param heading   the heading id varying from [1..6]
     * @param feature   the feature
     * @return  the same instance of the xhtml
     */
    public XHTMLBuilder addHeading(final int heading, final String feature) {
        if (heading >= 1 && heading <= 6) {
            content.append("<h").append(heading).append(">").append(feature).append("</h").
                    append(heading).append(">");
        }
        return this;
    }

    /**
     * Clears the xhtml
     * @return the same instance of the xhtml
     */
    public XHTMLBuilder clear() {
        content.setLength(0);
        return this;
    }

    /**
     * Checks if whether or not the builder is empty
     * @return  true if is empty, false otherwise
     */
    public boolean isEmpty() {
        return getLength() == 0;
    }

    /**
     * Gets the length of this builder
     * @return  the length
     */
    public int getLength() {
        return content.length();
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
