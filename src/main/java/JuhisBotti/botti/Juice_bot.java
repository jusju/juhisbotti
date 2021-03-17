package JuhisBotti.botti;

import java.text.DecimalFormat;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public class Juice_bot extends TelegramLongPollingBot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		// System.out.println(update.getMessage().getText());
		String command = update.getMessage().getText();
		SendMessage message = new SendMessage();
		message.setChatId(String.valueOf(update.getMessage().getChatId()));

		Kirjoittaja writer = new Kirjoittaja();

		if (command.equals("/help")) {
			message.setText("Voit käyttää bottia komennoilla:\n/tekija\n/paid");
		}
		if (command.equals("/start")) {
			message.setText(
					"Tervetuloa käyttämään Juhiksen bottia!" + "" + "\nLisätietoa komennolla /help !" + "");
		}
		if (command.equals("/tekija"))

		{
			message.setText("Botti on Juhiksen käsialaa!" + "");
		}

		if (command.equals("/paid")) {

			String msg = "Taulukon komennot:\nKutsukomento: /paid\nLisää suoritus: maksu + kuvaus + määrä(€)\nPoista maksu: poista  + rivinro\nListaa maksut: list\nMaksujen yhteissumma: sum\nTietyn kuukauden maksut: count + 2 + 2020\n"
					+ "Etsi kuukauden tai vuoden maksut: find + 2 + 2020 tai find 2020\nHenkilon maksut: search + Matti (+vuosi)\nTyhjennä taulu: clean";
			message.setText(msg);
		}

		if (command.contains(" ")) {
			String[] comm = command.split(" ");
			String test = comm[0];
			String array = "/paid";
			DecimalFormat des = new DecimalFormat("0.00");
			String[] month = { "tammikuu", "helmikuu", "maaliskuu", "huhtikuu", "toukokuu", "kesäkuu", "heinäkuu",
					"elokuu", "syyskuu", "lokakuu", "marraskuu", "joulukuu" };

			if (comm[1].equals("maksu") && test.equals(array)) {
				String amount = comm[comm.length - 1];

				double sum = Double.valueOf(amount);
				String store = update.getMessage().getFrom().getFirstName();
				String info = "";
				for (int i = 2; i < comm.length - 1; i++) {
					info += comm[i] + " ";
				}
				writer.kirjoitaUusi(new Maksu(sum, info, store));
				message.setText("Uusi maksu tallennettu!");

			} else if (comm[1].equals("poista") && test.equals(array)) {
				writer.poistaRivi(Integer.valueOf(comm[2]));

				message.setText("Maksu poistettu!");

			}

			else if (comm[1].equals("list") && test.equals(array)) {
				List<Maksu> maksut = writer.lista();
				String mes = "Kaikki maksut:\n";
				if (maksut.isEmpty()) {
					message.setText("Listassa ei ole maksuja!");
				} else {
					for (int i = 0; i < maksut.size(); i++) {
						mes += maksut.get(i).show() + "\n";
					}
					message.setText(mes);

				}

			} else if (comm[1].equals("sum") && test.equals(array)) {
				message.setText("Maksut yhteensä: " + des.format(writer.kaikkiMaksutYhteensa()) + "€");

			} else if (comm[1].equals("count") && test.equals(array)) {
				int months = Integer.valueOf(comm[2]);
				int years = Integer.valueOf(comm[3]);
				double kkMaksut = writer.kkMaksu(months, years);
				message.setText(
						"Maksut vuoden " + years + " " + month[months - 1] + "ssa " + des.format(kkMaksut) + "€");
			}

			else if (comm[1].equals("find") && test.equals(array)) {
				String mes = "";
				if (comm.length == 3) {
					int years = Integer.valueOf(comm[2]);
					mes = "Vuoden " + years + " maksut yhteensä " + des.format(writer.vuodenMaksu(years)) + "\n";
					List<Maksu> maksut = writer.vuodenMaksut(years);
					for (int i = 0; i < maksut.size(); i++) {
						mes += maksut.get(i).show() + "\n";
					}
					message.setText(mes);

				} else if (comm.length == 4) {
					int months = Integer.valueOf(comm[2]);
					int years = Integer.valueOf(comm[3]);
					mes = "Vuoden " + years + " " + month[months - 1] + "ssa maksut yhteensä "
							+ des.format(writer.kkMaksu(months, years)) + "\n";
					List<Maksu> maksut = writer.kkMaksut(months, years);
					for (int i = 0; i < maksut.size(); i++) {
						mes += maksut.get(i).show() + "\n";
					}
					message.setText(mes);

				}

			} else if (comm[1].equals("search") && test.equals(array)) {
				String henkilo = comm[2];
				String mes = "";

				if (comm.length == 3) {
					mes = "Henkilön " + henkilo + " maksut yhteensä " + des.format(writer.henkiloMaksu(henkilo))
							+ "€\n";
					List<Maksu> maksut = writer.henkiloMaksut(henkilo);
					for (int i = 0; i < maksut.size(); i++) {
						mes += maksut.get(i).show() + "\n";
					}
					message.setText(mes);
				} else if (comm.length == 4) {
					mes = "Henkilön " + henkilo + " maksut yhteensä "
							+ des.format(writer.henkiloVuosi(henkilo, Integer.valueOf(comm[3]))) + "€ vuonna "
							+ Integer.valueOf(comm[3]) + "\n";
					List<Maksu> maksut = writer.yhteensaHenkiloVuosi(henkilo, Integer.valueOf(comm[3]));
					for (int i = 0; i < maksut.size(); i++) {
						mes += maksut.get(i).show() + "\n";
					}
					message.setText(mes);
				}
			}

			else if (comm[1].equals("clean") && test.equals(array)) {
				writer.tyhjennaMaksut();
				message.setText("Lista tyhjennetty!");

			}
		}

		if (message.getText() != null) {
			try {
				execute(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "juiicebot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "1672474408:AAFMFC0-pzAzQbM_7VNiQVU71pPRvgTRYrc";
	}

}