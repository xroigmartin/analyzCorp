package xroigmartin.analyzcorp_backend.company_ticker.domain.exception;

public class SecJsonParserException extends RuntimeException {

  public SecJsonParserException(Throwable cause) {
    super("Error when parsing JSON of SEC", cause);
  }
}
