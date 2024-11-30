package xroigmartin.analyzcorp_backend.company_ticker.infrastructure.rest.sec.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import xroigmartin.analyzcorp_backend.company_ticker.domain.exception.SecRestClientException;
import xroigmartin.analyzcorp_backend.company_ticker.infrastructure.rest.sec.domain.CompanyTicker;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecCompanyTickerWsTest {

    private static final String SYSTEM_EMAIL_TEST = "system.email.test@test.com";
    private static final String COMPANIES_TICKERS_URL = "https://www.sec.gov/files/company_tickers.json";

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private DataBuffer dataBuffer;

    @Mock
    private RestTemplate restTemplate;

    private SecCompanyTickerWs secCompanyTickerWs;

    @BeforeEach
    void setUp() {
        secCompanyTickerWs = new SecCompanyTickerWs(restTemplate, SYSTEM_EMAIL_TEST);
    }

    @DisplayName("Successful fetch when find all companies tickers")
    @Test
    void shouldFetchAndProcessCompanyTickerSuccessfully() throws Exception {
        //Given
        Map<String, CompanyTicker> mockResponse = Map.of(
            "1", new CompanyTicker(1L, "Apple Inc.", "0000320193"),
            "2", new CompanyTicker(2L, "Microsoft Corp.", "0000789019")
        );

        ResponseEntity<Map<String, CompanyTicker>> responseEntity = ResponseEntity.ok(mockResponse);

        when(restTemplate.exchange(
                eq(COMPANIES_TICKERS_URL),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(new ParameterizedTypeReference<Map<String, CompanyTicker>>() {})
        )).thenReturn(responseEntity);

        //When
        secCompanyTickerWs.findAllCompaniesTickers();

        //Then
        verify(restTemplate, times(1))
                .exchange(
                        eq(COMPANIES_TICKERS_URL),
                        eq(HttpMethod.GET),
                        any(HttpEntity.class),
                        eq(new ParameterizedTypeReference<Map<String, CompanyTicker>>() {})
                );

    }

    @Test
    @DisplayName("Should throw SecRestClientException when RestClientException occurs")
    void shouldThrowSecRestClientExceptionWhenRestClientExceptionOccurs() {
        // Given
        when(restTemplate.exchange(
                eq(COMPANIES_TICKERS_URL),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(new ParameterizedTypeReference<Map<String, CompanyTicker>>() {})
        )).thenThrow(new RestClientException("Error"));

        // When & Then
        assertThrows(SecRestClientException.class, secCompanyTickerWs::findAllCompaniesTickers);
    }

}
