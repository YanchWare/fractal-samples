import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink, RouterOutlet } from '@angular/router';
import { AlertPopupComponent } from './shared/components/alert-popup/alert-popup.component';
import { AlertPopupConfig } from './shared/models/alert-popup.model';
import { AlertPopupService } from './shared/services/alert-popup.service';
import { Subscription, filter } from 'rxjs';
import { NavbarConfig } from './shared/models/navbar.model';
import { IMAGES } from './constants/images';
import { ButtonConfig } from './shared/models/button.model';
import { ROUTE } from './constants/route';
import { UserFacade } from './core/store/user/user.facade';
import { NavBarComponent } from './shared/components/navbar/navbar.component';

/**
 * Array of components used in the dashboard.
 */
const COMPONENTS = [NavBarComponent, AlertPopupComponent];

/**
 * Array of modules used in the form component.
 */
const MODULE = [RouterOutlet, CommonModule, ReactiveFormsModule, RouterLink];

/**
 * The root component of the application.
 */
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [...MODULE, ...COMPONENTS],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  /**
   * The title of the application.
   */
  title = 'yanchware-go';

  /**
   * Service for displaying alert popups.
   */
  alertPopupService = inject(AlertPopupService);

  /**
   * The configuration for the alert popup.
   */
  alertPopupConfig = signal<AlertPopupConfig>({} as AlertPopupConfig);

  /**
   * Indicates whether the alert popup is visible or not.
   */
  isVisible = signal<boolean>(false);

  /**
   * Inject the UserFacade Pattern.
   **/
  userFacade = inject(UserFacade);

  /**
   * Subscription for the alert popup subject.
   */
  alertPopupSubject$: Subscription = this.alertPopupService.alertPopupSubject
    .pipe(filter(Boolean))
    .subscribe((alertPopupConfig: AlertPopupConfig) => {
      this.alertPopupConfig.set(alertPopupConfig);
      this.isVisible.set(alertPopupConfig.isVisible);
    });
}
