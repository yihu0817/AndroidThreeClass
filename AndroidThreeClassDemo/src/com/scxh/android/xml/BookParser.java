package com.scxh.android.xml;

import java.io.InputStream;
import java.util.List;

public interface BookParser {
	/**
	 * Xml资源流转换成对应对象
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public List<Book> parse(InputStream is) throws Exception;

	/**
	 * 将对象转换成xml字符
	 * @param books
	 * @return
	 * @throws Exception
	 */
	public String serialize(List<Book> books) throws Exception;
}
