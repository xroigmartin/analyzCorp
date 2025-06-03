package xroigmartin.analyzcorp.infrastructure.in.rest.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp.domain.model.Company;
import xroigmartin.analyzcorp.infrastructure.in.rest.dto.CompanyDTO;

@UtilityClass
public class CompanyDTOUtils {

    public static CompanyDTO toDTO(Company company) {
        return new CompanyDTO(company.cik(), company.name(), company.ticker());
    }
}
