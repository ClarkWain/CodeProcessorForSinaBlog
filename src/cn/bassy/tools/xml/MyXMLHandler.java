package cn.bassy.tools.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cn.bassy.tools.processor.IProcessor;
import cn.bassy.tools.utils.Log;

/**
 * 概述：XML解析处理
 * 
 * @author 韦天鹏
 * @create 2017年6月16日
 */
public class MyXMLHandler extends DefaultHandler {
	StringBuilder result = new StringBuilder();
	OnResultListener listener;
	IProcessor processor;

	public MyXMLHandler(IProcessor processor, OnResultListener listener) {
		this.processor = processor;
		this.listener = listener;
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		String attrs = "";
		for (int i = 0; i < attributes.getLength(); i++) {
			attrs += String.format(" %s=\"%s\"", attributes.getQName(i), attributes.getValue(i));
		}

		String tag = "<" + qName + attrs + ">";
		tag = processor.startElement(tag);
		result.append(tag);
		Log.i("characters处理结果：tag = %s", tag);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String text = new String(ch, start, length);
		text = processor.processText(text);
		result.append(text);
		Log.i("characters处理结果：text = %s", text);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName != null) {
			String tag = "</" + qName + ">";
			result.append(processor.endElement(tag));
			Log.i("endElement处理结果：tag = %s", tag);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		if (listener != null) {
			listener.onResult(result.toString());
		}
	}
}