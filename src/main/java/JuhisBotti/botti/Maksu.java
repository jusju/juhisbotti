package JuhisBotti.botti;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class Maksu {

	private String henkilo;
	private LocalDate date;
	private double sum;
	private String info;

	public Maksu(double sum, String info, String henkilo) {
		this.henkilo = henkilo;
		this.sum = sum;
		this.info = info;
		this.date = LocalDate.now();
	}

	public Maksu(LocalDate date, double summa, String kuvaus, String maksaja) {
		this.henkilo = maksaja;
		this.sum = summa;
		this.info = kuvaus;
		this.date = date;
	}

	@Override
	public String toString() {
		return this.date + ";" + this.sum + ";" + this.info + ";" + this.henkilo;
	}

	public String show() {
		DecimalFormat des = new DecimalFormat("0.00");
		return this.date + des.format(this.sum) + " € " + this.info + "  " + "(" + this.henkilo + ")";
	}

	public double getSum() {
		return this.sum;
	}

	public int getYear() {
		return this.date.getYear();
	}

	public int getMonth() {
		return this.date.getMonthValue();
	}

	public String getHenkilo() {
		return this.henkilo;
	}

}
