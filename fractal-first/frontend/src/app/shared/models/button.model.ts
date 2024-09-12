/**
 * Represents the possible class types for a button.
 */
type ButtonClassType =
  | 'btn'
  | 'btn-primary'
  | 'btn-secondary'
  | 'btn-success'
  | 'btn-danger'
  | 'btn-warning'
  | 'btn-info'
  | 'btn-light'
  | 'btn-dark'
  | 'btn-link'
  | 'btn-outline-primary'
  | 'btn-outline-secondary'
  | 'btn-outline-success'
  | 'btn-outline-danger'
  | 'btn-outline-warning'
  | 'btn-outline-info'
  | 'btn-outline-light'
  | 'btn-outline-dark';

/**
 * Represents the type of a button.
 */
type ButtonType = 'button' | 'submit' | 'reset';

/**
 * Represents the configuration for a button.
 */
export interface ButtonConfig {
  id: string;
  classButtonType: ButtonClassType;
  typeButtonType: ButtonType;
  label: string;
  
  customClass?: string;
  isDisabled?: boolean;
  routerLink?: string;
  value?: string;
}
