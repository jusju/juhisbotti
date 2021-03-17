package JuhisBotti.botti;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kirjoittaja {

	private String tiedosto;

	public Kirjoittaja() {
		this.tiedosto = "taulukko.csv";
	}

	public void kirjoitaUusi(Maksu maksu) {

		List<Maksu> maksut = lista();
		maksut.add(maksu);

		try (PrintWriter writer = new PrintWriter(this.tiedosto)) {

			for (int i = 0; i < maksut.size(); i++) {
				writer.println(maksut.get(i).toString());
			}

			writer.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public void poistaRivi(int rivi) {
		List<Maksu> maksut = lista();
		List<Maksu> row = new ArrayList<>();

		for (int i = 0; i < maksut.size(); i++) {
			if (!(i == rivi - 1)) {
				row.add(maksut.get(i));
			}
		}

		try (PrintWriter writer = new PrintWriter(this.tiedosto)) {

			for (int i = 0; i < row.size(); i++) {
				writer.println(row.get(i).toString());
			}

			writer.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public List<Maksu> lista() {
		List<Maksu> maksut = new ArrayList<>();

		try (Scanner input = new Scanner(new File(this.tiedosto))) {

			while (input.hasNextLine()) {
				String row = input.nextLine();
				String[] block = row.split(";");
				LocalDate date = LocalDate.parse(block[0]);
				double sum = Double.valueOf(block[1]);
				String info = block[2];
				String henkilo = block[3];
				maksut.add(new Maksu(date, sum, info, henkilo));

			}

			input.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return maksut;
	}

	public double kaikkiMaksutYhteensa() {
		double sum = 0;

		List<Maksu> maksut = new ArrayList<>();
		maksut = lista();

		for (int i = 0; i < maksut.size(); i++) {
			sum += maksut.get(i).getSum();
		}

		return sum;

	}

	public double vuodenMaksu(int years) {
		double sum = 0;

		List<Maksu> maksut = new ArrayList<>();
		maksut = lista();

		for (int i = 0; i < maksut.size(); i++) {
			Maksu maksu = maksut.get(i);
			if (maksu.getYear() == years) {
				sum += maksu.getSum();
			}
		}

		return sum;

	}

	public double kkMaksu(int months, int years) {
		double sum = 0;

		List<Maksu> maksut = new ArrayList<>();
		maksut = lista();

		for (int i = 0; i < maksut.size(); i++) {
			Maksu maksu = maksut.get(i);
			if (maksu.getMonth() == months && maksu.getYear() == years) {
				sum += maksu.getSum();
			}
		}

		return sum;

	}

	public List<Maksu> kkMaksut(int kuukausi, int vuosi) {
		List<Maksu> maksut = new ArrayList<>();
		List<Maksu> all = new ArrayList<>();
		maksut = lista();

		for (int i = 0; i < maksut.size(); i++) {
			if (maksut.get(i).getMonth() == kuukausi && maksut.get(i).getYear() == vuosi) {
				all.add(maksut.get(i));
			}
		}

		return all;
	}

	public List<Maksu> vuodenMaksut(int vuosi) {
		List<Maksu> maksut = new ArrayList<>();
		List<Maksu> all = new ArrayList<>();
		maksut = lista();

		for (int i = 0; i < maksut.size(); i++) {
			if (maksut.get(i).getYear() == vuosi) {
				all.add(maksut.get(i));
			}
		}

		return all;
	}

	public double henkiloMaksu(String henkilo) {
		double sum = 0;

		List<Maksu> maksut = new ArrayList<>();
		maksut = lista();

		for (int i = 0; i < maksut.size(); i++) {
			Maksu maksu = maksut.get(i);
			if (maksu.getHenkilo().equals(henkilo)) {
				sum += maksu.getSum();
			}
		}

		return sum;
	}

	public double henkiloVuosi(String henkilo, int vuosi) {
		double sum = 0;

		List<Maksu> maksut = new ArrayList<>();
		maksut = lista();

		for (int i = 0; i < maksut.size(); i++) {
			Maksu maksu = maksut.get(i);
			if (maksu.getHenkilo().equals(henkilo) && maksu.getYear() == vuosi) {
				sum += maksu.getSum();
			}
		}

		return sum;
	}

	public List<Maksu> yhteensaHenkiloVuosi(String henkilo, int vuosi) {
		List<Maksu> maksut = new ArrayList<>();
		List<Maksu> all = new ArrayList<>();
		maksut = lista();

		for (int i = 0; i < maksut.size(); i++) {
			if (maksut.get(i).getHenkilo().equals(henkilo) && maksut.get(i).getYear() == vuosi) {
				all.add(maksut.get(i));
			}
		}

		return all;

	}

	public List<Maksu> henkiloMaksut(String henkilo) {
		List<Maksu> maksut = new ArrayList<>();
		List<Maksu> all = new ArrayList<>();
		maksut = lista();

		for (int i = 0; i < maksut.size(); i++) {
			if (maksut.get(i).getHenkilo().equals(henkilo)) {
				all.add(maksut.get(i));
			}
		}

		return all;

	}

	public void tyhjennaMaksut() {

		try (PrintWriter writer = new PrintWriter(this.tiedosto)) {

			writer.println("");

			writer.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
