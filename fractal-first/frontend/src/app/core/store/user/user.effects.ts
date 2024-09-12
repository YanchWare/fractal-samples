import { Injectable, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { map, switchMap } from 'rxjs';
import { ROUTE } from '../../../constants/route';
import { LoginResponse, RegisterResponse, UserData } from '../../models/user.model';
import { LoginRegisterService } from '../../../modules/services/loginRegisterService.service';
import {
  logOut,
  logOutSuccess,
  login,
  loginSuccess,
  register,
  registerSuccess,
} from './user.actions';

@Injectable()
export class UserEffects {
  // Inject dependencies
  action$ = inject(Actions);
  loginRegisterService = inject(LoginRegisterService);

  /**
   * Constructor
   * @param router - The router service
   */
  constructor(private router: Router) {}

  /**
   * Set user data into localstorage and redirect to the specified router
   * @param userData - The user data to be set
   */
  private setUserDataRedirect(userData: UserData): void {
    localStorage.setItem('token', userData.token);
    localStorage.setItem('name', userData.name);
    this.router.navigate([userData.router]);
  }

  /**
   * Remove user data and redirect to the login page
   */
  private removeUserDataRedirect(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('name');
    this.router.navigate([ROUTE.LOGIN]);
  }

  /**
   * Login effect, set the user data and redirect to the dashboard
   */
  public login$ = createEffect(() =>
    this.action$.pipe(
      ofType(login),
      switchMap(({ payload }) =>
        this.loginRegisterService.login(payload).pipe(
          map(({ token, name }: LoginResponse) => {
            const router: string = ROUTE.DASHBOARD;
            this.setUserDataRedirect({ token, name, router});
            return loginSuccess({response: { token, name }});
          })
        )
      )
    )
  );

  /**
   * Register effect, set the user data and redirect to the dashboard
   */
  public register$ = createEffect(() =>
    this.action$.pipe(
      ofType(register),
      switchMap(({ payload }) =>
        this.loginRegisterService.register(payload).pipe(
          map(({ token, name }: RegisterResponse) => {
            const router: string = ROUTE.DASHBOARD;
            this.setUserDataRedirect({ token, name, router});
            return registerSuccess({ response: { token, name } });
          })
        )
      )
    )
  );

  /**
   * Log out effect, remove the user data and redirect to the login page
   */
  public logOut$ = createEffect(() =>
    this.action$.pipe(
      ofType(logOut),
      map(() => {
        this.removeUserDataRedirect();
        return logOutSuccess();
      })
    )
  );
}
