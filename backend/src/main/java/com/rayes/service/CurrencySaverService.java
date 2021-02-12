package com.rayes.service;

import com.rayes.entity.Currency;
import com.rayes.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class CurrencySaverService {
    private static final Logger LOGGER = Logger.getLogger(CurrencySaverService.class.getName());

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Value("${period}")
    private Integer period;

    @Value("${timePointHour}")
    private Integer timePointHour;

    @Value("${timePointMinute}")
    private Integer timePointMinute;

    /**
     * Method for starting the save management schedule.
     * @throws Exception
     */
    @PostConstruct
    public void startWatch() throws Exception {
        LOGGER.info("START WATCH.");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                saveCurrencyListToDb();
            }
        };
        saveScheduleManagement(task, LocalTime.of(timePointHour, timePointMinute), period, TimeUnit.HOURS);
    }

    /**
     * Method for fetching and saving to DB currency list.
     */
    public void saveCurrencyListToDb() {
        LOGGER.info("SAVING CURRENCY LIST TO DB.");
        List<Currency> currencyList = currencyService.fetchCurrencyFromCRB();
        currencyRepository.saveAll(currencyList);
    }

    /**
     * Method for checking of exist today currency list in DB.
     * @return false if today currency list not exist in DB, else return true.
     */
    public boolean checkTodayCurrencyListInDb() {
        LOGGER.info("CHECKING OF EXISTING CURRENCY LIST IN DB.");
        List<Optional<Currency>> optionalList = currencyRepository.findByDateOfValue(LocalDate.now());

        return !optionalList.isEmpty();
    }

    /**
     * Method for calculating the left time before start.
     * @param timePoint
     * @return integer number of the left minutes.
     */
    public Integer calculateTimeBeforeStart(LocalTime timePoint) {
        LOGGER.info("CALCULATING TIME BEFORE START.");
        LocalTime now = LocalTime.now();

        LocalTime timeLeftUntilTomorrow = timePoint.minusHours(now.getHour()).minusMinutes(now.getMinute());

        return timeLeftUntilTomorrow.getHour() * 60 + timeLeftUntilTomorrow.getMinute();
    }

    /**
     * Method for save management schedule.
     * @param task Runnable task for executing
     * @param timePoint time before start
     * @param period rate
     * @param timeUnit time unit
     */
    public void saveScheduleManagement(Runnable task, LocalTime timePoint, Integer period, TimeUnit timeUnit) {
        LOGGER.info("START UP SAVE SCHEDULE MANAGEMENT.");
        if (checkTodayCurrencyListInDb()) {
            saveCurrencyListToDb();
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(task, calculateTimeBeforeStart(timePoint), period, timeUnit);
        } else {
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(task, calculateTimeBeforeStart(timePoint), period, timeUnit);
        }
    }


}
