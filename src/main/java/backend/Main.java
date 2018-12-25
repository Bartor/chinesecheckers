package backend;

import backend.socketing.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(1234);
        server.start();
        /***
         * ogólnie to przydałoby się tu dodać dużo observerów
         * i je implementować nawet tą klasą główną
         * one mają jedną metodę UPDATE, która przyjmuje stringa
         * string to będzie JSON postaci:
         * {"type": ..., "content": ...}
         * gdzie np. type to będzie "move" albo "connected" itd.
         * input działa na osobnym wątku, można odebrać na luzie kilka wiadomości od klienta
         * no i no w sumie tyle
         * może ci się uda to ogarnąć xD bo ten mój aktualny szkic tu jest bardzo chaotyczny
         ***/
    }
}
