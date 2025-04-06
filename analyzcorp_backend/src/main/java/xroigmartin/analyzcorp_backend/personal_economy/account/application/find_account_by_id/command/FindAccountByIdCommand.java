package xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.command;

import lombok.Builder;

@Builder
public record FindAccountByIdCommand(
        Long id
) {}
