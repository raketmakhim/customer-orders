package com.raket.customerorders.scheduler;

import com.raket.customerorders.dto.CustomerFullNameDto;
import com.raket.customerorders.dto.CustomerOrdersDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class ConcatenatingNamesScheduler {

    private static final String CUSTOMER_ORDER_SERVICE_API_URL = "http://localhost:8080";
    private static final String TARGET_URL = "https://postman-echo.com";
    private static final String STATUS = "active";

    private static final Logger logger = LogManager.getLogger(ConcatenatingNamesScheduler.class);

    private final WebClient webClient;
    private final WebClient targetWebClient;

    public ConcatenatingNamesScheduler(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(CUSTOMER_ORDER_SERVICE_API_URL).build();
        this.targetWebClient = webClientBuilder.baseUrl(TARGET_URL).build();
    }

    @Scheduled(fixedRate = 60000) // Run every minute
    public void fetchDataAndSendToTargetAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        webClient.get()
                .uri("/customers?status=" + STATUS)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToFlux(CustomerOrdersDto.class)
                .doOnNext(this::logFullNameDtoBeforeConcatenating)
                .flatMap(this::concatenateNameAndSend)
                .subscribe(responseEntity -> {
                    logger.info("Received response with status code: {}", responseEntity.getStatusCode());
                }, ex -> {
                    if (ex instanceof WebClientResponseException webClientEx) {
                        logger.error("HTTP error occurred: {}", webClientEx.getStatusCode());
                    } else {
                        logger.error("An unexpected error occurred: {}", ex.getMessage());
                    }
                });
    }

    private void logFullNameDtoBeforeConcatenating(CustomerOrdersDto responseData) {
        CustomerFullNameDto fullNameDto = new CustomerFullNameDto(responseData);
        logger.info("Received response with body: {}", fullNameDto);
    }

    private Mono<ResponseEntity<Void>> concatenateNameAndSend(CustomerOrdersDto responseData) {
        CustomerFullNameDto fullNameDto = new CustomerFullNameDto(responseData);
        return targetWebClient.post()
                .uri("/post")
                .body(BodyInserters.fromValue(fullNameDto))
                .retrieve()
                .toBodilessEntity();
    }
}