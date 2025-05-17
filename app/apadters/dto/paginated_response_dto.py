from typing import List
from pydantic import BaseModel

from app.apadters.dto.company_dto import CompanyDTO


class PaginatedCompanyResponseDTO(BaseModel):
    total: int
    limit: int
    offset: int
    items: List[CompanyDTO]