package com.ysquared.commons.htmlutil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {

	public static final String A = "a";
	public static final String SPAN = "span";

	private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;
	
	private static final Pattern PATTERN_A = Pattern.compile("<a .*?href=\"(.*?)\".*?>(.*?)</a>", FLAGS);
	private static final Pattern PATTERN_IMG = Pattern.compile("<img .*?src=\"(.*?)\".*?/>", FLAGS);

	private static final String STR_PATTERN_GENERIC_TAG = "<(\\w+).*?>(.*?)</\\1>";
	private static final Pattern PATTERN_GENERIC_TAG = Pattern.compile(STR_PATTERN_GENERIC_TAG, FLAGS);
	private static final Pattern PATTERN_GENERIC_START_TAG = Pattern.compile("< *?\\w+.*?>", FLAGS);
	private static final Pattern PATTERN_GENERIC_END_TAG = Pattern.compile("< *?/ *?\\w+ *?>", FLAGS);

	public static String getHrefFromA(String html) {
		if (html == null) {
			return null;
		}

		Matcher m = PATTERN_A.matcher(html);
		if (m.find()) {
			return m.group(1).trim();
		}
		return null;
	}

	public static String getSrcFromImg(String html) {
		if (html == null) {
			return null;
		}

		Matcher m = PATTERN_IMG.matcher(html);
		if (m.find()) {
			return m.group(1).trim();
		}
		return null;
	}

	public static String getInnerHtmlFromTag(String html) {
		return getInnerHtmlFromTag(html, null);
	}

	public static String getInnerHtmlFromTag(String html, String tag) {
		if (html == null) {
			return null;
		}

		Matcher m;
		if (tag == null) {
			m = PATTERN_GENERIC_TAG.matcher(html);
		} else {
			m = Pattern.compile(STR_PATTERN_GENERIC_TAG.replaceFirst("\\\\w\\+", tag), FLAGS).matcher(html);
		}
		if (m.find()) {
			return m.group(2).trim();
		}
		return null;
	}
	
	
	public static List<String> getListWithAllTags(String html, String tag) {
		if (html == null || tag == null) {
			return null;
		}

		List<String> tags = new ArrayList<String>();
		Pattern p = Pattern.compile(STR_PATTERN_GENERIC_TAG.replaceFirst("\\\\w\\+", tag), FLAGS);
		Matcher m = p.matcher(html);
		while (m.find()) {
			tags.add(m.group(0).trim());
		}
		return tags;
	}
	
	
	
	
	public static String getTextFromInnerHtml(String html) {
		return getTextFromInnerHtml(html, null, "");
	}
	
	public static String getTextFromInnerHtml(String html, String tag) {
		return getTextFromInnerHtml(html, tag, "");
	}
	
	public static String getTextFromInnerHtml(String html, String tag, String replaceWith) {
		return HtmlUtil.stripAndReplaceFromHtmlTags(HtmlUtil.getInnerHtmlFromTag(html, tag), replaceWith);
	}
	
	public static String stripFromHtmlTags(String html) {
		if (html == null) {
			return null;
		}
		String html2 = new String(html);
		Matcher m = PATTERN_GENERIC_START_TAG.matcher(html2);
		while(m.find()) {
			html2 = html2.replace(m.group(0), "");
		}
		m = PATTERN_GENERIC_END_TAG.matcher(html2);
		while(m.find()) {
			html2 = html2.replace(m.group(0), "");
		}

		return html2.trim();
	}

	public static String stripAndReplaceFromHtmlTags(String html, String replaceWith) {
		if (html == null) {
			return null;
		}
		String html2 = new String(html);
		Matcher m = PATTERN_GENERIC_START_TAG.matcher(html2);
		while(m.find()) {
			html2 = html2.replace(m.group(0), replaceWith);
		}
		
		m = PATTERN_GENERIC_END_TAG.matcher(html2);
		while(m.find()) {
			html2 = html2.replace(m.group(0), replaceWith);
		}

		return html2.trim();
	}
	
	
}
