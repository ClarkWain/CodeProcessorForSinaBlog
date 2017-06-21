package cn.bassy.tools.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * 概述：
 * 
 * @author 韦天鹏
 * @create 2017年6月17日
 * 
 */
public class ClipboardHelper {
	/**
	 * 从剪切板获得文字。
	 */
	public static String getSysClipboardHTMLText() {
		String ret = "";
		Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable clipTf = sysClip.getContents(null);

		if (clipTf == null) {
			return null;
		}

		if (clipTf.isDataFlavorSupported(DataFlavor.fragmentHtmlFlavor)) {
			try {
				ret = (String) clipTf.getTransferData(DataFlavor.allHtmlFlavor);
				int first = ret.indexOf("<body>");
				int last = ret.lastIndexOf("</body");

				if (first >= 0) {
					last = last > 0 ? last : ret.length();
					ret = ret.substring(first + "<body>".length(), last);
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("读取剪贴板内容时出现异常：" + e.getMessage());
			}
		} else {
			System.out.println("剪贴板内容无效，必须是html内容才行！");
		}

		return ret;
	}

	/**
	 * 将字符串复制到剪切板。
	 */
	public static void setTextToSysClipboard(String result) {
		Transferable t = new Transferable() {

			@Override
			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.fragmentHtmlFlavor.equals(flavor);
			}

			@Override
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { DataFlavor.fragmentHtmlFlavor };
			}

			@Override
			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
				return result;
			}
		};

		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		clip.setContents(t, null);
	}
}
