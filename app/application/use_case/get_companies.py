from app.domain.repository.company_repository import CompanyRepository
from app.domain.models.company import Company
from typing import List

class GetCompaniesUseCase:
    def __init__(self, repository: CompanyRepository):
        self.repository = repository
        
    def execute(self) -> List[Company]:
        return self.repository.get_all_companies()