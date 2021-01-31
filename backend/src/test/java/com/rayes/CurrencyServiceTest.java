package com.rayes;

import com.rayes.entity.Currency;
import com.rayes.service.CurrencyService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CurrencyServiceTest extends TestCase {
    @Autowired
    CurrencyService currencyService;

    @Test
    public void whenCallFetchCurrencyListShouldContainObjects() {
        //After fetching currency list check first item in list of not null
        assertNotNull(currencyService.fetchCurrencyFromCRB().get(0));
    }

    @Test
    public void shouldParseJsonFromCBR() {
        //After fetching currency list from Central Bank of Russia first item should be "Австралийский доллар"
        List<Currency> currencyList = null;
        try {

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.cbr-xml-daily.ru/daily_json.js")).build();

            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            currencyList = currencyService.parseJsonToCurrencyList(response.body(), "CRB");

        }  catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue("Австралийский доллар".equals(currencyList.get(0).getName()));
    }
}
