package cn.bassy.tools.processor;

/**
 * 概述：代码加工处理接口
 * 
 * @author 韦天鹏
 * @create 2017年6月16日
 */
public interface IProcessor {
	/**
	 * 预处理
	 * 
	 * @param code
	 *            输入代码（HTML格式）
	 * @return 返回处理后的内容
	 */
	String preProcess(String code);

	/**
	 * HTML标签开始
	 * 
	 * @param tag
	 *            标签名称及属性，如“{@code <span style='padding:10px'>}”
	 * @return 返回处理后的内容
	 */
	String startElement(String tag);

	/**
	 * HTML标签文本内容
	 * 
	 * @param text
	 *            标签内容，如“public void ”
	 * @return 返回处理后的内容
	 */
	String processText(String text);

	/**
	 * HTML标签结束
	 * 
	 * @param tag
	 *            标签名称，如“{@code </span>}”
	 * @return 返回处理后的内容
	 */
	String endElement(String tag);
}
