'use strict';

function _typeof(o) { "@babel/helpers - typeof"; return _typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (o) { return typeof o; } : function (o) { return o && "function" == typeof Symbol && o.constructor === Symbol && o !== Symbol.prototype ? "symbol" : typeof o; }, _typeof(o); }
function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }
function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, _toPropertyKey(descriptor.key), descriptor); } }
function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); Object.defineProperty(Constructor, "prototype", { writable: false }); return Constructor; }
function _toPropertyKey(t) { var i = _toPrimitive(t, "string"); return "symbol" == _typeof(i) ? i : i + ""; }
function _toPrimitive(t, r) { if ("object" != _typeof(t) || !t) return t; var e = t[Symbol.toPrimitive]; if (void 0 !== e) { var i = e.call(t, r || "default"); if ("object" != _typeof(i)) return i; throw new TypeError("@@toPrimitive must return a primitive value."); } return ("string" === r ? String : Number)(t); }
function _callSuper(t, o, e) { return o = _getPrototypeOf(o), _possibleConstructorReturn(t, _isNativeReflectConstruct() ? Reflect.construct(o, e || [], _getPrototypeOf(t).constructor) : o.apply(t, e)); }
function _possibleConstructorReturn(self, call) { if (call && (_typeof(call) === "object" || typeof call === "function")) { return call; } else if (call !== void 0) { throw new TypeError("Derived constructors may only return object or undefined"); } return _assertThisInitialized(self); }
function _assertThisInitialized(self) { if (self === void 0) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return self; }
function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function"); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, writable: true, configurable: true } }); Object.defineProperty(subClass, "prototype", { writable: false }); if (superClass) _setPrototypeOf(subClass, superClass); }
function _wrapNativeSuper(Class) { var _cache = typeof Map === "function" ? new Map() : undefined; _wrapNativeSuper = function _wrapNativeSuper(Class) { if (Class === null || !_isNativeFunction(Class)) return Class; if (typeof Class !== "function") { throw new TypeError("Super expression must either be null or a function"); } if (typeof _cache !== "undefined") { if (_cache.has(Class)) return _cache.get(Class); _cache.set(Class, Wrapper); } function Wrapper() { return _construct(Class, arguments, _getPrototypeOf(this).constructor); } Wrapper.prototype = Object.create(Class.prototype, { constructor: { value: Wrapper, enumerable: false, writable: true, configurable: true } }); return _setPrototypeOf(Wrapper, Class); }; return _wrapNativeSuper(Class); }
function _construct(t, e, r) { if (_isNativeReflectConstruct()) return Reflect.construct.apply(null, arguments); var o = [null]; o.push.apply(o, e); var p = new (t.bind.apply(t, o))(); return r && _setPrototypeOf(p, r.prototype), p; }
function _isNativeReflectConstruct() { try { var t = !Boolean.prototype.valueOf.call(Reflect.construct(Boolean, [], function () {})); } catch (t) {} return (_isNativeReflectConstruct = function _isNativeReflectConstruct() { return !!t; })(); }
function _isNativeFunction(fn) { try { return Function.toString.call(fn).indexOf("[native code]") !== -1; } catch (e) { return typeof fn === "function"; } }
function _setPrototypeOf(o, p) { _setPrototypeOf = Object.setPrototypeOf ? Object.setPrototypeOf.bind() : function _setPrototypeOf(o, p) { o.__proto__ = p; return o; }; return _setPrototypeOf(o, p); }
function _getPrototypeOf(o) { _getPrototypeOf = Object.setPrototypeOf ? Object.getPrototypeOf.bind() : function _getPrototypeOf(o) { return o.__proto__ || Object.getPrototypeOf(o); }; return _getPrototypeOf(o); }
customElements.define('compodoc-menu', /*#__PURE__*/function (_HTMLElement) {
  function _class() {
    var _this;
    _classCallCheck(this, _class);
    _this = _callSuper(this, _class);
    _this.isNormalMode = _this.getAttribute('mode') === 'normal';
    return _this;
  }
  _inherits(_class, _HTMLElement);
  return _createClass(_class, [{
    key: "connectedCallback",
    value: function connectedCallback() {
      this.render(this.isNormalMode);
    }
  }, {
    key: "render",
    value: function render(isNormalMode) {
      var tp = lithtml.html("\n        <nav>\n            <ul class=\"list\">\n                <li class=\"title\">\n                    <a href=\"index.html\" data-type=\"index-link\">yanchware-go documentation</a>\n                </li>\n\n                <li class=\"divider\"></li>\n                ".concat(isNormalMode ? "<div id=\"book-search-input\" role=\"search\"><input type=\"text\" placeholder=\"Type to search\"></div>" : '', "\n                <li class=\"chapter\">\n                    <a data-type=\"chapter-link\" href=\"index.html\"><span class=\"icon ion-ios-home\"></span>Getting started</a>\n                    <ul class=\"links\">\n                        <li class=\"link\">\n                            <a href=\"overview.html\" data-type=\"chapter-link\">\n                                <span class=\"icon ion-ios-keypad\"></span>Overview\n                            </a>\n                        </li>\n                        <li class=\"link\">\n                            <a href=\"index.html\" data-type=\"chapter-link\">\n                                <span class=\"icon ion-ios-paper\"></span>README\n                            </a>\n                        </li>\n                                <li class=\"link\">\n                                    <a href=\"dependencies.html\" data-type=\"chapter-link\">\n                                        <span class=\"icon ion-ios-list\"></span>Dependencies\n                                    </a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"properties.html\" data-type=\"chapter-link\">\n                                        <span class=\"icon ion-ios-apps\"></span>Properties\n                                    </a>\n                                </li>\n                    </ul>\n                </li>\n                    <li class=\"chapter\">\n                        <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#components-links"' : 'data-bs-target="#xs-components-links"', ">\n                            <span class=\"icon ion-md-cog\"></span>\n                            <span>Components</span>\n                            <span class=\"icon ion-ios-arrow-down\"></span>\n                        </div>\n                        <ul class=\"links collapse \" ").concat(isNormalMode ? 'id="components-links"' : 'id="xs-components-links"', ">\n                            <li class=\"link\">\n                                <a href=\"components/AlertPopupComponent.html\" data-type=\"entity-link\" >AlertPopupComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/AppComponent.html\" data-type=\"entity-link\" >AppComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/BarChartComponent.html\" data-type=\"entity-link\" >BarChartComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/ButtonComponent.html\" data-type=\"entity-link\" >ButtonComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/CardComponent.html\" data-type=\"entity-link\" >CardComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/CitiesComponent.html\" data-type=\"entity-link\" >CitiesComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/CityComponent.html\" data-type=\"entity-link\" >CityComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/DashboardComponent.html\" data-type=\"entity-link\" >DashboardComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/FormComponent.html\" data-type=\"entity-link\" >FormComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/LoginComponent.html\" data-type=\"entity-link\" >LoginComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/NavBarComponent.html\" data-type=\"entity-link\" >NavBarComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/RegistrationComponent.html\" data-type=\"entity-link\" >RegistrationComponent</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"components/StarRatingComponent.html\" data-type=\"entity-link\" >StarRatingComponent</a>\n                            </li>\n                        </ul>\n                    </li>\n                        <li class=\"chapter\">\n                            <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#injectables-links"' : 'data-bs-target="#xs-injectables-links"', ">\n                                <span class=\"icon ion-md-arrow-round-down\"></span>\n                                <span>Injectables</span>\n                                <span class=\"icon ion-ios-arrow-down\"></span>\n                            </div>\n                            <ul class=\"links collapse \" ").concat(isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"', ">\n                                <li class=\"link\">\n                                    <a href=\"injectables/AlertPopupService.html\" data-type=\"entity-link\" >AlertPopupService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/DashboardEffects.html\" data-type=\"entity-link\" >DashboardEffects</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/DashboardFacade.html\" data-type=\"entity-link\" >DashboardFacade</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/DashboardService.html\" data-type=\"entity-link\" >DashboardService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/LoginGuard.html\" data-type=\"entity-link\" >LoginGuard</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/LoginRegisterService.html\" data-type=\"entity-link\" >LoginRegisterService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/UserEffects.html\" data-type=\"entity-link\" >UserEffects</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/UserFacade.html\" data-type=\"entity-link\" >UserFacade</a>\n                                </li>\n                            </ul>\n                        </li>\n                    <li class=\"chapter\">\n                        <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#interfaces-links"' : 'data-bs-target="#xs-interfaces-links"', ">\n                            <span class=\"icon ion-md-information-circle-outline\"></span>\n                            <span>Interfaces</span>\n                            <span class=\"icon ion-ios-arrow-down\"></span>\n                        </div>\n                        <ul class=\"links collapse \" ").concat(isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"', ">\n                            <li class=\"link\">\n                                <a href=\"interfaces/AlertPopupConfig.html\" data-type=\"entity-link\" >AlertPopupConfig</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/ButtonConfig.html\" data-type=\"entity-link\" >ButtonConfig</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/CardConfig.html\" data-type=\"entity-link\" >CardConfig</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/Cities.html\" data-type=\"entity-link\" >Cities</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/City.html\" data-type=\"entity-link\" >City</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/CityInfoResponse.html\" data-type=\"entity-link\" >CityInfoResponse</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/Climate.html\" data-type=\"entity-link\" >Climate</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/CoworkingSpace.html\" data-type=\"entity-link\" >CoworkingSpace</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/DashboardState.html\" data-type=\"entity-link\" >DashboardState</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/FooterLogoModel.html\" data-type=\"entity-link\" >FooterLogoModel</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/FooterTextModel.html\" data-type=\"entity-link\" >FooterTextModel</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/FormModel.html\" data-type=\"entity-link\" >FormModel</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/FormOutputModel.html\" data-type=\"entity-link\" >FormOutputModel</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/GetCitiesPayload.html\" data-type=\"entity-link\" >GetCitiesPayload</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/InputFormModel.html\" data-type=\"entity-link\" >InputFormModel</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/InternetSpeed.html\" data-type=\"entity-link\" >InternetSpeed</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/LoginPayload.html\" data-type=\"entity-link\" >LoginPayload</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/LoginResponse.html\" data-type=\"entity-link\" >LoginResponse</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/NavbarConfig.html\" data-type=\"entity-link\" >NavbarConfig</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/RegisterPayload.html\" data-type=\"entity-link\" >RegisterPayload</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/RegisterResponse.html\" data-type=\"entity-link\" >RegisterResponse</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/TitleFormModel.html\" data-type=\"entity-link\" >TitleFormModel</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/UserData.html\" data-type=\"entity-link\" >UserData</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/UserState.html\" data-type=\"entity-link\" >UserState</a>\n                            </li>\n                        </ul>\n                    </li>\n                    <li class=\"chapter\">\n                        <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#miscellaneous-links"' : 'data-bs-target="#xs-miscellaneous-links"', ">\n                            <span class=\"icon ion-ios-cube\"></span>\n                            <span>Miscellaneous</span>\n                            <span class=\"icon ion-ios-arrow-down\"></span>\n                        </div>\n                        <ul class=\"links collapse \" ").concat(isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"', ">\n                            <li class=\"link\">\n                                <a href=\"miscellaneous/typealiases.html\" data-type=\"entity-link\">Type aliases</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"miscellaneous/variables.html\" data-type=\"entity-link\">Variables</a>\n                            </li>\n                        </ul>\n                    </li>\n                    <li class=\"chapter\">\n                        <a data-type=\"chapter-link\" href=\"coverage.html\"><span class=\"icon ion-ios-stats\"></span>Documentation coverage</a>\n                    </li>\n                    <li class=\"divider\"></li>\n                    <li class=\"copyright\">\n                        Documentation generated using <a href=\"https://compodoc.app/\" target=\"_blank\" rel=\"noopener noreferrer\">\n                            <img data-src=\"images/compodoc-vectorise.png\" class=\"img-responsive\" data-type=\"compodoc-logo\">\n                        </a>\n                    </li>\n            </ul>\n        </nav>\n        "));
      this.innerHTML = tp.strings;
    }
  }]);
}( /*#__PURE__*/_wrapNativeSuper(HTMLElement)));