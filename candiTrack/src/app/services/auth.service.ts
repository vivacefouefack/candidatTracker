
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface AuthDto {
  username: string;
  password: string;
}

interface AuthResponse {
  token: string;
  user: any;
  candidates: any[];
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = 'http://localhost:8484/api/auth';

  constructor(private http: HttpClient) {}

  login(data: AuthDto): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, data);
  }

  register(data: AuthDto): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/register`, data);
  }
}

