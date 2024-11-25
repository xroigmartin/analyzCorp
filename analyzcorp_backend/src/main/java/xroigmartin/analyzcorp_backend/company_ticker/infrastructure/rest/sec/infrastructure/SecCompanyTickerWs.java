package xroigmartin.analyzcorp_backend.company_ticker.infrastructure.rest.sec.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import xroigmartin.analyzcorp_backend.company_ticker.domain.exception.SecRestClientException;
import xroigmartin.analyzcorp_backend.company_ticker.domain.repository.FindCompanyTickerRepository;
import xroigmartin.analyzcorp_backend.company_ticker.infrastructure.rest.sec.domain.CompanyTicker;

import java.util.Map;

@Service
public class SecCompanyTickerWs implements FindCompanyTickerRepository {

    private static final String COMPANIES_TICKERS_URL = "https://www.sec.gov/files/company_tickers.json";

    @Value("${analyzCorpo.system-email}")
    private String systemEmail;
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;

    public SecCompanyTickerWs(ObjectMapper mapper, RestTemplate restTemplate) {
        this.mapper = mapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public void findAllCompaniesTickers() {
        fetchCompaniesTickers().values().forEach(companyTicker -> {
            System.out.println("Ticker: " + companyTicker.ticker() +
                    ", Company: " + companyTicker.title() +
                    ", CIK: " + companyTicker.cikStr());
        });
    }

    private Map<String, CompanyTicker> fetchCompaniesTickers(){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.USER_AGENT, systemEmail);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Map<String, CompanyTicker>> responseCompaniesTickers =
                    restTemplate.exchange(COMPANIES_TICKERS_URL,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<Map<String, CompanyTicker>>() {
                            });

            return responseCompaniesTickers.getBody();
        }
        catch (RestClientException e) {
            throw new SecRestClientException(e);
        }
    }

}
