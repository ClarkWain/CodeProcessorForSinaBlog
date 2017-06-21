package cn.bassy.tools.xml;

/**
 * 概述：结果回调
 * 
 * @author 韦天鹏
 * @create 2017年6月16日
 */
public interface OnResultListener {
	/**
	 * 操作完成
	 * 
	 * @param result
	 *            操作后的结果
	 */
	void onResult(String result);

	/**
	 * 解析中途遇到了异常
	 * 
	 * @param e
	 *            异常
	 */
	void onError(Exception e);
}
