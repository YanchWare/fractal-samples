import { ApplicationConfig } from '@angular/core';
import { provideRouter, withViewTransitions } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import { userReducer } from './core/store/user/user.reducer';
import { UserEffects } from './core/store/user/user.effects';
import { provideStore } from '@ngrx/store';
import { provideStoreDevtools } from '@ngrx/store-devtools';
import { provideEffects } from '@ngrx/effects';
import { dashboardReducer } from './core/store/dashboard/dashboard.reducer';
import { DashboardEffects } from './core/store/dashboard/dashboard.effects';

/**
 * Configuration object for the application.
 */
export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes, withViewTransitions()),
    provideHttpClient(),

    provideStore(),

    provideStore({ user: userReducer, search: dashboardReducer }),
    provideStoreDevtools(),
    provideEffects([UserEffects, DashboardEffects]),
  ],
  
};
