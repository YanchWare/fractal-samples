import { createAction, props } from "@ngrx/store";
import { Cities, CityInfoResponse, GetCitiesPayload } from "../../models/dashboard.model";

/**
 * Action to get cities for the dashboard.
 *
 * @param getCitiesPayload - Optional payload for getting cities.
 * @returns An action object with the payload.
 */
export const cities = createAction('[Dashboard] Get Cities', props<{ getCitiesPayload?: GetCitiesPayload }>());

/**
 * Action creator for the 'citiesSuccess' action.
 * This action is dispatched when the cities data is successfully retrieved.
 *
 * @param cities - The cities data.
 * @returns An action object with the cities data.
 */
export const citiesSuccess = createAction('[Dashboard] Get Cities Success', props<{ cities: Cities }>());

/**
 * Action to get city information.
 * @param cityId - The ID of the city.
 * @returns An action object with the city ID.
 */
export const getCity = createAction('[City] Get City Info', props<{ cityId: string }>());

/**
 * Action to indicate a successful retrieval of city information.
 *
 * @param city - The city information response.
 * @returns An action object with the city information.
 */
export const getCitySuccess = createAction('[City] Get City Info Success', props<{ city: CityInfoResponse }>());

/**
 * Action to reset the city.
 */
export const resetCity = createAction('[City] Reset City');

