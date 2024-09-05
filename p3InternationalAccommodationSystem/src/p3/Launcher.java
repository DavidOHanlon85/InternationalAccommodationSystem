package p3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Launcher {

	private static Scanner scanner = new Scanner(System.in);
	private static final int QUIT = 10;
	public static List<Accommodation> rooms = new ArrayList<Accommodation>();

	/**
	 * Entry point of program - no need to modify this method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launchMenu();
		scanner.close();// close scanner once menu system completes
	}

	// TODO modify readData method to populate List appropriately - method partially
	// completed already
	// TODO add static methods to this class as required to achieve tasks outlined
	// in menu
	// TODO modify launchMenu method to add calls to new methods you write etc to
	// accomplish the tasks outlined in the menu

	/**
	 * Launches menu system which in turn calls appropriate methods based on user
	 * choices Partially implemented already. Requires updating to add calls to
	 * other methods written to achieve the tasks described in tasks 3-9
	 */
	public static void launchMenu() {
		List<Accommodation> mainList = readRoomData("Rooms.csv");

		System.out.println();
		int option;
		System.out.println("AccommyNation.com - Wherever you go, you'll need to stay");

		// repeat until quit chosen
		do {
			displayOptions();

			// get input
			option = getMenuChoice("Enter choice ...");
			System.out.println();

			try {
				// process choice - invoke relevant methods etc.
				switch (option) {

				case 1:
					mainList = readRoomData("Rooms.csv");
					break;
				case 2:
					System.out.println("Number of places to stay is " + mainList.size());
					break;
				case 3:
					displayAllDetailsInListNumbered(rooms);
					break;
				case 4:
					Collections.sort(rooms, new CompareByPrice());
					displayAllDetailsInListNumbered(TopXNumberOfPlace(FilterByStars(rooms, 4), 3));
					System.out.println("Task Not Yet Implemented");
					// TODO add required method calls etc
					break;
				case 5:
					Map<String, Integer> numberOfCities = new HashMap<String, Integer>();
					int cityCounter = findNumberOfCities(numberOfCities);
					System.out.println("Number of cities: " + cityCounter);
					break;
				case 6:
					Collections.sort(rooms, new CompareByPrice().reversed());
					List<Accommodation> listOfAccom = TopXNumberOfPlace(
							filterByType(filterByCity(rooms, "Dublin"), Type.BNB), 4);
					displayAllDetailsInListNumbered(listOfAccom);
					break;
				case 7:
					List<Accommodation> accoInNYC = filterByCity(rooms, "New York");
					System.out.printf("Average Price in New York: %.2f", avgPriceForRoomList(accoInNYC));
					break;
				case 8:
					removeRooms(rooms, "Paris", 10);
					break;
				case 9:


					WriteToFile wtf = new WriteToFile();
					Thread t1 = new Thread(wtf);

					t1.run();

					break;
				case QUIT:
					System.out.println("Quitting");
					break;
				default:
					System.out.println("Try options again...");
				}

			} catch (Exception e) {
				System.out.println("Exception caught");
				System.out.println(e.getMessage());
				System.out.println("please try again");
			}

		} while (option != QUIT);
	}

	public static void removeRooms(List<Accommodation> rooms, String city, int boundaryValue)
			throws IllegalArgumentException {

		if (rooms == null || rooms.size() == 0) {
			throw new IllegalArgumentException("AL NULL OR EMPTY");
		}

		List<Accommodation> temp = new ArrayList<Accommodation>(rooms);

		for (Accommodation a : rooms) {
			if (a.getCity().equalsIgnoreCase(city) && a.getRooms() < boundaryValue) {
				temp.remove(a);
			}
		}

		rooms.clear();
		rooms.addAll(temp);

	}

	/**
	 * This method returns the average price for a room in a list
	 * 
	 * @param accoInNYC
	 */
	public static double avgPriceForRoomList(List<Accommodation> accoInNYC) throws IllegalArgumentException {

		if (accoInNYC == null || accoInNYC.size() == 0) {
			throw new IllegalArgumentException("AL NULL OR EMPTY");
		}

		double total = 0;

		for (Accommodation a : accoInNYC) {
			double cost = a.getPrice();
			total += cost;
		}

		double avg = total / accoInNYC.size();

		return avg;
	}

	/**
	 * This method filters by type
	 * 
	 * @param input
	 * @param type
	 * @return
	 */
	private static List<Accommodation> filterByType(List<Accommodation> input, Type type)
			throws IllegalArgumentException {

		List<Accommodation> results = new ArrayList<Accommodation>();

		if (input == null || input.size() == 0) {
			throw new IllegalArgumentException("AL NULL OR EMPTY");
		}

		for (Accommodation a : input) {
			if (a.getType() == type) {
				results.add(a);
			}
		}

		return results;
	}

	/**
	 * This method filters by city
	 * 
	 * @param input
	 * @param city
	 * @return
	 */
	private static List<Accommodation> filterByCity(List<Accommodation> input, String city)
			throws IllegalArgumentException {

		if (input == null || input.size() == 0) {
			throw new IllegalArgumentException("AL NULL OR EMPTY");
		}

		List<Accommodation> results = new ArrayList<Accommodation>();

		for (Accommodation a : input) {
			if (a.getCity().equalsIgnoreCase(city)) {
				results.add(a);
			}
		}

		return results;
	}

	/**
	 * This method returns the number of unique cities in a list
	 * 
	 * @param numberOfCities
	 * @return
	 */
	public static int findNumberOfCities(Map<String, Integer> numberOfCities) {
		int cityCounter = 0;

		for (Accommodation a : rooms) {
			if (numberOfCities.containsKey(a.getCity())) {
				continue;
			} else {
				numberOfCities.put(a.getCity(), 1);
				cityCounter++;
			}
		}
		return cityCounter;
	}

	public static List<Accommodation> TopXNumberOfPlace(List<Accommodation> filterByStars, int numberOfPlacesNeeded) {

		List<Accommodation> numberOfPlaces = new ArrayList<Accommodation>();

		for (int i = 0; i < numberOfPlacesNeeded; i++) {

			numberOfPlaces.add(filterByStars.get(i));
		}

		return numberOfPlaces;
	}

	public static List<Accommodation> FilterByStars(List<Accommodation> input, int minimumStars) {

		List<Accommodation> filteredByStars = new ArrayList<Accommodation>();

		for (Accommodation a : input) {
			if (a.getStars() >= 4) {
				filteredByStars.add(a);
			}
		}
		return filteredByStars;
	}

	/**
	 * This method displays all details about the hotels in the list to console in a
	 * number list
	 * 
	 * @param input
	 */
	private static void displayAllDetailsInListNumbered(List<Accommodation> input) {
		int counter = 1;
		for (Accommodation a : input) {
			System.out.println(counter + ")");
			a.printDetails();
			System.out.println();
			System.out.println();
			counter++;
		}

	}

	/**
	 * Read data from a given filename for a formatted csv file of accommodation
	 * data
	 * 
	 * @param filename
	 * @return list of accommodation objects for each row of the file containing
	 *         valid data
	 */
	public static List<Accommodation> readRoomData(String filename) {

		int attemptedReads = 0;

		// TODO update method to read from formatted csv

		File file = new File("Rooms.csv");

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line;

			line = br.readLine(); // read header

			line = br.readLine(); // read first line

			while (line != null) {

				attemptedReads++;

				try {
					String[] splitDetails = line.split(",");

					Accommodation a = new Accommodation();

					a.setName(splitDetails[0]);
					a.setType(Type.valueOf(splitDetails[1].toUpperCase()));

					int stars = Integer.parseInt(splitDetails[2]);

					if (stars >= 1 && stars <= 5) {
						a.setStars(stars);
					} else {
						throw new Exception("INVALID STARS - NOT ACCEPTED");
					}

					a.setCity(splitDetails[3]);

					a.setPrice(Double.parseDouble(splitDetails[4]));

					a.setRooms(Integer.parseInt(splitDetails[5]));

					rooms.add(a);

				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				line = br.readLine();
			}

			br.close();
			fr.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO create an Accommodation object for each line of the file and add to
		// rooms list
		// TODO skip any lines which contain data which doesnt match business rules
		System.out.println(attemptedReads + " attempted reads");
		System.out.println(rooms.size() + " entries read successfully");
		return rooms;
	}

	/**
	 * Display prompt and get int user input via static scanner repeat if invalid
	 * input type given - no need to modify this method
	 * 
	 * @return int value entered via scanner
	 */
	private static int getMenuChoice(String prompt) {
		try {
			System.out.println(prompt);
			int choice = scanner.nextInt();
			return choice;
		} catch (Exception e) {
			System.out.println("Invalid input try again");
			// clear buffer if required
			if (scanner.hasNext()) {
				scanner.next();// read and discard line break
			}
			return getMenuChoice(prompt);// return recursive call to method to recover
		}
	}

	/**
	 * Writes menu options to console - no need to modify this method
	 */
	private static void displayOptions() {
		System.out.println();
		System.out.println("Menu Options:");
		System.out.println("1. (Re)read Data From Original File (use to restore removed data for example)");
		System.out.println("2. Display the number of places to stay in the current list");
		System.out.println("3. Display details for all places to stay in the current list");
		System.out.println(
				"4. Display details of the 3 least expensive 4 Star places to stay in Los Angeles (low to high)");
		System.out.println("5. Display the number of cities in the current list");
		System.out.println("6. Display details of the 4 most expensive BnBs in Dublin (high to low)");
		System.out.println("7. Display the average price of a hotel in New York");
		System.out.println("8. Remove all entries with fewer than 10 rooms available for Paris from the current list");
		System.out.println("9. (Separate Thread) Write out to a formatted csv, "
				+ "\nthe name of each city and the average price of a hotel there (2 decimal places, alphabetcically by City name)");
		System.out.println("10. Quit");
	}

}
