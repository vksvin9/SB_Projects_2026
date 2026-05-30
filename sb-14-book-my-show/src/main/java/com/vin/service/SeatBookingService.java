package com.vin.service;

import com.vin.entity.Seat;
import com.vin.exception.ResourceNotFoundException;
import com.vin.exception.SeatAlreadyBookedException;
import com.vin.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatBookingService {

    private final SeatRepository seatRepository;

    /*
        synchronized prevents race condition
        Only one thread can execute booking at a time
     */
    public synchronized String bookSeat(String seatNumber) {

        Seat seat = seatRepository.findBySeatNumber(seatNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seat not found"));

        if (seat.isBooked()) {
            throw new SeatAlreadyBookedException(
                    "Seat already booked"
            );
        }

        // simulate payment delay
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        seat.setBooked(true);

        seatRepository.save(seat);

        return "Seat booked successfully : " + seatNumber;
    }
}