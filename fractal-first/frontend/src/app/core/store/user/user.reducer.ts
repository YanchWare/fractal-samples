import { createReducer, on } from "@ngrx/store";
import { logOutSuccess, loginSuccess, registerSuccess } from "./user.actions";

/**
 * The key used to access the user feature state in the Redux store.
 */
export const userFeatureKey = 'user';

/**
 * Represents the state of the user in the application.
 */
export interface UserState {
  name: string;
  token: string;
}

/**
 * Initial state for the User reducer.
 */
const initialState: UserState = {
  name: '',
  token: '',
}

/**
 * Reducer function for managing user state.
 */
export const userReducer = createReducer(
  initialState,
  
  /**
   * Updates the state with the user's information after a successful login.
   * @param state - The current user state.
   * @param response - The response object containing the user's token and name.
   * @returns The updated user state.
   */
  on(loginSuccess, (state, { response } ) => ({ ...state, token: response.token, name: response.name })),

  /**
   * Updates the state with the user's information after a successful registration.
   * @param state - The current user state.
   * @param response - The response object containing the user's token and name.
   * @returns The updated user state.
   */
  on(registerSuccess, (state, { response } ) => ({ ...state, token: response.token, name: response.name })),

  /**
   * Resets the user state to the initial state after a successful logout.
   * @returns The initial user state.
   */
  on(logOutSuccess, () => ( initialState ))
);
