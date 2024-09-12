import { CommonModule } from '@angular/common';
import { Component, OnDestroy, inject, signal } from '@angular/core';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { ROUTE } from '@src/app/constants/route';
import { CityInfoResponse } from '@src/app/core/models/dashboard.model';
import { DashboardFacade } from '@src/app/core/store/dashboard/dashboard.facade';
import { UserFacade } from '@src/app/core/store/user/user.facade';
import { BarChartComponent } from '@src/app/shared/components/bar-chart/bar-chart.component';
import { ButtonComponent } from '@src/app/shared/components/button/button.component';
import { CardComponent } from '@src/app/shared/components/card/card.component';
import { NavBarComponent } from '@src/app/shared/components/navbar/navbar.component';
import { StarRatingComponent } from '@src/app/shared/components/star-rating/star-rating.component';
import { ButtonConfig } from '@src/app/shared/models/button.model';
import { CardConfig } from '@src/app/shared/models/card.model';
import { Subject, Subscription, filter, map, take, takeUntil } from 'rxjs';

const MODULES = [CommonModule, RouterLink];
const COMPONENTS = [
  CardComponent,
  ButtonComponent,
  BarChartComponent,
  StarRatingComponent,
  NavBarComponent,
];

/**
 * Represents the CityComponent class.
 * This component is responsible for displaying city information.
 */
@Component({
  selector: 'app-city',
  standalone: true,
  imports: [...MODULES, ...COMPONENTS],
  templateUrl: './city.component.html',
  styleUrl: './city.component.scss',
})
export class CityComponent implements OnDestroy {
  /**
   * The router object for navigating between routes.
   */
  router = inject(Router);

  /**
   * Inject the UserFacade Pattern.
   **/
  userFacade = inject(UserFacade);

  /**
   * The ID of the city.
   */
  cityId = signal<string>('');

  /**
   * Represents the city information.
   */
  city = signal<CityInfoResponse>({} as CityInfoResponse);

  /**
   * The constant representing the route for the city component.
   */
  public ROUTE = ROUTE;

  /**
   * Configuration for the button element.
   */
  buttonElement: ButtonConfig = {
    id: 'back',
    classButtonType: 'btn-outline-primary',
    typeButtonType: 'button',
    label: 'Go back to cities',
  };

  /**
   * Configuration object for the card.
   */
  cardConfig: CardConfig = {
    cardCustomClass: 'h-100',
  };

  /**
   * Inject the DashboardFacade Pattern.
   */
  dashboardFacade = inject(DashboardFacade);

  /**
   * Subject used to signal the destruction of the component.
   * It emits a void value when the component is destroyed.
   */
  private destroy$ = new Subject<void>();

  /**
   * Subscription for router events related to navigation.
   * It listens for NavigationEnd events, extracts the city ID from the URL,
   * and retrieves the city data using the dashboardFacade.
   */
  routerSubscirption: Subscription = this.router.events
    .pipe(
      filter((event) => event instanceof NavigationEnd),
      map((event) => event as NavigationEnd),
      map((event) => event.urlAfterRedirects),
      takeUntil(this.destroy$)
    )
    .subscribe((event: string) => {
      this.cityId.set(event.split('/city/').filter((element) => element)[0]);
      return this.dashboardFacade.getCity(this.cityId());
    });

  /**
   * Subscription property that retrieves the city information.
   * It subscribes to the `getCity$` observable and sets the city information
   * to the `city` property of the component.
   */
  getCity: Subscription = this.dashboardFacade.getCity$
    .pipe(
      filter((city) => {
        return Object.keys(city).length > 0;
      }),
      take(1),
      takeUntil(this.destroy$)
    )
    .subscribe((city: CityInfoResponse) => {
      this.city.set(city);
    });

  /**
   * Lifecycle hook that is called when the component is destroyed.
   * It resets the city in the DashboardFacade and unsubscribes from observables.
   */
  ngOnDestroy() {
    this.dashboardFacade.resetCity();
    this.destroy$.next();
  }
}
