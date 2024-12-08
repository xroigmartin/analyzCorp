package xroigmartin.analyzcorp_backend.personal_economy.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateBankAccountDTO(
        @NotBlank @Size(max = 100) String bankName,
        @NotBlank @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$") String iban,
        @Size(max = 50) String alias
){}
