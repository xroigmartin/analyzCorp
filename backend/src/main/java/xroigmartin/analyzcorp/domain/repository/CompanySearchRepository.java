package xroigmartin.analyzcorp.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xroigmartin.analyzcorp.domain.model.Company;

public interface CompanySearchRepository {
    Page<Company> findByNameOrTicker(String name, String ticker, String cik, Pageable pageable);
}
