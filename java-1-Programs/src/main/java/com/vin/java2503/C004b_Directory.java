package com.vin.java2503;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class C004b_Directory implements FilenameFilter {
	static String path1 = "C:\\Users\\vin\\OneDrive\\Documents\\Vikas Worksapce\\01JavaProgram\\";

	public static void main(String[] args) throws IOException {
//		displayFilesDirs();
//		displayFilesUsingStream();
//		getLength();
//		createDirectory();
//		deleteDirectory();
//		getCWD();
//		searchFileInDirectory();
	}
	
	private static void displayFilesDirs() {
		File[] files = new File(path1).listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("Directory Name :- " + file.getName());
			} else {
				System.out.println("File Name :- " + file.getName());
			}
		}
	}

	private static void displayFilesUsingStream() throws IOException {
		Stream<Path> filepath = Files.walk(Paths.get(path1));
		filepath.forEach(System.out::println);
		filepath.close();
	}

	private static void getLength() {
		File file = new File(path1 + "abc.txt");
		long size = file.length();
		System.out.println("Size = " + size + "kb of file " + file.getAbsolutePath());
	}

	private static void createDirectory() {
		if (path1.length() == 0)
			System.out.println("Path does not exist");
		else {
			Path p = Paths.get(path1 + "vikas");
			if (!Files.exists(p)) {
				try {
					Files.createDirectories(p);
					System.out.println(p + " created" + " successfully");
				} catch (IOException err) {
					err.printStackTrace();
				}
			} else
				System.out.println("The directory " + "already exists");
		}
	}

	private static void deleteDirectory() {
		// deleing file/directory is same
//		File file = new File(path1+"vikas.txt");
		File file = new File(path1 + "vikas");
		file.delete();
	}

	private static void getCWD() {
		String path = System.getProperty("user.dir");
		System.out.println("Working Directory = " + path);
	}

// ================================================================================= //
	String initials;
	// constructor
	public C004b_Directory(String initials) {
		this.initials = initials;
	}
	@Override
	public boolean accept(File dir, String name) {
		return name.startsWith(initials);
	}
	private static void searchFileInDirectory() {
		File directory = new File(path1);
		C004b_Directory filterObImplFilenameFilter = new C004b_Directory("abc1.txt");
		String[] flist = directory.list(filterObImplFilenameFilter);
		if (flist == null) {
			System.out.println("Empty directory or directory does not exists.");
		} else {
			for (int i = 0; i < flist.length; i++) {
				System.out.println(flist[i] + " found");
			}
		}
	}
// ================================================================================= //

}
