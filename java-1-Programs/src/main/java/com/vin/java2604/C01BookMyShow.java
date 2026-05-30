package com.vin.java2604;

public class C01BookMyShow extends Thread {

        SeatBooking booking;
        String user;
        int seatCount;

        public C01BookMyShow(SeatBooking booking, String user, int seatCount) {
            this.booking = booking;
            this.user = user;
            this.seatCount = seatCount;
        }
        @Override
        public void run() {
            booking.bookSeat(user, seatCount);
        }
    public static void main(String[] args) {
        SeatBooking booking = new SeatBooking();
        C01BookMyShow show1 = new C01BookMyShow(booking, "User1", 3);
        C01BookMyShow show2 = new C01BookMyShow(booking, "User2", 2);
        C01BookMyShow show3 = new C01BookMyShow(booking, "User3", 5);

        show1.start();
        show2.start();
        show3.start();
    }
}
class SeatBooking{
    int totalSeats=10;

    public synchronized void bookSeat(String user, int seatCount){
        System.out.println("Booking "+seatCount+" seats for "+user);
        if(totalSeats>seatCount){
            System.out.println("Seats booked successfully for "+user);
            totalSeats=totalSeats-seatCount;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Not enough seats available for "+user);
        }
    }
}
