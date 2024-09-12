import { createReducer, on } from '@ngrx/store';
import { Cities, CityInfoResponse } from '../../models/dashboard.model';
import { citiesSuccess, getCitySuccess, resetCity } from './dashboard.actions';

/**
 * The key used to identify the dashboard feature in the store.
 */
export const dashboardFeatureKey = 'search';

/**
 * Represents the state of the dashboard.
 */
export interface DashboardState {
  cities: Cities;
  city: CityInfoResponse;
}

/**
 * The initial state for the dashboard reducer.
 */
const initialState: DashboardState = {
  cities: {} as Cities,
  city: {} as CityInfoResponse,
};

/**
 * Reducer function for the dashboard state.
 */
export const dashboardReducer = createReducer(
  initialState,
  /**
   * Updates the state with the list of cities.
   * @param state - The current state.
   * @param cities - The list of cities.
   * @returns The updated state with the list of cities.
   */
  on(citiesSuccess, (state, { cities }) => ({ ...state, cities })),
  /**
   * Updates the state with a single city.
   * @param state - The current state.
   * @param city - The city object.
   * @returns The updated state with the city object.
   */
  on(getCitySuccess, (state, { city }) => ({ ...state, city })),
  /**
   * Resets the city object in the state.
   * @param state - The current state.
   * @returns The updated state with an empty city object.
   */
  on(resetCity, (state) => ({ ...state, city: {} as CityInfoResponse }))
);
