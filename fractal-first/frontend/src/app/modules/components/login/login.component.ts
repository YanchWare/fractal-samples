import { Component, inject } from '@angular/core';
import { UserFacade } from '../../../core/store/user/user.facade';
import { Subscription, filter } from 'rxjs';
import { Validators } from '@angular/forms';
import { ROUTE } from '../../../constants/route';
import { IMAGES } from '../../../constants/images';
import { FormComponent } from '@src/app/shared/components/form/form.component';
import { FormModel, FormOutputModel } from '@src/app/shared/models/form.model';
import { LoginRegisterService } from '../../services/loginRegisterService.service';

/**
 * Represents the LoginComponent class.
 * This component is responsible for handling the login functionality.
 */
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  /**
   * Represents the form model for the login component.
   */
  formModel: FormModel = {
    type: 'login',
    logoSrc: IMAGES.YANCHWAREGO_LOGO,
    title: {
      label: 'Sign in',
    },
    inputElements: [
      {
        label: 'Email',
        inputType: 'email',
        inputValidator: [Validators.required, Validators.email],
        customClass: 'mb-2',
      },
      {
        label: 'Password',
        inputType: 'password',
        inputValidator: [Validators.required, Validators.minLength(6)],
        customClass: 'mb-3',
      },
    ],
    buttonElements: [
      {
        id: 'signin',
        classButtonType: 'btn-primary',
        typeButtonType: 'submit',
        label: 'Sign in',
        customClass: 'w-100',
      },
    ],
    footerText: {
      text: 'Don’t you have an account?',
      labelLink: 'Create new one',
      routerLink: `/${ROUTE.REGISTER}`,
    },
    footerLogo: {
      label: 'Powered by',
      logoSrc: IMAGES.YANCHWARE_LOGO,
    },
  };

  // Inject
  userFacade = inject(UserFacade);
  loginRegisterService = inject(LoginRegisterService);

  /**
   * Represents the subscription to the token$ observable.
   * It logs the token value when it is emitted.
   */
  token: Subscription = this.userFacade.token$
    .pipe(filter(Boolean))
    .subscribe((token) => {
      console.log('Token:', token);
    });

  /**
   * Handles the form submission event.
   * @param formOutputModel - The output model of the form.
   */
  onSubmitForm(formOutputModel: FormOutputModel) {
    this.userFacade.login({
      email: formOutputModel['email'],
      password: formOutputModel['password'],
    });
  }
}
