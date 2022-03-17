package data.FlightGenerator;

import java.util.UUID;

public class uuidGenerator {

    public static void main(String[] args){
        for(int i=0; i<5; i++){
            System.out.println(genUUID());
        }
    }

    public static String genUUID() {
        return UUID.randomUUID().toString();
    }
}
