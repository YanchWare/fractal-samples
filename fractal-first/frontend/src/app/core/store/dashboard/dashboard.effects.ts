import { Injectable, inject } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, of, switchMap } from 'rxjs';
import { DashboardService } from '../../../modules/services/dashboard.service';
import { Cities, CityInfoResponse, GetCitiesPayload } from '../../models/dashboard.model';
import { cities, citiesSuccess, getCity, getCitySuccess } from './dashboard.actions';

/**
 * Injectable class representing the effects for the dashboard feature.
 */
@Injectable()
export class DashboardEffects {
  /**
   * Observable stream of actions.
   */
  action$ = inject(Actions);

  /**
   * The service responsible for handling dashboard-related functionality.
   */
  dashboardService = inject(DashboardService);

  /**
   * Effect for handling the login action.
   * Retrieves the cities based on the provided payload and dispatches the citiesSuccess action.
   */
  public login$ = createEffect(() =>
    this.action$.pipe(
      ofType(cities),
      switchMap(({ getCitiesPayload }) =>
        this.dashboardService
          .getCities(getCitiesPayload || ({} as GetCitiesPayload))
          .pipe(
            map((cities: Cities) => {
              return citiesSuccess({ cities });
            })
          )
      )
    )
  );

  /**
   * Effect for handling the getCity action.
   * Retrieves the city information based on the provided cityId and dispatches the getCitySuccess action.
   */
  public getCity$ = createEffect(() =>
    this.action$.pipe(
      ofType(getCity),
      switchMap(({ cityId }) =>
        this.dashboardService
          .getCity(cityId)
          .pipe(
            map((city: CityInfoResponse) => {
              return getCitySuccess({ city });
            }),
            catchError((error: any) => {
              // Handle the error here
              console.log('Error: ', error);
              
              return of();
            })
          ),
      )
    )
  );
}
