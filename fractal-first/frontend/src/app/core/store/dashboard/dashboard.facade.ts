import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { Cities, GetCitiesPayload } from '../../models/dashboard.model';
import { cities, getCity, resetCity } from './dashboard.actions';
import { Observable } from 'rxjs';
import { cities$, getCity$ } from './dashboard.selectors';

/**
 * Facade Pattern for managing dashboard-related state and actions.
 */
@Injectable({ providedIn: 'root' })
export class DashboardFacade {

  /**
   * Observable stream of cities data.
   */
  public cities$: Observable<Cities> = this.store.select(cities$);

  /**
   * Observable stream of a specific city data.
   */
  public getCity$: Observable<any> = this.store.select(getCity$);

  constructor(private store: Store) {}

  /**
   * Dispatches an action to fetch cities data.
   * @param getCitiesPayload - Optional payload for fetching cities.
   */
  public cities(getCitiesPayload?: GetCitiesPayload): void {
    this.store.dispatch(cities({ getCitiesPayload }));
  }

  /**
   * Dispatches an action to fetch a specific city data.
   * @param cityId - The ID of the city to fetch.
   */
  public getCity(cityId: string): void {
    this.store.dispatch(getCity({ cityId }));
  }

  /**
   * Dispatches an action to reset the city data.
   */
  public resetCity(): void {
    this.store.dispatch(resetCity());
  }

}
