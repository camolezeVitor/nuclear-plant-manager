import { isPlatformBrowser } from "@angular/common";
import { inject, Injectable, PLATFORM_ID } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({
    providedIn: "root"
})
export class LoginService {

    private platform = inject(PLATFORM_ID);
    private router = inject(Router);

    isBrowser(): boolean {
        return isPlatformBrowser(this.platform);
    }

    isUserLoggedIn() {
        if (!this.isBrowser()) return;
        return localStorage.getItem('token') != null;
    }

    loginUser(token: string) {
        if (!this.isBrowser()) return;
        localStorage.setItem("token", token);
        console.log("TÁ AQUI!")
        this.router.navigateByUrl("app/setor-cadeia-de-suprimentos");
    }
    
    logoutUser() {
        if (!this.isBrowser()) return;
        localStorage.removeItem("token");
        this.router.navigateByUrl("login")
    }

    async verifyLoginState() {
        if (!this.isBrowser()) return;
    }

    private tokenIsExpired(): boolean | void {
        if (!this.isBrowser()) return;

        const token = localStorage.getItem('token');
        if (!token) return true; 

        const payload = this.decodeJwt(token);
        if (!payload) return true;

        const expiration = payload.exp * 1000; 
        return Date.now() > expiration;
    }


    private decodeJwt(token: string): any {
        try {
            const payload = token.split('.')[1];
            const decoded = atob(payload);
            return JSON.parse(decoded);
        } catch (error) {
            return null;
        }
    }
}