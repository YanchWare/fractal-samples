import { Injectable } from "@angular/core";
import { Store } from "@ngrx/store";
import { logOut, login, register } from "./user.actions";
import { Observable } from "rxjs";
import { token$, userState$ } from "./user.selectors";
import { UserState } from "./user.reducer";
import { LoginPayload, RegisterPayload } from "../../models/user.model";

@Injectable({ providedIn: 'root' })
export class UserFacade {
  /**
   * Observable representing the user state.
   */
  public userState$: Observable<UserState> = this.store.select(userState$);

  /**
   * Observable representing the user token.
   */
  public token$: Observable<string> = this.store.select(token$);

  constructor(private store: Store) {}

  /**
   * Dispatches a login action with the provided payload.
   * @param payload The login payload.
   */
  public login(payload: LoginPayload): void {
    this.store.dispatch(login({ payload }));
  }

  /**
   * Dispatches a register action with the provided payload.
   * @param payload The register payload.
   */
  public register(payload: RegisterPayload): void {
    this.store.dispatch(register({ payload }));
  }
  
  /**
   * Dispatches a log out action.
   */
  public logOut(): void {
    this.store.dispatch(logOut());
  }
}
