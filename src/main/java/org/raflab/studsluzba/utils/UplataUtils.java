package org.raflab.studsluzba.utils;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class UplataUtils {
    public static Double getSrednjiKursEUR() {
        String url = "https://kurs.resenje.org/api/v1/currencies/eur/rates/today";
        RestTemplate restTemplate = new RestTemplate();

        try {
            String json = restTemplate.getForObject(url, String.class);
            if (json == null) {
                throw new RuntimeException("Prazan odgovor sa API-ja");
            }


            String key = "\"exchange_middle\"";
            int index = json.indexOf(key);
            if (index == -1) {
                throw new RuntimeException("exchange_middle nije pronađen u JSON-u");
            }

            int colonIndex = json.indexOf(":", index);
            if (colonIndex == -1) {
                throw new RuntimeException("Ne mogu da pronađem ':' posle exchange_middle");
            }


            int start = colonIndex + 1;

            int end = start;
            while (end < json.length() &&
                    ((json.charAt(end) >= '0' && json.charAt(end) <= '9') || json.charAt(end) == '.')) {
                end++;
            }

            String numberStr = json.substring(start, end).trim();
            return Double.parseDouble(numberStr);

        } catch (Exception e) {
            throw new RuntimeException("Greška pri dohvaćanju srednjeg kursa EUR", e);
        }
    }

}
