/**
 * 
 */
package p3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.security.auth.login.AccountNotFoundException;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class WriteToFile implements Runnable {

	@Override
	public void run() {

		List<Accommodation> localCopy = Launcher.rooms;

		File file = new File("averageCosts.csv");

		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			Map<String, Double> cityAndTotalCost = new TreeMap<String, Double>();
			
			
			List<Accommodation> onlyHotels = new ArrayList<Accommodation>();
			
			for (Accommodation a : Launcher.rooms) {
				if (a.getType().equals(Type.HOTEL)) {
					onlyHotels.add(a);
				}
			}

			for (Accommodation a : onlyHotels) {
				if (cityAndTotalCost.containsKey(a.getCity())) {
					double currentTotal = cityAndTotalCost.get(a.getCity());
					cityAndTotalCost.put(a.getCity(), currentTotal + a.getPrice());
				} else {
					cityAndTotalCost.put(a.getCity(), a.getPrice());
				}
			}

			Map<String, Integer> cityAndNumberOfPlacesToStay = new TreeMap<String, Integer>();

			for (Accommodation a : onlyHotels) {

					if (cityAndNumberOfPlacesToStay.containsKey(a.getCity())) {
						int currentPlacesToStay = cityAndNumberOfPlacesToStay.get(a.getCity());
						cityAndNumberOfPlacesToStay.put(a.getCity(), currentPlacesToStay + 1);
					} else {
						cityAndNumberOfPlacesToStay.put(a.getCity(), 1);
					}
				
			}

			bw.write("City, Average Price");
			bw.newLine();

			for (String key : cityAndTotalCost.keySet()) {

				String line = String.format("%s, %.2f", key,
						(cityAndTotalCost.get(key) / cityAndNumberOfPlacesToStay.get(key)));

				bw.write(line);
				bw.newLine();

			}

			if (Thread.currentThread().isInterrupted()) {
				bw.close();
				fw.close();
			}

			bw.close();
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
