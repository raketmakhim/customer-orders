package com.raket.customerorders.scheduler;

import com.raket.customerorders.dto.CustomerOrdersDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

class ConcatenatingNamesSchedulerTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Test
    void fetchDataAndSendToTargetAPI_Success() {
        MockitoAnnotations.openMocks(this);


        // Mocking WebClient.Builder
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);

        // Mocking WebClient interactions
        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(CustomerOrdersDto.class)).thenReturn(Flux.just(new CustomerOrdersDto()));

        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.body(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toBodilessEntity()).thenReturn(Mono.just(ResponseEntity.status(HttpStatus.OK).build()));

        ConcatenatingNamesScheduler scheduler = new ConcatenatingNamesScheduler(webClientBuilder);

        // Call the method you want to test
        scheduler.fetchDataAndSendToTargetAPI();

        // Verify interactions
        verify(webClient, times(1)).get();
        verify(requestHeadersUriSpecMock, times(1)).uri(anyString());
        verify(requestHeadersSpec, times(2)).retrieve();
        verify(responseSpec, times(1)).bodyToFlux(CustomerOrdersDto.class);
        verify(requestBodyUriSpec, times(1)).uri(anyString());
        verify(requestBodyUriSpec, times(1)).body(any());
        verify(responseSpec, times(1)).toBodilessEntity();

    }
}
