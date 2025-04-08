package xroigmartin.analyzcorp_backend.control_panel.currency.application.find_currency_by_code.command;

public record FindCurrencyByCodeCommand(String code) {
    public static FindCurrencyByCodeCommand create(String code) {
        return new FindCurrencyByCodeCommand(code);
    }
}
