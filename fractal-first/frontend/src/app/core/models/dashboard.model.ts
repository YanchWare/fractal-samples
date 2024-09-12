/**
 * Represents a city.
 */
export interface City {
  cityId: string;
  name: string;
  country: string;
}

/**
 * Represents a collection of cities.
 */
export interface Cities {
  count: number;
  cities: City[];
}

/**
 * Represents the payload for getting cities.
 */
export interface GetCitiesPayload {
  limit: number;
  offset: number;
}

/**
 * Represents the response object for city information.
 */
export interface CityInfoResponse {
  cityId: string;
  name: string;
  country: string;
  costOfLivingIndex: number;
  internetSpeed: InternetSpeed;
  coworkingSpaces: CoworkingSpace[];
  safetyIndex: number;
  climate: Climate;
}

/**
 * Represents the internet speed with download and upload values.
 */
export interface InternetSpeed {
  download: number;
  upload: number;
}

/**
 * Represents a coworking space.
 */
export interface CoworkingSpace {
  name: string;
  address: string;
  rating: number;
}

/**
 * Represents the climate data.
 */
export interface Climate {
  averageTemperature: number;
  rainfall: number;
}
