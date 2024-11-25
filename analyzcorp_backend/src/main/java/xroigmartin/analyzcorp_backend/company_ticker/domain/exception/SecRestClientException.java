package xroigmartin.analyzcorp_backend.company_ticker.domain.exception;

public class SecRestClientException extends RuntimeException {

  public SecRestClientException(Throwable cause) {
    super("Error when fetch companies from SEC", cause);
  }
}
