package p3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class test {

	// input data

	String validNameOrCityLow, validNameOrCityHigh;
	String invalidNameOrCityNull, invalidNameOrCityEmptyString, invalidNameOrCityNoChars;

	Type hostel;
	Type hotel;
	Type bnb;

	int validStarsLow, validStarsHigh, invalidStarsLow, invalidStarsHigh;

	double validPriceLow, validPriceHigh, invalidPriceLow;

	int validRoomsLow, validRoomsHigh, invalidRoomsLow;

	Accommodation a;

	@BeforeEach
	void setUp() throws Exception {

		validNameOrCityLow = "A";
		validNameOrCityHigh = "Belfast";

		invalidNameOrCityNull = null;
		invalidNameOrCityEmptyString = "";
		invalidNameOrCityNoChars = " ";

		hostel = Type.HOSTEL;
		hotel = Type.HOTEL;
		bnb = Type.BNB;

		validStarsLow = 1;
		validStarsHigh = 5;
		invalidStarsLow = 0;
		invalidStarsHigh = 6;

		validPriceLow = 1.0;
		validPriceHigh = 1000.0;
		invalidPriceLow = -1.0;

		validRoomsLow = 1;
		validRoomsHigh = 100;
		invalidRoomsLow = -1;

		a = new Accommodation(validNameOrCityHigh, bnb, validStarsLow, validNameOrCityHigh, validPriceLow,
				validRoomsLow);

	}

	@Test
	void testAccomodationDefaultConstructor() {
		assertNotNull(a);
	}

	@Test
	void testAccomodationConstructorWithArgsValid() {
		assertEquals(validNameOrCityHigh, a.getName());
		assertEquals(bnb, a.getType());
		assertEquals(validStarsLow, a.getStars());
		assertEquals(validNameOrCityHigh, a.getCity());
		assertEquals(validPriceLow, a.getPrice());
		assertEquals(validRoomsLow, a.getRooms());
	}

	@Test
	void testAccomodationConstructorWithArgsInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setName(invalidNameOrCityNull);
		});

		assertEquals("INVALID NAME", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setName(invalidNameOrCityEmptyString);
		});

		assertEquals("INVALID NAME", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setName(invalidNameOrCityNoChars);
		});

		assertEquals("INVALID NAME", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setType(null);
		});

		assertEquals("INVALID TYPE", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setStars(invalidStarsLow);
		});

		assertEquals("INVALID STARS", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setStars(invalidStarsHigh);
		});

		assertEquals("INVALID STARS", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setCity(invalidNameOrCityNull);
		});

		assertEquals("INVALID CITY", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setCity(invalidNameOrCityEmptyString);
		});

		assertEquals("INVALID CITY", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setCity(invalidNameOrCityNoChars);
		});

		assertEquals("INVALID CITY", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setPrice(invalidPriceLow);
		});

		assertEquals("INVALID PRICE", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setRooms(invalidRoomsLow);
		});

		assertEquals("INVALID ROOMS", exp.getMessage());

	}

	@Test
	void testSetGetNameValid() {
		a.setName(validNameOrCityLow);
		assertEquals(validNameOrCityLow, a.getName());

		a.setName(validNameOrCityHigh);
		assertEquals(validNameOrCityHigh, a.getName());
	}

	@Test
	void testSetGetNameInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setName(invalidNameOrCityNull);
		});

		assertEquals("INVALID NAME", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setName(invalidNameOrCityEmptyString);
		});

		assertEquals("INVALID NAME", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setName(invalidNameOrCityNoChars);
		});

		assertEquals("INVALID NAME", exp.getMessage());
	}

	@Test
	void testSetGetTypeValid() {
		a.setType(bnb);
		assertEquals(bnb, a.getType());

		a.setType(hostel);
		assertEquals(hostel, a.getType());

		a.setType(hotel);
		assertEquals(hotel, a.getType());
	}

	@Test
	void testSetGetTypeInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setType(null);
		});

		assertEquals("INVALID TYPE", exp.getMessage());
	}

	@Test
	void testSetGetStarsValid() {
		a.setStars(validStarsLow);
		assertEquals(validStarsLow, a.getStars());

		a.setStars(validStarsHigh);
		assertEquals(validStarsHigh, a.getStars());

	}

	@Test
	void testSetGetStarsInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setStars(invalidStarsLow);
		});

		assertEquals("INVALID STARS", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setStars(invalidStarsHigh);
		});

		assertEquals("INVALID STARS", exp.getMessage());
	}

	@Test
	void testSetGetCityValid() {
		a.setCity(validNameOrCityHigh);
		assertEquals(validNameOrCityHigh, a.getCity());

		a.setCity(validNameOrCityLow);
		assertEquals(validNameOrCityLow, a.getCity());
	}

	@Test
	void testSetGetCityInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setCity(invalidNameOrCityNull);
		});

		assertEquals("INVALID CITY", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setCity(invalidNameOrCityEmptyString);
		});

		assertEquals("INVALID CITY", exp.getMessage());

		exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setCity(invalidNameOrCityNoChars);
		});

		assertEquals("INVALID CITY", exp.getMessage());

	}

	@Test
	void testSetGetPriceValid() {
		a.setPrice(validPriceLow);
		assertEquals(validPriceLow, a.getPrice());
		
		a.setPrice(validPriceHigh);
		assertEquals(validPriceHigh, a.getPrice());
	}

	@Test
	void testSetGetPriceInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setPrice(invalidPriceLow);
		});

		assertEquals("INVALID PRICE", exp.getMessage());
	}

	@Test
	void testSetGetRoomsValid() {
		a.setRooms(validRoomsLow);
		assertEquals(validRoomsLow, a.getRooms());
		
		a.setRooms(validRoomsHigh);
		assertEquals(validRoomsHigh, a.getRooms());
	}

	@Test
	void testSetGetRoomsInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			a.setRooms(invalidRoomsLow);
		});

		assertEquals("INVALID ROOMS", exp.getMessage());
	}

}
