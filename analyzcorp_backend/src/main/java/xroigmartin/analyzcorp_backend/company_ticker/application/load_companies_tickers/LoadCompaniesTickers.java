package xroigmartin.analyzcorp_backend.company_ticker.application.load_companies_tickers;

import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.company_ticker.domain.repository.FindCompanyTickerRepository;

@Service
public class LoadCompaniesTickers {

    private final FindCompanyTickerRepository findCompanyTickerRepository;

    public LoadCompaniesTickers(FindCompanyTickerRepository findCompanyTickerRepository) {
        this.findCompanyTickerRepository = findCompanyTickerRepository;
    }

    public void loadCompaniesTickers() {
        this.findCompanyTickerRepository.findAllCompaniesTickers();
    }

}
