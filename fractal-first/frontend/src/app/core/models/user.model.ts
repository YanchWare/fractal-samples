/**
 * Represents the data of a user.
 */
export interface UserData {
  token: string;
  name: string;
  router: string;
}

/**
 * Represents the payload for user login.
 */
export interface LoginPayload {
  email: string;
  password: string;
}

/**
 * Represents the response received after a successful login.
 */
export interface LoginResponse {
  token: string;
  name: string;
}

/**
 * Represents the payload for user registration.
 */
export interface RegisterPayload {
  name: string;
  surname: string;
  email: string;
  password: string;
}

/**
 * Represents the response object returned when registering a user.
 */
export interface RegisterResponse {
  token: string;
  name: string;
}
