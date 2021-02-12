package com.rayes;

import com.rayes.repository.CurrencyRepository;
import com.rayes.service.CurrencySaverService;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback(value = false)
public class CurrencySaverServiceTest extends TestCase {
    @Autowired
    CurrencySaverService saverService;

    @Autowired
    CurrencyRepository currencyRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() {
        currencyRepository.deleteByDateOfValue(LocalDate.now());
    }

    @Test
    public void shouldReturnFalseWhenTodayCurrencyListWasNotDownloaded() {
        boolean existingCurrencies = saverService.checkTodayCurrencyListInDb();
        assertFalse(existingCurrencies);
    }

    @Test
    public void shouldReturnTrueWhenTodayCurrencyListWasDownloaded() {
        saverService.saveCurrencyListToDb();

        boolean existingCurrencies = saverService.checkTodayCurrencyListInDb();

        assertTrue(existingCurrencies);
    }

    @Test
    public void shouldReturnZeroWhenCalculatingTheTimeBeforeStartWithTheCurrentTime() {
        Integer minutesLeft = saverService.calculateTimeBeforeStart(LocalTime.now());

        assertTrue(minutesLeft.equals(0));
    }

    @Test
    public void shouldReturnFifteenMinutesWhenCalculatingTheTimeBeforeStart() {
        Integer minutesLeft = saverService.calculateTimeBeforeStart(LocalTime.now().plusMinutes(15));

        assertTrue(minutesLeft.equals(15));
    }

    @Test
    public void shouldExecutingTaskWitchFixedRate() throws Exception {
        final Integer[] testInt = {0};
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(testInt[0]);
                testInt[0]++;
            }
        };
        saverService.saveScheduleManagement(task, LocalTime.now().plusSeconds(1), 1, TimeUnit.SECONDS);
        Thread.sleep(1001);

        assertTrue(testInt[0] > 0);

    }


}
