package cn.bassy.tools.processor;

/**
 * 概述：适用于Java代码的加工处理器
 * 
 * @author 韦天鹏
 * @create 2017年6月16日
 */
public class JavaCodeProcessor implements IProcessor {

	boolean isFirstPreTag = true;

	@Override
	public String preProcess(String code) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append(code.replace("<br>", "<br/>").replace("<p>", "<p/>").replace("宋体", "Consolas"));
		return sb.toString();
	}

	@Override
	public String startElement(String tag) {
		return replaceFont(addPadding(tag));
	}

	@Override
	public String processText(String text) {
		String newText;

		newText = replacePrefixSpace(text);
		newText = replaceComments(newText);

		return newText;
	}

	@Override
	public String endElement(String tag) {
		if (tag.contains("</br>") || tag.contains("</p>")) {
			return ""; // 忽略</br>这种情况，前面已经处理过了
		}
		return tag;
	}

	/** 增加内边距 */
	private String addPadding(String tag) {
		if (tag.contains("<pre style=\"") && isFirstPreTag) {
			return tag.replace("<pre style=\"", "<pre style=\"padding:5px 5px 5px 10px;");
		} else {
			return tag;
		}
	}

	/**
	 * 使用中文字体会导致格式转换有些问题
	 * 
	 * @param tag
	 *            标签内容，这其中包含了字体的配置
	 * @return 返回处理后的内容
	 */
	private String replaceFont(String tag) {
		if (tag.contains("宋体")) {
			return tag.replace("宋体", "");
		}
		return tag;
	}

	/**
	 * 将字符串前面的空格进行转义
	 * 
	 * @param text
	 *            输入内容
	 * @return 输出处理后的结果
	 */
	private String replacePrefixSpace(String text) {
		if (' ' != text.charAt(0)) {
			return text;
		} else {
			String sb = new String();
			for (int i = 0; i < text.length(); i++) {
				if (' ' == text.charAt(i)) {
					sb += "&nbsp";
				} else {
					sb += text.substring(i);
					break;
				}
			}
			return sb.toString();
		}
	}

	/**
	 * 对“{@code /**}”这一类注释进行转化，新浪博客默认不支持
	 * 
	 * @param text
	 * @return
	 */
	private String replaceComments(String text) {
		return text.replace("/**", "<b>/</b>**");
	}
}
