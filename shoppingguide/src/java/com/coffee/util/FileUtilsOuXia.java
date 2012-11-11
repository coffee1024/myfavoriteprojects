package com.coffee.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 * 
 * @since 1.2
 * 
 */
public class FileUtilsOuXia {
	/**
	 * 按文件名和字符编码读取文件，转化为字符串
	 * 
	 * @param path
	 * @param charset
	 * @return
	 */
	public static String readFileToString(String path, String charset) {
		BufferedReader reader = null;
		String tempString = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), charset));
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将字节流转换成字符串返回
	 * 
	 * @param is
	 *            输入流
	 * @return 字符串
	 */
	public static String readFileByLines(InputStream is) {
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString + "\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将文件一行一行的读成List返回
	 * 
	 * @param file
	 *            需要读取的文件
	 * @return 文件的一行就是一个List的Item的返回
	 */
	public static List<String> readFileToList(File file) {
		BufferedReader reader = null;
		List<String> list = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

	/**
	 * 将文件按照一定的编码方式一行一行的读成List返回
	 * 
	 * @param file
	 *            需要读取的文件
	 * @param encodType
	 *            字符编码
	 * @return 文件的一行就是一个List的Item的返回
	 */
	public static List<String> readFileToList(File file, String encodType) {
		BufferedReader reader = null;
		List<String> list = new ArrayList<String>();
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), encodType));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				// if (!(tempString.charAt(0) >= 'a' && tempString.charAt(0) <=
				// 'z'))
				// tempString = tempString.substring(1);
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

	/**
	 * 将指定的字符串内容以指定的方式写入到指定的文件中
	 * 
	 * @param 需要写人的文件
	 * @param content
	 *            需要写入的内容
	 * @param flag
	 *            是否追加写入
	 */
	public static void writeFile(File file, String content, Boolean flag) {
		FileWriter writer = null;
		try {
			if (!file.exists())
				createFile(file.getPath());
			writer = new FileWriter(file, flag);
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
					writer = null;
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 将指定的字符串内容以指定的方式及编码写入到指定的文件中
	 * 
	 * @param file
	 *            需要写人的文件
	 * @param content
	 *            需要写入的内容
	 * @param flag
	 *            是否追加写入
	 * @param encodType
	 *            文件编码
	 */
	public static void writeFile(File file, String content, Boolean flag,
			String encodType) {
		BufferedWriter writer = null;
		try {
			if (!file.exists())
				createFile(file.getPath());

			FileOutputStream writerStream = new FileOutputStream(file, flag);
			writer = new BufferedWriter(new OutputStreamWriter(writerStream,
					encodType));
			writer.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
					writer = null;
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 拷贝文件夹
	 * 
	 * @param oldPath
	 *            源目录
	 * @param newPath
	 *            目标目录
	 */
	public static void copyFolder(String oldPath, String newPath) {
		FileInputStream input = null;
		FileOutputStream output = null;
		try {
			(new File(newPath)).mkdirs();
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					input = new FileInputStream(temp);
					output = new FileOutputStream(newPath + "/"
							+ (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();

				}
				if (temp.isDirectory()) {
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
					output = null;
				}
				if (input != null) {
					input.close();
					input = null;
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 将文件重命名
	 * 
	 * @param oldName
	 *            源文件名
	 * @param newName
	 *            新文件名
	 */
	public static void reName(String oldName, String newName) {
		File oldF = new File(oldName);
		File newF = new File(newName);
		oldF.renameTo(newF);
	}

	/**
	 * 将一个文件列表文件中所有文件拷贝到指定目录中
	 * 
	 * @param listFile
	 *            包含需要拷贝的文件的列表的文件，每个文件写在一行
	 * @param targetFloder
	 *            目标目录
	 */
	public static void copyFilesFromList(String listFile, String targetFloder) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(listFile));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				copyFile(tempString, targetFloder);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 拷贝文件
	 * 
	 * @param oldPath
	 *            源文件
	 * @param newPath
	 *            目标文件
	 */
	public static void copyFile(String oldPath, String newPath) {
		FileInputStream input = null;
		FileOutputStream output = null;
		try {
			File temp = new File(oldPath);
			input = new FileInputStream(temp);
			output = new FileOutputStream(newPath + "/"
					+ (temp.getName()).toString());
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = input.read(b)) != -1) {
				output.write(b, 0, len);
			}
			output.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
					output = null;
				}
				if (input != null) {
					input.close();
					input = null;
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 删除文件列表
	 * 
	 * @param files
	 *            需要删除的文件/文件夹列表
	 * @return 删除成功true，否则返回false
	 */
	public static boolean deleteFiles(List<String> files) {
		boolean flag = true;
		for (String file : files) {
			flag = delete(file);
			if (!flag)
				break;
		}
		return flag;
	}

	/**
	 * 删除文件或文件夹
	 * 
	 * @param fileName
	 *            要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		} else {
			if (file.isFile())
				return deleteFile(fileName);
			else
				return deleteDirectory(fileName);
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile())
			return file.delete();
		return false;
	}

	/**
	 * 删除目录及目录下的文件
	 * 
	 * @param dir
	 *            要删除的目录路径
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		if ((!dirFile.exists()) || (!dirFile.isDirectory()))
			return false;
		boolean flag = true;
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			return false;
		}
		return dirFile.delete();
	}

	// 创建文件 (若文件夹不存在则先创建文件夹)
	public static boolean createFile(String localFileURL) {
		try {
			localFileURL = localFileURL.replace("\\", "/");
			File tempFile = new File(localFileURL);
			if (!tempFile.exists()) {
				// 先判断父文件夹是否存在 不存在则创建父文件夹；存在则创建本文件
				int endIndex = localFileURL.lastIndexOf("/");
				String parentFileDir = "";
				if (endIndex != -1) {
					parentFileDir = localFileURL.substring(0, endIndex);
					createDirectory(parentFileDir);
					tempFile.createNewFile();
				} else
					return false;
			}
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	// 从后往前循环递归创建文件夹
	public static void createDirectory(String localFileDir) throws Exception {
		localFileDir = localFileDir.replace("\\", "/");
		System.out.println(localFileDir);
		if (localFileDir.endsWith("/")) // 去掉最后一个斜杠
			localFileDir = localFileDir.substring(0, localFileDir.length() - 1);
		File tempDir = new File(localFileDir);
		if (!tempDir.exists()) {
			// 先判断父文件夹是否存在 不存在则创建父文件夹；存在则创建本文件夹
			int endIndex = localFileDir.lastIndexOf("/");
			String parentFileDir = "";
			if (endIndex != -1) {
				parentFileDir = localFileDir.substring(0, endIndex);
				File parentFile = new File(parentFileDir);
				if (!parentFile.exists())
					createDirectory(parentFileDir);
			} else
				throw new Exception();
			tempDir.mkdir();
		}
	}

	public static void main(String[] args) {

	}
}