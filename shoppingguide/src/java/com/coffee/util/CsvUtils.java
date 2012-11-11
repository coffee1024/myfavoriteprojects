package com.coffee.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 本类提供关于CSV文件的方法
 * 
 * 
 */
public class CsvUtils {
	/**
	 * 将表格数据写成Comma Separated Values格式文件。
	 * 
	 * @param strArrayList
	 *            表格数据，以List<String[]>格式传输
	 * @param outputFile
	 *            转换后生成的文件
	 * @param charset
	 *            文件生成的字符编码方式，默认为gb18030
	 */
	public static void strArrayList2Csv(List<String[]> strArrayList,
			String outputFile, String charset) throws Exception {
		if (strArrayList == null || outputFile == null || outputFile.equals("")) {
			throw new Exception("CsvTool.strArrayList2Csv()的参数传入有误");
		}
		if (charset == null || charset.equals("")) {
			charset = "gb18030";
		}

		File fileOutPutFile = new File(outputFile);

		// 指定以何种编码写入文件，故不用FileWriter，而在 FileOutputStream 上构造一个
		// OutputStreamWriter。
		// buffered
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(fileOutPutFile), charset));
		String line = null;

		try {
			for (int i = 0; i < strArrayList.size(); i++) {
				if (strArrayList.get(i) != null) {
					line = "";
					for (int j = 0; j < strArrayList.get(i).length; j++) {
						if (strArrayList.get(i)[j] != null) {
							line = line
									+ "\""
									+ strArrayList.get(i)[j].replaceAll("\"",
											"\"\"") + "\"" + ",";
						}
					}
					line = line.substring(0, line.length() - 1);
					out.write(line + System.getProperty("line.separator"));
				}
			}
		} catch (Exception e) {
			throw new Exception(e.toString());
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
				out = null;
			} catch (Exception e) {
			}
		}
	}

	public static OutputStream strArrayList2CsvOutputstream(
			List<String[]> strArrayList, String charset) throws Exception {
		if (strArrayList == null) {
			throw new Exception("CsvTool.strArrayList2Csv()的参数传入有误");
		}
		if (charset == null || charset.equals("")) {
			charset = "gb18030";
		}

		// buffered
		OutputStream outputStream = new ByteArrayOutputStream();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				outputStream, charset));
		String line = null;

		try {
			for (int i = 0; i < strArrayList.size(); i++) {
				line = "";
				for (int j = 0; j < strArrayList.get(i).length; j++) {
					line = line + "\""
							+ strArrayList.get(i)[j].replaceAll("\"", "\"\"")
							+ "\"" + ",";
				}
				line = line.substring(0, line.length() - 1);
				out.write(line + System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			throw new Exception(e.toString());
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
				out = null;
			} catch (Exception e) {
			}
		}

		return outputStream;
	}

	public static List<String[]> csv2StrArrayList(String csv, String charset)
			throws Exception {
		if (csv == null || csv.equals("")) {
			throw new Exception("CsvTool.csv2StrArrayList()参数传入错误");
		}
		File fileCsv = new File(csv);
		if (fileCsv == null || !fileCsv.isFile()) {
			throw new Exception(csv + "所代表的不是一个文件");
		}

		ArrayList<String[]> list = new ArrayList<String[]>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					fileCsv), charset));
			String line = "";
			String record = "";
			while ((line = br.readLine()) != null) {
				record = record + line;
				while (hasOddQuot(record)) {
					// 按行读取，当前读到数据包含的"的个数为偶时表示已成功读到一条record.
					line = br.readLine();
					if (line == null) {
						throw new Exception("CsvTool.csv2StrArrayList():" + csv
								+ "文件内容有误");
					} else {
						record = record + "\n" + line;
					}
				}

				// 对当前的record处理
				String[] splitedRecordByComma = record.split(",");
				ArrayList<String> fieldArray = new ArrayList<String>();
				String[] fields = null;
				String field = "";
				for (int i = 0; i < splitedRecordByComma.length; i++) {
					field = splitedRecordByComma[i];
					while (hasOddQuot(field)) {
						// 若包含奇数个"，则表示当前字段还没读完，继续循环读取
						i++;
						if (i > splitedRecordByComma.length) {
							throw new Exception("CsvTool.csv2StrArrayList():"
									+ csv + "文件内容有误");
						}
						field = field + "," + splitedRecordByComma[i];
					}

					// 对当前field的处理
					field = field.replaceAll("\"\"", "\"");
					if (field.startsWith("\"") && field.endsWith("\"")) {
						field = field.substring(1, field.length() - 1);
					} else if (!field.startsWith("\"") && !field.endsWith("\"")) {
						// do nothing
					} else {
						throw new Exception("CsvTool.csv2StrArrayList():" + csv
								+ "文件内容有误");
					}
					fieldArray.add(field);
				}

				fields = new String[fieldArray.size()];
				list.add(fieldArray.toArray(fields));

				// 重置record开始下一条记录的处理
				record = "";
			}
		} catch (Exception e) {
			throw new Exception(e.toString());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				br = null;
			} catch (Exception e) {
			}
		}
		return list;
	}

	/**
	 * 判断字符串中"个数的奇偶性
	 * 
	 * @param str
	 *            含有"的字符串
	 * @return 奇数返回true,偶数返回false
	 */
	private static boolean hasOddQuot(String str) {
		str = str.replaceAll("[^\"]", "");
		int quotNum = str.length();
		if (quotNum % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		// String[] line1={"11\"好","1,2"};
		// String[] line2={"21","2\n2"};
		List<String[]> list = csv2StrArrayList("e:/test.csv", "utf8");
		// list.add(line1);
		// list.add(line2);
		CsvUtils.strArrayList2Csv(list, "e:/test1.csv", "");
	}
}
