export const TIMER = 7000;

/**
 * Represents the type of an alert popup.
 * Possible values include: 'primary', 'secondary', 'success', 'danger', 'warning', 'info', 'light', 'dark'.
 */
export type AlertPopupType =
  | 'primary'
  | 'secondary'
  | 'success'
  | 'danger'
  | 'warning'
  | 'info'
  | 'light'
  | 'dark';

/**
 * Represents the configuration for an alert popup.
 */
export interface AlertPopupConfig {
  alertType: AlertPopupType;
  label: string;
  isVisible: boolean;
}
