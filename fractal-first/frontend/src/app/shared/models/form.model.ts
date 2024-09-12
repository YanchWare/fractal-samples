import { ValidatorFn } from '@angular/forms';
import { ButtonConfig } from './button.model';

export interface FormOutputModel {
  [key: string]: string;
}

type TypeFormModel = 'login' | 'register' | 'search';

/**
 * Represents a form model.
 */
export interface FormModel {
  type: TypeFormModel;
  inputElements: InputFormModel[];

  buttonElements?: ButtonConfig[];
  title?: TitleFormModel;
  cardCustomClass?: string;
  backgroundSrc?: string;
  logoSrc?: string;
  footerText?: FooterTextModel;
  footerLogo?: FooterLogoModel;
}

/**
 * Represents the model for an input form.
 */
export interface InputFormModel {
  label: string;
  inputType: InputFormType;
  inputValidator: ValidatorFn[];
  customClass?: string;
}

/**
 * Represents the type of input form.
 */
export type InputFormType = 'text' | 'email' | 'password';

/**
 * Represents the form model for a title.
 */
export interface TitleFormModel {
  label: string;

  customClass?: string;
}

/**
 * Represents the model for the footer text.
 */
export interface FooterTextModel {
  text: string;
  labelLink: string;
  routerLink: string;
}

/**
 * Represents the model for the footer logo.
 */
export interface FooterLogoModel {
  label: string;
  logoSrc: string;
}
