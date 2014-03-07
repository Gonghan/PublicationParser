package relatedWorks;

import java.util.ArrayList;

import com.gonghan.model.Article;
import com.gonghan.model.MyPerson;

public class Start {

	public static Person[] path(String s1,String s2){
		return path(Person.create(s1,toURLPT(s1)),Person.create(s2,toURLPT(s2)));
	}
	private static Person[] path(Person p1, Person p2) {
		CoauthorPath cp = new CoauthorPath(p1, p2);
		Person path[] = cp.getPath();
		int i;
		System.out.println("# of Person objects = " + Person.numberOfPersons());
		if (path == null) {
			System.out.println("<" + p1 + "," + p2 + "> not connected");
			return null;
		}

		for (i = 0; i < path.length; i++) {
			System.out.println(i + ": " + path[i] + " "
					+ path[i].getNumberOfPublications() + " "
					+ (path[i].getPersonRecord() == null ? " " : "*"));
		}
		System.out.println();
		return path;
	}

	public static void main(String[] args) {

		Person p1, p2;

		p1 = Person.create("Michael Ley", "l/Ley:Michael");
		p2 = Person.create("Peter Sturm", "s/Sturm:Peter");
		path(p1, p2);
		MyPerson mp = new MyPerson(p1.getName());
		ArrayList<Article> list = mp.getArticles();
		for (Article a : list) {
			System.out.println(a.toString());
		}
		// p2 = Person.create("Bernd Walter", "w/Walter:Bernd");
		// path(p1, p2);
		// p2 = Person.create("Alan T. Murray", "m/Murray:Alan_T=");
		// path(p1, p2);
		// p2 = Person.create("B. Vogel", "v/Vogel:B=");
		// path(p1, p2);
		// p2 = Person.create("Alan M. Turing", "t/Turing:Alan_M=");
		// path(p1, p2);
	}

	private static String toURLPT(String s) {
		s = s.replace(".", "=");
		String strs[] = s.split(" ");
		String last = strs[strs.length - 1];
		String first = strs[0];
		if (strs.length == 3) {
			first = first + "_" + strs[1];
		}
		char firstLetter = Character.toLowerCase(last.charAt(0));
		return firstLetter + "/" + last + ":" + first;
	}
}
