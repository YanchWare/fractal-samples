import { CommonModule } from '@angular/common';
import {
  Component,
  OnDestroy,
  OnInit,
  computed,
  inject,
  signal,
} from '@angular/core';
import { Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ROUTE } from '@src/app/constants/route';
import { ButtonComponent } from '@src/app/shared/components/button/button.component';
import { CardComponent } from '@src/app/shared/components/card/card.component';
import { FormComponent } from '@src/app/shared/components/form/form.component';
import { ButtonConfig } from '@src/app/shared/models/button.model';
import { FormModel } from '@src/app/shared/models/form.model';
import { Subject, Subscription, filter, takeUntil } from 'rxjs';
import { Cities, City } from '../../models/dashboard.model';
import { DashboardFacade } from '../../store/dashboard/dashboard.facade';

const MODULES = [CommonModule, RouterLink];
const COMPONENTS = [FormComponent, CardComponent, ButtonComponent];

/**
 * Component for displaying a list of cities.
 */
@Component({
  selector: 'app-cities',
  standalone: true,
  imports: [...MODULES, ...COMPONENTS],
  templateUrl: './cities.component.html',
  styleUrl: './cities.component.scss',
})
export class CitiesComponent implements OnInit, OnDestroy {
  /**
   * Signal for storing the cities data.
   */
  cities = signal<Cities>({} as Cities);

  /**
   * Computed property related with cities signal => cities.
   */
  citiesList = computed<City[]>(() => this.cities().cities);

  /**
   * Signal for storing the city filter name.
   */
  cityFilterName = signal<string>('');

  /**
   * Computed property for getting the filtered list of cities based on the city filter name.
   */
  citiesListFiltered = computed<City[]>(() =>
    this.citiesList()?.filter((city) => city.name.toLowerCase().includes(this.cityFilterName().toLowerCase()))
  );

  /**
   * Form model for the search input.
   */
  formModel: FormModel = {
    type: 'search',
    cardCustomClass: 'w-100',
    inputElements: [
      {
        label: 'Choose your next destination',
        inputType: 'text',
        inputValidator: [Validators.required],
        customClass: 'mb-3',
      },
    ],
  };

  /**
   * Button configuration for the explore button.
   */
  buttonElement: ButtonConfig = {
    id: 'explore',
    classButtonType: 'btn-outline-primary',
    typeButtonType: 'button',
    label: 'Explore',
    customClass: 'w-100',
  };

  /**
   * Constant for storing the route path.
   */
  public ROUTE = ROUTE;

  /**
   * Subject for managing the component's lifecycle.
   */
  private destroy$ = new Subject<void>();

  /**
   * Inject the DashboardFacade Pattern.
   */
  dashboardFacade = inject(DashboardFacade);

  /**
   * Subscription for receiving cities data from the DashboardFacade.
   */
  cities$: Subscription = this.dashboardFacade.cities$
    .pipe(filter(Boolean), takeUntil(this.destroy$))
    .subscribe((cities: Cities) => {
      this.cities.set(cities);
    });

  /**
   * Lifecycle hook that is called after the component has been initialized.
   */
  ngOnInit() {
    this.dashboardFacade.cities();
  }

  /**
   * Lifecycle hook that is called when the component is about to be destroyed.
   */
  ngOnDestroy() {
    this.destroy$.next();
  }

  /**
   * Filters the cities based on the provided search city name.
   * @param searchCity The name of the city to filter.
   */
  filterCities(searchCity: string) {
    this.cityFilterName.set(searchCity);
  }
}
