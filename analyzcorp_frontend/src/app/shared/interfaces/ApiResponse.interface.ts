import {ApiError} from './ApiError.interface';

export interface ApiResponse<T> {
  statusCode: number;
  message: string;
  path: string;
  data: T;
  error: ApiError
  timestamp: Date;
}
