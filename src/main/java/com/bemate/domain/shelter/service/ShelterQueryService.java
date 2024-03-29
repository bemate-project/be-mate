package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.endpoint.request.ShelterApiRequest;
import com.bemate.domain.shelter.entity.Shelter;
import com.bemate.domain.shelter.repository.ShelterRepository;
import com.bemate.global.exception.ShelterNotFoundException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterQueryService {

    private final ShelterRepository shelterRepository;
    private static final String BASE_URL = "http://apis.data.go.kr";
    private static final String PATH = "1543061/abandonmentPublicSrvc/shelter";

    @Value("${openapi.shelter.key}")
    private String serviceKey;

    public Shelter findByName(String name) {
        return shelterRepository.findByShelterName(name)
                .orElseThrow(() -> ShelterNotFoundException.byName(name));
    }

    public Shelter findById(Long id) {
        return shelterRepository.findById(id)
                .orElseThrow(() -> ShelterNotFoundException.byId(id));
    }

    public List<String> getAllShelters(ShelterApiRequest shelterApiRequest) {
        var response = getApiResponse(shelterApiRequest);

        return parseShelterName(response);
    }

    private JsonObject getApiResponse(ShelterApiRequest shelterApiRequest) {
        var webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        var response = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path(PATH)
                                .queryParam("serviceKey", serviceKey)
                                .queryParam("upr_cd", shelterApiRequest.getStateProvinceCode())
                                .queryParam("org_cd", shelterApiRequest.getCityCode())
                                .queryParam("_type", "json")
                                .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return JsonParser
                .parseString(response)
                .getAsJsonObject();
    }

    private List<String> parseShelterName(JsonObject json) {
        var items = json
                .get("response").getAsJsonObject()
                .get("body").getAsJsonObject()
                .get("items").getAsJsonObject()
                .get("item").getAsJsonArray();

        var shelters = new ArrayList<String>();
        for (var item : items) {
            shelters.add(
                    item
                            .getAsJsonObject()
                            .get("careNm")
                            .getAsString()
            );
        }

        return shelters;
    }
}
