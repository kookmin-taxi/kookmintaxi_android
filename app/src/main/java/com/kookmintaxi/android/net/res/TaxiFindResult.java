package com.kookmintaxi.android.net.res;

/**
 * Created by JaewookAhn on 15/02/2017.
 */

public class TaxiFindResult extends Common {

    public RoomInfo room_info;

    public static class RoomInfo {
        public User[] users;
        public int users_count;
        public boolean activation;
        public int activation_count;
        public boolean done;
    }
}
