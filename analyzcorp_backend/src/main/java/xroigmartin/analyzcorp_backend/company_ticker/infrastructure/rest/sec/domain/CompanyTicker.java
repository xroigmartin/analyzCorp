package xroigmartin.analyzcorp_backend.company_ticker.infrastructure.rest.sec.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CompanyTicker(
        @JsonProperty("cik_str") long cikStr,
        @JsonProperty("ticker") String ticker,
        @JsonProperty("title") String title) {
}
