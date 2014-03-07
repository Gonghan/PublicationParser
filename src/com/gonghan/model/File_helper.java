package com.gonghan.model;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class File_helper {

	private final String filename = "F:\\SOC\\dblp.xml";

	public void scan() {
		Scanner scan;
		try {
			scan = new Scanner(Paths.get(filename));
			String line ="";
			for(int i=0;i<1000;i++){
				line=scan.next();
				System.out.println(line);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		File_helper f = new File_helper();
		f.scan();
	}
}
