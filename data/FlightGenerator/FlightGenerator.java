import java.time.*;
import java.util.Random;
import java.util.UUID;

public class FlightGenerator {
    private final String[] AIRPORTS = {"ATL", "DFW", "LAX", "JFK", "MIA", "LHR", "HND"};
    private Random rand;

    public FlightGenerator() {
        this.rand = new Random();
    }
    public static void main(String[] args) {
        FlightGenerator fg = new FlightGenerator();
        fg.run();
    }

    public void run(){
        for (int i = 0; i < AIRPORTS.length-1; i++) {
            for (int k = i+1; k < AIRPORTS.length; k++) {
                for (int j = 0; j < 3; j++) {
                    if (!(i == 0 && k == 1 && j == 0)) System.out.print(",");
                    else System.out.print("\t");
                    String UUID = genUUID();
                    String departureCode = AIRPORTS[i];
                    String arrivalCode = AIRPORTS[k];
                    LocalTime departureTime = genDepartTime(rand);
                    Duration flightDuration = genFlightDuration(rand);
                    LocalTime arrivalTime = genArrivalTime(departureTime, flightDuration);
                    int firstSeats = rand.nextInt(2, 7);
                    int businessSeats = rand.nextInt(2, 7);
                    int economySeats = rand.nextInt(2, 7);
                    boolean isInternational = (departureCode.equals("LHR") || departureCode.equals("HND") || arrivalCode.equals("LHR") || arrivalCode.equals("HND"));

                    System.out.print(FormatObject(UUID, departureCode, arrivalCode, departureTime, arrivalTime, 
                        flightDuration.toMinutes(), firstSeats, businessSeats, economySeats, isInternational));
                }
            }
        }
    }

    public String genUUID() {
        return UUID.randomUUID().toString();
    }

    public Duration genFlightDuration(Random rand) {
        int minutes = rand.nextInt(90,240);
        return Duration.ofMinutes(minutes);
    }

    public LocalTime genDepartTime(Random rand) {
        int hour = rand.nextInt(0, 24);
        int minute = rand.nextInt(0,60);
        return LocalTime.of(hour, minute);
    }

    public LocalTime genArrivalTime(LocalTime departureTime, Duration flightDuration) {
        return departureTime.plusMinutes(flightDuration.toMinutes());
    }

    public String FormatObject(String UUID, String departureCode, String arrivalCode, LocalTime departureTime, 
        LocalTime arrivalTime, long flightDuration, int firstSeats, int businessSeats, int economySeats, boolean isInternational) {
        return "{" +
                "\n\t\t\"flightID\": \"" + UUID + "\"," +
                "\n\t\t\"departureCode\": \"" + departureCode + "\"," +
                "\n\t\t\"arrivalCode\": \"" + arrivalCode + "\"," +
                "\n\t\t\"departureTime\": \"" + departureTime + "\"," +
                "\n\t\t\"arrivalTime\": \"" + arrivalTime + "\"," +
                "\n\t\t\"duration\": \"" + flightDuration + "\"," +
                "\n\t\t\"firstSeats\": " + firstSeats + "," +
                "\n\t\t\"businessSeats\": " + businessSeats + "," +
                "\n\t\t\"economySeats\": " + economySeats + "," +
                "\n\t\t\"isFull\": false," +
                "\n\t\t\"isInternational\": " + isInternational +
            "\n\t}";
    }
}
