import { Routes } from '@angular/router';
import { ROUTE } from './constants/route';
import { LoginComponent } from './modules/components/login/login.component';
import { RegistrationComponent } from './modules/components/registration/registration.component';
import { DashboardComponent } from './modules/components/dashboard/dashboard.component';
import { LoginGuard } from './guards/login.guard';
import { CityComponent } from './modules/components/city/city.component';
import { HomeComponent } from './modules/components/home/home.component';

/**
 * Defines the routes for the application.
 */
export const routes: Routes = [
    /**
     * Route for the login page.
     */
    { path: ROUTE.LOGIN, component: LoginComponent },

    /**
     * Route for the registration page.
     */
    { path: ROUTE.REGISTER, component: RegistrationComponent },

    
    
    { path: ROUTE.HOME, component: HomeComponent, canActivate: [LoginGuard], children:[
        /**
         * Route for the dashboard page.
         * Requires authentication using the LoginGuard.
         */
        { path: ROUTE.DASHBOARD, component: DashboardComponent, canActivate: [LoginGuard] },
        
        /**
         * Route for a specific city page.
         * Requires authentication using the LoginGuard.
         * The city ID is passed as a parameter.
         */
        { path: `${ROUTE.CITY}/:id`, component: CityComponent, canActivate: [LoginGuard] },
    ] },

    
    /**
     * Default route that redirects to the dashboard page.
     */
    { path: '', redirectTo: ROUTE.HOME, pathMatch: 'full' },

    /**
     * Wildcard route that redirects to the home page for any other unknown routes.
     */
    { path: '**', redirectTo: ROUTE.HOME },
];
