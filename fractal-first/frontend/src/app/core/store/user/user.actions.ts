import { createAction, props } from "@ngrx/store";
import { LoginPayload, LoginResponse, RegisterPayload, RegisterResponse } from "../../models/user.model";

/**
 * Action to initiate the login process for a user.
 * @param payload - The login payload containing user credentials.
 * @returns An action object with the payload.
 */
export const login = createAction('[User] Login', props<{ payload: LoginPayload }>());

/**
 * Action creator for login success.
 * 
 * @param response The login response.
 * @returns An action object with the response payload.
 */
export const loginSuccess = createAction('[User] Login Success', props<{ response: LoginResponse }>());

/**
 * Registers a user.
 *
 * @param payload - The payload containing the registration information.
 * @returns An action object with the payload.
 */
export const register = createAction('[User] Register', props<{ payload: RegisterPayload }>());

/**
 * Action creator for registering success.
 * 
 * @param response The response object containing the registration details.
 * @returns An action object with the response payload.
 */
export const registerSuccess = createAction('[User] Registered Success', props<{ response: RegisterResponse }>());

/**
 * Action creator for logging out the user.
 */
export const logOut = createAction('[User] Logout');

/**
 * Action creator for the 'logOutSuccess' action.
 * This action is dispatched when a user successfully logs out.
 */
export const logOutSuccess = createAction('[User] Logout Success');

/**
 * Action creator for setting user data.
 * This action is dispatched when user data needs to be set in the store.
 */
export const setUserData = createAction('[User] Set User Data');

/**
 * Action creator for setting user data successfully.
 * This action is dispatched when user data is successfully set.
 */
export const setUserDataSuccess = createAction('[User] Set User Data Successfully');
