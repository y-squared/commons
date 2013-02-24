package com.ysquared.commons.htmlutil;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.ysquared.commons.htmlutil.HtmlUtil;

public class HtmlUtilTest extends TestCase {
	
	

	@Test
	public void testGetHrefFromA() {
		String href = HtmlUtil.getHrefFromA(HtmlTexts.a1);
		assertEquals("www.google.com", href);

		href = HtmlUtil.getHrefFromA(HtmlTexts.a2);
		assertEquals("http://www.google.com", href);

		href = HtmlUtil.getHrefFromA(HtmlTexts.a3);
		assertEquals("www.visionworld.com", href);

		href = HtmlUtil.getHrefFromA(HtmlTexts.a4);
		assertEquals("www.google.com", href);

		href = HtmlUtil.getHrefFromA(HtmlTexts.a5);
		assertEquals("www.google.com", href);

		href = HtmlUtil.getHrefFromA(HtmlTexts.a6);
		assertEquals(null, href);

		href = HtmlUtil.getHrefFromA(HtmlTexts.a7);
		assertEquals(null, href);

		href = HtmlUtil.getHrefFromA(HtmlTexts.a8);
		assertEquals(null, href);

		href = HtmlUtil.getHrefFromA(HtmlTexts.a9);
		assertEquals(null, href);
		
		href = HtmlUtil.getHrefFromA(HtmlTexts.longHtml);
		assertEquals("http://www.latio.lv/en/piedavajuma/?view=88642&amp;currency=3", href);
	}

	@Test
	public void testGetNameFromA() {
		String html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.a1);
		assertEquals("Google!", html);

		html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.a2);
		assertEquals("Google!", html);

		html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.a3);
		assertEquals("<b>VisionWorld</b>", html);

		html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.a4);
		assertEquals("Google!", html);
		
		html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.a5);
		assertNotSame("Google!", html);
		
		html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.a5, HtmlUtil.A);
		assertEquals("Google!", html);
		
		html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.a6);
		assertEquals("", html);
		
		html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.a7);
		assertEquals(null, html);

		html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.a8);
		assertEquals(null, html);

		html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.a9);
		assertEquals(null, html);
		
		html = HtmlUtil.getInnerHtmlFromTag(HtmlTexts.longHtml, HtmlUtil.A);
		assertEquals("Noliktavas Street<br />Centre (middle)<br />Rīga", html);
	}
	
	@Test
	public void testStripFromHtmlTags() {
		String text = HtmlUtil.stripFromHtmlTags(HtmlTexts.a1);
		assertEquals("Google!", text);
		
		text = HtmlUtil.stripFromHtmlTags(HtmlTexts.a2);
		assertEquals("Google!", text);
		
		text = HtmlUtil.stripFromHtmlTags(HtmlTexts.a3);
		assertEquals("VisionWorld", text);
		
		text = HtmlUtil.stripFromHtmlTags(HtmlTexts.a4);
		assertEquals("Google!", text);
		
		text = HtmlUtil.stripFromHtmlTags(HtmlTexts.a5);
		assertEquals("hejsanGoogle!...", text);
		
		text = HtmlUtil.stripFromHtmlTags(HtmlTexts.a6);
		assertEquals("", text);
		
		text = HtmlUtil.stripFromHtmlTags(HtmlTexts.a7);
		assertEquals("awf", text);
		
		text = HtmlUtil.stripFromHtmlTags(HtmlTexts.a8);
		assertEquals("", text);
		
		text = HtmlUtil.stripFromHtmlTags(HtmlTexts.a9);
		assertEquals(null, text);
		
		text = HtmlUtil.getTextFromInnerHtml(HtmlTexts.longHtml, HtmlUtil.A, " ");
		assertEquals("Noliktavas Street Centre (middle) Rīga", text);
	}
	
	
	@Test
	public void testGetListWithAllTags() {
		List<String> trs = HtmlUtil.getListWithAllTags(HtmlTexts.fullTable, "tr");
		assertEquals(5, trs.size());
		
		List<String> tds = HtmlUtil.getListWithAllTags(HtmlTexts.fullTable, "td");
		assertEquals(12, tds.size());	
	}
}
