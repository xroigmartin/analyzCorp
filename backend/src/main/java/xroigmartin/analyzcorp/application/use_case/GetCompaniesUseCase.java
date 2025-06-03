package xroigmartin.analyzcorp.application.use_case;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp.domain.model.Company;
import xroigmartin.analyzcorp.domain.repository.CompanySearchRepository;

@Service
public class GetCompaniesUseCase {

    private final CompanySearchRepository companySearchRepository;

    public GetCompaniesUseCase( @Qualifier("jpa") CompanySearchRepository companySearchRepository) {
        this.companySearchRepository = companySearchRepository;
    }

    public Page<Company> execute(String companyName, String ticker, String cik, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return companySearchRepository.findByNameOrTicker(companyName, ticker, cik, pageable);
    }
}
