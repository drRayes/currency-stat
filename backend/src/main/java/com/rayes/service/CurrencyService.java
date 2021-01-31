package com.rayes.service;

import com.rayes.entity.Currency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CurrencyService {
    private static final Logger LOGGER = Logger.getLogger("CurrencyService.class");

    /**
     * Method for fetching currency list from CRB.
     * @return currency list from CRB
     */
    public List<Currency> fetchCurrencyFromCRB() {
        LOGGER.info("Fetching currency list from CRB.");

        List<Currency> currencyList = null;
        try {

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.cbr-xml-daily.ru/daily_json.js")).build();

            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            currencyList = parseJsonToCurrencyList(response.body(), "CRB");

        }  catch (Exception e) {
            LOGGER.warning("Exception while fetching currency list from CRB " + e);
            e.printStackTrace();
        }
        return currencyList;
    }

    /**
     * Method for parsing json to currency list
     * @param responseBody
     * @param source
     * @return currency list
     */
    public List<Currency> parseJsonToCurrencyList(String responseBody, String source) {
        LOGGER.info("Parsing json from " + source + " .");

        List<Currency> currencyList = new LinkedList<>();
        String[] splitJson = responseBody.split("\\{");

        try {
            for (int i = 3; i < splitJson.length - 2; i++) {
                String string = splitJson[i].trim().replaceAll("\\}", "").replaceAll("\"", "");
                String[] splitByComa = string.split(",");
                for(int k = 0; k < splitByComa.length - 1; k++) {
                    splitByComa[k] = splitByComa[k].substring(splitByComa[k].indexOf(":") + 1).trim();
                }

                currencyList.add(new Currency(
                        splitByComa[0], Long.valueOf(splitByComa[1]), splitByComa[2], Long.valueOf(splitByComa[3]), splitByComa[4],
                        BigDecimal.valueOf(Double.valueOf(splitByComa[5])), BigDecimal.valueOf(Double.valueOf(splitByComa[6]))
                ));

            }
        } catch (Exception e) {
            LOGGER.warning("Exception while parsing json from " + source + " " + e);
            e.printStackTrace();
        }
        return currencyList;
    }
}
