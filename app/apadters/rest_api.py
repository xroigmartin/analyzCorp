from typing import Optional
from fastapi import APIRouter, HTTPException, Query

from app.apadters.cache.cache import get_cached_companies
from app.apadters.dto.company_dto import CompanyDTO
from app.application.use_case.get_companies import GetCompaniesUseCase
from app.domain.exceptions.sec_api_exception import SECAPIException
from app.domain.models.company import Company
from app.infrastructure.repository.sec_company_client import SECCompanyClient


router = APIRouter()

@router.get("/companies", response_model=list[CompanyDTO])
def get_companies(
    name: Optional[str] = Query(None),
    cik: Optional[str] = Query(None),
    ticker: Optional[str] = Query(None),
):
    companies: list[Company] = list(get_cached_companies())
    
    if name:
        companies = [c for c in companies if name.lower() in c.name.lower()]
    if ticker: 
        companies = [c for c in companies if ticker.lower() in c.ticker.lower()]
    if cik:
        companies = [c for c in companies if cik.lower() in c.cik.lower()]
        
    return [
        CompanyDTO(cik=c.cik, name=c.name, ticker=c.ticker)
        for c in companies[:50]
    ]