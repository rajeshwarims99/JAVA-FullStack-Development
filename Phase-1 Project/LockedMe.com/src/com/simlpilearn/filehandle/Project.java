package com.simlpilearn.filehandle;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Project {

//-----------------------------------------------------------

	public static void ListofFiles(String fname) {
		// Creating a File object for directory
		File directoryPath = new File(fname);
		// List of all files and directories
		String contents[] = directoryPath.list();
		System.out.println("List of files and directories in the specified directory:");
		for (int i = 0; i < contents.length; i++) {
			System.out.println(contents[i]);
		}
	}

//--------------------------------------------------------------------
	// read file....
	public static List<String> readFile(String filename, List<String> result) {
		List<String> list = Collections.emptyList();
		try {

			list = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
			Iterator<String> it = result.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// -----------------------------------------------------------------
	// search file

	public static class MyFilenameFilter implements FilenameFilter {

		String initials;

		// constructor to initialize object
		public MyFilenameFilter(String initials) {
			this.initials = initials;
		}

		public boolean accept(File dir, String name) {
			return name.startsWith(initials);
		}
	}

	// ---------------------------------------------------------------

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String choice;
		System.out.println("Lockedme");
		System.out.println("Rajeshwari.M.Shettar");
		// System.out.println("File Handling");
		System.out.println("1.for getting the file list ");
		System.out.println("2.for busuness operations");
		System.out.println("3.close the applications");
		while (true) {
			System.out.println("Enter your choice:");
			choice = sc.nextLine();

			if (choice.equals("1")) {
				// ask user to enter location..
				System.out.println("Enter the location");
				String loc = sc.next();

				// call method to get list of file in ascending order..
				ListofFiles(loc);

			} else if (choice.equals("2")) {

				// Business level operations..

				System.out.println("Business Operations");
				System.out.println("11.Read");
				System.out.println("22.Write");
				System.out.println("33.Append");
				System.out.println("44.Delete");
				System.out.println("55.Search");

				System.out.println("Enter your choice:");
				choice = sc.nextLine();

				if (choice.equals("11")) {
					System.out.println("Enter the location");
					String fname = sc.next();
					List<String> result = readFile(fname);
					Iterator<String> it = result.iterator();
					while (it.hasNext()) {
						System.out.println(it.next());
					}
				} else if (choice.equals("22")) {
					try {
						System.out.println("Enter the location");
						String fname = sc.next();
						FileWriter f = new FileWriter(fname);
						f.write("HELLO");
						f.close();
						System.out.println("File write done");
					} catch (IOException e) {

						System.out.println("There are some Exception");
						e.printStackTrace();
					}
				} else if (choice.equals("33")) {
					try {

						System.out.println("Enter the location");
						String fname = sc.next();
						File f = new File(fname);
						String data = "append successful";
						if (!f.exists()) {
							f.createNewFile();
						}
						FileWriter fileWriter = new FileWriter(fname);
						fileWriter.write(data);
						fileWriter.close();
						System.out.println("Append Done");
					} catch (IOException e) {

					}
				}

				else if (choice.equals("44")) {

					try {
						System.out.println("Enter the location");
						String fname = sc.next();
						Files.deleteIfExists(Paths.get(fname));

					} catch (NoSuchFileException e) {
						System.out.println(e);
					} catch (DirectoryNotEmptyException e) {
						System.out.println("Directory is not Empty");
					} catch (IOException e) {
						System.out.println("IOException " + e);
					}
					System.out.println("Deletion successfull");

				} else if (choice.equals("55")) {
					try {
						System.out.println("Enter the location");
						String fname = sc.next();

						File directory = new File(fname);

						MyFilenameFilter filter = new MyFilenameFilter("file.cpp");

						String[] flist = directory.list(filter);
						if (flist == null) {
							System.out.println("Empty directory or directory does not exists.");
						} else {
							for (int i = 0; i < flist.length; i++) {
								System.out.println(flist[i] + " found");
							}
						}

					}

					catch (Exception e) {
						e.printStackTrace();
					}

				}
			} else if (choice.equals("3")) {
				// write code to close the file
				System.out.println(" The program is been exited successfully.");
				sc.close();
				System.exit(0);
				System.out.println("application closed");

			}

			
		}
	}

	private static List<String> readFile(String fname) {

		List<String> list = Collections.emptyList();
		try {
			list = Files.readAllLines(Paths.get(fname), StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	// ------------------------------------

}
