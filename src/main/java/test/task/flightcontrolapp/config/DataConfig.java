package test.task.flightcontrolapp.config;

import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.task.flightcontrolapp.model.Baggage;
import test.task.flightcontrolapp.model.Destination;
import test.task.flightcontrolapp.model.Flight;
import test.task.flightcontrolapp.model.Passenger;
import test.task.flightcontrolapp.model.Ticket;
import test.task.flightcontrolapp.repository.BaggageRepository;
import test.task.flightcontrolapp.repository.CouponRepository;
import test.task.flightcontrolapp.repository.DestinationRepository;
import test.task.flightcontrolapp.repository.FlightRepository;
import test.task.flightcontrolapp.repository.PassengerRepository;
import test.task.flightcontrolapp.repository.TicketRepository;

@Configuration
public class DataConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataConfig.class);

    @Bean
    public BaggageRepository createBaggage() {
        BaggageRepository br = new BaggageRepository();
        br.saveAll(List.of(
                new Baggage(1L, 1.1f, 1L),
                new Baggage(2L, 2.3f, 1L),
                new Baggage(3L, 6.0f, 2L),
                new Baggage(4L, 3.7f, 3L),
                new Baggage(5L, 1.2f, 1L)
        ));
        logger.info("Baggage data is created");
        return br;
    }

    @Bean
    public DestinationRepository createDestinations() {
        DestinationRepository dr = new DestinationRepository();
        dr.saveAll(List.of(
                new Destination(1L, "Nigeria", List.of(1L, 3L)),
                new Destination(2L, "Ukraine", List.of(2L)),
                new Destination(3L, "France", List.of(4L)),
                new Destination(4L, "Mexico", List.of(5L, 6L))
        ));
        logger.info("Destinations data is created");
        return dr;
    }

    @Bean
    public FlightRepository createFlights() {
        FlightRepository fr = new FlightRepository();
        fr.saveAll(List.of(
                new Flight(1L, "AH-225", List.of(1L, 4L, 7L)),
                new Flight(2L, "KC-135", List.of(2L, 5L, 8L)),
                new Flight(3L, "AH-24", List.of(3L, 10L, 13L)),
                new Flight(4L, "AH-12BK", List.of(16L, 6L, 14L)),
                new Flight(5L, "CRJ-200", List.of(17L, 11L, 9L)),
                new Flight(6L, "TY-154", List.of(18L, 12L, 15L))
        ));
        logger.info("Flights data is created");
        return fr;
    }

    @Bean
    public PassengerRepository createPassenger() {
        PassengerRepository pr = new PassengerRepository();
        pr.saveAll(List.of(
                new Passenger(1L, "Sam", "Obissanya", List.of(1L, 2L, 3L)),
                new Passenger(2L, "Ada", "Kwens", List.of(4L, 5L, 6L)),
                new Passenger(3L, "Chi", "Omyama", List.of(7L, 8L, 9L)),
                new Passenger(4L, "Taras", "Leonovets", List.of(10L, 11L, 12L)),
                new Passenger(5L, "Dart", "Weider", List.of(13L, 14L, 15L)),
                new Passenger(6L, "Obi wan", "Kenobi", List.of(16L, 17L, 18L))
        ));
        logger.info("Passengers data is created");
        return pr;
    }

    @Bean
    public TicketRepository createTickets() {
        TicketRepository tr = new TicketRepository();
        tr.saveAll(List.of(
                new Ticket(1L, 111, 1L,"S1", 1L),
                new Ticket(2L, 221, 2L, "S2", 1L),
                new Ticket(3L, 331, 3L, "S3", 1L),
                new Ticket(4L, 412, 1L, "A4", 2L),
                new Ticket(5L, 522, 2L, "A5", 2L),
                new Ticket(6L, 642, 4L, "A6", 2L),
                new Ticket(7L, 713, 1L, "C7", 3L),
                new Ticket(8L, 823, 2L, "C8", 3L),
                new Ticket(9L, 953, 5L, "C9", 3L),
                new Ticket(10L, 134, 3L, "T10", 4L),
                new Ticket(11L, 154, 5L, "T11", 4L),
                new Ticket(12L, 264, 6L, "T12", 4L),
                new Ticket(13L, 335, 3L, "DW13", 5L),
                new Ticket(14L, 445, 4L, "DW14", 5L),
                new Ticket(15L, 565, 6L, "DW15", 5L),
                new Ticket(16L, 646, 4L, "O16", 6L),
                new Ticket(17L, 765, 5L, "O17", 6L),
                new Ticket(18L, 866, 6L, "O18", 6L),
                new Ticket(19L, 911, 1L, "U19", null),
                new Ticket(20L, 203, 3L, "U20", null),
                new Ticket(21L, 215, 5L, "U21", null)
        ));
        logger.info("Tickets data is created");
        return tr;
    }

    @Bean
    public CouponRepository createCoupons() {
        logger.info("Coupons data is creating");
        return new CouponRepository(Set.of(17L, 53L, 835L, 9123L));
    }
}
