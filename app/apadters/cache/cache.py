from functools import lru_cache
from app.infrastructure.repository.sec_company_client import SECCompanyClient


@lru_cache(maxsize=1)
def get_cached_companies():
    client = SECCompanyClient()
    return tuple(client.get_all_companies())