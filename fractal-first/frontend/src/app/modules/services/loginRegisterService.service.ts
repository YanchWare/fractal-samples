import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { LoginPayload, LoginResponse, RegisterPayload, RegisterResponse } from '../../core/models/user.model';
// import { environment } from '../../../../environments/environment';
// import { ENDPOINTS } from '../../../constants/endpoints';

/**
 * Service responsible for handling login and registration functionality.
 */
@Injectable({
  providedIn: 'root'
})
export class LoginRegisterService {
  constructor(private http: HttpClient) {}

  /**
   * Performs a login operation.
   * @param loginPayload - The login payload containing the email and password.
   * @returns An observable that emits the login response.
   */
  login(loginPayload: LoginPayload): Observable<LoginResponse> {
    // FIXME: MOCK DATA
    // return this.http.post<LoginResponse>(`${environment.baseUrl}/${ENDPOINTS.LOGIN}`, loginPayload);
    const mockResponse: LoginResponse = {
      token: 'your-token-string',
      name: loginPayload.email.replace(/@.*/, '')
    };
    return of(mockResponse);
  }

  /**
   * Performs a registration operation.
   * @param registerPayload - The registration payload containing the email and password.
   * @returns An observable that emits the registration response.
   */
  register(registerPayload: RegisterPayload): Observable<RegisterResponse> {
    // FIXME: MOCK DATA
    // return this.http.post<RegisterResponse>(`${environment.baseUrl}/${ENDPOINTS.REGISTER}`, registerPayload );
    const mockResponse: RegisterResponse = {
      token: 'your-token-string',
      name: registerPayload.email.replace(/@.*/, '')
    };
    return of(mockResponse);
  }
}
