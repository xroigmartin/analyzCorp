package xroigmartin.analyzcorp_backend.company_ticker.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xroigmartin.analyzcorp_backend.company_ticker.application.load_companies_tickers.LoadCompaniesTickers;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

@RestController
@RequestMapping("companies-tickers")
public class CompanyTickersRestController {

    private final LoadCompaniesTickers loadCompaniesTickers;

    public CompanyTickersRestController(LoadCompaniesTickers loadCompaniesTickers) {
        this.loadCompaniesTickers = loadCompaniesTickers;
    }

    @PostMapping("/synchronize")
    public ResponseEntity<ApiResponse<Void>> synchronizeCompanyTickers() {
        this.loadCompaniesTickers.loadCompaniesTickers();
        var apiResponse = ApiResponseHandler.<Void>generateSuccess(null, "", HttpStatus.OK.value());
        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }
}
