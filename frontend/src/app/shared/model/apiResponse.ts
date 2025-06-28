import {ApiError} from './ApiError';

export interface ApiResponse<T> {
  data: T;
  error: ApiError;
  timestamp: string;
}
