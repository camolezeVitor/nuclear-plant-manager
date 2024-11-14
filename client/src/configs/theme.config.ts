import { PrimeNGConfig } from "primeng/api";
import { definePreset } from "primeng/themes";
import { Nora } from "primeng/themes/nora";

const NUCLEAR_PRESET = definePreset(Nora, {
    semantic: {
        primary: {
            50: '{yellow.50}',
            100: '{yellow.100}',
            200: '{yellow.200}',
            300: '{yellow.300}',
            400: '{yellow.400}',
            500: '{yellow.500}',
            600: '{yellow.600}',
            700: '{yellow.700}',
            800: '{yellow.800}',
            900: '{yellow.900}',
            950: '{yellow.950}'
        }
    }
});

export function configurePrimengTheme(config: PrimeNGConfig) {
    config.theme.set({ 
        preset: NUCLEAR_PRESET,
        options: {
          darkModeSelector: '.dark-mode'
        }
    });
    config.ripple.set(true);
}