from fastapi import APIRouter, HTTPException

from app.apadters.dto.company_dto import CompanyDTO
from app.application.use_case.get_companies import GetCompaniesUseCase
from app.domain.exceptions.sec_api_exception import SECAPIException
from app.infrastructure.repository.sec_company_client import SECCompanyClient


router = APIRouter()

@router.get("/companies", response_model=list[CompanyDTO])
def get_companies():
    try:
        use_case = GetCompaniesUseCase(SECCompanyClient())
    
        companies = use_case.execute()
    
        return [
            CompanyDTO(cik=company.cik, name=company.name, ticker=company.ticker)
            for company in companies[:20]
        ]
    except SECAPIException as e:
        raise HTTPException(status_code = 502, detail = e.message)