import { Component, inject } from '@angular/core';
import { Validators } from '@angular/forms';
import { FormComponent } from '@src/app/shared/components/form/form.component';
import { FormModel, FormOutputModel } from '@src/app/shared/models/form.model';
import { IMAGES } from '../../../constants/images';
import { ROUTE } from '../../../constants/route';
import { LoginRegisterService } from '../../services/loginRegisterService.service';
import { UserFacade } from '../../../core/store/user/user.facade';

/**
 * Represents the Registration Component.
 */
@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [FormComponent],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.scss',
})
export class RegistrationComponent {
  /**
   * Represents the form model for registration.
   */
  formModel: FormModel = {
    type: 'register',
    logoSrc: IMAGES.YANCHWAREGO_LOGO,
    title: {
      label: 'Create an account'
    },
    inputElements: [
      {
        label: 'Name',
        inputType: 'text',
        inputValidator: [Validators.required],
        customClass: 'my-2',
      },
      {
        label: 'Surname',
        inputType: 'text',
        inputValidator: [Validators.required],
        customClass: 'my-2',
      },
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
        customClass: 'mb-2',
      },
      {
        label: 'Confirm Password',
        inputType: 'password',
        inputValidator: [Validators.required, Validators.minLength(6)],
        customClass: 'mb-3',
      },
    ],
    buttonElements: [
      {
        id: 'signup',
        classButtonType: 'btn-primary',
        typeButtonType: 'submit',
        label: 'Sign up',
        customClass: 'w-100',
      }],
    footerText: {
        text: 'Do you have an account?',
        labelLink: 'Sign in',
        routerLink: `/${ROUTE.LOGIN}`,
    },
    footerLogo: {
        label: 'Powered by',
        logoSrc: IMAGES.YANCHWARE_LOGO,
    },
  };

  /**
   * Inject Service for handling login and registration functionality.
   */
  loginRegisterService = inject(LoginRegisterService);
  userFacade = inject(UserFacade);

  /**
   * Handles the form submission event.
   * @param formOutputModel - The output model from the form.
   */
  onSubmitForm(formOutputModel: FormOutputModel) {
    this.userFacade.register({
      name: formOutputModel['name'],
      surname: formOutputModel['surname'],
      email: formOutputModel['email'],
      password: formOutputModel['password'],
    });
  }
}
