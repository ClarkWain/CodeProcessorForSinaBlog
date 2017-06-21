package cn.bassy.tools.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import cn.bassy.tools.processor.IProcessor;
import cn.bassy.tools.utils.Log;

/**
 * 概述：XML解析助手，将包含HTML标签的代码当作XML来解析处理
 * 
 * @author 韦天鹏
 * @create 2017年6月13日
 */
public class XMLHelper {
	/**
	 * 解析XML
	 * 
	 * @param code
	 *            Web格式代码
	 * @param processor
	 *            代码加工处理器
	 * @param listener
	 *            结果回调
	 */
	public static void parse(String code, IProcessor processor, OnResultListener listener) {
		try {
			Log.i("输入内容：\n%s", code);
			code = processor.preProcess(code);
			Log.i("preProcess处理结果：\n%s", code);
			
			InputStream is = new ByteArrayInputStream(code.getBytes());
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(is, new MyXMLHandler(processor, listener));
		} catch (Exception e) {
			e.printStackTrace();
			listener.onError(e);
		}
	}

}
