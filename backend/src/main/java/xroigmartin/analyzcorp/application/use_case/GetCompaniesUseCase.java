package xroigmartin.analyzcorp.application.use_case;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp.domain.model.Company;
import xroigmartin.analyzcorp.domain.repository.CompanySearchRepository;

import java.util.List;

@Service
public class GetCompaniesUseCase {

    private final CompanySearchRepository companySearchRepository;

    public GetCompaniesUseCase( @Qualifier("jpa") CompanySearchRepository companySearchRepository) {
        this.companySearchRepository = companySearchRepository;
    }

    public List<Company> execute(String companyName, String ticker, int limit, int offset) {
        return companySearchRepository.findByNameOrTicker(companyName, ticker, limit, offset);
    }
}
