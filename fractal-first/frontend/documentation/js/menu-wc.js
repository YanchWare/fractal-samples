'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">yanchware-go documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#components-links"' :
                            'data-bs-target="#xs-components-links"' }>
                            <span class="icon ion-md-cog"></span>
                            <span>Components</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="components-links"' : 'id="xs-components-links"' }>
                            <li class="link">
                                <a href="components/AlertPopupComponent.html" data-type="entity-link" >AlertPopupComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/AppComponent.html" data-type="entity-link" >AppComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/BarChartComponent.html" data-type="entity-link" >BarChartComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/ButtonComponent.html" data-type="entity-link" >ButtonComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/CardComponent.html" data-type="entity-link" >CardComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/CitiesComponent.html" data-type="entity-link" >CitiesComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/CityComponent.html" data-type="entity-link" >CityComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/DashboardComponent.html" data-type="entity-link" >DashboardComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/FormComponent.html" data-type="entity-link" >FormComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/LoginComponent.html" data-type="entity-link" >LoginComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/NavBarComponent.html" data-type="entity-link" >NavBarComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/RegistrationComponent.html" data-type="entity-link" >RegistrationComponent</a>
                            </li>
                            <li class="link">
                                <a href="components/StarRatingComponent.html" data-type="entity-link" >StarRatingComponent</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#injectables-links"' :
                                'data-bs-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/AlertPopupService.html" data-type="entity-link" >AlertPopupService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/DashboardEffects.html" data-type="entity-link" >DashboardEffects</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/DashboardFacade.html" data-type="entity-link" >DashboardFacade</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/DashboardService.html" data-type="entity-link" >DashboardService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/LoginGuard.html" data-type="entity-link" >LoginGuard</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/LoginRegisterService.html" data-type="entity-link" >LoginRegisterService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/UserEffects.html" data-type="entity-link" >UserEffects</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/UserFacade.html" data-type="entity-link" >UserFacade</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#interfaces-links"' :
                            'data-bs-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/AlertPopupConfig.html" data-type="entity-link" >AlertPopupConfig</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/ButtonConfig.html" data-type="entity-link" >ButtonConfig</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/CardConfig.html" data-type="entity-link" >CardConfig</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Cities.html" data-type="entity-link" >Cities</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/City.html" data-type="entity-link" >City</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/CityInfoResponse.html" data-type="entity-link" >CityInfoResponse</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Climate.html" data-type="entity-link" >Climate</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/CoworkingSpace.html" data-type="entity-link" >CoworkingSpace</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/DashboardState.html" data-type="entity-link" >DashboardState</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/FooterLogoModel.html" data-type="entity-link" >FooterLogoModel</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/FooterTextModel.html" data-type="entity-link" >FooterTextModel</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/FormModel.html" data-type="entity-link" >FormModel</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/FormOutputModel.html" data-type="entity-link" >FormOutputModel</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/GetCitiesPayload.html" data-type="entity-link" >GetCitiesPayload</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/InputFormModel.html" data-type="entity-link" >InputFormModel</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/InternetSpeed.html" data-type="entity-link" >InternetSpeed</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/LoginPayload.html" data-type="entity-link" >LoginPayload</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/LoginResponse.html" data-type="entity-link" >LoginResponse</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/NavbarConfig.html" data-type="entity-link" >NavbarConfig</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/RegisterPayload.html" data-type="entity-link" >RegisterPayload</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/RegisterResponse.html" data-type="entity-link" >RegisterResponse</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/TitleFormModel.html" data-type="entity-link" >TitleFormModel</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/UserData.html" data-type="entity-link" >UserData</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/UserState.html" data-type="entity-link" >UserState</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#miscellaneous-links"'
                            : 'data-bs-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/typealiases.html" data-type="entity-link">Type aliases</a>
                            </li>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank" rel="noopener noreferrer">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});