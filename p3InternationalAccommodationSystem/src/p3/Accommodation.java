/**
 * 
 */
package p3;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class Accommodation {

	// Instance Variables

	private String name;
	private Type type;
	private int stars;
	private String city;
	private double price;
	private int rooms;

	// Constructors

	/**
	 * Default Constructor
	 */
	public Accommodation() throws IllegalArgumentException {

	}

	/**
	 * Constructor with args
	 * 
	 * @param name
	 * @param type
	 * @param stars
	 * @param city
	 * @param price
	 * @param rooms
	 */
	public Accommodation(String name, Type type, int stars, String city, double price, int rooms)
			throws IllegalArgumentException {
		this.setName(name);
		this.setType(type);
		this.setStars(stars);
		this.setCity(city);
		this.setPrice(price);
		this.setRooms(rooms);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 * @throws IllegalArgumentException if null or less than one character
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (name == null || name.equals(" ") || name.equals("") || name.length() < 1) {
			throw new IllegalArgumentException("INVALID NAME");
		} else {
			this.name = name;
		}

	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 * @throws IllegalArgumentException
	 */
	public void setType(Type type) throws IllegalArgumentException {
		if (type == null) {
			throw new IllegalArgumentException("INVALID TYPE");
		} else {
			this.type = type;
		}

	}

	/**
	 * @return the stars
	 */
	public int getStars() {
		return stars;
	}

	/**
	 * 
	 * @param stars
	 * @throws IllegalArgumentException
	 */
	public void setStars(int stars) throws IllegalArgumentException {
		if (stars >= 1 && stars <= 5) {
			this.stars = stars;
		} else {
			throw new IllegalArgumentException("INVALID STARS");
		}

	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city
	 * @throws IllegalArgumentException
	 */
	public void setCity(String city) throws IllegalArgumentException {
		if (city == null || city.equals(" ") || city.equals("") || city.length() < 1) {
			throw new IllegalArgumentException("INVALID CITY");
		} else {
			this.city = city;
		}

	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price
	 * @throws IllegalArgumentException
	 */
	public void setPrice(double price) throws IllegalArgumentException {
		if (price < 0) {
			throw new IllegalArgumentException("INVALID PRICE");
		} else {
			this.price = price;
		}

	}

	/**
	 * @return the rooms
	 */
	public int getRooms() {
		return rooms;
	}

	/**
	 * 
	 * @param rooms
	 * @throws IllegalArgumentException
	 */
	public void setRooms(int rooms) throws IllegalArgumentException {
		if (rooms < 0) {
			throw new IllegalArgumentException("INVALID ROOMS");
		} else {
			this.rooms = rooms;
		}

	}
	
	public void printDetails() {
		System.out.println("Name:    " + getName());
		System.out.println("City:    " + getCity());
		System.out.println("Type:    " + getType());
		System.out.printf("Capacity:%d Rooms @ £%.2f\n", getRooms(), getPrice());
		System.out.print("Rating   ");
		printStars(getStars());
	}
	
	private void printStars(int stars) {
		for (int i = 0; i < stars; i++) {
			System.out.print("*");
		}
	}

}
