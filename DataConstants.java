public abstract class DataConstants {
    protected static final String FLIGHTS_FILE_NAME = "flights.json";
    protected static final String FLIGHTS_FLIGHT_ID = "flightID";
    protected static final String FLIGHTS_ARRIVAL_CODE = "arrivalCode";
    protected static final String FLIGHTS_DEPARTURE_TIME = "departureTime";
    protected static final String FLIGHTS_ARRIVAL_TIME = "arrivalTime";
    protected static final String FLIGHTS_DURATION = "duration";
    protected static final String FLIGHTS_FIRST_SEATS = "firstSeats";
    protected static final String FLIGHTS_BUSINESS_SEATS = "businessSeats";
    protected static final String FLIGHTS_ECONOMY_SEATS = "economySeats";
    protected static final String FLIGHTS_IS_FULL = "isFull";
    protected static final String FLIGHTS_IS_INTERNATIONAL = "isInternational";
    protected static final String FLIGHTS_SEATS = "seats";

    protected static final String SEATS_SEAT_ID = "seatID";
    protected static final String SEATS_SEAT_NUM = "seatNum";
    protected static final String SEATS_SEAT_CLASS = "seatClass";
    protected static final String SEATS_TYPE = "type";
    protected static final String SEATS_BOOKED = "booked";

    protected static final String HOTELS_FILE_NAME = "hotels.json";
    protected static final String HOTELS_HOTEL_ID = "hotelID";
    protected static final String HOTELS_HOTEL_RATING = "hotelRating";
    protected static final String HOTELS_CAPACITY = "hotelCapacity";
    protected static final String HOTELS_NAME = "hotelName";
    protected static final String HOTELS_LOCATION = "location";
    protected static final String HOTELS_AIRPORT_CODE = "airCode";
    
    protected static final String ROOMS_FILE_NAME = "rooms.json";
    protected static final String ROOMS_ROOM_ID = "roomID";
    protected static final String ROOMS_HOTEL_ID = "hotelID";
    protected static final String ROOMS_NOT_AVAIL = "notAvail";
    protected static final String ROOMS_BED_TYPE = "bedType";
    protected static final String ROOMS_IS_SMOKING = "isSmoking";
    protected static final String ROOMS_HAS_BALCONY = "hasBalcony";
    protected static final String ROOMS_HAS_PULLOUT_COUCH = "hasPulloutCouch";

    protected static final String FLIGHT_BOOKINGS_FILE_NAME = "flightBookings.json";
    protected static final String FLIGHT_BOOKINGS_USER_ID = "userID";
    protected static final String FLIGHT_BOOKINGS_GUEST_INFO = "guestInfo";
    protected static final String FLIGHT_BOOKINGS_FLIGHT_ID = "flightID";
    protected static final String FLIGHT_BOOKINGS_SEAT_ID = "seatID";
    protected static final String FLIGHT_BOOKINGS_NUM_CHECKED_BAGS = "numCheckedBags";

    protected static final String GUEST_INFO_NAME = "name";
    protected static final String GUEST_INFO_AGE = "age";
    protected static final String GUEST_INFO_SEAT_ID = "seatID";
    
    protected static final String HOTEL_BOOKINGS_FILE_NAME = "hotelBookings.json";
    protected static final String HOTEL_BOOKINGS_USER_ID = "userID";
    protected static final String HOTEL_BOOKINGS_ROOM_ID = "roomID";
    protected static final String HOTEL_BOOKINGS_DATE_STAY = "dateStay";
}
