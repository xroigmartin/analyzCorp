package xroigmartin.analyzcorp_backend.personal_economy.application.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.application.dto.BankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.application.interfaces.BankAccountService;
import xroigmartin.analyzcorp_backend.personal_economy.application.utils.BankAccountUtils;
import xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.service.BankAccountJpaService;

import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountJpaService bankAccountJpaService;

    @Override
    public List<BankAccountDTO> findAllBankAccount() {
        return bankAccountJpaService.findAllBankAccount()
                .stream()
                .map(BankAccountUtils::convertBankAccountToBankAccountDTO)
                .toList();
    }

}
