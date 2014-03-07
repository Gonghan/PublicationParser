package relatedWorks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Start {

	private static void path(Person p1, Person p2) {
		CoauthorPath cp = new CoauthorPath(p1, p2);
		Person path[] = cp.getPath();
		int i;
		System.out.println("# of Person objects = " + Person.numberOfPersons());
		if (path == null) {
			System.out.println("<" + p1 + "," + p2 + "> not connected");
			return;
		}

		for (i = 0; i < path.length; i++) {
			System.out.println(i + ": " + path[i] + " "
					+ path[i].getNumberOfPublications() + " "
					+ (path[i].getPersonRecord() == null ? " " : "*"));
		}
		System.out.println();
	}

	public void readFile(String filename) {
		Scanner scanner;
		try {
			scanner = new Scanner(new File(filename));
			String line = scanner.next();
			int counter=0;
			while (scanner.hasNextLine()) {
				//System.out.println(line);
				line = scanner.nextLine();
				counter++;
			}
			System.out.println(counter);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Start().readFile("F:/SOC/hdblp.xml");
		
		Person p1, p2;
		p1 = Person.create("Michael Ley", "l/Ley:Michael");
		Publication pubs[] = p1.getPublications();
		for (Publication p : pubs) {
			// System.out.println(p.getKey());
		}
		p2 = Person.create("Peter Sturm", "s/Sturm:Peter");
		path(p1, p2);
		p2 = Person.create("Bernd Walter", "w/Walter:Bernd");
		path(p1, p2);
		p2 = Person.create("Alan T. Murray", "m/Murray:Alan_T=");
		path(p1, p2);
		p2 = Person.create("B. Vogel", "v/Vogel:B=");
		path(p1, p2);
		p2 = Person.create("Alan M. Turing", "t/Turing:Alan_M=");
		path(p1, p2);

	}

}
