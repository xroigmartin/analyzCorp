from typing import Generic, List, TypeVar
from pydantic import BaseModel

T = TypeVar('T')

class PaginatedResponseDTO(BaseModel, Generic[T]):
    total: int
    limit: int
    offset: int
    items: List[T]