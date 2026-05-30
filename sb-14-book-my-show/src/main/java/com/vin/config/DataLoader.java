package com.vin.config;

import com.vin.entity.Seat;
import com.vin.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final SeatRepository seatRepository;

    @Override
    public void run(String... args) {

        for (int i = 1; i <= 5; i++) {

            Seat seat = Seat.builder()
                    .seatNumber("A" + i)
                    .booked(false)
                    .build();

            seatRepository.save(seat);
        }
    }
}