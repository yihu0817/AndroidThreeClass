package com.scxh.android.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.scxh.android.R;
import com.scxh.android.util.Logs;
import com.scxh.android.util.ReadAssetsFile;

public class XmlParserPullActivity extends Activity {
	private TextView mQianTxt, textViewhou;
	private String text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xml_parser_main_act);
		mQianTxt = (TextView) this.findViewById(R.id.textqian);
		textViewhou = (TextView) this.findViewById(R.id.texthou);

		text = ReadAssetsFile.readtxt(XmlParserPullActivity.this, "books.xml");
		mQianTxt.setText(text);
	}
	/**
	 * Xml字符转对象
	 */
	public void onXmlToObjectClick(View v) {
		readXML();
	}

	/**
	 * JAVA对象转xml字符
	 */
	public void onJavaObjectToXmlStringClick(View v) {
		writerXML();
	}

	public void readXML() {
		BookParser bookParser = new PullBookParser();
		String data = "";
		try {
			InputStream is = getAssets().open("books.xml");
			List<Book> books = bookParser.parse(is);
			
			for (Book book : books) {
				data += book.toString();
				Logs.v("book" + book.toString());
			}
			textViewhou.setText(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writerXML() {
		List<Book> books = new ArrayList<Book>();

		Book book = new Book();
		book.setId(100);
		book.setName("android 学习");
		book.setPrice(99.0);
		books.add(book);

		book = new Book();
		book.setId(101);
		book.setName("java编程学习");
		book.setPrice(99.0);
		books.add(book);

		BookParser bookParser = new PullBookParser();

		try {
			String xmlStr = bookParser.serialize(books);
			Logs.v(xmlStr);
			textViewhou.setText(xmlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
