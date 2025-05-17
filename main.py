from fastapi import FastAPI

from app.apadters import rest_api

app = FastAPI()

app.include_router(rest_api.router)