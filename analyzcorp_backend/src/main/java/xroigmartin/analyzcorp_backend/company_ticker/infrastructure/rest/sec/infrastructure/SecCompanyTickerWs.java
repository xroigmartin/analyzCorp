package xroigmartin.analyzcorp_backend.company_ticker.infrastructure.rest.sec.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xroigmartin.analyzcorp_backend.company_ticker.domain.exception.SecJsonParserException;
import xroigmartin.analyzcorp_backend.company_ticker.domain.repository.FindCompanyTickerRepository;
import xroigmartin.analyzcorp_backend.company_ticker.infrastructure.rest.sec.domain.CompanyTicker;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class SecCompanyTickerWs implements FindCompanyTickerRepository {

    private static final String COMPANIES_TICKERS_URL = "https://www.sec.gov/files/company_tickers.json";

    @Value("${analyzCorpo.system-email}")
    private String systemEmail;
    private final ObjectMapper mapper;

    public SecCompanyTickerWs(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void findAllCompaniesTickers() {
        var webClient = WebClient.builder()
                .baseUrl(COMPANIES_TICKERS_URL)
                .defaultHeaders(headers -> headers.add(HttpHeaders.USER_AGENT, systemEmail))
                .build();

        Flux<DataBuffer> dataBufferFlux = webClient.get()
                .retrieve()
                .bodyToFlux(DataBuffer.class);

        Mono<String> jsonResponseMono = dataBufferFlux
                        .map(dataBuffer -> dataBuffer.toString(StandardCharsets.UTF_8))
                        .reduce((s1, s2) -> s1 + s2);

        jsonResponseMono.subscribe(jsonResponse -> {
            try {
                Map<String, CompanyTicker> companyTickerMap = mapper.readValue(jsonResponse, new TypeReference<>() {});

                companyTickerMap.values().forEach(companyTicker -> {
                    System.out.println("Ticker: " + companyTicker.ticker() +
                            ", Company: " + companyTicker.title() +
                            ", CIK: " + companyTicker.cikStr());
                });

            } catch (JsonProcessingException e) {
                throw new SecJsonParserException(e);
            }
        });

    }

}
