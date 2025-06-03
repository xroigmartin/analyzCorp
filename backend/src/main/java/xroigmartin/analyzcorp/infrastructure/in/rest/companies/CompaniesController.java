package xroigmartin.analyzcorp.infrastructure.in.rest.companies;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xroigmartin.analyzcorp.application.use_case.GetCompaniesUseCase;
import xroigmartin.analyzcorp.application.use_case.ImportCompaniesFromSecUseCase;
import xroigmartin.analyzcorp.infrastructure.in.rest.dto.CompanyDTO;
import xroigmartin.analyzcorp.infrastructure.in.rest.dto.PaginatedResponseDTO;
import xroigmartin.analyzcorp.infrastructure.in.rest.utils.CompanyDTOUtils;
import xroigmartin.analyzcorp.infrastructure.in.rest.utils.PaginatedResponseUtils;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompaniesController {

    private final GetCompaniesUseCase getCompaniesUseCase;
    private final ImportCompaniesFromSecUseCase importCompaniesFromSecUseCase;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginatedResponseDTO<CompanyDTO>> getCompanies(@RequestParam(required = false) String name,
                                                                         @RequestParam(required = false) String ticker,
                                                                         @RequestParam(required = false) String cik,
                                                                         @RequestParam(required = false, defaultValue = "10") int size,
                                                                         @RequestParam(required = false, defaultValue = "0") int page) {

        var companyPage = getCompaniesUseCase.execute(name, ticker, cik, page, size);
        var paginatedResponse = PaginatedResponseUtils.toPaginatedResponseDTO(companyPage, CompanyDTOUtils::toDTO);
        return ResponseEntity.ok(paginatedResponse);
    }

    @GetMapping("/import")
    public ResponseEntity<Void> importFromSec(){
        importCompaniesFromSecUseCase.execute();
        return ResponseEntity.accepted().build();
    }
}
