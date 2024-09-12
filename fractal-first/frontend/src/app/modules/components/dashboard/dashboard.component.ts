import { CommonModule } from '@angular/common';
import { Component, OnDestroy, computed, inject, signal } from '@angular/core';
import { NavBarComponent } from '@src/app/shared/components/navbar/navbar.component';
import { Subject, Subscription } from 'rxjs';
import { CitiesComponent } from '../../../core/components/cities/cities.component';
import { UserFacade } from '../../../core/store/user/user.facade';
import { UserState } from '../../../core/store/user/user.reducer';

/**
 * Array of components used in the dashboard.
 */
const COMPONENTS = [NavBarComponent, CitiesComponent];

/**
 * Represents the DashboardComponent class.
 */
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, ...COMPONENTS],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent implements OnDestroy {
  /**
   * Signal that represents the user state.
   */
  user = signal<UserState>({} as UserState);

  /**
   * Computed signal that represents the user's token.
   */
  token = computed<string>(() => this.user()?.token);

  /**
   * Computed signal that represents the user's name.
   */
  name = computed<string>(
    () => this.user()?.name || localStorage.getItem('name') || ''
  );

  /**
   * Inject the UserFacade Pattern.
   **/
  userFacade = inject(UserFacade);

  /**
   * Subject used to signal the destruction of the component.
   * This subject is used to unsubscribe from observables and perform cleanup operations.
   */
  private destroy$ = new Subject<void>();

  /**
   * Reactive subscription to the user state.
   */
  token$: Subscription = this.userFacade.userState$.subscribe(
    (user: UserState) => {
      this.user.set(user);
    }
  );

  /**
   * Lifecycle hook that is called when the component is about to be destroyed.
   * It is used to perform any necessary cleanup logic, such as unsubscribing from observables or
   * releasing resources.
   */
  ngOnDestroy() {
    this.token$.unsubscribe();
    this.destroy$.next();
  }

}
