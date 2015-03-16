package com.scxh.android.xml;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.scxh.android.util.Logs;

public class PullBookParser implements BookParser {

	@Override
	public List<Book> parse(InputStream is) throws Exception {
		List<Book> books = null;
		Book book = null;

//		 XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//		 XmlPullParser parser = factory.newPullParser();

		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式

		
		int eventType = parser.getEventType();//返回当前项类型,如:START_TAG(开始标签), END_TAG(结束标签), TEXT(文本内容), etc.)
		while (eventType != XmlPullParser.END_DOCUMENT) {
			
			switch (eventType) {
			
			case XmlPullParser.START_DOCUMENT:
				books = new ArrayList<Book>();
				Logs.v("START_DOCUMENT");
				
				break;
			case XmlPullParser.START_TAG:
				Logs.v("START_TAG  :"+ parser.getName());
				
				if (parser.getName().equals("book")) {
					book = new Book();
					
				} else if (parser.getName().equals("id")) {
					eventType = parser.next();  //指向TEXT内容
					book.setId(Integer.parseInt(parser.getText()));
					
				} else if (parser.getName().equals("name")) {
					eventType = parser.next();//指向TEXT内容
					book.setName(parser.getText());
					
				} else if (parser.getName().equals("price")) {
					eventType = parser.next();//指向TEXT内容
					book.setPrice(Double.parseDouble(parser.getText()));
					
				}
				break;
			case XmlPullParser.END_TAG:
				Logs.v("END_TAG  :"+ parser.getName());
				
				if (parser.getName().equals("book")) {
					books.add(book);
					book = null;
				}
				break;
			}
	
			eventType = parser.next();
		}
		return books;

	}

	@Override
	public String serialize(List<Book> books) throws Exception {

		// XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		// XmlSerializer serializer = factory.newSerializer();

		XmlSerializer serializer = Xml.newSerializer(); // 由android.util.Xml创建一个XmlSerializer实例
		StringWriter writer = new StringWriter();
		
		serializer.setOutput(writer); // 设置输出方向为writer
		
		serializer.startDocument("UTF-8", true);
		
		serializer.startTag("", "books");
		for (Book book : books) {
			serializer.startTag("", "book");
			
//			serializer.attribute("", "id", book.getId() + "");
			serializer.startTag("", "id");
			serializer.text(book.getId() + "");
			serializer.endTag("", "id");
			
			serializer.startTag("", "name");
			serializer.text(book.getName());
			serializer.endTag("", "name");

			serializer.startTag("", "price");
			serializer.text(book.getPrice() + "");
			serializer.endTag("", "price");

			serializer.endTag("", "book");
		}
		serializer.endTag("", "books");
		
		serializer.endDocument();

		return writer.toString();
	}

}
