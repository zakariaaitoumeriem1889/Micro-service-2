package org.sid.gatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CircuitBreakerRestController {

    @GetMapping("/defaultCountries")
    public Map<String, String> countries() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "default Countries");
        data.put("countries", "Maroc, Algérie, Tunisie, Côte d'ivoire, Mali ...");
        return data;
    }

    @GetMapping("/defaultSalat")
    public Map<String, String> salat() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "Horaire Salawat En Nwakchot");
        data.put("Fajr", "7:00");
        data.put("Addohr", "14:00");
        return data;
    }
}
