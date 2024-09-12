import { createFeatureSelector, createSelector } from "@ngrx/store";
import { UserState, userFeatureKey } from "./user.reducer";

/**
 * Retrieves the user feature state from the store.
 * 
 * @returns The user feature state.
 */
export const getUserFeature = createFeatureSelector<UserState>(userFeatureKey);

/**
 * Selects the token from the user feature state.
 *
 * @param getUserFeature - The selector function to get the user feature state.
 * @returns The token value from the user feature state.
 */
export const token$ = createSelector(getUserFeature, ({ token }) => token);

/**
 * Selects the user state from the user feature state.
 *
 * @param getUserFeature - The selector function to get the user feature state.
 * @returns The user state.
 */
export const userState$ = createSelector(getUserFeature, state => state);