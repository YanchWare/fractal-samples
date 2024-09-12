import { createFeatureSelector, createSelector } from "@ngrx/store";
import { DashboardState, dashboardFeatureKey } from "./dashboard.reducer";

/**
 * Retrieves the dashboard feature state from the store.
 * 
 * @returns The dashboard feature state.
 */
export const getDashboardFeature = createFeatureSelector<DashboardState>(dashboardFeatureKey);

/**
 * Selects the cities from the dashboard feature state.
 *
 * @param getDashboardFeature - The selector function to get the dashboard feature state.
 * @returns An observable of the cities.
 */
export const cities$ = createSelector(getDashboardFeature, ({ cities }) => cities);

/**
 * Retrieves the city from the dashboard feature state.
 * @returns An observable that emits the city value.
 */
export const getCity$ = createSelector(getDashboardFeature, ({ city }) => city);
