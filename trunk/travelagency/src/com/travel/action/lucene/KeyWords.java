package com.travel.action.lucene;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

public class KeyWords {
	private Analyzer analyzer;
	private String print;

	public KeyWords() {
	}

	public KeyWords(Analyzer analyzer) {
		setAnalyzer(analyzer);
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}


	public String getPrint() {
		return print;
	}


	public List<String> getKeyWords(String input) {
		Reader reader = new StringReaderEx(input);
		return this.getKeyWords(reader);
	}

	public List<String> getKeyWords(Reader reader) {
		List<String> arrayList=new ArrayList<String>();
		try {
			LinkedList<CToken> list = new LinkedList<CToken>();
			int wordsCount = 0;
			
			//collect token
			TokenStream ts = analyzer.tokenStream("", reader);
			ts.reset();
			TermAttribute termAtt = (TermAttribute) ts
					.addAttribute(TermAttribute.class);
			while (ts.incrementToken()) {
					list.add(new CToken(termAtt.term(), wordsCount));
			}
			
			int c = 0;
			if (list.size() > 0) {
				for (CToken ctoken : list) {
					c = ctoken.i;
					arrayList.add(ctoken.t);
				}
			}
			return arrayList;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}
	}

	// -------------------------------------------

	static class CToken {
		String t;
		int i;

		CToken(String t, int i) {
			this.t = t;
			this.i = i;
		}
	}

	static class StringReaderEx extends StringReader {
		private int inputLength;

		public StringReaderEx(String s) {
			super(s);
			inputLength = s.length();
		}
	}

}
