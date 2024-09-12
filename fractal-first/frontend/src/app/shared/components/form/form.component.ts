import { CommonModule } from '@angular/common';
import { Component, OnInit, inject, input, output } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
} from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CardConfig } from '../../models/card.model';
import {
  FormModel,
  FormOutputModel,
  InputFormModel,
} from '../../models/form.model';
import { AlertPopupService } from '../../services/alert-popup.service';
import { ButtonComponent } from '../button/button.component';
import { CardComponent } from '../card/card.component';

/**
 * Array of modules used in the form component.
 */
const MODULE = [CommonModule, ReactiveFormsModule, RouterLink];

/**
 * Array of components used in the component.
 */
const COMPONENTS = [CardComponent, ButtonComponent];

/**
 * Represents a form component.
 */
@Component({
  selector: 'app-form',
  standalone: true,
  imports: [...MODULE, ...COMPONENTS],
  templateUrl: './form.component.html',
  styleUrl: './form.component.scss',
})
export class FormComponent implements OnInit {
  // Variables
  protected form: FormGroup = new FormGroup({});

  /**
   * Represents the configuration for the card in the form component.
   */
  cardModel: CardConfig = {
    cardBodyCustomClass: 'px-5 form__card',
  };

  // Input
  /**
   * Represents the model for the form.
   */
  formModel = input.required<FormModel>();

  // Output
  /**
   * Event emitted when the form is submitted.
   */
  onSubmitForm = output<FormOutputModel>();

  /**
   * Event emitted when the search value changes.
   */
  searchValueChanges = output<string>();

  // Inject
  formBuilder = inject(FormBuilder);
  alertPopupService = inject(AlertPopupService);

  /**
   * Subscribes to the value changes of the search form and emits the search value.
   * @param value - The form control value.
   */
  searchFormValueChanges = this.form.valueChanges.subscribe((value: FormControl) => {
    this.searchValueChanges.emit(Object.values(value)[0]);
  });

  ngOnInit(): void {
    this.formModel()?.inputElements.forEach((input: InputFormModel) => {
      this.form.addControl(
        input.label.toLowerCase().trim(),
        this.formBuilder.control('', [...input.inputValidator])
      );
    });
  }

  /**
   * Handles the form submission.
   */
  onSubmit() {
    if (this.form.valid) {
      if (
        this.formModel().type === 'register' &&
        this.form.value.password !== this.form.value['confirm password']
      ) {
        /*
         * Handle password mismatch with a custom alert popup.
         */
        this.alertPopupService.show({
          alertType: 'danger',
          label: 'Passwords do not match',
          isVisible: true,
        });
        return;
      }
      this.onSubmitForm.emit(this.form.value);
    } else {
      // Handle form validation errors
      console.log('Form is invalid');
    }
    this.form.reset();
  }

  /**
   * Updates the form value with the given value.
   * @param value - The form control value.
   */
  updateFormValue(value: FormControl) {
    this.form.patchValue(value);
  }
}
