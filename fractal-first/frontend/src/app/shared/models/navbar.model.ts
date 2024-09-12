import { ButtonConfig } from "./button.model";

/**
 * Represents the configuration for the navbar.
 */
export interface NavbarConfig {
  imgLogo: string;
  buttonsNavbarStart: ButtonConfig[];
  buttonsNavbarEnd: ButtonConfig[];
}
